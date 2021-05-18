package interfaces;

import java.util.ArrayList;

import companies.Depot;

//interface Company
public interface Company {
    
    public ArrayList<Depot> getDepot();
    
    public String getProductInitial();
    
	public void listTransactionForCompany();
	
	public void wirteCompanyToFile();

}
