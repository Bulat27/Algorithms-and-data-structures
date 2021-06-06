package mojMiniHashingProjekat;

public class Automobile {
	
	private String manufacturer;
	private String model;
	private String idNumber;
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public Automobile(String manufacturer, String model, String idNumber) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.idNumber = idNumber;
	}
	@Override
	public String toString() {
		return " manufacturer: " + manufacturer + " model:" + model + " idNumber: " + idNumber;
	}
	
	
	
	
	
}
