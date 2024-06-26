package consoleBoard;

import java.util.ArrayList;

public class BoardManager {
	private ArrayList<Post> board = new ArrayList<>();

	private BoardManager() {
	}

	private static BoardManager instance = new BoardManager();

	public static BoardManager getInstance() {
		return instance;
	}

	public Post addPost(String title, String content, User user) {
		// 로그인 되면 유저 활성화 되게 해놨으므로 null 아닐 때 포스트 작성
		if (user != null) {
			if (title != null && content != null) {
				Post post = new Post(title, content);
				board.add(post);
				return post;
			}
		}
		return new Post();
	}

	// 작성자가 쓴 글 보여주기
	public Post getPost(User user) {
		for (Post post : board) {
			if (post.getWriterId().equals(user.getId())) {
				return post;
			}
		}
		return new Post();
	}

	public int size() {
		return board.size();
	}

	// 자기 글만 수정을 할 수 있어야함
	public void setTitlePost(User user, int index, String title) {
		// 권한이 있으면
		if (user.isRight()) {
			// 쓴 글중에 수정할 걸 선택해서
			Post post = board.get(index);
			post.setTitle(title);
			board.set(index, post);
		}
	}

	public void setContentPost(User user, int index, String content) {
		if (user.isRight()) {
			Post post = board.get(index);
			post.setContent(content);
		}
	}

	public boolean removePost(int index) {
		if (index >= 0 && index < board.size()) {
		// 삭제할 포스팅 선택
		Post post = board.get(index);
		return board.remove(post);
		} else {
			return false;
		}
	}
	
	// 탈퇴할 때 지우는 용
	public void removeUserPostsAll(User user) {
	    for (int i = 0; i < board.size(); i++) {
	        if (board.get(i).getWriterId().equals(user.getId())) {
	            board.remove(i);
	        }
	    }
	}
	
}
