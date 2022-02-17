package searchPurchase;

import java.io.Serializable;

//Bean
//検索結果で得られた本の情報を格納するクラス

public class SearchBooksDetalBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String title;
	private String author_name;
	private String price;
	private String info;
	private String stock;
	private String image;
	private String publish_name;
	private String category_name;
	private String sales_date;

	public SearchBooksDetalBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public SearchBooksDetalBean(String books1, String books2, String books3, String books4, String books5,
			String books6, String books7, String books8, String books9, String books10) {
		//インスタンス生成時配列にいれた本の情報の変数のなかに値をいれる

		this.isbn = books1;
		this.title = books2;
		this.author_name = books3;
		this.price = books4;
		this.info = books5;
		this.stock = books6;
		this.image = books7;
		this.publish_name = books8;
		this.category_name = books9;
		this.sales_date = books10;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public String getPrice() {
		return price;
	}

	public String getInfo() {
		return info;
	}

	public String getStock() {
		return stock;
	}

	public String getImage() {
		return image;
	}

	public String getPublish_name() {
		return publish_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public String getSales_date() {
		return sales_date;
	}


	public void setIsbn(String inbn) {
		this.isbn = inbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setPublish_name(String publish_name) {
		this.publish_name = publish_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public void setSales_date(String sales_date) {
		this.sales_date = sales_date;
	}






}






