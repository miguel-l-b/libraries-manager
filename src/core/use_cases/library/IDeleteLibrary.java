package core.use_cases.library;

import exceptions.InvalidValueException;

public interface IDeleteLibrary {
    void deleteLibrary(int cep, int number)  throws InvalidValueException;
}
