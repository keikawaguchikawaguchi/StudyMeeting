package acount;

public class HistoryBooksBean {
	private int purchaseID;
	private String isbn;
	private String title;
	private String author;
	private int price;
	private String info;
	private String image;
	private String publish;
	private String category;
	private int purchaseCount;

	public HistoryBooksBean(int purchaseID, String isbn, String title, String author,
			int price, String info,	String image, String publish, String category, int purchaseCount) {
		super();
		this.purchaseID = purchaseID;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.info = info;
		this.image = image;
		this.publish = publish;
		this.category = category;
		this.purchaseCount = purchaseCount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	public int getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return author;
	}
	public void setAuthorName(String authorName) {
		this.author = authorName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}



}