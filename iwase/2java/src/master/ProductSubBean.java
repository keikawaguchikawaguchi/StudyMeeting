package master;

import java.util.Map;

public class ProductSubBean {
Map<String,String> authorMap;
Map<String,String> categoryMap;
Map<String,String> publishMap;

public ProductSubBean(Map<String, String> authorMap, Map<String, String> categoryMap, Map<String, String> publishMap) {
	super();
	this.authorMap = authorMap;
	this.categoryMap = categoryMap;
	this.publishMap = publishMap;
}

public Map<String, String> getAuthorMap() {
	return authorMap;
}

public void setAuthorMap(Map<String, String> authorMap) {
	this.authorMap = authorMap;
}

public Map<String, String> getCategoryMap() {
	return categoryMap;
}

public void setCategoryMap(Map<String, String> categoryMap) {
	this.categoryMap = categoryMap;
}

public Map<String, String> getPublishMap() {
	return publishMap;
}

public void setPublishMap(Map<String, String> publishMap) {
	this.publishMap = publishMap;
}



}
