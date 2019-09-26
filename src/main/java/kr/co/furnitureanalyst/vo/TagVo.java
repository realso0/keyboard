package kr.co.furnitureanalyst.vo;

public class TagVo {

	private String tagName;

	private float tagVal;

	

	public TagVo(){};

	

	public TagVo(String tagName, float tagVal) {

		super();

		this.tagName = tagName;

		this.tagVal = tagVal;

	}

	

	public String getTagName() {

		return tagName;

	}

 

	public void setTagName(String tagName) {

		this.tagName = tagName;

	}

	public float gettagVal() {

		return tagVal;

	}

	public void settagVal(float tagVal) {

		this.tagVal = tagVal;

	}

	@Override

	public String toString() {

		return "TagVo [tagName=" + tagName + ", tagVal=" + tagVal + "]";

	}

}
