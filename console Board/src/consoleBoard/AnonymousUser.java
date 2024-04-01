package consoleBoard;

abstract class AnonymousUser extends User implements Anonymous {
	
	public AnonymousUser(String id, String password) {
		super(id, password);
	}
}
