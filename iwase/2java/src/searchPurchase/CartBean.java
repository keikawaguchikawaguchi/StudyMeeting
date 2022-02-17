package searchPurchase;

public class CartBean {
	//	private String isbn;
	//	private String title;
	//	private String author_name;
	//	private String price;
	//	private String info;
	//	private String stock;
	//	private String image;
	//	private String publish_name;
	//	private String category_name;
	//	private String sales_date;

	private int NumOfPur;
	private SearchBooksDetalBean searchBDB;

	public CartBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//	public CartBean(String isbn, String title, String author_name, String price, String info, String stock, String image,
	//			String publish_name, String category_name, String sales_date, String user_id, int NumOfPur) {
	// TODO 自動生成されたコンストラクター・スタブ
	//		this.isbn = isbn;
	//		this.title = title;
	//		this.author_name = author_name;
	//		this.price = price;
	//		this.info = info;
	//		this.stock = stock;
	//		this.image = image;
	//		this.publish_name = publish_name;
	//		this.category_name = category_name;
	//		this.sales_date = sales_date;

	public CartBean(int NumOfPur, SearchBooksDetalBean searchBDB) {

		this.NumOfPur = NumOfPur;
		this.searchBDB = searchBDB;

	}





	public int getNumOfPur() {
		return NumOfPur;
	}

	public void setNumOfPur(int numOfPur) {
		NumOfPur = numOfPur;
	}

	public SearchBooksDetalBean getSearchBDB() {
		return searchBDB;
	}

	public void setSearchBDB(SearchBooksDetalBean searchBDB) {
		this.searchBDB = searchBDB;
	}
}

