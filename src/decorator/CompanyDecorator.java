package decorator;

import interfaces.Company;

//abstract class CompanyDecorator that implements Company
//the class exportedCompany will extend this class.
public abstract class CompanyDecorator implements Company {
    
	protected Company c;
	
    public CompanyDecorator(Company c){
        this.c = c;       
    }    
}
