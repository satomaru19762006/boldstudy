package login;

import dao.userDao;

public class PostUserLogic {
	public void execute(user u) {
		userDao userdao = new userDao();
		userdao.execute(u);
	}

}
