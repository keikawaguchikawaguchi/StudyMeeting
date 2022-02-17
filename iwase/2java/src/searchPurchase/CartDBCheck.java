package searchPurchase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDBCheck {

	public List<String> cartDBCheck(Map<String, CartBean> mapCartBean){
		List <String> cartCheck = new ArrayList<String>();

		for(Map.Entry<String, CartBean> entry: mapCartBean.entrySet()) {
			String[] isbnNumOfPur = new CartDBCheckBeanDAO().getIsbnPurOfNum(entry.getKey());
			if(isbnNumOfPur[0] != null) {
				if(entry.getValue().getNumOfPur() > Integer.parseInt(isbnNumOfPur[1])) {
					cartCheck.add(entry.getKey());
				}
			}else {
				cartCheck.add(entry.getKey());
			}

		}

		return cartCheck;
	}
}
