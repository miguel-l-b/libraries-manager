package main.nogui;

import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import Input.MultipleChoice;
import core.entities.Library;
import core.entities.Logradouro;
import infrastructure.providers.api.CEPProvider;
import infrastructure.repositories.jackson.LibraryRepository;

public class App {
    public final CEPProvider API_CEP;
    public final LibraryRepository REPOSITORY;

    public App(String pathData, String urlApi) {
        this.API_CEP = new CEPProvider(urlApi);
        this.REPOSITORY = new LibraryRepository(pathData);
    }

    public static void printMessage(String message) {
        ConsoleManager.println(message, Colors.CYAN);
        stop();
    }

    public static void printError(Exception e) {
		printError(e.getMessage());
	}
    public static void printError(String message) {
		ConsoleManager.println(message, Colors.RED);
		stop();
	}

	public static String stop() {
		ConsoleManager.println("Pressione qualquer tecla para continuar...", Colors.YELLOW_BOLD);
		return Keyboard.getString();
	}

    public static String formatLibrary(Library library, Logradouro logradouro) {
        return String.format(
            "%s %s, %s, %d - %s, %s, %s - %s, %s", library.getName(),
            library.getEmail(), logradouro.getLogradouro(), library.getNumber(), library.getComplement(), logradouro.getBairro(),
            logradouro.getCidade(), logradouro.getEstado(), library.getCep()
        );
    }

    public void main(String[] args) {
        try {
            for(;;) {
                ConsoleManager.clear();
                ConsoleManager.println("Escolha uma opção: ", Colors.GREEN);
                ConsoleManager.println("1 - Adicionar biblioteca", Colors.CYAN);
                ConsoleManager.println("2 - Listar bibliotecas", Colors.CYAN);
                ConsoleManager.println("3 - Deletar biblioteca", Colors.CYAN);
                ConsoleManager.println("4 - Atualizar biblioteca", Colors.CYAN);
                ConsoleManager.println("5 - Sair", Colors.CYAN);

                Keyboard.multipleChoiceInt(new MultipleChoice<Integer>() {
                    @Override
                    public void handle(Integer choise) {
                        switch(choise) {
                            case 1:
                                new Create(REPOSITORY, API_CEP).run();
                                break;
                            case 2:
                                new Read(REPOSITORY, API_CEP).run();
                                break;
                            case 3:
                                new Delete(REPOSITORY, API_CEP).run();
                                break;
                            case 4:
                                new Update(REPOSITORY, API_CEP).run();
                                break;
                            case 5:
                                System.exit(0);
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                });

                ConsoleManager.clear();
            }
        }
        catch (Exception e) {  }
    }
}
