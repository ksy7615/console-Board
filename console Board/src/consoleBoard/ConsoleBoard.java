package consoleBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleBoard {
	private Map<User, ArrayList<Post>> map;
	private ArrayList<Post> board;
//	private List keySet;

	private UserManager userManager;
	private BoardManager boardManager;
	private User user;
	private boolean isExit;

	private Scanner scanner = new Scanner(System.in);

	public ConsoleBoard() {
		map = new HashMap<>();
		board = new ArrayList<>();
//		keySet = new ArrayList(map.keySet());
		userManager = UserManager.getInstance();
		boardManager = BoardManager.getInstance();
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

	private void writePost() {
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
	
	private void printModifyPostMenu() {
		System.out.println("[1] 제목수정");
		System.out.println("[2] 내용수정");
	}
	
	private void modifyTitle() {
		
	}
	
	private void runModifyPostMenu(int select) {
		if(select == 1)
			modifyTitle();
//		else if(select == 2)
//			modifyContent();
	}

	private void runBoard(int select) {
		if (select == 1)
			join();
//		else if(select == 2)
//			leave();
		else if (select == 3)
			login();
		else if (select == 4)
			logout();
//		else if (select == 5)
//			viewPostAll();
		else if (select == 6)
			writePost();
		else if(select == 7) {
			printModifyPostMenu();
			runModifyPostMenu(inputNumber("선택"));
		}
//		else if(select == 8)
//			deletePost();
		else if (select == 0)
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
