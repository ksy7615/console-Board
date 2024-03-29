package consoleBoard;

public class Board {
	// Board CRUD
	String title, content;
	
	public Board() {
		
	}
	
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
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
		return new Board(this.title, this.content);
	}
	
}
