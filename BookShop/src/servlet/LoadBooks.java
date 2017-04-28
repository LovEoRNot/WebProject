package servlet;

import tools.Book;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;

/**
 * Servlet implementation class LoadBooks
 */
@WebServlet("/LoadBooks")
public class LoadBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//这部分是在页面加载时调用的，用于显示页面上的书本信息
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		BookDao bd = new BookDao();
		List<Book> bookList = new ArrayList<Book>();
		try {
			bookList = bd.getBooks();
		} catch (Exception e) {
			System.out.println("not found this part");
		}
		session.setAttribute("bookList", bookList);	
		response.sendRedirect("shopping.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
