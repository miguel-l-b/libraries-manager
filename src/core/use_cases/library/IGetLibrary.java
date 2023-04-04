package core.use_cases.library;

import core.entities.Library;
import exceptions.InvalidValueException;

public interface IGetLibrary {
    Library getLibraryBy(int cep, int number) throws InvalidValueException;
    Library[] getLibrariesBy(int cepOrNumber) throws InvalidValueException;
    Library[] getLibrariesBy(String name) throws InvalidValueException;
    Library[] getAllLibraries();
}
