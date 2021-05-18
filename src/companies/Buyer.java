package companies;

import java.util.ArrayList;

//Buyer's transaction info tracked when native products at a depot are sold.
public class Buyer {

	private String totalPaidByBuyer;
	private String buyerInitial;
	private String buyerDepot;	
	private String sellerDepotNpPrice;
	private String sellerDepotNpDeliveryPrice;
	private String numOfNpSoldFromSellerDepot;
	private String sellerDepotID;
	private ArrayList<ExternalProduct> npSold;

	public Buyer(String totalPaidByBuyer, String buyerInitial, String buyerDepot,
			String sellerDepotNpPrice, String sellerDepotNpDeliveryPrice, String numOfNpSoldFromSellerDepot, String sellerDepotID,
			ArrayList<ExternalProduct> npSold) {
		
		this.totalPaidByBuyer = totalPaidByBuyer;
		this.buyerInitial = buyerInitial;
		this.buyerDepot = buyerDepot;
		this.sellerDepotNpPrice = sellerDepotNpPrice;
		this.sellerDepotNpDeliveryPrice = sellerDepotNpDeliveryPrice;
		this.numOfNpSoldFromSellerDepot = numOfNpSoldFromSellerDepot;
		this.sellerDepotID = sellerDepotID;
		this.npSold = npSold;		
	}
	
	//in use
	public Buyer(BuyerBuilder buyerBuilder) {
		this.totalPaidByBuyer = buyerBuilder.totalPaidByBuyer;
		this.buyerInitial = buyerBuilder.buyerInitial;
		this.buyerDepot = buyerBuilder.buyerDepot;
		this.sellerDepotNpPrice = buyerBuilder.sellerDepotNpPrice;
		this.sellerDepotNpDeliveryPrice = buyerBuilder.sellerDepotNpDeliveryPrice;
		this.numOfNpSoldFromSellerDepot = buyerBuilder.numOfNpSoldFromSellerDepot;
		this.sellerDepotID = buyerBuilder.sellerDepotID;
		this.npSold = buyerBuilder.npSold;
	}

	public String getSellerDepotID() {
		return sellerDepotID;
	}
	
	public String getTotalPaidByBuyer() {
		return totalPaidByBuyer;
	}

	public String getBuyerInitial() {
		return buyerInitial;
	}

	public String getBuyerDepot() {
		return buyerDepot;
	}
	
	public String getSellerDepotNpPrice() {
		return sellerDepotNpPrice;
	}

	public String getSellerDepotNpDeliveryPrice() {
		return sellerDepotNpDeliveryPrice;
	}

	public String getNumOfNpSoldFromSellerDepot() {
		return numOfNpSoldFromSellerDepot;
	}

	public ArrayList<ExternalProduct> getNpSold() {
		return npSold;
	}

	@Override
	public String toString() {
		
		String npSoldToWhere = "";
		String transactionIn = "";
		String addInfo = "";
		String toReturn = "";
		
		if (this.npSold != null ) {
			
			npSoldToWhere = "\n\n<The following native products at THIS depot have been sold to the company " + this.buyerInitial + ">";
			transactionIn = "\nBuyer Depot: " + this.buyerDepot + ", Total earned from the buyer depot: " + this.totalPaidByBuyer + " (x * y + z)";
			addInfo = "\n\n!! NOTE: Please refer to THIS depot info above or see the below !!" 
					+ "\nThe native products info at THIS depot \"" + this.sellerDepotID + "\""
					+ "\nx -> native products price: " + this.sellerDepotNpPrice
					+ "\ny -> # of native products sold to the buyer depot " + this.buyerDepot + ": " + this.numOfNpSoldFromSellerDepot
					+ "\nz -> native products delivery price: " + this.sellerDepotNpDeliveryPrice;
			
			toReturn = npSoldToWhere + transactionIn + addInfo + "\n\nnativeProduct sold=" + this.npSold;
			
		}else {
			toReturn = "none";
		}
		

		return toReturn;

	}
	
	
	
	public static class BuyerBuilder {

		private String totalPaidByBuyer;
		private String buyerInitial;
		private String buyerDepot;	
		private String sellerDepotNpPrice;
		private String sellerDepotNpDeliveryPrice;
		private String numOfNpSoldFromSellerDepot;
		private String sellerDepotID;
		private ArrayList<ExternalProduct> npSold;

		public BuyerBuilder(String totalPaidByBuyer, String buyerInitial, String buyerDepot,
				String sellerDepotNpPrice, String sellerDepotNpDeliveryPrice, String numOfNpSoldFromSellerDepot/*, String sellerDepotID,
				ArrayList<ExternalProduct> npSold*/) {
			
			this.totalPaidByBuyer = totalPaidByBuyer;
			this.buyerInitial = buyerInitial;
			this.buyerDepot = buyerDepot;
			this.sellerDepotNpPrice = sellerDepotNpPrice;
			this.sellerDepotNpDeliveryPrice = sellerDepotNpDeliveryPrice;
			this.numOfNpSoldFromSellerDepot = numOfNpSoldFromSellerDepot;
			
			this.sellerDepotID = "";
			this.npSold = null;		
		}
				
		
		public String getTotalPaidByBuyer() {
			return totalPaidByBuyer;
		}

		public BuyerBuilder setTotalPaidByBuyer(String totalPaidByBuyer) {
			this.totalPaidByBuyer = totalPaidByBuyer;
			return this;
		}

		public String getBuyerInitial() {
			return buyerInitial;
		}

		public BuyerBuilder setBuyerInitial(String buyerInitial) {
			this.buyerInitial = buyerInitial;
			return this;
		}

		public String getBuyerDepot() {
			return buyerDepot;
		}

		public BuyerBuilder setBuyerDepot(String buyerDepot) {
			this.buyerDepot = buyerDepot;
			return this;
		}

		public String getSellerDepotNpPrice() {
			return sellerDepotNpPrice;
		}

		public BuyerBuilder setSellerDepotNpPrice(String sellerDepotNpPrice) {
			this.sellerDepotNpPrice = sellerDepotNpPrice;
			return this;
		}

		public String getSellerDepotNpDeliveryPrice() {
			return sellerDepotNpDeliveryPrice;
		}

		public BuyerBuilder setSellerDepotNpDeliveryPrice(String sellerDepotNpDeliveryPrice) {
			this.sellerDepotNpDeliveryPrice = sellerDepotNpDeliveryPrice;
			return this;
		}

		public String getNumOfNpSoldFromSellerDepot() {
			return numOfNpSoldFromSellerDepot;
		}

		public BuyerBuilder setNumOfNpSoldFromSellerDepot(String numOfNpSoldFromSellerDepot) {
			this.numOfNpSoldFromSellerDepot = numOfNpSoldFromSellerDepot;
			return this;
		}

		public String getSellerDepotID() {
			return sellerDepotID;
		}

		public BuyerBuilder setSellerDepotID(String sellerDepotID) {
			this.sellerDepotID = sellerDepotID;
			return this;
		}

		public ArrayList<ExternalProduct> getNpSold() {
			return npSold;
		}

		public BuyerBuilder setNpSold(ArrayList<ExternalProduct> npSold) {
			this.npSold = npSold;
			return this;
		}

		public Buyer build() {
			return new Buyer(this);
		}
	
	}
	
}
