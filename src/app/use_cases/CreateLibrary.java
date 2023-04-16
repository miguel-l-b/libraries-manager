package app.use_cases;

import app.ports.repositories.library.ICreateLibraryRepository;
import core.entities.CEP;
import core.entities.Email;
import core.entities.Library;
import core.use_cases.library.ICreateLibrary;
import exceptions.InvalidValueException;

public class CreateLibrary implements ICreateLibrary {
    private final ICreateLibraryRepository REPOSITORY;

    public CreateLibrary(ICreateLibraryRepository repository) { this.REPOSITORY = repository; }

    @Override
    public void createLibrary(Library library) throws InvalidValueException {
        if (library.getName() == null || library.getName().isBlank())
            throw new InvalidValueException(String.format("the name: \"%s\" cannot be empty", library.getName()));
        if (!CEP.validation(library.getCep()))
            throw new InvalidValueException(String.format("the cep: \"%s\" is not a CEP", library.getCep()));
        if (library.getNumber() <= 0 || library.getNumber() > 99999)
            throw new InvalidValueException(String.format("the number: \"%s\" is not a number", library.getName()));
        if (!Email.validation(library.getEmail()))
            throw new InvalidValueException(String.format("the email: \"%s\" is not a email", library.getEmail()));
        if (library.getComplement() == null || library.getComplement().isBlank())
            throw new InvalidValueException(
                    String.format("the complement: \"%s\" cannot be empty", library.getComplement())
                );

        try {
            this.REPOSITORY.create(
                new Library(
                    library.getName(), library.getEmail(), library.getComplement(), library.getCep(),
                    library.getNumber()
                )
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
