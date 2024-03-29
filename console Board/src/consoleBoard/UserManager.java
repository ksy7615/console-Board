package consoleBoard;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> list = new ArrayList<>();

	private UserManager() {
	}
	
	private static UserManager instance = new UserManager();
	
	public static UserManager getInstance() {
		return instance;
	}

	public User addUser(String id, String password) {
		if (isValidId(id)) {
			User user = new User(id, password);
			list.add(user);
			return user;
		}
		return new User();
	}

	public User getUser(int index) {
		User user = list.get(index);
		return user;
	}

	public boolean isValidId(String id) {
		for (User user : list) {
			if (id.equals(user.getId()))
				return false;
		}
		return true;
	}
	
	// 아이디로 조회용
	public User getUserById(String id) {
		for(User user : list) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return new User();
	}
	
	public boolean isValidLogin(String id, String password) {
		for(User user : list) {
			if(user.getId().equals(id) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean removeUser(User user) {
		String id = user.getId();
		User target = getUserById(id);
		
		return list.remove(target);
	}
	
	public int size() {
		return list.size();
	}
}
