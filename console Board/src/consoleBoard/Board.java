package consoleBoard;

public class Board {
	// Board CRUD
	String title, content;
	String writerId;

	public Board() {

	}

	public Board(String title, String content, String writerId) {
		this.title = title;
		this.content = content;
		this.writerId = writerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Board clone() {
		return new Board(this.title, this.content, this.writerId);
	}

	public Board addPost(String title, String content, String writerId, User user) {
		if (writerId.equals(user.getId())) {
			if (title != null && content != null) {
				Board board = new Board(title, content, writerId);
				return board.clone();
			} else {
				System.err.println("제목과 내용을 입력해주세요.");
				return new Board();
			}
		} else {
			System.err.println("존재하지 않는 ID입니다.");
			return new Board();
		}
	}

}
