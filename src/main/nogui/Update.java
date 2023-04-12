package main.nogui;

import java.io.Console;

import Console.Colors;
import Console.ConsoleManager;
import infrastructure.repositories.jackson.LibraryRepository;

public class Update {
	public final LibraryRepository REPOSITORY;

	public Update(LibraryRepository repository) { this.REPOSITORY = repository; }

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Atualizar biblioteca", Colors.GREEN);
		ConsoleManager.print("Buscando dados", Colors.CYAN);
	}
}
