package app.ports.repositories.library;

import core.entities.Library;

public interface IFindAllLibrariesByNumberRepository {
    Library[] findAllLibrariesByNumber(int data);
}
