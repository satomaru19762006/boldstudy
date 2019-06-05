package login;

import dao.userDao;

public class PostUserLogic {
	private userDao userdao = new userDao();
	//利用者の登録
	public void create(user u) {
		userdao.createUser(u);
	}

	//利用者の更新
	public void update(user u) {
		userdao.updateUser(u);
	}

	//利用者の削除
	public void delete(user u) {
		userdao.deleteUser(u);
	}

}
