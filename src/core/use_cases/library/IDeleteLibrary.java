package core.use_cases.library;

import exceptions.InvalidValueException;

public interface IDeleteLibrary {
    void deleteLibrary(String cep, int number)  throws InvalidValueException;
}
