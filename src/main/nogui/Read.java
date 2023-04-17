package main.nogui;

import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import Input.MultipleChoice;
import app.use_cases.GetLibrary;
import core.entities.CEP;
import core.entities.Library;
import core.entities.Logradouro;
import exceptions.InvalidValueException;
import infrastructure.providers.api.CEPProvider;
import infrastructure.repositories.jackson.LibraryRepository;

public class Read {
	public final GetLibrary REPOSITORY;
	public final CEPProvider API_CEP;

	public Read(LibraryRepository repository, CEPProvider apiCep) {
		this.REPOSITORY = new GetLibrary(repository, repository);
		this.API_CEP = apiCep;
	}
	private Read(GetLibrary repository, CEPProvider apiCep) {
		this.REPOSITORY = repository;
		this.API_CEP = apiCep;
	}

	private void printLibrary(Library data) {
		if(data == null) return;
		String address;
		try {
			Logradouro l = API_CEP.getAddress(CEP.parseCep(data.getCep()));
			address = App.formatLibrary(data, l);
		} catch (Exception e) {
			address = String.format(
				"%s %s, %s - %s, %d", data.getName(),
				data.getEmail(), data.getComplement(), data.getCep(), data.getNumber()
			);
		}
		ConsoleManager.println(address);
	}

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Listar bibliotecas", Colors.GREEN);
		ConsoleManager.println("Escolha uma busca: ", Colors.GREEN);
		ConsoleManager.println("1 - Buscar por nome", Colors.CYAN);
		ConsoleManager.println("2 - Buscar por CEP ou Numero", Colors.CYAN);
		ConsoleManager.println("3 - Buscar por CEP e Numero", Colors.CYAN);
		ConsoleManager.println("4 - Mostrar todos", Colors.CYAN);
		ConsoleManager.println("5 - Voltar para o menu principal", Colors.CYAN);
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
									printLibrary(l);
								App.stop();
							}
							catch (InvalidValueException e) { App.printError(e); }
							break;
						case(2):
							ConsoleManager.print(" Cep ou Numero: ", Colors.CYAN);
							String cepOrNumber = Keyboard.getString();

							try {
								for (Library l : REPOSITORY.getLibrariesBy(Integer.parseInt(cepOrNumber)))
									printLibrary(l);
								App.stop();
							}
							catch (InvalidValueException e) { App.printError(e); }
							break;
						case(3):
							ConsoleManager.print(" Cep: ", Colors.CYAN);
							String cep = Keyboard.getString();
							ConsoleManager.print(" Numero: ", Colors.CYAN);
							int number = Integer.parseInt(Keyboard.getString());
							
							try {
								printLibrary(REPOSITORY.getLibraryBy(cep, number));
								App.stop();
							}
							catch (InvalidValueException e) { App.printError(e); }
							break;
						case(4):
								for (Library l : REPOSITORY.getAllLibraries())
									printLibrary(l);
								App.stop();
							break;
						case(5):
							break;
						default:
							App.printError(new InvalidValueException("Opção inválida!")); 
							new Read(REPOSITORY, API_CEP).run();
							break;
					}
				}
			});
		}
		catch (Exception e) {
			App.printError(e); 
			new Read(REPOSITORY, API_CEP).run();
		}
	}
}
