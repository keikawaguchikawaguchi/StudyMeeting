package searchPurchase;

import java.util.HashMap;
import java.util.Map;

public class IntoCart {


	public Map<String,CartBean> firstCartInto (int NumOfPur, SearchBooksDetalBean searchBDB, String isbn) {
		CartBean cartBean = new CartBean(NumOfPur, searchBDB);
		Map<String,CartBean> mapCartBean = new HashMap<String, CartBean>();
		mapCartBean.put(isbn, cartBean);
		System.out.println("mapCartBeanのサイズ：" + mapCartBean.size());
		System.out.println(mapCartBean);
		return mapCartBean;

	}

	public CartBean newItemCartInto (int NumOfPur, SearchBooksDetalBean searchBDB) {
		CartBean cartBean = new CartBean(NumOfPur, searchBDB);
		return cartBean;
	}


	public CartBean secondItemCartInto (int NumOfPur, CartBean cartBean) {
		int sumNumOfPur = cartBean.getNumOfPur() + NumOfPur;
		cartBean.setNumOfPur(sumNumOfPur);
		return cartBean;
	}

	public void changeNumOfPur(int NumOfPur, String isbn) {

	}




}
