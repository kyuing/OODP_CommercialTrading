package companies;

import java.util.ArrayList;

import companyAbstract.CompanyAbstract;
import interfaces.Company;

//When a buyer company attempt to buy external products from seller companies (one at a time), 
//the seller company picked will an OBJ of this class so that they buyer company can reference it
public class exportedCompany extends CompanyAbstract {

    public exportedCompany(Company c) {
		super(c);
	}
    
	@Override
	public ArrayList<Depot> getDepot() {
		return c.getDepot();
	}

	@Override
	public String getProductInitial() {
		return c.getProductInitial();
	}

	@Override
	public void listTransactionForCompany() {}

	@Override
	public void wirteCompanyToFile() {}

}
