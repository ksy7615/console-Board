package consoleBoard;

import java.util.ArrayList;

public class BoardManager {
	private ArrayList<Post> board;
	
	public BoardManager() {
		board = new ArrayList<>();
	}
	
	public Post addPost(String title, String content, User user) {
		// 로그인 되면 유저 활성화 되게 해놨으므로 null 아닐 때 포스트 작성
		if (user != null) {
			if (title != null && content != null) {
				Post post = new Post(title, content);
				board.add(post);
				return post.clone();
			}
		}
		return new Post();
	}

}
