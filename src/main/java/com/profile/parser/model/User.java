package com.profile.parser.model;

public class User {
	 String username;
	    String password;
	    String email;
	    Integer employeeId;
	    String employeeName;
	    String employeeRole;

	   

	   

	    public String getEmployeeRole() {
			return employeeRole;
		}

		public User() {
			super();
		}

		public void setEmployeeRole(String employeeRole) {
			this.employeeRole = employeeRole;
		}

		public User(String username,  String email, Integer employeeId, String employeeName, String employeeRole) {
			super();
			this.username = username;
			this.email = email;
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.employeeRole = employeeRole;
		}

		@Override
		public String toString() {
			return "User [username=" + username + ", email=" + email + ", employeeId=" + employeeId + ", employeeName="
					+ employeeName + ", employeeRole=" + employeeRole + "]";
		}

		public Integer getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}

		public String getEmployeeName() {
			return employeeName;
		}

		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}

		public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
}
