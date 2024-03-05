package com.soy.ajax.basic.domain;

public class UserVO {

	private String userId;
	private String userPw;
	
	public UserVO() {}

	
	
	
	public UserVO(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + "]";
	}
	
	
	
	
}
