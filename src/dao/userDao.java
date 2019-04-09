package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

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
                    "select user_name from user where user_id = ? and password = ?");
            ps.setString(1, userId);
            ps.setString(2, password);
            rs = ps.executeQuery();

            String userName = null;
            //***追加***
            List<user> userlist = new ArrayList<user>();
            //*********
            while(rs.next()) {
                userName = rs.getString("user_name");
            }

            RequestDispatcher dispatch = null;
            if (userName != null) {
            	//***追加***
                ps = con.prepareStatement(
                        "select user_id,user_name,password from user");
                rs = ps.executeQuery();
                while(rs.next()) {
                	user user = new user(rs.getString("user_id") , rs.getString("user_name") , rs.getString("password"));
                    userlist.add(user);
                }

            	session.setAttribute("userlist", userlist);
            	//**********
                dispatch = request.getRequestDispatcher("LoginOK.jsp");
                dispatch.forward(request, response);
            } else {
                dispatch = request.getRequestDispatcher("LoginNG.jsp");
                dispatch.forward(request, response);
            }
        } catch(SQLException e_sql) {
            e_sql.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
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
