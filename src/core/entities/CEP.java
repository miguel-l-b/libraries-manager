package core.entities;

import java.util.regex.Pattern;

public class CEP implements Cloneable {
    public static boolean validation(String data) { 
        return 
            Pattern.compile("\\d{5}-\\d{3}").matcher(data).matches() || 
            Pattern.compile("\\d{8}").matcher(data).matches();
    }
    public static boolean validation(int data) { return data > 10000000 || data < 99999999; }

    public static CEP parseCep(int value) throws Exception { return new CEP(value); }
    public static CEP parseCep(String value) throws Exception { return new CEP(value); }

    public static int parseInt(CEP value) { return Integer.parseInt(((CEP)value).value.replace("-", "")); }
    public static int parseInt(String value) throws Exception {
        if(!CEP.validation(value)) throw new Exception("the value is not a CEP");
        return Integer.parseInt(value.replace("-", ""));
    }

    public static String parseStr(CEP value) { return ((CEP)value).value; }
    public static String parseStr(int value) throws Exception {
        if(!CEP.validation(value)) throw new Exception("the value is not a CEP");
        return String.format("%05d-%03d", value / 1000, value % 1000);
    }

    private String value;

    public CEP(String value) throws Exception {
        if(!CEP.validation(value)) new Exception("the value is not a CEP");
        this.value = value;
    }
    public CEP(int value) throws Exception { this.value = CEP.parseStr(value); }
    public CEP() { }

    public String getValue() { return this.value; }
    public void setValue(String value) throws Exception {
        if(!CEP.validation(value)) new Exception("the value is not a CEP");
        this.value = value;
    }
   
    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new CEP(this.value); }
        catch (Exception e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;

        if(!(obj instanceof CEP)) return false;

        if(!this.value.equals(((CEP)obj).value)) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 23;

        hash = 3 * hash + this.value.hashCode();

        if(hash < 0) hash *= -1;

        return hash;
    }
    @Override
    public String toString() { return String.format("CEP@%s", this.value); }

    
}