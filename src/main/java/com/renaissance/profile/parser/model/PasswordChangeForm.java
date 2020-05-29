package com.renaissance.profile.parser.model;

public class PasswordChangeForm {
	
	private String username;
	private String oldPassword;
	private String newPassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "PasswordChangeForm [username=" + username + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + "]";
	}
	

}
