package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.user;

public class userDao {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    //登録
	public boolean createUser(user u){
        try {
        	con = ConnectionFactory.createConnection();
            ps = con.prepareStatement(
                    "insert into  user(user_id,user_name,password) values (?,?,?)");
            ps.setString(1, u.getUser_id());
            ps.setString(2, u.getUser_name());
            ps.setString(3, u.getPassword());
            int result = ps.executeUpdate();

            if(result != 1) {
            	return false;
            }

        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

		return true;
	}

    //更新
	public boolean updateUser(user u){
        try {
        	con = ConnectionFactory.createConnection();
            ps = con.prepareStatement(
                    "update user set user_name = ?, password = ?  where user_id = ?");
            ps.setString(1, u.getUser_name());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getUser_id());
            int result = ps.executeUpdate();

            if(result != 1) {
            	return false;
            }

        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

		return true;
	}

    //削除
	public boolean deleteUser(user u){
        try {
        	con = ConnectionFactory.createConnection();
            ps = con.prepareStatement(
                    "delete from user where user_id = ?");
            ps.setString(1, u.getUser_id());
            int result = ps.executeUpdate();

            if(result != 1) {
            	return false;
            }

        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

		return true;
	}

}
