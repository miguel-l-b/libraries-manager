package core.use_cases.library;

import core.entities.Library;
import exceptions.InvalidValueException;

public interface IUpdateLibrary {
    void updateLibrary(String cep, int number, Library newData)  throws InvalidValueException;
}
