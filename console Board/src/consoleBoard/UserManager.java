package consoleBoard;

import java.util.ArrayList;

public class UserManager {
	ArrayList<User> list;
	
	public UserManager() {
		list = new ArrayList<>();
	}
	
	public User addUser(String id, String password) {
		User user = new User(id, password);
		list.add(user);
		return user.clone();
	}
	
	public User getUser(int index) {
		User user = list.get(index);
		return user;
	}
}
