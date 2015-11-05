package billionCompanies;

public class CompanyInfo {
    private String companyName;
    private String companySymbol;
    private float companyMarketCap;
    private String companyLocation;

    public CompanyInfo(String name, String symbol, float marketcap, String location) {
        this.companyName = name;
        this.companySymbol = symbol; 
        this.companyMarketCap = marketcap; 
        this.companyLocation = location; 
    } 
    
    public CompanyInfo(CompanyInfo info) {
        this.companyName = info.getCompanyName();
        this.companySymbol = info.getCompanySymbol(); 
        this.companyMarketCap = info.getCompanyMarketCap(); 
        this.companyLocation = info.getCompanyLocation(); 	
	}

	// companyName
    public String getCompanyName() {
        return this.companyName; 
    }
    
    public void setCompanyName(String name) {
        this.companyName = name; 
    }
    
    // companySymbol 
    public String getCompanySymbol() {
        return this.companySymbol; 
    }
    
    public void setCompanySymbol(String symbol) {
        this.companySymbol = symbol; 
    }
    
    // companyMarketCap 
    public float getCompanyMarketCap() {
        return this.companyMarketCap; 
    }
    
    public void setCompanyMarketCap(float marketcap) {
        this.companyMarketCap = marketcap; 
    }
    
    // companyLocation
    public String getCompanyLocation() {
        return this.companyLocation; 
    } 
    
    public void setCompanyLocation(String location) {
        this.companyLocation = location; 
    } 

    // output company information
    public void outputCompanyInfo() {
        System.out.println("Name       : " + this.companyName); 
        System.out.println("Symbol     : " + this.companySymbol); 
        System.out.println("Market Cap : " + this.companyMarketCap); 
        System.out.println("Location   : " + this.companyLocation);
    }
}
