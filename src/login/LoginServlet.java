package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatch = null;
        dispatch = request.getRequestDispatcher("Login.jsp");
        dispatch.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        //***追加***
        String new_id = request.getParameter("new_id");
        String new_name = request.getParameter("new_name");
        String new_password = request.getParameter("new_password");
        String registration = request.getParameter("registration");

        HttpSession session = request.getSession();
        RequestDispatcher dispatch = null;

        user u = new user(new_id,new_name,new_password);
    	PostUserLogic pul = new PostUserLogic();


    	if (!StringUtils.isEmptyOrWhitespaceOnly(registration)) {
	    	switch (registration) {
		    	case "登録":
		    		pul.create(u);
		    		break;
		    	case "更新":
		    		pul.update(u);
		    		break;
		    	case "削除":
		    		pul.delete(u);
		    		break;
		    	default:
	    	}
    	}
//        if (!StringUtils.isEmptyOrWhitespaceOnly(registration) && registration.equals("登録")) {
//        	pul.create(u);
//
//        }
//        if (!StringUtils.isEmptyOrWhitespaceOnly(registration) && registration.equals("更新")) {
//        	pul.update(u);
//
//        }
        //**********
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

            if (userName != null || registration.equals("登録") || registration.equals("更新") || registration.equals("削除")) {
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
    }
}