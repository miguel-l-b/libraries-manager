package main.gui;

public class App {
    public final String PATH_DATA;
    public final String URL_API;
    
    public App(String pathData, String urlApi) {
        this.PATH_DATA = pathData;
        this.URL_API = urlApi;
    }

    public static void main(String[] args) {
        new LaunchPage();
    }
}