package core.entities;

import exceptions.InvalidValueException;

public class Library implements Cloneable {
    private String name;
    private String email;
    private String complement;
    private String cep;
    private int number;

    public Library() { }
    public Library(String name, String email, String complement, String cep, int number) throws InvalidValueException {
        setName(name);
        setEmail(email);
        setComplement(complement);
        setCep(cep);
        setNumber(number);
    }

    public void setName(String data) throws InvalidValueException {
        if(data == null || data.length() > 20)
            throw new InvalidValueException("the name is invalid");

        this.name = data;
    }
    public void setEmail(String data) throws InvalidValueException {
        if(!Email.validation(data))
            throw new InvalidValueException("the email is invalid");

        this.email = data;
    }
    public void setComplement(String data) throws InvalidValueException {
        if(data == null || data.length() > 20)
            throw new InvalidValueException("the complement is invalid");

        this.complement = data;
    }
    public void setCep(String data) throws InvalidValueException {
        if(!CEP.validation(data))
            throw new InvalidValueException("the cep is invalid");
        
        this.cep = data;
    }
    public void setNumber(int data) throws InvalidValueException {
        if(data <= 0)
            throw new InvalidValueException("the number is invalid");
        
        this.number = data;
    }

    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getComplement() { return this.complement; }
    public String getCep() { return this.cep; }
    public int getNumber() { return this.number; }

    @Override
    public String toString() {
        try { return getName()+"@"+getCep()+"."+getNumber(); }
        catch (Exception e) { return null; }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new Library(this.name, this.email, this.complement, this.cep, this.number); }
        catch (Exception e) { throw new CloneNotSupportedException(); }
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
        hash = 5 * hash + this.cep.hashCode();
        hash = 7 * hash + Integer.valueOf(this.number).hashCode();

        if(hash < 0) return hash *= -1;

        return hash;
    }
}
