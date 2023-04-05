package app.ports.repositories.library;

import core.entities.Library;

public interface IFindLibrariesByCepRepository {
    Library[] findLibrariesByCep(int data);
}
