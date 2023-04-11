package app.use_cases;

import java.util.HashSet;
import java.util.Set;

import app.ports.repositories.library.*;
import core.entities.Library;
import core.use_cases.library.IGetLibrary;
import exceptions.InvalidValueException;

public class FindAllLibraries implements IGetLibrary {
	private final IFindAllLibrariesRepository REPOSITORY;
	private final IFindLibrariesByCepRepository REPOSITORY_BY_CEP;
	private final IFindAllLibrariesByNameRepository REPOSITORY_BY_NAME;
	private final IFindAllLibrariesByNumberRepository REPOSITORY_BY_NUMBER;
	private final IFindLibraryByCepAndNumberRepository REPOSITORY_BY_CEP_AND_NUMBER;

	public FindAllLibraries(IFindAllLibrariesRepository repository, IFindLibrariesByCepRepository repositoryByCep,
			IFindAllLibrariesByNameRepository repositoryByName,
			IFindAllLibrariesByNumberRepository repositoryByNumber,
			IFindLibraryByCepAndNumberRepository repositoryByCepAndNumber) {
		this.REPOSITORY = repository;
		this.REPOSITORY_BY_CEP = repositoryByCep;
		this.REPOSITORY_BY_NAME = repositoryByName;
		this.REPOSITORY_BY_NUMBER = repositoryByNumber;
		this.REPOSITORY_BY_CEP_AND_NUMBER = repositoryByCepAndNumber;
	}

	@Override
	public Library[] getAllLibraries() { return this.REPOSITORY.findAllLibraries(); }

	@Override
	public Library[] getLibrariesBy(int cepOrNumber) throws InvalidValueException {
		Set<Library> result = new HashSet();
		Library[] ceps = this.REPOSITORY_BY_CEP.findLibrariesByCep(cepOrNumber);
		Library[] numbers = this.REPOSITORY_BY_NUMBER.findAllLibrariesByNumber(cepOrNumber);

		for (Library library : numbers)
			result.add(library);

		for (Library library : ceps)
			result.add(library);

		return (Library[]) result.toArray();
	}

	@Override
	public Library[] getLibrariesBy(String name) throws InvalidValueException {
		return this.REPOSITORY_BY_NAME.findAllLibrariesByName(name);
	}

	@Override
	public Library getLibraryBy(int cep, int number) throws InvalidValueException {
		return this.REPOSITORY_BY_CEP_AND_NUMBER.findLibraryBy(cep, number);

	}
}