package dao;

import login.user;

public class daotest {
	public static void main(String[] args)  {
		user u = new user("1","流川　楓","root");
		userDao daotest = new userDao();
		boolean result = daotest.execute(u);
		System.out.println(result);

	}

}
