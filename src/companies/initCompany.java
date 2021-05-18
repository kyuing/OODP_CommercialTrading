package companies;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import interfaces.Company;
import print.IO;

//root of each of the companies
public class initCompany implements Company {

    PrintWriter out;
	private String name;
	private String productName;
    ArrayList<Depot> depot;

	public initCompany(String name, String productName, ArrayList<Depot> depot) {
    	
    	this.name = name;
    	this.productName = productName;
        this.depot = depot;
    }
    

	@Override
	public ArrayList<Depot> getDepot() {
		
		return this.depot;
	}
	
	@Override
	public String toString() {

		return name + "\n" + depot;
	}
	
	@Override
	public String getProductInitial() {
		return productName;
	}
	
	@Override
	public void listTransactionForCompany() {
		
		String cName = "";
		cName = this.name;
		
		String printOp = null;
		
		
		System.out.println(cName);
		
		for (int i = 0; i < this.depot.size(); i++) {
			
			if(i >= 25) {
				if(printOp != null) {
					printOp = IO.menu(IO.askUserForPrintingMore(), "^[y|Y|n|N]$");	
				}
				if(printOp != null && !printOp.equalsIgnoreCase("y")) {
					System.out.println("going back to menu..."); break;
					
				}else {					
					System.out.println(this.depot.get(i));
					printOp = null;		
				}
			}else {
				if(i < 25) {
					System.out.println(this.depot.get(i));
					printOp = null;												
				}

				printOp = "";		
			}
		}	
	}


	@Override
	public void wirteCompanyToFile() {

		try {
			out = new PrintWriter(new FileWriter(this.name + ".txt", false));	//overwrite
			out.println(this.name);
			out.println();
			for (Depot d : this.depot) {
				out.println(d);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error found");
		} finally {
			out.close();
			System.out.println("\n!!!!!! " + this.name + ".txt is created !!!!!!");
		}

	}

}
