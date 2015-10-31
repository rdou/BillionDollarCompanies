public class CompanyInfo {
    private String companyName;
    private String companySymbol;
    private Float companyMarketCap;
    
    public CompanyInfo(String name, String symbol, Float marketcap) {
        this.companyName = name;
        this.companySymbol = symbol; 
        this.companyMarketCap = marketcap; 
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

    // output company information
    public void outputCompanyInfo() {
        System.out.println("Name       : " + this.companyName); 
        System.out.println("Symbol     : " + this.companySymbol); 
        System.out.println("Market Cap : " + this.companyMarketCap); 
    }
}
