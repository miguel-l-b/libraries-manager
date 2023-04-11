package app.use_cases;

import app.ports.repositories.library.IDeleteLibraryRepository;
import core.entities.CEP;
import core.use_cases.library.IDeleteLibrary;

public class DeleteLibrary implements IDeleteLibrary {
    private final IDeleteLibraryRepository REPOSITORY;

    public DeleteLibrary(IDeleteLibraryRepository repository) { this.REPOSITORY = repository; }

    @Override
    public void deleteLibrary(int cep, int number) {
        if (!CEP.validation(cep))
            throw new IllegalArgumentException("invalid CEP");
        if (number <= 0)
            throw new IllegalArgumentException("invalid number");
            
        this.REPOSITORY.delete(cep, number);
    }
}