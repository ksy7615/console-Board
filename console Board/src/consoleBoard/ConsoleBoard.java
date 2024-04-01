package consoleBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleBoard {
	private Map<User, ArrayList<Post>> map;
	private ArrayList<Post> board;
//	private List keySet = new ArrayList(map.keySet());

	private UserManager userManager;
	private BoardManager boardManager;

	private User user;
	private boolean isExit;

	private Scanner scanner = new Scanner(System.in);

	public ConsoleBoard() {
		map = new HashMap<>();
		board = new ArrayList<>();
		userManager = UserManager.getInstance();
		boardManager = BoardManager.getInstance();

		// 관리자 처음부터 넣어주고 시작
		map.put(userManager.addUser("admin", "admin1234"), new ArrayList<Post>());
		user = null;
	}

	private void printBoard() {
		System.out.println("-----BOARD-----");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 게시글 조회");
		System.out.println("[6] 게시글 작성");
		System.out.println("[7] 게시글 수정");
		System.out.println("[8] 게시글 삭제");
		System.out.println("[9] 관리자");
		System.out.println("[0] 종료");
		System.out.println("---------------");
	}

	private void join() {
		String id = inputString("id");
		String password = inputString("password");

		for (int i = 0; i < map.size(); i++) {
			if (id.equals(userManager.getUser(i).getId())) {
				System.err.println("중복된 아이디입니다.");
				return;
			}
		}
		User user = userManager.addUser(id, password);
		map.put(user, new ArrayList<Post>());

		System.out.println("회원가입 완료");
	}

	private void leave() {
		if (this.user != null) {
			String password = inputString("password 재확인");

			if (userManager.isValidLogin(this.user.getId(), password)) {
				userManager.removeUser(this.user);
				boardManager.removeUserPostsAll(this.user);

				map.remove(this.user);
				this.user = null;

				System.out.println("탈퇴 완료");
			} else {
				System.err.println("비밀번호가 일치하지 않습니다.");
				return;
			}
		} else {
			System.err.println("로그인 후 이용 해주세요.");
			return;
		}
	}

	private void login() {
		if (this.user == null) {
			String id = inputString("id");
			String password = inputString("password");

			if (!userManager.isValidLogin(id, password)) {
				System.err.println("회원정보를 다시 확인하세요.");
				return;
			}
			this.user = userManager.getUserById(id);
			System.out.println(String.format("%s님 로그인 완료", id));
		} else {
			System.err.println("이미 로그인 된 상태입니다.");
			return;
		}
	}

	private void logout() {
		this.user = null;
		System.out.println("로그아웃 완료");
	}

	private void writePostPublic() {
		if (this.user == null) {
			System.err.println("회원만 포스팅할 수 있습니다.");
			return;
		}
		String title = inputString("제목");
		String content = inputString("내용");

		Post post = new Post(title, content, user.getId());
		board.add(post);

		map.get(this.user).add(post);
		System.out.println("포스팅이 등록되었습니다.");
	}

	private void viewMyPost() {
		System.out.println("===============");
		for (int i = 0; i < map.get(user).size(); i++) {
			System.out.println(String.format("%d) 작성자 : %s\n%s", i + 1, user.getId(), map.get(user).get(i)));
		}
		System.out.println("===============");
	}

	private void viewAllPosts() {
		System.out.println("===============");
		for (User user : map.keySet()) {
			ArrayList<Post> userPosts = map.get(user);
			for (int i = 0; i < userPosts.size(); i++) {
				System.out.println(String.format("%d) 작성자 : %s\n%s", i + 1, user.getId(), userPosts.get(i)));
			}
		}
		System.out.println("===============");
	}

	private void printModifyPostMenu() {
		System.out.println("[1] 제목수정");
		System.out.println("[2] 내용수정");
	}

	private void modifyTitle() {
		if (this.user != null) {
			viewMyPost();
			int index = inputNumber("제목 수정할 글") - 1;
			int size = map.get(user).size();

			if (index < 0 || index >= size) {
				System.err.println("유효한 값이 아닙니다.");
				return;
			}

			String title = inputString("새로운 제목");

			Post post = map.get(this.user).get(index);

			post.setTitle(title);
			map.get(this.user).set(index, post);

			System.out.println("제목 수정 완료");
		} else {
			System.err.println("로그인 후 이용 해주세요.");
			return;
		}
	}

	private void modifyContent() {
		if (this.user != null) {
			viewMyPost();
			int index = inputNumber("내용 수정할 글") - 1;
			int size = map.get(user).size();

			if (index < 0 || index >= size) {
				System.err.println("유효한 값이 아닙니다.");
				return;
			}

			String content = inputString("새로운 내용");

			Post post = map.get(this.user).get(index);

			post.setContent(content);
			map.get(this.user).set(index, post);

			System.out.println("내용 수정 완료");
		} else {
			System.err.println("로그인 후 이용 해주세요.");
			return;
		}
	}

	private void runModifyPostMenu(int select) {
		if (select == 1)
			modifyTitle();
		else if (select == 2)
			modifyContent();
	}

	private void deletePost() {
		if (this.user != null) {
			viewMyPost();
			int index = inputNumber("삭제할 글") - 1;
			int size = map.get(user).size();

			if (index < 0 || index >= size) {
				System.err.println("유효한 값이 아닙니다.");
				return;
			}

			boardManager.removePost(index);
			map.get(this.user).remove(index);
			board.remove(index);

			System.out.println("포스팅 삭제 완료");
		} else {
			System.err.println("로그인 후 이용 해주세요.");
			return;
		}
	}

	private void printAdminMenu() {
		System.out.println("[1] 유저 추방");
		System.out.println("[2] 게시글 삭제");
	}

	private void deportUser() {
		if (this.user.getId().equals("admin")) {
			printAllUsers();

			int index = inputNumber("추방할 회원 번호");

			// 관리자 본인도 탈퇴할 수 없음
			if (index == 0) {
				System.err.println("관리자는 탈퇴할 수 없습니다.");
				return;
			}

			if (index < 0 || index >= map.size()) {
				System.err.println("유효한 값이 아닙니다.");
				return;
			}

			User target = userManager.getUser(index);
			userManager.removeUser(target);
			boardManager.removeUserPostsAll(target);

			map.remove(target);
			System.out.println("추방 완료");
		} else {
			System.err.println("관리자만 사용할 수 있는 기능입니다.");
			return;
		}
	}

	private void printAllUsers() {
		System.out.println();
		for (int i = 0; i < map.size(); i++) {
			System.out.println(String.format("%d) %s\n", i, userManager.getUser(i).getId()));
		}
	}

	private void deletePostByAdmin() {
		if (this.user.getId().equals("admin")) {
			viewAllPosts();
			
			int index = inputNumber("삭제할 글")-1;
			int size = 0;

			for (User user : map.keySet()) {
				ArrayList<Post> userPosts = map.get(user);
				size = userPosts.size();
			}
			
			if (index < 0 || index >= size) {
				System.err.println("유효한 값이 아닙니다.");
				return;
			}
			
			
			

		} else {
			System.err.println("관리자만 사용할 수 있는 기능입니다.");
			return;
		}
	}

	private void runAdminMenu(int select) {
		if (select == 1)
			deportUser();
		else if (select == 2)
			deletePostByAdmin();
	}

	private void runBoard(int select) {
		if (select == 1)
			join();
		else if (select == 2)
			leave();
		else if (select == 3)
			login();
		else if (select == 4)
			logout();
		else if (select == 5)
			viewAllPosts();
		else if (select == 6) {
			writePostPublic();
		} else if (select == 7) {
			printModifyPostMenu();
			runModifyPostMenu(inputNumber("선택"));
		} else if (select == 8)
			deletePost();
		else if (select == 9) {
			printAdminMenu();
			runAdminMenu(inputNumber("선택"));
		} else if (select == 0)
			isExit = true;
	}

	private void printStatus() {
		int userSize = map.size();
		int postSize = board.size();

		String message = String.format("User : %d\nPost : %d", userSize, postSize);

		System.out.println(message);
	}

	public void run() {
		while (!isExit) {
			printStatus();
			printBoard();
			int select = inputNumber("선택");
			runBoard(select);
		}
	}

	private int inputNumber(String message) {
		int number = -1;

		try {
			System.out.print(message + " : ");
			String input = scanner.nextLine();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자를 입력하세요.");
		}
		return number;
	}

	private String inputString(String message) {
		System.out.print(message + " : ");
		return scanner.nextLine();
	}

}
