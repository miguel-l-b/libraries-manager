package infrastructure.providers.api;

import app.ports.providers.ICEPProvider;
import core.entities.Logradouro;
import core.validations.CEP;
import infrastructure.repositories.jackson.Json;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class CEPProvider implements ICEPProvider {
    public final String URL;

    public CEPProvider(String URL) {
        this.URL = URL;
    }

    public static Object getObjeto(Class tipoObjetoRetorno, String urlWebService, String... parametros) {
        try {
            for (String parametro : parametros)
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20");

            URL url = new URL (urlWebService);
            HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            connection.disconnect();

            return Json.fromJson(responseJson, tipoObjetoRetorno);
        }
        catch (Exception erro) { return null; }	
    }

    public static Object postObjeto (Object objetoEnvio, Class tipoObjetoRetorno, String urlWebService) {
        try {
            String requestJson = Json.toJson(objetoEnvio);

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));

            DataOutputStream stream =
            new DataOutputStream (connection.getOutputStream());
            stream.write (requestJson.getBytes("UTF-8"));
            stream.flush ();
            stream.close ();
            connection.connect ();

            String responseJson = inputStreamToString (connection.getInputStream());
            connection.disconnect();
            return Json.fromJson(responseJson, tipoObjetoRetorno);
        }
        catch (Exception erro) { return null; }
    }


    public static String inputStreamToString (InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1)
                    writer.write(buffer, 0, n);
            }
            finally { is.close(); }

        return writer.toString();
        }
        return "";
    }
    
    @Override
    public Logradouro getAddress(CEP cep) throws Exception {
        return (Logradouro) CEPProvider.getObjeto(Logradouro.class, this.URL, CEP.parseStr(cep));
    }
    
}
