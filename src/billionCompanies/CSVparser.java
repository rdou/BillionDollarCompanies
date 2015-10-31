import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class CSVparser implements Comparator<CompanyInfo> {
	private BufferedReader bufferReader;
	private FileReader fileReader;
    private List<CompanyInfo> companyinfoList; 

    public CSVparser(String fileName) throws FileNotFoundException {
        try {
		    this.fileReader       = new FileReader(fileName);
		    this.bufferReader     = new BufferedReader(fileReader);
            this.companyinfoList  = new ArrayList<CompanyInfo> ();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error in finding CSV file!"); 
            System.exit(-1);
        }
	}

    public int compare(CompanyInfo c1, CompanyInfo c2) {
        return c2.getCompanyMarketCap().compareTo(c1.getCompanyMarketCap());  
    }
    
    public void parseCSVFile() throws IOException { 
	    String[] oneLineInfo = new String[8];
        String oneLine = new String();

        try { 
            this.bufferReader.readLine();
            while ((oneLine = this.bufferReader.readLine()) != null) {
                oneLineInfo = oneLine.split("\","); 
                
                if (this.validCompany(oneLineInfo)) { 
                    this.companyinfoList.add(new CompanyInfo(oneLineInfo[1].substring(1, oneLineInfo[1].length()),
                                                             oneLineInfo[0].substring(1, oneLineInfo[0].length()),
                                                             Float.parseFloat(oneLineInfo[3].substring(2, oneLineInfo[3].length() - 1)))); 
                }
            }

            Collections.sort(this.companyinfoList, this);
        }
        catch (IOException e) {
            System.out.println("Error in reading CSV file!"); 
            System.exit(-1);
        }
    }
   
    public void printInfo() {
        for (CompanyInfo item : this.companyinfoList) {
            System.out.println(item.getCompanyName()); 
        }  
    }
    
    private boolean validCompany(String[] oneLineInfo) {
        int lastChar = oneLineInfo[3].length() - 1; 
 
        return (oneLineInfo[5].equals("\"Technology") && 
               (!oneLineInfo[3].equals("\"n/a")) && 
               (oneLineInfo[3].charAt(lastChar) == 'B') &&
               Float.parseFloat(oneLineInfo[3].substring(2, lastChar)) >= 1f);
    }
    
    public static void main(String[] args) {
        try { 
            CSVparser test = new CSVparser("companylist.csv");
            test.parseCSVFile();
            test.printInfo();
        } 
        catch (FileNotFoundException e1) {
            System.exit(-1); 
        }
        catch (IOException e2) {
            System.exit(-1); 
        }
    }
}
