package infrastructure.repositories.jackson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import app.ports.repositories.library.ICreateLibraryRepository;
import app.ports.repositories.library.IDeleteLibraryRepository;
import app.ports.repositories.library.IFindAllLibrariesRepository;
import app.ports.repositories.library.IFindLibraryByCepAndNumberRepository;
import app.ports.repositories.library.IUpdateLibraryByCepAndNumberRepository;
import core.entities.CEP;
import core.entities.Library;

public class LibraryRepository implements ICreateLibraryRepository, IDeleteLibraryRepository,
    IFindAllLibrariesRepository, IFindLibraryByCepAndNumberRepository,
    IUpdateLibraryByCepAndNumberRepository {
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
        try {
        if(this.findLibraryBy(CEP.parseInt(data.getCep()), data.getNumber()) != null)
            throw new IllegalArgumentException("library already exists");
        } catch(Exception e) { throw new IllegalArgumentException(e.getMessage()); }

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

        int index = this.getIndexOfLibraryBy(cep, number);
        if(index < 0)
            throw new IllegalArgumentException("library not exists");
        
        Library[] newArray = new Library[this.libraries.length - 1];
        int j = 0;
        for (int i = 0; i < this.libraries.length; i++) {
            if (i != index) {
                newArray[j++] = this.libraries[i];
            }
        }
        this.libraries = newArray;
        
        this.writeCurrentLibraries();
    }

    @Override
    public Library[] findAllLibraries() {
        this.readerCurrentLibraries();
        return this.libraries;
    }

    @Override
    public Library updateLibraryByCepAndNumber(int cep, int number, Library newData) {
        this.readerCurrentLibraries();
        int index = this.getIndexOfLibraryBy(cep, number);

        this.libraries[index] = newData;
        this.writeCurrentLibraries();
        return this.libraries[index];
    }

    private int getIndexOfLibraryBy(int cep, int number) {
        for (int i = 0; i < libraries.length; i++)
            try {
                if(CEP.parseInt(libraries[i].getCep()) == cep && libraries[i].getNumber() == number)
                    return i;
            } catch(Exception e) { }
        return -1;
    } 

    @Override
    public Library findLibraryBy(int cep, int number) {
        this.readerCurrentLibraries();
        int index = this.getIndexOfLibraryBy(cep, number);        
        if(index < 0) return null;
        return this.libraries[index];
    }
}