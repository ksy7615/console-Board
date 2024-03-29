package consoleBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConsoleBoard {
	private Map<User, ArrayList<Board>> map;
	private int log;
	private boolean isExit;
	
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
	
	public void run() {
		while(!isExit) {
			printBoard();
			break;
		}
	}

}
