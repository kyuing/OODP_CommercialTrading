package companies;

import java.util.ArrayList;
import decorator.CompanyDecorator;
import interfaces.Company;

//When a buyer company attempt to buy external products from seller companies (one at a time), 
//the seller company picked will an OBJ of this class so that they buyer company can reference it
//at runtime. 
public class exportedCompany extends CompanyDecorator {

    public exportedCompany(Company c) {
		super(c);
	}
    
    //no particular methods are added. just extend CompanyDecorator(in turn, inherit all from the interface Company)
    //Because the overridden method getDepot() below has full access to the exported(seller) company 
    //at the viewpoint of buyer company
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
