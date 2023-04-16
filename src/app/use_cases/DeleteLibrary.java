package app.use_cases;

import app.ports.repositories.library.IDeleteLibraryRepository;
import core.entities.CEP;
import core.use_cases.library.IDeleteLibrary;
import exceptions.InvalidValueException;

public class DeleteLibrary implements IDeleteLibrary {
    private final IDeleteLibraryRepository REPOSITORY;

    public DeleteLibrary(IDeleteLibraryRepository repository) { this.REPOSITORY = repository; }

    @Override
    public void deleteLibrary(String cep, int number) throws InvalidValueException {
        if (!CEP.validation(cep))
            throw new InvalidValueException(String.format("the cep: \"%s\" is not a CEP", cep));
        if (number <= 0 || number > 99999)
            throw new InvalidValueException(String.format("the number: \"%s\" is not a number", number));

        try { this.REPOSITORY.delete(CEP.parseInt(cep), number); }
        catch (Exception e) { throw new InvalidValueException(e.getMessage()); }
    }
}