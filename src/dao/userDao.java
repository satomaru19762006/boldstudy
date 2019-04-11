package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.user;

public class userDao {
    private static final long serialVersionUID = 1L;

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
	public boolean execute(user u){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javasystem","root","root");
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

}
