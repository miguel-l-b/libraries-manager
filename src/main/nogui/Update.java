package main.nogui;


import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import app.use_cases.GetLibrary;
import app.use_cases.UpdateLibraryByCepAndNumber;
import core.entities.Library;
import exceptions.InvalidValueException;
import infrastructure.repositories.jackson.LibraryRepository;

public class Update {
	public final UpdateLibraryByCepAndNumber REPOSITORY_UPDATE;
	public final GetLibrary REPOSITORY_FIND;

	public Update(LibraryRepository repository) {
		this.REPOSITORY_FIND = new GetLibrary(repository, repository);
		this.REPOSITORY_UPDATE = new UpdateLibraryByCepAndNumber(repository);
	}

	private void printLibrary(Library data) {
		ConsoleManager.println(data.getName()+" "+data.getCep()+" "+data.getNumber());
	}

	private void printError(Exception e) {
		ConsoleManager.println(e.getMessage(), Colors.RED);
		stop();
	}

	private void stop() {
		ConsoleManager.println("Pressione qualquer tecla para continuar...", Colors.YELLOW_BOLD);
		Keyboard.getString();
	}

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Atualizar biblioteca", Colors.GREEN);

		ConsoleManager.print("Buscando dados", Colors.CYAN);
		ConsoleManager.print(" Cep: ", Colors.CYAN);
		String cep = Keyboard.getString();
		ConsoleManager.print(" Numero: ", Colors.CYAN);
		int number = Integer.parseInt(Keyboard.getString());
		try {
			Library library = REPOSITORY_FIND.getLibraryBy(cep, number);
			printLibrary(library);
			ConsoleManager.println("\n");
			ConsoleManager.println("Novos dados", Colors.GREEN);
			ConsoleManager.print(" Nome: ", Colors.CYAN);
			String data = "";
			if(!(data = Keyboard.getString()).isBlank())
					library.setName(data);
			ConsoleManager.print(" Cep: ", Colors.CYAN);
			if(!(data = Keyboard.getString()).isBlank())
				library.setCep(Integer.valueOf(data));
			ConsoleManager.print(" Number: ", Colors.CYAN);
			if(!(data = Keyboard.getString()).isBlank())
				library.setNumber(Integer.valueOf(data));
			
			printLibrary(library);
			REPOSITORY_UPDATE.updateLibrary(cep, number, library);
			stop();
		}
		catch (Exception e) { printError(e); stop(); return; }
	}
}
