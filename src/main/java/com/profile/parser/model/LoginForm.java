package com.profile.parser.model;

import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {
	 @SuppressWarnings("deprecation")
	@NotBlank(message = "username can't empty!")
	    String username;

	    @NotBlank(message = "password can't empty!")
	    String password;

	    
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }
	    
	    public void setPassword(String password) {
			this.password = password;
		}
	    
	    public String getPassword() {
			return password;
		}

}
