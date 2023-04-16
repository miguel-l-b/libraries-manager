package main.nogui;

import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import app.use_cases.DeleteLibrary;
import app.use_cases.GetLibrary;
import core.entities.CEP;
import core.entities.Library;
import core.entities.Logradouro;
import infrastructure.providers.api.CEPProvider;
import infrastructure.repositories.jackson.LibraryRepository;

public class Delete {
	public final CEPProvider API_CEP;
	public final DeleteLibrary REPOSITORY_DELTE;
	public final GetLibrary REPOSITORY_GET;

	
	public Delete(LibraryRepository repository, CEPProvider apiCep) {
		this.API_CEP = apiCep;
		this.REPOSITORY_DELTE = new DeleteLibrary(repository);
		this.REPOSITORY_GET = new GetLibrary(repository, repository);
	}

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Delete biblioteca", Colors.GREEN);

		ConsoleManager.print(" Cep: ", Colors.CYAN);
		String cep = Keyboard.getString();
		ConsoleManager.print(" Numbero: ", Colors.CYAN);
		int number = Integer.parseInt(Keyboard.getString());

		try {
			Library library = REPOSITORY_GET.getLibraryBy(cep, number);
			Logradouro l = API_CEP.getAddress(CEP.parseCep(library.getCep()));
			ConsoleManager.println(App.formatLibrary(library, l));

			ConsoleManager.print("Confirma? (s/n): ", Colors.CYAN);
			if ("Nn".indexOf(Keyboard.getString()) != -1) {
				App.printMessage("Operação cancelada");
				return;
			}
		}
		catch (Exception e) {
			App.printError("Biblioteca não encontrada");
			return;
		}

		try { REPOSITORY_DELTE.deleteLibrary(cep, number); }
		catch (Exception e) {
			ConsoleManager.println(e.getMessage(), Colors.RED);
			ConsoleManager.println("Pressione qualquer tecla para continuar...", Colors.RED);
			Keyboard.getString();
		}
	}
}
