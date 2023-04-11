package app.use_cases;

import app.ports.repositories.library.ICreateLibraryRepository;
import core.entities.CEP;
import core.entities.Library;
import core.use_cases.library.ICreateLibrary;
import exceptions.InvalidValueException;

public class CreateLibrary implements ICreateLibrary {
    private final ICreateLibraryRepository REPOSITORY;

    public CreateLibrary(ICreateLibraryRepository repository) {
        this.REPOSITORY = repository;
    }


    @Override
    public void createLibrary(String name, String cep, int number) {
        if(name == null || name.isBlank())
            throw new IllegalArgumentException(String.format("the name: \"%s\" cannot be empty", name));
        if(!CEP.validation(cep))
            throw new IllegalArgumentException(String.format("the cep: \"%s\" is not a CEP", cep));
        if(number <= 0 || number > 99999)
            throw new IllegalArgumentException(String.format("the number: \"%s\" is not a number", number));
        
        try {
            this.REPOSITORY.create(new Library(name, CEP.parseInt(cep), number));
        }
        catch (Exception e) {throw new IllegalArgumentException(e.getMessage());}
    }
    
}
