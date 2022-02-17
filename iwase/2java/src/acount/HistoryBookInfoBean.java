package acount;


public class HistoryBookInfoBean {
	//フィールド---------------------------
			private String title;			//商品タイトル名
			private int purchase_count;	//注文商品ごとの注文数
			private String isbn;			//isbn
			private int price;				//価格

			//コンストラクタ-------------------------
				public HistoryBookInfoBean() {
				}
				public HistoryBookInfoBean(String title,int purchase_count,String isbn,int price) {
					this.title = title;
					this.purchase_count = purchase_count;
					this.isbn = isbn;
					this.price = price;
				}

			//メソッド-------------------------
				public String getTitle() {
					return title;
				}
				public void setTitle(String title) {
					this.title = title;
				}
				public int getPurchase_count() {
					return purchase_count;
				}
				public void setPurchase_count(int purchase_count) {
					this.purchase_count = purchase_count;
				}
				public String getIsbn() {
					return isbn;
				}
				public void setIsbn(String isbn) {
					this.isbn = isbn;
				}
				public int getPrice() {
					return price;
				}
				public void setPrice(int price) {
					this.price = price;
				}

}