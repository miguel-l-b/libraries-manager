package app.ports.repositories.library;

import core.entities.Library;

public interface IFindAllLibrariesByNameRepository {
    Library[] findAllLibrariesByName(String data);
}
