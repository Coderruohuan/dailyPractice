package reflect;

public class User extends Person {

	private String username;

	private String password;

	public String nickName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void print() {
		System.out.println(this.getUsername());
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@SuppressWarnings("unused")
	private void sing() {
		System.out.println("user sing.....");
	}

	public void run() {
		System.out.println("user run.....");
	}
}