package companyAbstract;

import interfaces.Company;

//abstract class CompanyDecorator that implements Company
//the class exportedCompany will extend this class.
public abstract class CompanyAbstract implements Company {
    
	protected Company c;
	
    public CompanyAbstract(Company c){
        this.c = c;       
    }    
}
