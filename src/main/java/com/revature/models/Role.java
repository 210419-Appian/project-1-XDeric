package com.revature.models;

public class Role { // Admin,Employee,Standard,Premium
	
	private int roleId;
	private String roleName;
	
	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.roleName = role;
	}
	
	public Role() {
		super();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRole(String role) {
		this.roleName = role;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
}
