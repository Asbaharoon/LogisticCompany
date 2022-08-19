package constant;

public enum ParcelStatus {
	IN_TRANSIT("IN_TRANSIT"), DELIVERED("DELIVERED");

	private String parcelStatusName;

	ParcelStatus(String parcelStatusName) {
		this.parcelStatusName = parcelStatusName;
	}

	@Override
	public String toString() {
		return parcelStatusName;
	}
}
