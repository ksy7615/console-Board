package consoleBoard;

public class User implements Anonymous {
	private String id, password;
	private static boolean isRight;
	private static boolean anonyMode;
	
	public User() {
		
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	// 익명용 setId
	public void setId() {
		this.id = "*****";
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
	
	public boolean anonyMode() {
		return this.anonyMode;
	}
	
	public void setAnonyMode() {
		this.anonyMode = !anonyMode;
	}
	
	public User clone() {
		return new User(this.id, this.password);
	}

}
