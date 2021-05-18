package companies;

//the class template for 2nd external products at a depot
public class ExternalProductII {

	private NativeProduct ep;
	
	public ExternalProductII(NativeProduct ep) {

		this.ep = ep;
	}

	public NativeProduct getEp() {
		return ep;
	}

	public void setEp(NativeProduct ep) {
		this.ep = ep;
	}
	
	@Override
	public String toString() {
		return "\n" + ep.getName() + "[productName=" + ep.getProductName() + ", id=" + ep.getId() + "]";
	}
	
}
