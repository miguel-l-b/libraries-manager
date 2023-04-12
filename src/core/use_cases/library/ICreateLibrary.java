package core.use_cases.library;

import exceptions.InvalidValueException;

public interface ICreateLibrary {
    void createLibrary(String name, String cep, int number)  throws InvalidValueException;
}
