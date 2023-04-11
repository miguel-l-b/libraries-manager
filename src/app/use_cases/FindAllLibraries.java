package app.use_cases;

import java.util.HashSet;
import java.util.Set;

import app.ports.repositories.library.IFindAllLibrariesRepository;
import app.ports.repositories.library.IFindLibraryByCepAndNumberRepository;
import core.entities.CEP;
import core.entities.Library;
import core.use_cases.library.IGetLibrary;
import exceptions.InvalidValueException;

public class FindAllLibraries implements IGetLibrary {
	private final IFindAllLibrariesRepository REPOSITORY_ALL;
	private final IFindLibraryByCepAndNumberRepository REPOSITORY_BY;

	public FindAllLibraries(IFindAllLibrariesRepository repositoryAll, IFindLibraryByCepAndNumberRepository repositoryBy) { 
		this.REPOSITORY_ALL = repositoryAll;
		this.REPOSITORY_BY = repositoryBy;
	}

	@Override
	public Library[] getAllLibraries() {
		return this.REPOSITORY_ALL.findAllLibraries();
	}

	@Override
	public Library[] getLibrariesBy(int cepOrNumber) {
		if(cepOrNumber <= 0)
			throw new IllegalArgumentException(String.format("the data: \"%s\" is not a CEP or number", cepOrNumber));
		Library[] all = this.getAllLibraries();
		Set<Library> filtered = new HashSet<>();

		for (Library library : all)
			if(library.getCep() == cepOrNumber || library.getNumber() == cepOrNumber)
				filtered.add(library);

		return (Library[]) filtered.toArray();
	}

	@Override
	public Library[] getLibrariesBy(String name) throws InvalidValueException {
		if(name == null || name.isBlank())
			throw new IllegalArgumentException(String.format("the name: \"%s\" cannot be empty", name));
		
		Library[] all = this.getAllLibraries();
		Set<Library> filtered = new HashSet<>();

		for (Library library : all)
			if(library.getName().toUpperCase() == name.toUpperCase())
				filtered.add(library);
		
		return (Library[]) filtered.toArray();
		
	}

	@Override
	public Library getLibraryBy(int cep, int number) throws InvalidValueException {
		if(!CEP.validation(cep))
			throw new IllegalArgumentException(String.format("the cep: \"%s\" is not a CEP", cep));
		if(number <= 0 || number > 99999)
			throw new IllegalArgumentException(String.format("the number: \"%s\" is not a number", number));

		return this.REPOSITORY_BY.findLibraryBy(cep, number);
	}

}