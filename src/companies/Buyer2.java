package companies;

import java.util.ArrayList;

//Buyer2's transaction info tracked when native products at a depot are sold.
public class Buyer2 {

	private String totalPaidByBuyer;
	private String buyerInitial;
	private String buyerDepot;	
	private String sellerDepotNpPrice;
	private String sellerDepotNpDeliveryPrice;
	private String numOfNpSoldFromSellerDepot;
	private String sellerDepotID;
	private ArrayList<ExternalProductII> npSold;

	public Buyer2(String totalPaidByBuyer, String buyerInitial, String buyerDepot,
			String sellerDepotNpPrice, String sellerDepotNpDeliveryPrice, String numOfNpSoldFromSellerDepot, String sellerDepotID,
			ArrayList<ExternalProductII> npSold) {
		
		this.totalPaidByBuyer = totalPaidByBuyer;
		this.buyerInitial = buyerInitial;
		this.buyerDepot = buyerDepot;
		this.sellerDepotNpPrice = sellerDepotNpPrice;
		this.sellerDepotNpDeliveryPrice = sellerDepotNpDeliveryPrice;
		this.numOfNpSoldFromSellerDepot = numOfNpSoldFromSellerDepot;
		this.sellerDepotID = sellerDepotID;
		this.npSold = npSold;		
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

	public ArrayList<ExternalProductII> getNpSold() {
		return npSold;
	}

	@Override
	public String toString() {
		
		String npSoldToWhere = "";
		String transactionIn = "";
		String addInfo = "";
		String toReturn = "";

		
		if (this.npSold != null) {
			
			npSoldToWhere = "\n\n<The following native products at THIS depot have been sold to the company " + this.buyerInitial + ">";
			transactionIn = "\nBuyer Depot: " + this.buyerDepot + ", Total earned: " + this.totalPaidByBuyer + " (x * y + z)";
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
	
}
