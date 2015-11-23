package billionCompanies;

/** CompanyInfo
 * Store and upade company information. 
 * @author Chenchen Zhou
 * @version 1.0 
 */ 
public class CompanyInfo {
    private String companyName;
    private String companySymbol;
    private float companyMarketCap;
    private String companyLocation;
    
    /**
     * CompanyInfo constructor_1
     * @param name company name. 
     * @param symbol company symbol. 
     * @param marketcap company marketcap 
     * @param location company location.
     */ 
    public CompanyInfo(String name, String symbol, float marketcap, String location) {
        this.companyName = name;
        this.companySymbol = symbol; 
        this.companyMarketCap = marketcap; 
        this.companyLocation = location; 
    } 
    
    /**
     * CompanyInfo constructora_2_
     * @param info CompanyInfo object 
     */ 
    public CompanyInfo(CompanyInfo info) {
        this.companyName = info.getCompanyName();
        this.companySymbol = info.getCompanySymbol(); 
        this.companyMarketCap = info.getCompanyMarketCap(); 
        this.companyLocation = info.getCompanyLocation(); 	
	}

    /**
     * This method returns company name.
     * @return company name. 
     */	
    public String getCompanyName() {
        return this.companyName; 
    }
    
    /**
     * This method set company name.
     * @param name company name. 
     */	
    public void setCompanyName(String name) {
        this.companyName = name; 
    }
    
   /**
     * This method returns company symbol.
     * @return company symbol. 
     */	 
    public String getCompanySymbol() {
        return this.companySymbol; 
    }
    
    /**
     * This method set company symbol.
     * @param symbol company symbol. 
     */	
    public void setCompanySymbol(String symbol) {
        this.companySymbol = symbol; 
    }
    
   /**
     * This method returns company marketcap.
     * @return company marketcap. 
     */	 
    public float getCompanyMarketCap() {
        return this.companyMarketCap; 
    }
     
    /**
     * This method set company marketcap.
     * @param marketcap company marketcap. 
     */	
    public void setCompanyMarketCap(float marketcap) {
        this.companyMarketCap = marketcap; 
    }
    
   /**
     * This method returns company location.
     * @return company location. 
     */	 
    public String getCompanyLocation() {
        return this.companyLocation; 
    } 
    
    /**
     * This method set company location.
     * @param location company location. 
     */	
    public void setCompanyLocation(String location) {
        this.companyLocation = location; 
    } 
}
