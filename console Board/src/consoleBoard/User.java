package consoleBoard;

public class User {
	private String id, password;
	private boolean isRight;
	
	public User() {
		
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean isRight() {
		return this.isRight;
	}

	public void setRight() {
		this.isRight = !isRight;
	}
	
	public User clone() {
		return new User(this.id, this.password);
	}

}
