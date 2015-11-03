package billionCompanies;

public class CompanyInfo {
    private String companyName;
    private String companySymbol;
    private Float companyMarketCap;
    private String companyLocation;

    public CompanyInfo(String name, String symbol, Float marketcap, String location) {
        this.companyName = name;
        this.companySymbol = symbol; 
        this.companyMarketCap = marketcap; 
        this.companyLocation = location; 
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
    public Float getCompanyMarketCap() {
        return this.companyMarketCap; 
    }
    
    public void setCompanyMarketCap(Float marketcap) {
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
