package app.ports.repositories.library;

import core.entities.Library;

public interface ICreateLibraryRepository {
    Library create(String name, int cep, int number);
}
