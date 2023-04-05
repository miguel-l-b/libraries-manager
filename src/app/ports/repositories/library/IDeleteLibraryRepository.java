package app.ports.repositories.library;

public interface IDeleteLibraryRepository {
    void delete(int cep, int number);
}