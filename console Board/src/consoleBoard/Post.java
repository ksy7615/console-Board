package consoleBoard;

public class Post {
	// Board CRUD
	String title, content;
	String writerId;

	public Post() {

	}
	
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Post(String title, String content, String writerId) {
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

	public String getWriterId() {
		return writerId;
	}

	public Post clone() {
		return new Post(this.title, this.content, this.writerId);
	}
	
	@Override
	public String toString() {
		String message = "";
		
		message += String.format("제목 : %s", this.title);
		message += "\n";
		message += String.format("내용 : %s", this.content);
		
		return message;
	}

}
