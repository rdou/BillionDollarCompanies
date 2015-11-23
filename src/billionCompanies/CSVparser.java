package billionCompanies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

/** CSVparser
 * Parse CSV file to get company information. 
 * @author Chenchen Zhou
 * @version 1.0
 */ 
public class CSVparser {
    private List<String[]> companyinfoList; 
    private List<List<CompanyInfo>> companyinfoByYear; 
    
    /**
     * CSVparser constructor
     */
    public CSVparser() { 
        this.companyinfoList  = new ArrayList<String[]> ();
        this.companyinfoByYear = new ArrayList<List<CompanyInfo>> (); 
    }
    
    /** parseCSVFile
     * Parsing a CSV file to get companys' historical marketcap and their locations.
     * CSV file contains information like this:  
     * 
     * "AAPL","Apple Inc.","643,120.6","500,741.8","499,696.4","377,519.9","297,089.5","190,983.6", 
     * "75,998.2","72,901.7","37.387538,-122.039057" 
     * 
     * After parsing file, this method will update companyinfoList. 
     * @param filename name of CSV file to be parsed.
     */
    public void parseCSVFile(String filename) { 
        BufferedReader bufferReader = null;
        FileReader fileReader = null; 

        try { 
            fileReader = new FileReader(filename);
	        bufferReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }

	    String[] oneLineInfo = new String[11]; 
        String oneLine = new String();

        try { 
            // ignore fisrt line of CSV file 
            bufferReader.readLine();
            while ((oneLine = bufferReader.readLine()) != null) {
                oneLineInfo = oneLine.split("\",");
                this.companyinfoList.add(oneLineInfo); 
            }
        }
        catch (IOException e) {
            e.printStackTrace(); 
        }
        
        try { 
            fileReader.close(); 
            bufferReader.close(); 
        }
        catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }
        catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    
    /** cleanUpData 
     * Read data from companyinfoList, reorganize and store them to companyinfoByYear. 
     */  
    public void cleanUpData() {
        // CSV file contains 8 years company marketcap information 
        for (int i = 2; i < 10; i++) { 
            List<CompanyInfo> tempCompanyInfo = new ArrayList<CompanyInfo> ();
            
            // store companys' marketcap by year  
            /*
            Example:
            AAPL 643.0B 37.387538,-122.039057
            GOOGL 359.0B 37.420284,-122.075047
            FB 216.0B 37.484849,-122.148365
            INTC 175.0B 37.376132,-121.975223
            CSCO 142.0B 37.388050,-121.973880
            ORCL 197.0B 37.395785,-121.952682
            EBAY 69.0B 37.295986,-121.927607
            LNKD 28.0B 37.423304,-122.070659
            CRM 37.0B 37.794092,-122.394815
            ADBE 36.0B 37.331,-121.894
            YHOO 47.0B 37.417281,-122.025137
            */
            for (String[] string : this.companyinfoList) {
                tempCompanyInfo.add(new CompanyInfo(string[1].substring(1, string[1].length()),
                                                    string[0].substring(1, string[0].length()),
                                                    parseMarketCap(string[i].substring(1, string[i].length())),
                                                    string[10].substring(1, string[10].length() - 1))); 
            } 
            companyinfoByYear.add(tempCompanyInfo); 
        }
    }
    
    /** parseMarketCap 
     * Get marketcap value from companyinfoList.
     * @param marketcap company marketcap information. 
     * @return company marketcap value. 
     */ 
    private float parseMarketCap(String marketcap) {
        return Float.parseFloat(marketcap.split(",")[0]); 
    }

    /** returnCompanyInfo 
     * Return all marketcap information by year. 
     * @return companyinfoByYear marketcap information by year. 
     */
    public List<List<CompanyInfo>> returnCompanyInfo() {
        return this.companyinfoByYear;
    } 
}
