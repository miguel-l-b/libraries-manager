package app.use_cases;

import app.ports.repositories.library.ICreateLibraryRepository;
import core.entities.Library;
import core.use_cases.library.ICreateLibrary;
import exceptions.InvalidValueException;

public class CreateLibrary implements ICreateLibrary {
    private final ICreateLibraryRepository REPOSITORY;

    public CreateLibrary(ICreateLibraryRepository repository) {
        this.REPOSITORY = repository;
    }


    @Override
    public void createLibrary(String name, int cep, int number) {
        try {
            this.REPOSITORY.create(new Library(name, cep, number));
        }
        catch (InvalidValueException e) {throw new IllegalArgumentException(e.getMessage());}
    }
    
}
