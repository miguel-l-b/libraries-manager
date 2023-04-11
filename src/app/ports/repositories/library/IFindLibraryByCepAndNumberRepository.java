package app.ports.repositories.library;

import core.entities.Library;

public interface IFindLibraryByCepAndNumberRepository {
	Library findLibraryBy(int cep, int number);
}
