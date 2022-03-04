package acount;

import java.sql.Timestamp;

public class HistoryBean {
	//フィールド-------------e--------------
	private int purchaseID;			//注文番号
	private Timestamp purchaseDate;	//注文日
	private String selectAdpost;		//送付先郵便番号
	private String selectAddress;		//送付先住所
	private double tax;				//タックス


	public HistoryBean(int purchaseID, Timestamp purchaseDate, String selectAdpost, String selectAddress, double tax) {
		super();
		this.purchaseID = purchaseID;
		this.purchaseDate = purchaseDate;
		this.selectAdpost = selectAdpost;
		this.selectAddress = selectAddress;
		this.tax = tax;
	}


	public int getPurchaseID() {
		return purchaseID;
	}


	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}


	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	public String getSelectAdpost() {
		return selectAdpost;
	}


	public void setSelectAdpost(String selectAdpost) {
		this.selectAdpost = selectAdpost;
	}


	public String getSelectAddress() {
		return selectAddress;
	}


	public void setSelectAddress(String selectAddress) {
		this.selectAddress = selectAddress;
	}


	public double getTax() {
		return tax;
	}


	public void setTax(double tax) {
		this.tax = tax;
	}






}
