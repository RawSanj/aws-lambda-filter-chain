package com.github.rawsanj.lambda.domain;

import java.util.UUID;

public class User {

	private String id;

	private String userName;

	private String fullName;

	private Integer age;

	private String company;

	public User() {
		this.id = UUID.randomUUID().toString();
	}

	public User(String userName, String fullName, Integer age, String company) {
		this.id = UUID.randomUUID().toString();
		this.userName = userName;
		this.fullName = fullName;
		this.age = age;
		this.company = company;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!id.equals(user.id)) return false;
		return getUserName().equals(user.getUserName());

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + getUserName().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", userName='" + userName + '\'' +
				", fullName='" + fullName + '\'' +
				", age=" + age +
				", company='" + company + '\'' +
				'}';
	}
}
