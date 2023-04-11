package core.use_cases.library;

import core.entities.Library;

public interface IUpdateLibrary {
    void updateLibrary(String cep, int number, Library newData);
}
