package searchPurchase;

import java.util.List;
import java.util.Map;

import acount.LoginBean;
import acount.TaxBean;

public class UserSessionBean {
	private List<SearchBooksDetalBean> searchBDBList;
	private Map<String,CartBean> MapCartBean;
	private LoginBean loginInfo;
	private String loginHed;
	private TaxBean taxBean;

	public UserSessionBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public List<SearchBooksDetalBean> getSearchBDBList() {
		return searchBDBList;
	}

	public void setSearchBDBList(List<SearchBooksDetalBean> searchBDBList) {
		this.searchBDBList = searchBDBList;
	}


	public Map<String, CartBean> getMapCartBean() {
		return MapCartBean;
	}

	public void setMapCartBean(Map<String, CartBean> mapCartBean) {
		MapCartBean = mapCartBean;
	}

	public LoginBean getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginBean loginInfo) {
		this.loginInfo = loginInfo;
	}

	public String getLoginHed() {
		return loginHed;
	}

	public void setLoginHed(String loginHed) {
		this.loginHed = loginHed;
	}


	public TaxBean getTaxBean() {
		return taxBean;
	}

	public void setTaxBean(TaxBean taxBean) {
		this.taxBean = taxBean;
	}



}
