package app.ports.repositories.library;

import core.entities.Library;

public interface IUpdateLibraryByCepAndNumber {
    Library updateLibraryByCepAndNumber(int cep, int number, Library newData);
}