package main.nogui;

import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import Input.MultipleChoice;
import app.use_cases.GetLibrary;
import core.entities.Library;
import exceptions.InvalidValueException;
import infrastructure.repositories.jackson.LibraryRepository;

public class Read {
	public final GetLibrary REPOSITORY;

	public Read(LibraryRepository repository) { this.REPOSITORY = new GetLibrary(repository, repository); }
	public Read(GetLibrary repository) { this.REPOSITORY = repository; }

	public void printError(Exception e) {
		ConsoleManager.println(e.getMessage(), Colors.RED);
		ConsoleManager.println("Pressione qualquer tecla para continuar...", Colors.RED);
		Keyboard.getString();
	}

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Listar bibliotecas", Colors.GREEN);
		ConsoleManager.println("Escolha uma busca: ", Colors.GREEN);
		ConsoleManager.println("1 - Buscar por nome", Colors.CYAN);
		ConsoleManager.println("2 - Buscar por CEP ou Numero", Colors.CYAN);
		ConsoleManager.println("3 - Buscar por CEP e Numero", Colors.CYAN);

		try {
			Keyboard.multipleChoiceInt(new MultipleChoice<Integer>() {
				@Override
				public void handle(Integer choice) {
					switch(choice) {
						case(1):
							ConsoleManager.print(" Nome: ", Colors.CYAN);
							String name = Keyboard.getString();

							try {
								for (Library l : REPOSITORY.getLibrariesBy(name))
									ConsoleManager.println(l.toString(), Colors.WHITE);
							}
							catch (InvalidValueException e) { printError(e); }
							break;
						case(2):
							ConsoleManager.print(" Cep ou Numero: ", Colors.CYAN);
							String cepOrNumber = Keyboard.getString();

							try {
								for (Library l : REPOSITORY.getLibrariesBy(cepOrNumber))
									ConsoleManager.println(l.toString(), Colors.WHITE);
							}
							catch (InvalidValueException e) { printError(e); }
							break;
						case(3):
							ConsoleManager.print(" Cep: ", Colors.CYAN);
							int cep = Integer.valueOf(Keyboard.getString());
							ConsoleManager.print(" Numero: ", Colors.CYAN);
							int number = Integer.parseInt(Keyboard.getString());
							
							try {
								ConsoleManager.println(REPOSITORY.getLibraryBy(cep, number).toString(), Colors.WHITE);
							}
							catch (InvalidValueException e) { printError(e); }
							break;
						default:
							printError(new InvalidValueException("Opção inválida!")); 
							new Read(REPOSITORY).run();
							break;
					}
				}
			});
		}
		catch (Exception e) { 
			printError(e); 
			new Read(REPOSITORY).run();
		}
	}
}
