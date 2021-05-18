package print;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//class that does printing IO jobs
public final class IO {
	
	static Scanner sc;
	private static Pattern p = null;
    private static Matcher m = null;

    public static String printUnderLine() {
    	return "_________________________________________________________________________________________";
    }
    
    public static String printHyphen() {
    	return "-----------------------------------------------------------------------------------------";
    }
    
	public static String printMenu() {
		return "___________________________________________________________________________"
				+ "\n[MAIN MENU: Please select menu]"
				+ "\n1: Print all transactions for companies"
				+ "\n   This option has large records and will print transactions for all companies."
				+ "\n   Each printing will have a 2 seconds term."
				+ "\n   To see all the detail, please find \'All companies.txt\',"
				+ "\n   which will automatically be saved.\n\n"
				+ "\n2: Print all transactions for a company"
				+ "\n\n3: Close program";
	}
	
	public static String printMenuForACompany() {
		return "___________________________________________________________________________"
				+ "\n[Please select an option to see all transactions for a company]"
				+ "\n1: Big A"
				+ "\n2: Big B"
				+ "\n3: Big C"
				+ "\n4: go back to the menu";
	}
	

	public static String askUserForPrintingMore() {
		return "___________________________________________________________________________"
				+ "\nWould you like to print the remaining rows?(y/n)"
				+ "\n\ny: print"
				+ "\nn: go back to menu";
	}
	
	/**
	 * method to prompt menu and return a valid input user enters
	 * @param prompt
	 * @param regx
	 * @return valid input
	 */
	public static String menu(String prompt, String regx) {
		
		boolean isValid = false;
		String input = "";
		sc = new Scanner(System.in);
		
		do {	
			System.out.println(prompt);
			try {
				input = sc.nextLine();
				if(matchesInput(input, regx)) {
					isValid = true;
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Try again");
				isValid = false;
			}
		} while (!isValid);
		
		return input;
	}

	/**
	 * method to validate user's input
	 * @param input
	 * @param regx
	 * @return true if @param input matches with @param regx defined
	 */
	private static boolean matchesInput(String input, String regx) {
		p = Pattern.compile(regx);
		m = p.matcher(input);
		return m.find();
	}
}
