package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.BookOnCart;

/**
 * Servlet implementation class BookCart
 */
@WebServlet("/BookCart")
public class BookCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		//获得书本信息
		String id = (String) request.getParameter("id");
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		name =  java.net.URLDecoder.decode(name , "UTF-8");		
		String author = new String(request.getParameter("author").getBytes("ISO-8859-1"),"UTF-8");
		author =  java.net.URLDecoder.decode(author , "UTF-8");		
		String press = new String(request.getParameter("press").getBytes("ISO-8859-1"),"UTF-8");
		press =  java.net.URLDecoder.decode(press , "UTF-8");		
		String price = (String) request.getParameter("price");
		float t_price = Float.valueOf(price);
		String num = (String) request.getParameter("num");
		int t_num = Integer.valueOf(num);
		BookOnCart book = new BookOnCart(id, name, author, press, t_price, t_num);
		
		if(session.getAttribute("cartBooks") == null) {
			List<BookOnCart> cartBooks = new ArrayList<BookOnCart>();
			cartBooks.add(book);
			session.setAttribute("cartBooks", cartBooks);
		} else {
			List<BookOnCart> carBooks = (List<BookOnCart>) session.getAttribute("cartBooks");
			session.removeAttribute("cartBooks");
			carBooks.add(book);			
			session.setAttribute("cartBooks", carBooks);
		}	
		response.getWriter().write("sucess");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
