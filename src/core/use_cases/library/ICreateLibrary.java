package core.use_cases.library;

import core.entities.Library;
import exceptions.InvalidValueException;

public interface ICreateLibrary {
    void createLibrary(Library data)  throws InvalidValueException;
}
