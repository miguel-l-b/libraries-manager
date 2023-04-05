package app.ports.repositories.library;

import core.entities.Library;

public interface IFindAllLibrariesRepository {
    Library[] findAllLibraries();
}
