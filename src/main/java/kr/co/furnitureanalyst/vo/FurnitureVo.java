package kr.co.furnitureanalyst.vo;

import java.util.Arrays;
import java.util.Map;

public class FurnitureVo {
	String _id;
	String furName;
	String furBrand;
	int furMaxPrice;
	int furMinPrice;
	int furLikeCount;
	String[] furTag;
	String furType;
	String furLink;
	String furImage;
	String furColor;
	String furCategory;
	Map furBuyGender;
	Map furBuyAge;
	int furViewCount;
	public FurnitureVo() {
		
		// TODO Auto-generated constructor stub
	}
	public FurnitureVo(String _id, String furName, String furBrand, int furMaxPrice, int furMinPrice, int furLikeCount,
			String[] furTag, String furType, String furLink, String furImage, String furColor, String furCategory,
			Map furBuyGender, Map furBuyAge, int furViewCount) {
		super();
		this._id = _id;
		this.furName = furName;
		this.furBrand = furBrand;
		this.furMaxPrice = furMaxPrice;
		this.furMinPrice = furMinPrice;
		this.furLikeCount = furLikeCount;
		this.furTag = furTag;
		this.furType = furType;
		this.furLink = furLink;
		this.furImage = furImage;
		this.furColor = furColor;
		this.furCategory = furCategory;
		this.furBuyGender = furBuyGender;
		this.furBuyAge = furBuyAge;
		this.furViewCount = furViewCount;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFurName() {
		return furName;
	}
	public void setFurName(String furName) {
		this.furName = furName;
	}
	public String getFurBrand() {
		return furBrand;
	}
	public void setFurBrand(String furBrand) {
		this.furBrand = furBrand;
	}
	public int getFurMaxPrice() {
		return furMaxPrice;
	}
	public void setFurMaxPrice(int furMaxPrice) {
		this.furMaxPrice = furMaxPrice;
	}
	public int getFurMinPrice() {
		return furMinPrice;
	}
	public void setFurMinPrice(int furMinPrice) {
		this.furMinPrice = furMinPrice;
	}
	public int getFurLikeCount() {
		return furLikeCount;
	}
	public void setFurLikeCount(int furLikeCount) {
		this.furLikeCount = furLikeCount;
	}
	public String[] getFurTag() {
		return furTag;
	}
	public void setFurTag(String[] furTag) {
		this.furTag = furTag;
	}
	public String getFurType() {
		return furType;
	}
	public void setFurType(String furType) {
		this.furType = furType;
	}
	public String getFurLink() {
		return furLink;
	}
	public void setFurLink(String furLink) {
		this.furLink = furLink;
	}
	public String getFurImage() {
		return furImage;
	}
	public void setFurImage(String furImage) {
		this.furImage = furImage;
	}
	public String getFurColor() {
		return furColor;
	}
	public void setFurColor(String furColor) {
		this.furColor = furColor;
	}
	public String getFurCategory() {
		return furCategory;
	}
	public void setFurCategory(String furCategory) {
		this.furCategory = furCategory;
	}
	public Map getFurBuyGender() {
		return furBuyGender;
	}
	public void setFurBuyGender(Map furBuyGender) {
		this.furBuyGender = furBuyGender;
	}
	public Map getFurBuyAge() {
		return furBuyAge;
	}
	public void setFurBuyAge(Map furBuyAge) {
		this.furBuyAge = furBuyAge;
	}
	public int getFurViewCount() {
		return furViewCount;
	}
	public void setFurViewCount(int furViewCount) {
		this.furViewCount = furViewCount;
	}
	@Override
	public String toString() {
		return "FurnitureVo [_id=" + _id + ", furName=" + furName + ", furBrand=" + furBrand + ", furMaxPrice="
				+ furMaxPrice + ", furMinPrice=" + furMinPrice + ", furLikeCount=" + furLikeCount + ", furTag="
				+ Arrays.toString(furTag) + ", furType=" + furType + ", furLink=" + furLink + ", furImage=" + furImage
				+ ", furColor=" + furColor + ", furCategory=" + furCategory + ", furBuyGender=" + furBuyGender
				+ ", furBuyAge=" + furBuyAge + ", furViewCount=" + furViewCount + "]";
	}
	

	

	

	
}
