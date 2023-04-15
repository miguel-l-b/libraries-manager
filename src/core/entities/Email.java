package core.entities;

public class Email {
	public static boolean validation(String data) {
		return data.matches("^[a-zA-Z0-9-.+]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
	}
}
