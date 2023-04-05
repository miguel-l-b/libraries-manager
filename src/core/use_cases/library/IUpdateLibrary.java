package core.use_cases.library;

import core.entities.Library;

public interface IUpdateLibrary {
    void updateLibrary(Integer cep, Integer number, Library newData);
}
