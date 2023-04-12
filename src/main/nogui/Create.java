package main.nogui;

import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import app.use_cases.CreateLibrary;
import infrastructure.repositories.jackson.LibraryRepository;

public class Create {
	public final CreateLibrary REPOSITORY;

	public Create(LibraryRepository repository) { this.REPOSITORY = new CreateLibrary(repository); }

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Adicionar biblioteca", Colors.GREEN);

		ConsoleManager.print(" Nome: ", Colors.CYAN);
		String name = Keyboard.getString();
		ConsoleManager.print(" Cep: ", Colors.CYAN);
		String cep = Keyboard.getString();
		ConsoleManager.print(" Numbero: ", Colors.CYAN);
		int number = Integer.parseInt(Keyboard.getString());


		try { REPOSITORY.createLibrary(name, cep, number); }
		catch (Exception e) {
			ConsoleManager.println(e.getMessage(), Colors.RED);
			ConsoleManager.println("Pressione qualquer tecla para continuar...", Colors.RED);
			Keyboard.getString();
		}
	}
}
