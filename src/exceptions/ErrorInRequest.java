package exceptions;

public enum ErrorInRequest {
    
    NotExist("The entity not exist"),
    InternalErrorBD("Error internal in exec BD");

    private String msg;

    ErrorInRequest(String msg) { this.msg = msg; }
    public String getMsg() { return this.msg; }
}
