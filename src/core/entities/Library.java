package core.entities;

import core.validations.CEP;
import exceptions.InvalidValueException;

public class Library {
    private String name;
    private int cep;
    private int number;

    public Library(String name, int cep, int number) throws InvalidValueException {
        setName(name);
        setCep(cep);
        setNumber(number);
    }

    public void setName(String data) throws InvalidValueException {
        if(data == null || data.length() > 20)
            throw new InvalidValueException("the data is invalid");

        this.name = data;
    }

    public void setCep(int data) throws InvalidValueException {
        if(!CEP.Validation(data))
            throw new InvalidValueException("the cep is invalid");
        
        this.cep = cep;
    }

    public void setCep(String data) throws InvalidValueException {
        if(!CEP.Validation(data))
            throw new InvalidValueException("the cep is invalid");

        String[] initAndEnd = data.split("-");

        this.cep = Integer.parseInt(initAndEnd[0]+initAndEnd[1]);
    }

    public void setNumber(int data) throws InvalidValueException {
        if(data <= 0)
            throw new InvalidValueException("the number is invalid");
        
        this.number = data;
    }

    public String getName() { return this.name; }

    public int getCep() { return this.cep; }

    public String getCepStr() {
        return this.cep+"".substring(0, 4)+"-"+this.cep+"".substring(5);
    }

    public int getNumber() { return this.number; }

    @Override
    public String toString() { return getName()+"@"+getCepStr()+"."+getNumber(); }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try { return new Library(this.name, this.cep, this.number); }
        catch (InvalidValueException e) { throw new CloneNotSupportedException(); }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;

        if(!(obj instanceof Library)) return false;

        if(!this.name.equals(((Library)(obj)).name)) return false;
        if(this.cep != ((Library)(obj)).cep) return false;
        if(this.number != ((Library)(obj)).number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 24;

        hash = 3 * hash + this.name.hashCode();
        hash = 5 * hash + Integer.valueOf(this.cep).hashCode();
        hash = 7 * hash + Integer.valueOf(this.number).hashCode();

        if(hash < 0) return hash *= -1;

        return hash;
    }
}
