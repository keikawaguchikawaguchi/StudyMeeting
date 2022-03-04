package master;

import java.util.Date;

public class ProductBean {

	private String isbn;
	private String title;
	private String authorID;
	private int price;
	private String categoryID;
	private String info;
	private int stock;
	private String image;
	private String publishID;
	private Date sales_date;



	public ProductBean(String isbn, String title, String authorID, int price, String categoryID, String info, int stock,
			String image, String publishID, Date sales_date) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authorID = authorID;
		this.price = price;
		this.categoryID = categoryID;
		this.info = info;
		this.stock = stock;
		this.image = image;
		this.publishID = publishID;
		this.sales_date = sales_date;
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



	public String getAuthorID() {
		return authorID;
	}



	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public String getCategoryID() {
		return categoryID;
	}



	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}



	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.info = info;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getPublishID() {
		return publishID;
	}



	public void setPublishID(String publishID) {
		this.publishID = publishID;
	}



	public Date getSales_date() {
		return sales_date;
	}



	public void setSales_date(Date sales_date) {
		this.sales_date = sales_date;
	}


}


