package companies;

import java.util.ArrayList;

import print.IO;

//the root for all trades and all transaction records at each depot in each company
public class Depot {

	private String id;
	private int initialCash;
	private int cash; 
	private int price;
	private int deliveryPrice;
	private int numOfNative;
	
	private String sellerI;
	private String totalPaidToSeller;
	private String sellerInitial;
	private String sellerDepot;
	private String sellerDepotNpPrice;
	private String sellerDepotNpDeliveryPrice;
	private String numOfNpBoughtFromSellerDepot;

	private String sellerII;
	private String totalPaidToSellerII;
	private String sellerInitialII;
	private String sellerDepotII;
	private String sellerDepotNpPriceII;
	private String sellerDepotNpDeliveryPriceII;
	private String numOfNpBoughtFromSellerDepotII;
	
	private Buyer buyer;
	private Buyer2 buyer2;
	private ArrayList<Buyer> buyers;
	private ArrayList<Buyer2> buyers2;
	
	private ArrayList<NativeProduct> nativeProduct = null;
	private ArrayList<ExternalProduct> externalProduct = null;
	private ArrayList<ExternalProductII> externalProductII = null;
	
	public Depot(String id, int cash, int price, int deliveryPrice, ArrayList<NativeProduct> nativeProduct) {
		
		this.id = id;
		this.cash = cash;
		this.initialCash = cash;
		this.price = price;
		this.deliveryPrice = deliveryPrice;
		this.nativeProduct = nativeProduct;		
	}
	
	
	public int getNumOfNative() {
		return numOfNative;
	}

	public void setNumOfNative(int numOfNative) {
		this.numOfNative = numOfNative;
	}

	public Buyer getBuyer() {
		return this.buyer;
	}
	
	public Buyer2 getBuyer2() {
		return this.buyer2;
	}

	public void setBuyer(String buyerInfo, ArrayList<ExternalProduct> npSold) {
		
		if (this.buyers == null) {
			this.buyers = new ArrayList<>();
		}
		
		String[] s = buyerInfo.split(" ");
		
		//builder
		if (npSold != null) {
			if (!npSold.isEmpty()) {
				this.buyer = new Buyer.BuyerBuilder(s[0], s[1], s[2], s[3], s[4], s[5]).setSellerDepotID(this.id).setNpSold(npSold).build();
				this.buyers.add(this.buyer);
			}
	
		}

		/*************************************************************************************
		if (npSold != null) {
			if (!npSold.isEmpty()) {
				this.buyer = new Buyer(s[0], s[1], s[2], s[3], s[4], s[5], this.id, npSold);
				this.buyers.add(this.buyer);
			}
	
		}
		**************************************************************************************/
		
	}
	

	public void setBuyer2(String buyerInfo, ArrayList<ExternalProductII> npSold2) {
		
		if (this.buyers2 == null) {
			this.buyers2 = new ArrayList<>();
		}
		
		String[] s = buyerInfo.split(" ");
		
		if (npSold2 != null) {
			if (!npSold2.isEmpty()) {
				this.buyer2 = new Buyer2(s[0], s[1], s[2], s[3], s[4], s[5], this.id, npSold2);
				this.buyers2.add(this.buyer2);
			}
		}
	}

	public String getSellerI() {
		return sellerI;
	}

	public void setSellerI(String sellerI) {
		
		this.sellerI = sellerI;

		String[] s = this.sellerI.split(" ");
		this.totalPaidToSeller = s[0];
		this.sellerInitial = s[1];
		this.sellerDepot = s[2];
		this.sellerDepotNpPrice = s[3];
		this.sellerDepotNpDeliveryPrice = s[4];
		this.numOfNpBoughtFromSellerDepot = s[5];

	}

	public String getSellerII() {
		return sellerII;
	}
	
	public void setSellerII(String sellerII) {
		this.sellerII = sellerII;
		String[] s = this.sellerII.split(" ");
		this.totalPaidToSellerII = s[0];
		this.sellerInitialII = s[1];
		this.sellerDepotII = s[2];
		this.sellerDepotNpPriceII = s[3];
		this.sellerDepotNpDeliveryPriceII = s[4];
		this.numOfNpBoughtFromSellerDepotII = s[5];
	}
	
	public String getId() {
		return id;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}


	public ArrayList<NativeProduct> getNativeProduct() {
		return nativeProduct;
	}


	public void setNativeProduct(ArrayList<NativeProduct> nativeProduct) {
		this.nativeProduct = nativeProduct;
	}


	public ArrayList<ExternalProduct> getExternalProduct() {
		return externalProduct;
	}


