package main.nogui;


import Console.Colors;
import Console.ConsoleManager;
import Input.Keyboard;
import app.use_cases.GetLibrary;
import app.use_cases.UpdateLibraryByCepAndNumber;
import core.entities.CEP;
import core.entities.Library;
import infrastructure.providers.api.CEPProvider;
import infrastructure.repositories.jackson.LibraryRepository;

public class Update {
	public final UpdateLibraryByCepAndNumber REPOSITORY_UPDATE;
	public final GetLibrary REPOSITORY_FIND;
	public final CEPProvider API_CEP;

	public Update(LibraryRepository repository, CEPProvider cepProvider) {
		this.REPOSITORY_FIND = new GetLibrary(repository, repository);
		this.REPOSITORY_UPDATE = new UpdateLibraryByCepAndNumber(repository);
		this.API_CEP = cepProvider;
		
	}

	public void run() {
		ConsoleManager.clear();
		ConsoleManager.println("Atualizar biblioteca", Colors.GREEN);

		ConsoleManager.println("Buscando dados", Colors.CYAN);
		ConsoleManager.print(" Cep: ", Colors.CYAN);
		String cep = Keyboard.getString();
		ConsoleManager.print(" Numero: ", Colors.CYAN);
		int number = Integer.parseInt(Keyboard.getString());
		try {
			Library library = REPOSITORY_FIND.getLibraryBy(cep, number);
			ConsoleManager.println(App.formatLibrary(library, API_CEP.getAddress(CEP.parseCep(cep))));
			ConsoleManager.println("\n");
			ConsoleManager.println("Novos dados", Colors.GREEN);
			ConsoleManager.println("para manter deixe o campo em branco\n\n", Colors.YELLOW);
			String data = "";

			ConsoleManager.print(" Nome ", Colors.CYAN);
			ConsoleManager.print(String.format("( %s ): ", library.getName()), Colors.WHITE);
			if(!(data = Keyboard.getString()).isBlank())
					library.setName(data);

			ConsoleManager.print(" Email ", Colors.CYAN);
			ConsoleManager.print(String.format("( %s ): ", library.getEmail()), Colors.WHITE);
			if(!(data = Keyboard.getString()).isBlank())
				library.setEmail(data);

			ConsoleManager.print(" Complemento ", Colors.CYAN);
			ConsoleManager.print(String.format("( %s ): ", library.getComplement()), Colors.WHITE);
			if(!(data = Keyboard.getString()).isBlank())
				library.setComplement(data);

			ConsoleManager.print(" Cep ", Colors.CYAN);
			ConsoleManager.print(String.format("( %s ): ", library.getCep()), Colors.WHITE);
			if(!(data = Keyboard.getString()).isBlank())
				library.setCep(data);

			ConsoleManager.print(" Number ", Colors.CYAN);
			ConsoleManager.print(String.format("( %d ): ", library.getNumber()), Colors.WHITE);
			if(!(data = Keyboard.getString()).isBlank())
				library.setNumber(Integer.valueOf(data));
			
			ConsoleManager.println(App.formatLibrary(library, API_CEP.getAddress(CEP.parseCep(cep))));
			REPOSITORY_UPDATE.updateLibrary(cep, number, library);
			App.stop();
		}
		catch (Exception e) { App.printError(e); }
	}
}
