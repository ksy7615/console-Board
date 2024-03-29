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

	// 작성자가 쓴 글 보여주기
	public Post getPost(User user) {
		for(Post post : board) {
			if(post.getWriterId().equals(user.getId())) {
				return post;
			}
		}
		return new Post();
	}
	
	// 자기 글만 수정을 할 수 있어야함
	public void setTitlePost(User user, int index, String title) {
		// 권한이 있으면
		if(user.isRight()) {
			// 쓴 글중에 수정할 걸 선택해서
			Post post = board.get(index);
			post.setTitle(title);
		}
	}
	
	

}
