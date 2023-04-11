package infrastructure.repositories.jackson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import app.ports.repositories.library.ICreateLibraryRepository;
import app.ports.repositories.library.IDeleteLibraryRepository;
import app.ports.repositories.library.IFindAllLibrariesByNameRepository;
import app.ports.repositories.library.IFindAllLibrariesByNumberRepository;
import app.ports.repositories.library.IFindAllLibrariesRepository;
import app.ports.repositories.library.IFindLibraryByCepAndNumberRepository;
import app.ports.repositories.library.IUpdateLibraryByCepAndNumberRepository;
import core.entities.Library;

public class LibraryRepository implements ICreateLibraryRepository, IDeleteLibraryRepository,
    IFindAllLibrariesByNameRepository, IFindAllLibrariesByNumberRepository, IFindAllLibrariesRepository,
    IUpdateLibraryByCepAndNumberRepository, IFindLibraryByCepAndNumberRepository {
    private final String path;
    private BufferedReader reader;
    private FileWriter writer;
    private Library[] libraries;

    public LibraryRepository(String path) {
        if(path == null || path.isEmpty())
            throw new IllegalArgumentException("the path is not empty");

        this.path = path;
        try { this.reader = new BufferedReader(new FileReader(path)); }
        catch (FileNotFoundException e) { throw new IllegalArgumentException("path invalid"); }
    }

    private void readerCurrentLibraries() {
        try {
            this.reader = new BufferedReader(new FileReader(path));
            this.libraries = (Library[]) Json.fromJson(reader.readLine(), Library[].class);
            reader.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private void writeCurrentLibraries() {
        try {
            this.writer = new FileWriter(path);
            this.writer.write(Json.toJson(this.libraries));
            this.writer.flush();
            this.writer.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void create(Library data) {
        this.readerCurrentLibraries();
        if(this.findLibraryBy(data.getCep(), data.getNumber()) != null)
            throw new IllegalArgumentException("library already exists");

        Library[] newLibraries = new Library[this.libraries.length + 1];
        for(int i = 0; i < this.libraries.length; i++)
            newLibraries[i] = this.libraries[i];
        
        this.libraries = newLibraries;
        this.libraries[this.libraries.length - 1] = data;

        this.writeCurrentLibraries();
    }

    @Override
    public void delete(int cep, int number) {
        this.readerCurrentLibraries();

        if(this.findLibraryBy(cep, number) != null)
            throw new IllegalArgumentException("library not exists");
        
        Library[] newLibraries = new Library[this.libraries.length - 1];

        for(int i = 0, j = 0; i < this.libraries.length; i++)
            if(this.libraries[i].getCep() != cep && this.libraries[i].getNumber() != number)
                newLibraries[j++] = this.libraries[i];
        
        this.writeCurrentLibraries();
    }

    @Override
    public Library[] findAllLibrariesByName(String data) {
        return null;
    }

    @Override
    public Library[] findAllLibrariesByNumber(int data) {
        this.readerCurrentLibraries();
    }

    @Override
    public Library[] findAllLibraries() {
        this.readerCurrentLibraries();
        return this.libraries;
    }

    @Override
    public Library updateLibraryByCepAndNumber(int cep, int number, Library newData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Library findLibraryBy(int cep, int number) {
        this.readerCurrentLibraries();
        
        for(Library library : this.libraries)
            if(library.getCep() == cep && library.getNumber() == number)
                return library;

        return null;
    }
}