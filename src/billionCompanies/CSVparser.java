import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVparser {
	private BufferedReader bufferReader;
	private FileReader fileReader;
    private List<String> companyName;	
    private List<String> companySymbol;	
    private List<String> companyMarketCap;    

    public CSVparser(String fileName) throws FileNotFoundException {
        try {
		    this.fileReader       = new FileReader(fileName);
		    this.bufferReader     = new BufferedReader(fileReader);
            this.companyName      = new ArrayList<String> ();
            this.companySymbol    = new ArrayList<String> ();
            this.companyMarketCap = new ArrayList<String> ();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error in finding CSV file!"); 
        }
	}

    public void parseCSVFile() throws IOException { 
	    String[] oneLineInfo = new String[8];
        String oneLine = new String();

        try { 
            this.bufferReader.readLine();
            while ((oneLine = this.bufferReader.readLine()) != null) {
                oneLineInfo = oneLine.split("\","); 
                
                if (this.validCompany(oneLineInfo)) { 
                    companySymbol.add(oneLineInfo[0]); 
                    companyName.add(oneLineInfo[1]); 
                    companyMarketCap.add(oneLineInfo[3]); 
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error in reading CSV file!"); 
        }
    }
   
    public List<String> returnCompanyName() {
        return this.companyName; 
    }
   
    public List<String> returnCompanySymbol() {
        return this.companySymbol;
    }
    
    public List<String> returnCompanyMarketCap() {
        return this.companyMarketCap; 
    }
    
    public void printInfo() {
        System.out.println("printInfo: ");
        for (String item : this.companySymbol) {
            System.out.println(item.substring(1, item.length())); 
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
