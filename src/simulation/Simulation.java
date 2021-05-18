package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import companies.Depot;
import companies.ExternalProduct;
import companies.ExternalProductII;
import companies.NativeProduct;
import companies.exportedCompany;
import companies.initCompany;
import interfaces.Company;
import ramdomGenerator.RandomGenerator;

public class Simulation {

	RandomGenerator r;
	Company bigA;
	Company bigB;
	Company bigC;
	Company exported;
	ArrayList<String> npToDelete;
	
	public Simulation() {}
	
	public Company getBigA() {	return bigA;	}

	public Company getBigB() {	return bigB;	}

	public Company getBigC() {	return bigC;	}

	public void start() {
		
		//singleton randomGenerator
		r = RandomGenerator.getInstance();
		//r = new RandomGenerator();

		/**
		 * init each of companies.
		 * 
		 * The main purpose of this phase each is to create each of:
		 * - company
		 * - depot
		 *   - native product 
		 *   - external product 1
		 *   - external product 2
		 *
		 * 
		 * random number works will be done for native product in each company first
		 * and then for the external products (this will be explained later at the part where necessary)
		 * 
		 * based on the concept above, 
		 * initializing each of the company OBJs doesn't need to be in random, so we go in order only for this phase.
		 * since each obj will have its native product first with some random number works. 
		 * 
		 * 
		 * BTW, "n" == native
		 */
		bigA = new initCompany("BIG A", "A", depotGenerator("n", "A"));
		bigB = new initCompany("BIG B", "B", depotGenerator("n", "B"));
		bigC = new initCompany("BIG C", "C", depotGenerator("n", "C"));

		
		//start trading between companies.
		depotGenerator(null, null);
	}
	
