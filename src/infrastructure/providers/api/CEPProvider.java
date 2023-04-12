package infrastructure.providers.api;

import app.ports.providers.ICEPProvider;
import core.entities.CEP;
import core.entities.Logradouro;
import infrastructure.repositories.jackson.Json;

import java.io.BufferedReader;
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

    public CEPProvider(String URL) { this.URL = URL; }

    private static Object getObjeto(Class<?> tipoObjetoRetorno, String urlWebService, String... parametros) {
        try {
            for (String parametro : parametros)
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20");

            URL url = new URL(urlWebService);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            connection.disconnect();

            return Json.fromJson(responseJson, tipoObjetoRetorno);
        }
        catch (Exception erro) {
            return null;
        }
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        if (is == null)
            return "";

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1)
                writer.write(buffer, 0, n);
        }
        finally { is.close(); }

        return writer.toString();
    }

    @Override
    public Logradouro getAddress(CEP cep) throws Exception {
        return (Logradouro) CEPProvider.getObjeto(Logradouro.class, this.URL, CEP.parseStr(cep));
    }

}
