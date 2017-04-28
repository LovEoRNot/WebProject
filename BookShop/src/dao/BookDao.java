package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.Book;
import tools.BookOnCart;

public class BookDao extends BaseDao {
	
	//�����ݿ��л�ȡȫ�����鱾
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
	
	//�����鱾�Ŀ�棬���봦�ڹ��ﳵ�е��鼮����Ϣ
	public boolean upadateBook(BookOnCart boc) throws SQLException, Exception {
		String sql = "SELECT * FROM book WHERE id = ?";
		Object[] params = new Object[1];
		params[0] = boc.getId();
		ResultSet rs = doQuery(sql, params);
		//���ж����ݿ��������黹��û�У��еĻ�ִ�и��²����������˳�
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
