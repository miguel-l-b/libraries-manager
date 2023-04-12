package main.nogui;

import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import app.use_cases.DeleteLibrary;
import exceptions.InvalidValueException;
import infrastructure.repositories.jackson.LibraryRepository;

public class Delete extends Thread {
	public final DeleteLibrary REPOSITORY;
	
	public Delete(LibraryRepository repository) { this.REPOSITORY = new DeleteLibrary(repository); }

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Delete biblioteca", Colors.GREEN);

		ConsoleManager.print(" Cep: ", Colors.CYAN);
		int cep = Integer.parseInt(Keyboard.getString());
		ConsoleManager.print(" Numbero: ", Colors.CYAN);
		int number = Integer.parseInt(Keyboard.getString());


		try { REPOSITORY.deleteLibrary(cep, number); }
		catch (InvalidValueException e) {
			ConsoleManager.println(e.getMessage(), Colors.RED);
			ConsoleManager.println("Pressione qualquer tecla para continuar...", Colors.RED);
			Keyboard.getString();
		}
	}
}
