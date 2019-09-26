package kr.co.furnitureanalyst.vo;

import java.util.Arrays;

public class UserVo {

	private String userId;
	private String password;
	private String birthday;
	private String gender;
	private String userQuestion;
	private String userAnswer;
	private String createDate;
	private String loginDate;
	private String keepTime;
	private String[] userLIkeList;
	private	String[] userLikeBrand;
	private String[] userVisitList;
	private String[] userLikeType;
	private String[] userLikeTag;
	
	public UserVo(){}
	
	public UserVo(String userId, String password, String birthday, String gender, String userQuestion,
			String userAnswer, String createDate, String loginDate, String keepTime, String[] userLIkeList,
			String[] userLikeBrand, String[] userVisitList, String[] userLikeType, String[] userLikeTag) {
		super();
		this.userId = userId;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
		this.createDate = createDate;
		this.loginDate = loginDate;
		this.keepTime = keepTime;
		this.userLIkeList = userLIkeList;
		this.userLikeBrand = userLikeBrand;
		this.userVisitList = userVisitList;
		this.userLikeType = userLikeType;
		this.userLikeTag = userLikeTag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserQuestion() {
		return userQuestion;
	}
	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getKeepTime() {
		return keepTime;
	}
	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}
	public String[] getUserLIkeList() {
		return userLIkeList;
	}
	public void setUserLIkeList(String[] userLIkeList) {
		this.userLIkeList = userLIkeList;
	}
	public String[] getUserLikeBrand() {
		return userLikeBrand;
	}
	public void setUserLikeBrand(String[] userLikeBrand) {
		this.userLikeBrand = userLikeBrand;
	}
	public String[] getUserVisitList() {
		return userVisitList;
	}
	public void setUserVisitList(String[] userVisitList) {
		this.userVisitList = userVisitList;
	}
	public String[] getUserLikeType() {
		return userLikeType;
	}
	public void setUserLikeType(String[] userLikeType) {
		this.userLikeType = userLikeType;
	}
	public String[] getUserLikeTag() {
		return userLikeTag;
	}
	public void setUserLikeTag(String[] userLikeTag) {
		this.userLikeTag = userLikeTag;
	}
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", password=" + password + ", birthday=" + birthday + ", gender=" + gender
				+ ", userQuestion=" + userQuestion + ", userAnswer=" + userAnswer + ", createDate=" + createDate
				+ ", loginDate=" + loginDate + ", keepTime=" + keepTime + ", userLIkeList="
				+ Arrays.toString(userLIkeList) + ", userLikeBrand=" + Arrays.toString(userLikeBrand)
				+ ", userVisitList=" + Arrays.toString(userVisitList) + ", userLikeType="
				+ Arrays.toString(userLikeType) + ", userLikeTag=" + Arrays.toString(userLikeTag) + "]";
	}
	
	
}