	public ArrayList<Depot> depotGenerator(String type, String productName) {

		ArrayList<Depot> depot = null;

		// each company creates 50 depots and native products in each depot first.
		if (type != null && type.equals("n")) { // add native product to a depot
			int cash = 0;
			String depotID = "";
			depot = new ArrayList<>();
			for (int i = 0; i < 50; i++) {

				cash = r.getRandomNum("cash");
				depotID = productName + "D" + String.valueOf(i + 1);
				depot.add(new Depot(depotID, cash, r.getRandomNum("price"), r.getRandomNum("deliveryPrice"),
						nativeProductGenerator(productName)));
				int numOfNative = depot.get(depot.size() - 1).getNativeProduct().size();
				depot.get(depot.size() - 1).setNumOfNative(numOfNative);

			}
		} else { // this is where trading simulation starts.

			String pickCompany = "";
			String companyPicked = "";

			do {
				pickCompany = r.pickCompany();

				if (pickCompany.equals("A")) {

					r.shuffleOtherTwoCompanies("A");

					do {
						companyPicked = r.pickOtherTwoCompanies("A");

						if (companyPicked != null && companyPicked.equals("B")) {

							// init and shuffle 50 random external depots,
							// and refresh every time a company trades with one other.
							r.shuffleExternalDepots();

							// init deletion list which will be used
							// to delete the native product sold 
							npToDelete = new ArrayList<>();

							// this if clause may not need
							// since a stack that has 50 random depot 
							//-the same size as the for loop just below-
							// index nums will pop one num at a time.
							// just left it in case.
							if (!r.getPickRandomDepot().isEmpty() && r.getPickRandomDepot() != null) {
								for (int i = 0; i < bigA.getDepot().size(); i++) {
									// System.out.println(companyPicked);

									// set one of the external products
									bigA.getDepot().get(i).setExternalProduct(externalProductGenerator("A", i, bigB));
								}
							}

							// after finishing trading, update the seller depots at seller company
							deleteNPSold(bigB);
						}

						// do the same as above for the buyer company to set the other external product
						// from the seller company left.
						if (companyPicked != null && companyPicked.equals("C")) {
							r.shuffleExternalDepots();
							npToDelete = new ArrayList<>();
							if (!r.getPickRandomDepot().isEmpty() && r.getPickRandomDepot() != null) {
								for (int i = 0; i < bigA.getDepot().size(); i++) {
									// System.out.println(companyPicked);
									
									// set the other external products
									bigA.getDepot().get(i)
											.setExternalProductII(externalProductGeneratorII("A", i, bigC));
								}
							}
							deleteNPSold(bigC);
						}
					} while (!r.getA_pickOtherTwoCompanies().isEmpty());
				}

				// do the same as the if clause above looking at one buyer company and two
				// seller companies.
				if (pickCompany.equals("B")) {

					r.shuffleOtherTwoCompanies("B");

					do {
						companyPicked = r.pickOtherTwoCompanies("B");
						if (companyPicked != null && companyPicked.equals("A")) {
							r.shuffleExternalDepots();
							npToDelete = new ArrayList<>();
							if (!r.getPickRandomDepot().isEmpty() && r.getPickRandomDepot() != null) {
								for (int i = 0; i < bigB.getDepot().size(); i++) {
									// System.out.println(companyPicked);
									bigB.getDepot().get(i).setExternalProduct(externalProductGenerator("B", i, bigA));
								}
							}
							deleteNPSold(bigA);
						}
						if (companyPicked != null && companyPicked.equals("C")) {
							r.shuffleExternalDepots();
							npToDelete = new ArrayList<>();
							if (!r.getPickRandomDepot().isEmpty() && r.getPickRandomDepot() != null) {
								for (int i = 0; i < bigB.getDepot().size(); i++) {
									// System.out.println(companyPicked);
									bigB.getDepot().get(i)
											.setExternalProductII(externalProductGeneratorII("B", i, bigC));
								}
							}
							deleteNPSold(bigC);
						}
					} while (!r.getB_pickOtherTwoCompanies().isEmpty());

				}

				// do the same as the if clause above looking at one buyer company and two
				// seller companies.
				if (pickCompany.equals("C")) {

					r.shuffleOtherTwoCompanies("C");

					do {
						companyPicked = r.pickOtherTwoCompanies("C");
						if (companyPicked != null && companyPicked.equals("A")) {
							r.shuffleExternalDepots();
							npToDelete = new ArrayList<>();
							if (!r.getPickRandomDepot().isEmpty() && r.getPickRandomDepot() != null) {
								for (int i = 0; i < bigC.getDepot().size(); i++) {
									// System.out.println(companyPicked);
									bigC.getDepot().get(i).setExternalProduct(externalProductGenerator("C", i, bigA));
								}
							}
							deleteNPSold(bigA);

						}
						if (companyPicked != null && companyPicked.equals("B")) {
							r.shuffleExternalDepots();
							npToDelete = new ArrayList<>();
							if (!r.getPickRandomDepot().isEmpty() && r.getPickRandomDepot() != null) {
								for (int i = 0; i < bigC.getDepot().size(); i++) {
									// System.out.println(companyPicked);
									bigC.getDepot().get(i)
											.setExternalProductII(externalProductGeneratorII("C", i, bigB));
								}
							}
							deleteNPSold(bigB);
						}
					} while (!r.getC_pickOtherTwoCompanies().isEmpty());

				}

			} while (!r.getPickCompany().isEmpty());

		}

		return depot;
	}

	public void deleteNPSold(Company c) {

		String[] s;
		int index = 0;
		String idA, idB = "";
		
		for (int i = 0; i < npToDelete.size(); i++) {
		
			s = npToDelete.get(i).split(" ");
			index = Integer.parseInt(s[0]);
			idA = s[1];
			
			/**
			 * since 50 depots per a company are, npToDelete.size() is quite big.
			 * index value will be the same differing idA before moving to the next depot level value 
			 * which indicates the next depot and native products sold.      
			 * 
			 * That means, the nested for-loop doesn't go through all the depots.
			 * 
			 * It only loops through OBJs of getNativeProduct()
			 * which j should be max 50 per a depot.
			 * May Not be a super efficient looping solution but it seems it does its job.
			 */
			for (int j = 0; j < c.getDepot().get(index).getNativeProduct().size(); j++) {
				
				idB = c.getDepot().get(index).getNativeProduct().get(j).getId();
				if(idA.equals(idB)) {
					c.getDepot().get(index).getNativeProduct().remove(j);
				}
				
			}
			
		}
		
	}

	//generate native products for each depot in each company
	public ArrayList<NativeProduct> nativeProductGenerator(String productName) {

		ArrayList<NativeProduct> nativeProduct = new ArrayList<>();		
		int numOfNative = r.getRandomNum("numOfNative");

		for (int i = 0; i < numOfNative; i++) {

			nativeProduct.add(new NativeProduct(i+1, r.generate_Random_Hex_Bytes(), productName));
			//System.out.println(nativeProduct.get(i));
		}

		return nativeProduct;
	}

