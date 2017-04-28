package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.Book;
import tools.BookOnCart;

public class BookDao extends BaseDao {
	
	//从数据库中获取全部的书本
	public List<Book> getBooks() throws Exception{
		List<Book> bookList = new ArrayList<Book>();
		String sql = "SELECT * FROM book"; 
		ResultSet rs = doQuery(sql, null);
		while(rs.next()){
			String id = rs.getString(1);
			int type = rs.getInt(2);
			String name = rs.getString(3);
			String author = rs.getString(4);
			String press = rs.getString(5);
			float price = rs.getFloat(6);
			int inventory = rs.getInt(7);
			Book book = new Book(id, type, name, author, press, price, inventory);
			bookList.add(book);
		}
		rs.close();
		
		return bookList;
	}
	
	//更新书本的库存，传入处在购物车中的书籍的信息
	public boolean upadateBook(BookOnCart boc) throws SQLException, Exception {
		String sql = "SELECT * FROM book WHERE id = ?";
		Object[] params = new Object[1];
		params[0] = boc.getId();
		ResultSet rs = doQuery(sql, params);
		//先判断数据库中这种书还有没有，有的话执行更新操作，否则退出
		if( rs.next() ){
			int inventory = rs.getInt("inventory");			
			String _sql = "UPDATE book SET inventory = ? WHERE id = ?";
			Object[] _params = new Object[2];
			_params[0] = inventory - boc.getBuyCount();
			_params[1] = boc.getId();
			int num = doUpdate(_sql, _params);
			if(num > 0) {
				return true;
			}
		}
		rs.close();
		
		return false;
	}
	
}
