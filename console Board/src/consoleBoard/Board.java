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

}
