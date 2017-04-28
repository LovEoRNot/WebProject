package tools;

public class Book {
	public String id; //编号
	public int type; //类型
	public String name; //书名
	public String author; //作者
	public String press; //出版社
	public float price; //价格
	public int inventory; //库存
	
	public Book(String id, int type, String name, String author, String press, float price, int inventory) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.author = author;
		this.press = press;
		this.price = price;
		this.inventory = inventory;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
}
