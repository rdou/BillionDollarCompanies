package billionCompanies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class CSVparser implements Comparator<CompanyInfo> {
    private List<CompanyInfo> companyinfoList; 
    
    public CSVparser() { 
            this.companyinfoList  = new ArrayList<CompanyInfo> ();
    }
	
    public int compare(CompanyInfo c1, CompanyInfo c2) {
        return c2.getCompanyMarketCap().compareTo(c1.getCompanyMarketCap());  
    }
	
    public void parseCSVFile(String filename) { 
        BufferedReader bufferReader = null;
        FileReader fileReader = null; 
        boolean parseOriginalCSVFile = false; 

        try { 
            fileReader = new FileReader(filename);
	        bufferReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error in opening CSV file!"); 
            e.printStackTrace(); 
        }

	    String[] oneLineInfo = new String[9];
        String oneLine = new String();

        try { 
            bufferReader.readLine();
            while ((oneLine = bufferReader.readLine()) != null) {
                oneLineInfo = oneLine.split("\","); 
                
                if (parseOriginalCSVFile) { 
                    if (this.validCompany(oneLineInfo)) { 
                        this.companyinfoList.add(new CompanyInfo(oneLineInfo[1].substring(1, oneLineInfo[1].length()),
                                                                 oneLineInfo[0].substring(1, oneLineInfo[0].length()),
                                                                 Float.parseFloat(oneLineInfo[3].substring(2, oneLineInfo[3].length() - 1)),
                                                                 ""));
                    }
                }
                else {
                    if (oneLineInfo.length == 9) { 
                        this.companyinfoList.add(new CompanyInfo(oneLineInfo[1].substring(1, oneLineInfo[1].length()),
                                                                 oneLineInfo[0].substring(1, oneLineInfo[0].length()),
                                                                 Float.parseFloat(oneLineInfo[3].substring(2, oneLineInfo[3].length() - 1)),
                                                                 oneLineInfo[8].substring(1, oneLineInfo[8].length())));
                    } 
                } 
            }

            Collections.sort(this.companyinfoList, this);
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
   
    public void printInfo() {
        int i = 0; 
        for (CompanyInfo item : this.companyinfoList) {
            if (i < 30) { 
                System.out.println(item.getCompanySymbol() + " " + item.getCompanyMarketCap() + "B " + item.getCompanyLocation()); 
                i++;
            } 
            else 
                break;
        }  
    }

    public List<CompanyInfo> returnCompanyInfo() {
        return this.companyinfoList;
    } 

    private boolean validCompany(String[] oneLineInfo) {
        int lastChar = oneLineInfo[3].length() - 1; 
 
        return (oneLineInfo[5].equals("\"Technology") && 
               (!oneLineInfo[3].equals("\"n/a")) && 
               (oneLineInfo[3].charAt(lastChar) == 'B') &&
               Float.parseFloat(oneLineInfo[3].substring(2, lastChar)) >= 1f);
    }
    

    /*
    public static void main(String[] args) {
            CSVparser test = new CSVparser();
            test.parseCSVFile("NASDAQ.csv");
            test.parseCSVFile("NYSE.csv");
            test.printInfo();
    }
    */
}
