package algorithm.model;

public class BaseInfo {
	private String title;
	private String content;
	private String date;
	private String keywords;
	private boolean isSolve;
	private String className;

	public BaseInfo() {
		
	}
	
	public BaseInfo(String content, String keywords, String date,boolean isSolve) {
		this.content=content;
		this.keywords=keywords;
		this.date=date;
		this.isSolve=isSolve;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public boolean isSolve() {
		return isSolve;
	}

	public void setSolve(boolean isSolve) {
		this.isSolve = isSolve;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
