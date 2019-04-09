package login;

public class user {
	private String user_id;
	private String user_name;
	private String password;

	public user(String user_id,String user_name,String password) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getPassword() {
		return password;
	}

}
