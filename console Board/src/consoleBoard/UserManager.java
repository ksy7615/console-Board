package consoleBoard;

import java.util.ArrayList;

public class UserManager {
	ArrayList<User> list;

	public UserManager() {
		list = new ArrayList<>();
	}

	public User addUser(String id, String password) {
		if (isValidUser(id)) {
			User user = new User(id, password);
			list.add(user);
			return user.clone();
		}
		return new User();
	}

	public User getUser(int index) {
		User user = list.get(index);
		return user;
	}

	public boolean isValidUser(String id) {
		for (User user : list) {
			if (id.equals(user.getId()))
				return false;
		}
		return true;
	}
}
