package billionCompanies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class CSVparser {
    private List<String[]> companyinfoList; 
    private List<List<CompanyInfo>> companyinfoByYear; 

    public CSVparser() { 
        this.companyinfoList  = new ArrayList<String[]> ();
        this.companyinfoByYear = new ArrayList<List<CompanyInfo>> (); 
    }
	
    public void parseCSVFile(String filename) { 
        BufferedReader bufferReader = null;
        FileReader fileReader = null; 

        try { 
            fileReader = new FileReader(filename);
	        bufferReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error in opening CSV file!"); 
            e.printStackTrace(); 
        }

	    String[] oneLineInfo = new String[11]; 
        String oneLine = new String();

        try { 
            bufferReader.readLine();
            while ((oneLine = bufferReader.readLine()) != null) {
                oneLineInfo = oneLine.split("\",");
                //System.out.println(oneLine); 
                this.companyinfoList.add(oneLineInfo); 
            }
        }
        catch (IOException e) {
            System.out.println("Error in reading CSV file!"); 
            e.printStackTrace(); 
        }
        finally {
            try { 
                bufferReader.close(); 
                fileReader.close(); 
            }
            catch (FileNotFoundException e) {
                e.printStackTrace(); 
            }
            catch (IOException e) {
                e.printStackTrace(); 
            }
        }
    }
    
    public void cleanUpData() {
        for (int i = 2; i < 10; i++) { 
            List<CompanyInfo> tempCompanyInfo = new ArrayList<CompanyInfo> ();

            for (String[] string : this.companyinfoList) {
                tempCompanyInfo.add(new CompanyInfo(string[1].substring(1, string[1].length()),
                                                    string[0].substring(1, string[0].length()),
                                                    parseMarketCap(string[i].substring(1, string[i].length())),
                                                    string[10].substring(1, string[10].length() - 1))); 
            } 
            companyinfoByYear.add(tempCompanyInfo); 
        }
    }
    
    private float parseMarketCap(String marketcap) {
        return Float.parseFloat(marketcap.split(",")[0]); 
    }

    /*
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
    public void printInfo() {
        for (List<CompanyInfo> info : this.companyinfoByYear) {
            for (CompanyInfo company : info) {
                System.out.println(company.getCompanySymbol() + " " + company.getCompanyMarketCap() + "B " + company.getCompanyLocation()); 
            } 
        } 
    } 
    
    public List<CompanyInfo> returnOneYearCompanyInfo() {
    	if (this.companyinfoByYear != null)
    		return this.companyinfoByYear.get(0);
    	else 
    		return null;
    }
    
    public List<List<CompanyInfo>> returnCompanyInfo() {
        return this.companyinfoByYear;
    } 
}
