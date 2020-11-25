package com.e2e.exercises.exercise2.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootPojo {

	@JsonProperty("Userlistpojo")
	private List<Userlistpojo> userListPojo;

	public List<Userlistpojo> getUserListPojo() {
		return userListPojo;
	}

	public void setUserListPojo(List<Userlistpojo> userListPojo) {
		this.userListPojo = userListPojo;
	}
	
	
	
	
}
