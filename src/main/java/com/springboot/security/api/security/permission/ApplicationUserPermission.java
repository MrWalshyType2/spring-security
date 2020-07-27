package com.springboot.security.api.security.permission;

public enum ApplicationUserPermission {
	// Specifies what permissions a role has
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permission;
	
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}
}
