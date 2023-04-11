package app.use_cases;

import app.ports.repositories.library.IUpdateLibraryByCepAndNumberRepository;
import core.entities.Library;
import core.use_cases.library.IUpdateLibrary;

public class UpdateLibraryByCepAndNumber implements IUpdateLibrary{

	private final IUpdateLibraryByCepAndNumberRepository REPOSITORY;

	public UpdateLibraryByCepAndNumber(IUpdateLibraryByCepAndNumberRepository repository){this.REPOSITORY = repository;}

	@Override
	public void updateLibrary(Integer cep, Integer number, Library newData) { 
		this.REPOSITORY.updateLibraryByCepAndNumber(cep, number, newData);
	 }
}