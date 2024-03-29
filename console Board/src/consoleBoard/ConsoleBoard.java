package consoleBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleBoard {
	private Map<User, ArrayList<Board>> map;
	private int log;
	private boolean isExit;
	
	private Scanner scanner = new Scanner(System.in);
	
	public ConsoleBoard() {
		map = new HashMap<>();
		log = -1;
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
		
	}
	
	private void runBoard(int select) {
		if(select == 1)
			join();
//		else if(select == 2)
//			leave();
//		else if(select == 3)
//			login();
//		else if(select == 4)
//			logout();
//		else if(select == 5)
//			viewPost();
//		else if(select == 6)
//			writePost();
//		else if(select == 7)
//			modifyPost();
//		else if(select == 8)
//			deletePost();
		else if(select == 0)
			isExit = true;
	}
		
	public void run() {
		while(!isExit) {
			printBoard();
			int select = inputNumber("선택");
			runBoard(select);
		}
	}
	
	private int inputNumber(String message) {
		int number = -1;
		
		try {
			System.out.print(message + " : ");
			String input = scanner.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자를 입력하세요.");
		}
		return number;
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		
		return scanner.next();
	}

}