	public ArrayList<ExternalProduct> externalProductGenerator(String buyer, int buyerIndex, Company seller) {
		
		//export the seller company, init externalProduct and init shuffled
		exported = new exportedCompany(seller); 
		ArrayList<ExternalProduct> externalProduct = new ArrayList<>();
		List<?> shuffled = new ArrayList<>();	//to have exported products in random.
		
		//get cash of a buyer depot of a company as needed
		int cash = 0;
		if (buyer.equals("A")) {
			cash = bigA.getDepot().get(buyerIndex).getCash();
		}else if (buyer.equals("B")) {
			cash = bigB.getDepot().get(buyerIndex).getCash();
		}else {
			cash = bigC.getDepot().get(buyerIndex).getCash();
		}

		//take numOfExternalOrder in random
		int numOfExternalOrder = r.getRandomNum("numOfExternalOrder");  
		int p, delP = 0;
		
		//this is where a stack pops one num generated at r.shuffleExternalDepots();
		//in the method public ArrayList<Depot> depotGenerator(String type, String productName)
		int randomDepot = r.getRandomDepot();
		
		boolean isAbleToBuyAtleastOne = false;
		boolean isOkToSkip = false;
		
		//p == price  delP == delivery price of the random seller depot of a company
		p = exported.getDepot().get(randomDepot).getPrice();
		delP = exported.getDepot().get(randomDepot).getDeliveryPrice();
		
		/************************************************************************************************
		 // the following can work but it seldom happens a situation a buyer is able to order 
		 // based on sumOfExternalPrice
		  
		int sumOfExternalPrice = 0;
		sumOfExternalPrice = (p * numOfExternalOrder) + delP;
		sumOfExternalPrice = (exported.getDepot().get(randomDepot).getPrice() * numOfExternalOrder)
		+ exported.getDepot().get(randomDepot).getDeliveryPrice();
		*************************************************************************************************/		
		
		//clone the native products at the random seller depot of the seller company
		shuffled = (List<?>) exported.getDepot().get(randomDepot).getNativeProduct().clone();
		//shuffled = exported.getDepot().get(randomDepot).getNativeProduct();  //this shuffles the original one.
		Collections.shuffle(shuffled);	//shuffle it
		
		int showMeTheMoney = 0; //goes to the seller depot
		
			
		for (int i = 0; i < shuffled.size(); i++) {
	
			
			// can buy at least one (one product price + delivery price)
			if (externalProduct.size() < numOfExternalOrder && !isOkToSkip && cash >= p + delP) {
				isAbleToBuyAtleastOne = true;
				isOkToSkip = true;
				externalProduct.add(new ExternalProduct((NativeProduct) shuffled.get(i)));								
				
				//randomDepot == the original seller depot index
				//getId() = native obj id.
				String del = String.valueOf(randomDepot) + " "
						+ externalProduct.get(externalProduct.size() - 1).getEp().getId();
				npToDelete.add(del);
				//System.out.println(del);

				cash = cash - p - delP;
				showMeTheMoney = showMeTheMoney + p + delP;

				if (buyer.equals("A")) {
					bigA.getDepot().get(buyerIndex).setCash(cash);
				} else if (buyer.equals("B")) {
					bigB.getDepot().get(buyerIndex).setCash(cash);
				} else {
					bigC.getDepot().get(buyerIndex).setCash(cash);
				}

			// delivery price is already paid so you can buy more  
			// as far as externalProduct.size() < numOfExternalOrder && cash >= p
			} else if (externalProduct.size() < numOfExternalOrder && isAbleToBuyAtleastOne && cash >= p) {
				externalProduct.add(new ExternalProduct((NativeProduct) shuffled.get(i)));
				
				//randomDepot == the original seller depot index
				//getId() = native obj id.
				String del = String.valueOf(randomDepot) + " "
						+ externalProduct.get(externalProduct.size() - 1).getEp().getId();
				npToDelete.add(del);
				//System.out.println(del);

				cash = cash - p;
				showMeTheMoney = showMeTheMoney + p;

				if (buyer.equals("A")) {
					bigA.getDepot().get(buyerIndex).setCash(cash);
				} else if (buyer.equals("B")) {
					bigB.getDepot().get(buyerIndex).setCash(cash);
				} else {
					bigC.getDepot().get(buyerIndex).setCash(cash);
				}

			} else {
				
				/**************************************************************************************
				 * the following codes shows why trade stopped at particular point per a depot.
				 * but decided not to print and not to add them to company transaction logs.
				 * Because:
				 * - 1. valid successful transaction records are more important than failed transaction records. 
				 * - 2. the trade logic above does its trading jobs, 
				 *      filtering exceeded external orders and checking cash allowance state at run time
				 *      to have 1.

				if (externalProduct.size() >= numOfExternalOrder) {
					System.out.println("\nexternalProduct.size() " + externalProduct.size());
					System.out.println("numOfExternalOrder " + numOfExternalOrder);	
				}
						
				if(cash < p + delP) {
					System.out.println("\nCANNOT BUY THE EXTERNAL PRODUCT DUE TO LOW Cash allowance");
					System.out.println("Cash Allowance left: " + cash);
				}
					
					
				if (buyer.equals("A")) {
					System.out.println("\ncurrent buyer depot: " + bigA.getDepot().get(buyerIndex));
				}else if (buyer.equals("B")) {
					System.out.println("\ncurrent buyer depot: " + bigB.getDepot().get(buyerIndex));
				}else {
					System.out.println("\ncurrent buyer depot: " + bigC.getDepot().get(buyerIndex));
				}
				
				System.out.println("\ncurrent seller depot: " + externalProduct);
				System.out.println("\ncurrent seller depot: " + shuffled);
				System.out.println("\ncurrent seller depot: " + exported.getDepot().get(randomDepot));
				****************************************************************************************/

				break;

			}

		}
		
	
		if (buyer.equals("A")) {

			//update seller info at buyer
			bigA.getDepot().get(buyerIndex).setSellerI(String.valueOf(showMeTheMoney) + " "	//total paid to seller
			+ exported.getProductInitial() + " " 	//seller company e.g. A
			+ exported.getDepot().get(randomDepot).getId() + " "	//depot ID in the seller company
			+ String.valueOf(p) + " "	//price
			+ String.valueOf(delP) + " "	//delivery price
			+ String.valueOf(externalProduct.size()));	//# of external products	
			
			//update buyer info at seller
			exported.getDepot().get(randomDepot).setBuyer(String.valueOf(showMeTheMoney) + " "	//total paid by buyer
					+ bigA.getProductInitial() + " " 	//buyer company e.g. A
					+ bigA.getDepot().get(buyerIndex).getId() + " " //depot ID in the buyer company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()),	//# of external products
					externalProduct);	//native product sold.
					
			
		} else if (buyer.equals("B")) {

			//update seller info at buyer
			bigB.getDepot().get(buyerIndex).setSellerI(String.valueOf(showMeTheMoney) + " "	//total paid to seller
					+ exported.getProductInitial() + " " 	//seller company e.g. A
					+ exported.getDepot().get(randomDepot).getId() + " "	//depot ID in the seller company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()));	//# of external products	
			
			//update buyer info at seller
			exported.getDepot().get(randomDepot).setBuyer(String.valueOf(showMeTheMoney) + " "	//total paid by buyer
					+ bigB.getProductInitial() + " " 	//buyer company e.g. A
					+ bigB.getDepot().get(buyerIndex).getId() + " " //depot ID in the buyer company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()),	//# of external products
					externalProduct);	//native product sold.

		} else {

			//update seller info at buyer
			bigC.getDepot().get(buyerIndex).setSellerI(String.valueOf(showMeTheMoney) + " "	//total paid to seller
					+ exported.getProductInitial() + " " 	//seller company e.g. A
					+ exported.getDepot().get(randomDepot).getId() + " "	//depot ID in the seller company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()));	//# of external products	
			
			//update buyer info at seller
			exported.getDepot().get(randomDepot).setBuyer(String.valueOf(showMeTheMoney) + " "	//total paid by buyer
					+ bigC.getProductInitial() + " " 	//buyer company e.g. A
					+ bigC.getDepot().get(buyerIndex).getId() + " " //depot ID in the buyer company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()),	//# of external products
					externalProduct);	//native product sold.

		}

		
		//pay a sum of the product fees to the original seller depot of the seller company
		exported.getDepot().get(randomDepot).setCash(exported.getDepot().get(randomDepot).getCash() + showMeTheMoney);
		//seller.getDepot().get(randomDepot).setCash(seller.getDepot().get(randomDepot).getCash() + showMeTheMoney);
	
		return externalProduct;
	}
	
	//all works the same as the method 
	//public ArrayList<ExternalProduct> externalProductGenerator(String buyer, int buyerIndex, Company seller)
	//here, we look at ExternalProductII 
	public ArrayList<ExternalProductII> externalProductGeneratorII(String buyer, int buyerIndex, Company seller) {

		exported = new exportedCompany(seller);
		ArrayList<ExternalProductII> externalProduct = new ArrayList<>();
		List<?> shuffled = new ArrayList<>(); // to have exported products in random.

		int cash = 0;
		if (buyer.equals("A")) {
			cash = bigA.getDepot().get(buyerIndex).getCash();
		} else if (buyer.equals("B")) {
			cash = bigB.getDepot().get(buyerIndex).getCash();
		} else {
			cash = bigC.getDepot().get(buyerIndex).getCash();
		}

		int numOfExternalOrder = r.getRandomNum("numOfExternalOrder");
		int p, delP = 0;
		int randomDepot = r.getRandomDepot();

		boolean isAbleToBuyAtleastOne = false;
		boolean isOkToSkip = false;

		p = exported.getDepot().get(randomDepot).getPrice();
		delP = exported.getDepot().get(randomDepot).getDeliveryPrice();

		/************************************************************************************************
		 * // the following can work but it seldom happens a situation a buyer is able
		 * to order // based on sumOfExternalPrice
		 * 
		 * int sumOfExternalPrice = 0; sumOfExternalPrice = (p * numOfExternalOrder) +
		 * delP; sumOfExternalPrice = (exported.getDepot().get(randomDepot).getPrice() *
		 * numOfExternalOrder) +
		 * exported.getDepot().get(randomDepot).getDeliveryPrice();
		 *************************************************************************************************/

		shuffled = (List<?>) exported.getDepot().get(randomDepot).getNativeProduct().clone();
		// shuffled = exported.getDepot().get(randomDepot).getNativeProduct(); //this shuffles the original one.
		Collections.shuffle(shuffled);

		int showMeTheMoney = 0;

		for (int i = 0; i < shuffled.size(); i++) {

			// can buy at least one
			if (externalProduct.size() < numOfExternalOrder && !isOkToSkip && cash >= p + delP) {
				isAbleToBuyAtleastOne = true;
				isOkToSkip = true;
				externalProduct.add(new ExternalProductII((NativeProduct) shuffled.get(i)));

				// randomDepot == the original seller depot index
				// getId() = native obj id.
				String del = String.valueOf(randomDepot) + " "
						+ externalProduct.get(externalProduct.size() - 1).getEp().getId();
				npToDelete.add(del);
				//System.out.println(del);

				cash = cash - p - delP;
				showMeTheMoney = showMeTheMoney + p + delP;

				if (buyer.equals("A")) {
					bigA.getDepot().get(buyerIndex).setCash(cash);
				} else if (buyer.equals("B")) {
					bigB.getDepot().get(buyerIndex).setCash(cash);
				} else {
					bigC.getDepot().get(buyerIndex).setCash(cash);
				}

				// delivery price is already paid so you can buy one
				// as far as externalProduct.size() < numOfExternalOrder && cash >= p
			} else if (externalProduct.size() < numOfExternalOrder && isAbleToBuyAtleastOne && cash >= p) {
				externalProduct.add(new ExternalProductII((NativeProduct) shuffled.get(i)));

				// randomDepot == the original seller depot index
				// getId() = native obj id.
				String del = String.valueOf(randomDepot) + " "
						+ externalProduct.get(externalProduct.size() - 1).getEp().getId();
				npToDelete.add(del);
				//System.out.println(del);

				cash = cash - p;
				showMeTheMoney = showMeTheMoney + p;

				if (buyer.equals("A")) {
					bigA.getDepot().get(buyerIndex).setCash(cash);
				} else if (buyer.equals("B")) {
					bigB.getDepot().get(buyerIndex).setCash(cash);
				} else {
					bigC.getDepot().get(buyerIndex).setCash(cash);
				}

			} else {
				
				/**************************************************************************************
				 * the following codes shows why trade stopped at particular point per a depot.
				 * but decided not to print and not to add them to company transaction logs.
				 * Because:
				 * - 1. valid successful transaction records are more important than failed transaction records. 
				 * - 2. the trade logic above does its trading jobs, 
				 *      filtering exceeded external orders and checking cash allowance state at run time
				 *      to have 1.

				if (externalProduct.size() >= numOfExternalOrder) {
					System.out.println("\nexternalProduct.size() " + externalProduct.size());
					System.out.println("numOfExternalOrder " + numOfExternalOrder);	
				}
						
				if(cash < p + delP) {
					System.out.println("\nCANNOT BUY THE EXTERNAL PRODUCT DUE TO LOW Cash allowance");
					System.out.println("Cash Allowance left: " + cash);
				}
					
					
				if (buyer.equals("A")) {
					System.out.println("\ncurrent buyer depot: " + bigA.getDepot().get(buyerIndex));
				}else if (buyer.equals("B")) {
					System.out.println("\ncurrent buyer depot: " + bigB.getDepot().get(buyerIndex));
				}else {
					System.out.println("\ncurrent buyer depot: " + bigC.getDepot().get(buyerIndex));
				}
				
				System.out.println("\ncurrent seller depot: " + externalProduct);
				System.out.println("\ncurrent seller depot: " + shuffled);
				System.out.println("\ncurrent seller depot: " + exported.getDepot().get(randomDepot));
				****************************************************************************************/


				break;

			}

		}

		if (buyer.equals("A")) {

			//update seller info at buyer
			bigA.getDepot().get(buyerIndex).setSellerII(String.valueOf(showMeTheMoney) + " "	//total paid to seller
			+ exported.getProductInitial() + " " 	//seller company e.g. A
			+ exported.getDepot().get(randomDepot).getId() + " "	//depot ID in the seller company
			+ String.valueOf(p) + " "	//price
			+ String.valueOf(delP) + " "	//delivery price
			+ String.valueOf(externalProduct.size()));	//# of external products	
			
			//update buyer info at seller
			exported.getDepot().get(randomDepot).setBuyer2(String.valueOf(showMeTheMoney) + " "	//total paid by buyer
					+ bigA.getProductInitial() + " " 	//buyer company e.g. A
					+ bigA.getDepot().get(buyerIndex).getId() + " " //depot ID in the buyer company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()),	//# of external products
					externalProduct);	//native product sold.			
			
		} else if (buyer.equals("B")) {

			//update seller info at buyer
			bigB.getDepot().get(buyerIndex).setSellerII(String.valueOf(showMeTheMoney) + " "	//total paid to seller
					+ exported.getProductInitial() + " " 	//seller company e.g. A
					+ exported.getDepot().get(randomDepot).getId() + " "	//depot ID in the seller company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()));	//# of external products
			
			//update buyer info at seller
			exported.getDepot().get(randomDepot).setBuyer2(String.valueOf(showMeTheMoney) + " "	//total paid by buyer
					+ bigB.getProductInitial() + " " 	//buyer company e.g. A
					+ bigB.getDepot().get(buyerIndex).getId() + " " //depot ID in the buyer company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()),	//# of external products
					externalProduct);	//native product sold.
					
		} else {

			//update seller info at buyer
			bigC.getDepot().get(buyerIndex).setSellerII(String.valueOf(showMeTheMoney) + " "	//total paid to seller
					+ exported.getProductInitial() + " " 	//seller company e.g. A
					+ exported.getDepot().get(randomDepot).getId() + " "	//depot ID in the seller company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()));	//# of external products	
			
			//update buyer info at seller
			exported.getDepot().get(randomDepot).setBuyer2(String.valueOf(showMeTheMoney) + " "	//total paid by buyer
					+ bigC.getProductInitial() + " " 	//buyer company e.g. A
					+ bigC.getDepot().get(buyerIndex).getId() + " " //depot ID in the buyer company
					+ String.valueOf(p) + " "	//price
					+ String.valueOf(delP) + " "	//delivery price
					+ String.valueOf(externalProduct.size()),	//# of external products
					externalProduct);	//native product sold.
					
		}

		
		// pay a sum of the product fees to the original seller depot
		exported.getDepot().get(randomDepot).setCash(exported.getDepot().get(randomDepot).getCash() + showMeTheMoney);
		//seller.getDepot().get(randomDepot).setCash(seller.getDepot().get(randomDepot).getCash() + showMeTheMoney);

		return externalProduct;
	}
}
