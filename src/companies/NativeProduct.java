package companies;

//the root for creating the initial native products at each depot in each company
public class NativeProduct {

	private String name;
	private String productName;
	private String id;
	
	public NativeProduct(int counter, String id, String productName) {
		
		this.id = id;
		this.productName = productName;
		this.name = "NP" + counter;
	}
	

	public String getName() {
		return name;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "\n" + this.name + "[productName=" + productName + ", id=" + id + "]";
	}
}
