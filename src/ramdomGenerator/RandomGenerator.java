package ramdomGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class RandomGenerator {

	private static RandomGenerator INSTANCE;
	Stack<String> pickCompany;
	Stack<String> A_pickOtherTwoCompanies;
	Stack<String> B_pickOtherTwoCompanies;
	Stack<String> C_pickOtherTwoCompanies;
	Stack<Integer> pickRandomDepot;
	
	//singleton
	public static RandomGenerator getInstance() {
		
		if (INSTANCE == null) {  
			INSTANCE = new RandomGenerator();  
        }  
		return INSTANCE;
	}
	
	public RandomGenerator() {
		
		shuffleCompanies();
				
	}
	
	public void shuffleOtherTwoCompanies(String buyer) {
		
		if(buyer.equals("A")) {
		
			ArrayList<String> l = new ArrayList<String>();
			l.add("B");
			l.add("C");
			
			Collections.shuffle(l);
			//System.out.println("shuffled other companies: " + l);
			
			A_pickOtherTwoCompanies = new Stack<>();
			A_pickOtherTwoCompanies.add(l.get(0));
			A_pickOtherTwoCompanies.add(l.get(1));
		}
		
		if(buyer.equals("B")) {
			
			ArrayList<String> l = new ArrayList<String>();
			l.add("A");
			l.add("C");
			
			Collections.shuffle(l);
			//System.out.println("shuffled other companies: " + l);
			
			B_pickOtherTwoCompanies = new Stack<>();
			B_pickOtherTwoCompanies.add(l.get(0));
			B_pickOtherTwoCompanies.add(l.get(1));
		}
		
		if (buyer.equals("C")) {

			ArrayList<String> l = new ArrayList<String>();
			l.add("A");
			l.add("B");

			Collections.shuffle(l);
			//System.out.println("shuffled other companies: " + l);

			C_pickOtherTwoCompanies = new Stack<>();
			C_pickOtherTwoCompanies.add(l.get(0));
			C_pickOtherTwoCompanies.add(l.get(1));
		}

	}
	
	public String pickOtherTwoCompanies(String buyer) {
		
		
		if(buyer.equals("A")) {

			if(!A_pickOtherTwoCompanies.isEmpty()) {
				//System.out.println("STACK pickOneCompanyForTrading: " + A_pickOtherTwoCompanies);
				return A_pickOtherTwoCompanies.pop();				
			}

		}
		
		if (buyer.equals("B")) {
			if(!B_pickOtherTwoCompanies.isEmpty()) {
				//System.out.println("STACK pickOneCompanyForTrading: " + B_pickOtherTwoCompanies);
				return B_pickOtherTwoCompanies.pop();				
			}
		}

		if (buyer.equals("C")) {
			if(!C_pickOtherTwoCompanies.isEmpty()) {
				//System.out.println("STACK pickOneCompanyForTrading: " + C_pickOtherTwoCompanies);
				return C_pickOtherTwoCompanies.pop();	
			}
		}
		
		return null;

	}
	
	//https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
	public void shuffleCompanies() {
		
		ArrayList<String> l = new ArrayList<String>();
		l.add("A");
		l.add("B");
		l.add("C");
		Collections.shuffle(l);
		//System.out.println("shuffled companies: " + l);
		
		pickCompany = new Stack<>();
		pickCompany.add(l.get(0));
		pickCompany.add(l.get(1));
		pickCompany.add(l.get(2));
		
	}
	
	public String pickCompany() {
			
		//System.out.println("STACK pickCompany: " + pickCompany);
		return pickCompany.pop();

	}
	
	public void shuffleExternalDepots() {
		
		
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			l.add(i);
		}
		Collections.shuffle(l);
		//System.out.println(l);
		
		pickRandomDepot = new Stack<>();
		for (int i = 0; i < l.size(); i++) {
			pickRandomDepot.push(l.get(i));
		}
		
		//System.out.println(pickRandomDepot);
		
	}
	
	public int getRandomDepot() {
		
		return pickRandomDepot.pop();
	}


	public Stack<String> getPickCompany() {
		return pickCompany;
	}
	
	public Stack<Integer> getPickRandomDepot() {
		return pickRandomDepot;
	}

	public Stack<String> getA_pickOtherTwoCompanies() {
		return A_pickOtherTwoCompanies;
	}

	public Stack<String> getB_pickOtherTwoCompanies() {
		return B_pickOtherTwoCompanies;
	}

	public Stack<String> getC_pickOtherTwoCompanies() {
		return C_pickOtherTwoCompanies;
	}

	public int getRandomNum(String option) {
		
		int num = 0;
		//num = getRandomInteger(10,1);
		
		switch (option) {
		
			case "price":
				num = getRandomInteger(10,1);
				break;
				
			case "deliveryPrice":
				num = getRandomInteger(10,1);
				break;
				
			case "cash":
				num = getRandomInteger(100,50);
				break;
				
			case "numOfNative":
				num = getRandomInteger(50,15);
				break;
				
			case "numOfExternalOrder":
				num = getRandomInteger(40,3);
				break;

		}
		
		return num;
	}
	
	/* ref: https://www.java67.com/2015/01/how-to-get-random-number-between-0-and-1-java.html
	 * returns random integer between minimum and maximum range 
	 */ 
	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}
	
	//https://www.sanfoundry.com/java-program-generate-random-hexadecimal-bytes/
	public String generate_Random_Hex_Bytes() {
		
		Random random = new Random();
        int val = random.nextInt();
        //System.out.println(val);
        String hex = new String();
        hex = Integer.toHexString(val);
        //System.out.println(hex + "\n");
        
        return hex; 
	}

}
