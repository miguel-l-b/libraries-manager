package app.use_cases;

import app.ports.repositories.library.IUpdateLibraryByCepAndNumberRepository;
import core.entities.CEP;
import core.entities.Library;
import core.use_cases.library.IUpdateLibrary;
import exceptions.InvalidValueException;

public class UpdateLibraryByCepAndNumber implements IUpdateLibrary{

	private final IUpdateLibraryByCepAndNumberRepository REPOSITORY;

	public UpdateLibraryByCepAndNumber(IUpdateLibraryByCepAndNumberRepository repository){this.REPOSITORY = repository;}

	@Override
	public void updateLibrary(String cep, int number, Library newData) throws InvalidValueException {
		if(!CEP.validation(cep))
			throw new InvalidValueException(String.format("the cep: \"%s\" is not a CEP", cep));
		if(number <= 0 || number > 99999)
			throw new InvalidValueException(String.format("the number: \"%s\" is not a number", number));

		try { this.REPOSITORY.updateLibraryByCepAndNumber(CEP.parseInt(cep), number, (Library) newData.clone()); }
		catch (Exception e) { throw new InvalidValueException(e.getMessage()); }
	 }
}