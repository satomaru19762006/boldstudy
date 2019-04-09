<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="login.user,java.util.List,java.util.ArrayList" %>
<%
List<user> userlist = (List<user>) session.getAttribute("userlist");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン成功</title>
</head>
<body>
    ログインに成功しました！！
    <br/>
    <form method="POST" action="LoginServlet">
        <!-- 社員一覧を表示 -->
	    <br/>
	    <b>社員一覧</b>
	    <br/>
	    <table border="1" width="500" cellspacing="0" cellpadding="5" bordercolor="#333333">
	    <tr>
		<th bgcolor="#4169e1" width="150"><font color="#FFFFFF">id</font></th>
		<th bgcolor="#4169e1" width="150"><font color="#FFFFFF">名前</font></th>
		<th bgcolor="#4169e1" width="150"><font color="#FFFFFF">パスワード</font></th>
		<th bgcolor="#4169e1" width="150"><font color="#FFFFFF">処理</font></th>
		</tr>
		<tr>
		<td bgcolor="#4169e1" align="center" nowrap><input name="new_id" type="text" ></input></td>
		<td bgcolor="#4169e1" align="center" nowrap><input name="new_name" type="text" ></input></td>
		<td bgcolor="#4169e1" align="center" nowrap><input name="new_password" type="password" ></input></td>
		<td bgcolor="#4169e1" align="center" nowrap><input name="registration" type="submit" value="登録" ></td>
		</tr>
	    <% for(user u: userlist) { %>
			<tr>
			<td bgcolor="#f0f8ff" align="center" nowrap><input type="text" value="<%= u.getUser_id() %>"></input></td>
			<td bgcolor="#f0f8ff" align="center" nowrap><input type="text" value="<%= u.getUser_name() %>"></input></td>
			<td bgcolor="#f0f8ff" align="center" nowrap><input type="password" value="<%= u.getPassword() %>"></input></td>
			<td bgcolor="#4169e1" align="center" nowrap><input type="submit" value="更新"><input type="submit" value="削除"></td>
			</tr>
	    <% } %>
	    </table>
    </form>
    <br/>
    <form method="GET" action="LoginServlet">
        <input type="submit" value="ログイン画面へ">
    </form>
</body>
</html>