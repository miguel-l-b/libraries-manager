package exceptions;

public class ErrorInRequestException extends Exception {
    public ErrorInRequestException(ErrorInRequest msg) { super(msg.getMsg()); }
}
