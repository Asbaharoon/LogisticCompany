package constant;

public enum RoleType {
	CUSTOMER("CUSTOMER"), EMPLOYEE("EMPLOYEE"), ADMIN("ADMIN");

	private String roleName;
	private RoleType(String roleName){
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return roleName;
	}
}
