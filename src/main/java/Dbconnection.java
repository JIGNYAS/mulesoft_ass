import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/MyServ")
public class MyServ extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		PrintWriter pw= response.getWriter();
		pw.write("Connected");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesdb","root","root");
			String val=request.getParameter("insert");
			if(val!=null) {
				String name=request.getParameter("name");
				String actor=request.getParameter("actor");
				String actress=request.getParameter("actress");
				String director=request.getParameter("director");
				String yor=request.getParameter("year");
				String query="insert into movies (name,actor,actress,director,year)"+" values(?,?,?,?,?)";
				PreparedStatement stmt=conn.prepareStatement(query);
				stmt.setString(1, name);
				stmt.setString(2, actor);
				stmt.setString(3, actress);
				stmt.setString(4, director);
				stmt.setString(5, yor);
				stmt.execute();
				pw.write("<p> Inserted</p>");
			}
			else {
				Statement stm=conn.createStatement();
				ResultSet rs=stm.executeQuery("select * from movies");
				while(rs.next()) {
					pw.write("<p>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</p>");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