	public void setExternalProduct(ArrayList<ExternalProduct> externalProduct) {
		this.externalProduct = externalProduct;
	}
	
	public ArrayList<ExternalProductII> getExternalProductII() {
		return externalProductII;
	}


	public void setExternalProductII(ArrayList<ExternalProductII> externalProduct) {
		this.externalProductII = externalProduct;
	}

	//print all transaction records based on a buyer depot
	@Override
	public String toString() {
		
		String dash = IO.printHyphen();
		String priceInfo = "\n\n<The default native products price info at THIS depot>";
		String sellerInfo = "",transactionOut = "",sellerInfoII = "",transactionOutII = ""; 
		
		if(this.totalPaidToSeller.equals("0") || this.numOfNpBoughtFromSellerDepot.equals("0")) {
			sellerInfo = "\n\n<THIS depot didn't buy any external products from the company " + this.sellerInitial + ">";

		}else {
			sellerInfo = "\n\n<THIS depot bought the following external product(s) from the company " + this.sellerInitial + ">";	
		}
		
		transactionOut = "\nSeller Depot: " + this.sellerDepot + ", Total paid: " + this.totalPaidToSeller + " (x * y + z)"
				+ "\n\nThe native product info at the seller depot " + this.sellerDepot
				+ "\nx -> native product price: " + this.sellerDepotNpPrice
				+ "\ny -> # of native product sold: " + this.numOfNpBoughtFromSellerDepot
				+ "\nz -> native product delivery price: " + this.sellerDepotNpDeliveryPrice;
		
		
		if(this.totalPaidToSellerII.equals("0") || this.numOfNpBoughtFromSellerDepotII.equals("0")) {
			sellerInfoII = "\n\nTHIS depot didn't buy any external products from the company " + this.sellerInitialII + ">";

		}else {
			sellerInfoII = "\n\n<THIS depot bought the following external product(s) from the company " + this.sellerInitialII + ">";	
		}
		
		//sellerInfoII = "\n\n<THIS depot bought the following external product(s) from the company " + this.sellerInitialII + ">";
		transactionOutII = "\nSeller Depot: " + this.sellerDepotII + ", Total paid: " + this.totalPaidToSellerII + " (x * y + z)"
				+ "\n\nThe native product info at the seller depot " + this.sellerDepotII
				+ "\nx -> native product price: " + this.sellerDepotNpPriceII
				+ "\ny -> # of native product sold: " + this.numOfNpBoughtFromSellerDepotII
				+ "\nz -> native product delivery price: " + this.sellerDepotNpDeliveryPriceII;
		
		String toReturn = "";
		
		if(this.buyers != null && this.buyers2 != null) {
			
			toReturn = "\n\n" + dash + "\n" + 
					"depotID=" + id + ", intialCash=" + initialCash + ", currentCash=" + cash + ", initial # of native products=" + this.numOfNative +  
					priceInfo + "\nprice=" + price + ", deliveryPrice="
			+ deliveryPrice + "\n\nnativeProduct left=" + nativeProduct
			+ this.buyers + this.buyers2
			+ sellerInfo + transactionOut + "\n\nexternalProduct=" + externalProduct 
			+ sellerInfoII + transactionOutII + "\n\nexternalProductII=" + externalProductII + "";
			
		}else if(this.buyers != null && this.buyers2 == null) {
			toReturn = "\n\n" + dash + "\n" + 
					"depotID=" + id + ", intialCash=" + initialCash + ", currentCash=" + cash + ", initial # of native products=" + this.numOfNative + 
					priceInfo + "\nprice=" + price + ", deliveryPrice="
			+ deliveryPrice + "\n\nnativeProduct left=" + nativeProduct
			+ this.buyers
			+ sellerInfo + transactionOut + "\n\nexternalProduct=" + externalProduct 
			+ sellerInfoII + transactionOutII + "\n\nexternalProductII=" + externalProductII + "";
		}else if(this.buyers2 != null && this.buyers == null) {
			toReturn = "\n\n" + dash + "\n" + 
					"depotID=" + id + ", intialCash=" + initialCash + ", currentCash=" + cash + ", initial # of native products=" + this.numOfNative +
					priceInfo + "\nprice=" + price + ", deliveryPrice="
			+ deliveryPrice + "\n\nnativeProduct left=" + nativeProduct
			+ this.buyers2
			+ sellerInfo + transactionOut + "\n\nexternalProduct=" + externalProduct 
			+ sellerInfoII + transactionOutII + "\n\nexternalProductII=" + externalProductII + "";
		}
		
		return toReturn;

	}
	
}
