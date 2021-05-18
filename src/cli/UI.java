package cli;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import companies.Depot;
import print.IO;
import simulation.Simulation;

//Client class
public class UI {

	Simulation s;

	public UI() {
		
		init();
		menu();
	}
	
	private void init() {
		s = new Simulation();
		s.start();
	}
	
	private void menu() {

		String op = IO.menu(IO.printMenu(), "^[1|2|3]$");

		switch (op) {
			case "1":
				printAllTransactions();
				menu();
	
			case "2":
				printAllTransactionsForACompany();
				menu();
	
			case "3":
				System.out.println(IO.printUnderLine() + "\nBYE :)");
				System.exit(0);
	
			default:
				menu();
		}
	}

	private void printAllTransactionsForACompany() {
		
		String op = IO.menu(IO.printMenuForACompany(), "^[1|2|3|4]$");

		switch (op) {
			case "1":
				s.getBigA().listTransactionForCompany();
				s.getBigA().wirteCompanyToFile();
				menu();
	
			case "2":
				s.getBigB().listTransactionForCompany();
				s.getBigB().wirteCompanyToFile();
				menu();
	
			case "3":
				s.getBigC().listTransactionForCompany();
				s.getBigC().wirteCompanyToFile();
				menu();
				
			case "4":
				System.out.println("Going back to the menu...");
				menu();
	
			default:
				menu();
		}

	}

	private void printAllTransactions() {

		
		s.getBigA().listTransactionForCompany();
		System.out.println("\n!!! Waiting for 2 sec to output Big B..");
		try { Thread.sleep(2000); } catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		s.getBigB().listTransactionForCompany();
		System.out.println("\n!!! Waiting for 2 sec to output Big C.."); 
		try { Thread.sleep(2000); } catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		s.getBigC().listTransactionForCompany();
		
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(new FileWriter("All companies" + ".txt", false));	//overwrite
			out.println("All companies");
			out.println();
			
			out.println(IO.printUnderLine());
			out.println("Big A");
			for (Depot d : s.getBigA().getDepot()) {
				out.println(d);
			}
			
			out.println(IO.printUnderLine());
			out.println("Big B");
			for (Depot d : s.getBigB().getDepot()) {
				out.println(d);
			}
			
			out.println(IO.printUnderLine());
			out.println("Big C");
			for (Depot d : s.getBigC().getDepot()) {
				out.println(d);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error found");
		} finally {
			out.close();
			System.out.println("\n!!!!!! All companies.txt is created !!!!!!");
		}
		
	}

}
