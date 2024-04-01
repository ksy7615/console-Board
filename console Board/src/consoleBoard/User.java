package consoleBoard;

public class User {
	private String id, password;
	private static boolean isRight;
	
	public User() {
		
	}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	// 익명용 setId >> 다시 바꿔놓을 수 있도록 . .
	public void setId(String id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return String.format("%s님", this.id);
	}

}
