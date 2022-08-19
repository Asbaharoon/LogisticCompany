package constant;

public enum EmployeeType {
	COURIER("COURIER"), OFFICE_CLERK("OFFICE_CLERK");

	private String employeeRoleName;

	EmployeeType(String employeeRoleName) {
		this.employeeRoleName = employeeRoleName;
	}

	@Override
	public String toString() {
		return employeeRoleName;
	}
}
