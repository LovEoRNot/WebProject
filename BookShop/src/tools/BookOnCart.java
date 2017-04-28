package tools;

public class BookOnCart {
	public String id; //���
	public String name; //����
	public String author;//����
	public String press;//ӡˢ��
	public float price;//����
	public int buyCount; //��������
		
	public BookOnCart(String id, String name, String author, String press, float price, int buyCount) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.press = press;
		this.price = price;
		this.buyCount = buyCount;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	
	
}
