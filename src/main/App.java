package main;

public class App {
    public static String PATH_DATA = "data/libraries.json";
    public static String URL_API = "https://api.postmon.com.br/v1/cep";

    public static void main(String[] args) throws Exception {
        String pathData = App.PATH_DATA;
        String urlApi = App.URL_API;

        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("--path-data") && i+1 < args.length)
                pathData = args[i+1];
            else if(args[i].equals("--url-api") && i+1 < args.length)
                urlApi = args[i+1];
        }

        if(args[0].equals("-nogui"))
            new main.nogui.App(pathData, urlApi).main(args);
        else
            new main.gui.App(pathData, urlApi).main(args);
    }
}
