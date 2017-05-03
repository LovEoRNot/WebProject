package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.UserDao;
import tools.Book;
import tools.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	private UserDao userDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html");
		
		userDao = new UserDao();
		
		//获取session对象
		HttpSession session = request.getSession();
		//为了不管以何种方式打开页面都能完整的显示书本信息
		if(session.getAttribute("bookList") == null) {
			BookDao bd = new BookDao();
			List<Book> bookList = new ArrayList<Book>();
			try {
				bookList = bd.getBooks();
			} catch (Exception e) {
				System.out.println("not found this part");
			}
			session.setAttribute("bookList", bookList);
		}
		//获得用户名和密码
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		PrintWriter out = response.getWriter();
		user = new User(username, userpass);		
		//如果用户不存在则返回登录界面
		if( !userDao.checkUser(user) ){
			session.setAttribute("status", "0");//如果用户不存在，给status设置参数为0，存在设置为1		
			out.print("unvalid");
		}else{
			session.setAttribute("status", "1");
			session.setAttribute("user", username);
			out.print("valid");
		}
		out.close();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
