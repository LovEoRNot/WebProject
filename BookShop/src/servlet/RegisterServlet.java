package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import tools.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		UserDao userdao = new UserDao();
		//获得用户名和密码,同时将前台编码过的信息解码，得到中文字符
		String username = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		username =  java.net.URLDecoder.decode(username , "UTF-8");
		String userpass = request.getParameter("password");		
		//System.out.println(username+" "+userpass);	
		User user = new User(username, userpass);
		int n = 0;
		n = userdao.updateUser(user);
		if(n > 0){
			response.getWriter().print("sucess");
		}
		else{
			response.getWriter().print("false");
		}			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
