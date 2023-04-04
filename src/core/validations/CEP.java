package core.validations;

import java.util.regex.Pattern;

public class CEP {
    public static boolean Validation(String data) { return Pattern.compile("\\d{5}-\\d{3}").matcher(data).matches(); }

    public static boolean Validation(int data) { return data < 10000000 || data > 99999999; }
}