package billionCompanies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

import processing.core.PApplet;

/** 
 * Implements a map class to show companies in silicon valley.
 * @authod Chenchen Zhou
 * @version 1.0
 */
public class CompanyMap extends PApplet{
    private List<UnfoldingMap> maps = new ArrayList<UnfoldingMap> (); 
    private List<List<CompanyInfo>> companyinfoByYear = new ArrayList<List<CompanyInfo>> ();
    private List<Marker> companyMarker;
    private StaticMarker lastSelected = null;
    private StaticMarker lastClicked = null;
    
    private UnfoldingMap currentMap; 
    private String currentYear = "";
    private List<Marker> currentMarker;
    
    private final int NUM_OF_YEARS = 9;
    
    /** 
     * Setup canva and maps. 
     */ 
    @Override
    public void setup() {
        
        // set canva size  
        size(900, 600, OPENGL); 

        // map init 
        this.mapSetting();
        this.mapGetCompanyInfo(); 
        background(0);
	}
    
    /** 
     * Draw maps and markers.
     */
    @Override
    public void draw() {	
        // draw map keys
        this.addKey(this.currentYear);	
        
        // draw current map and markers
        this.currentMap.draw();
	}	
    
    /**
     * Press keyboard to update currentMap, currentYear and currentMarker. 
     */ 
    @Override
    public void keyPressed() {    	
        if (key == '1') {
            this.currentMap = this.maps.get(0);
            this.currentYear = "Year 2015";
        } 
        else if (key == '2') {
        	this.currentMap = this.maps.get(1);
            this.currentYear = "Year 2014";
        } 
        else if (key == '3') {
        	this.currentMap = this.maps.get(2);
            this.currentYear = "Year 2013";
        } 
        else if (key == '4') {
        	this.currentMap = this.maps.get(3);
            this.currentYear = "Year 2012";
        } 
        else if (key == '5') {
        	this.currentMap = this.maps.get(4);
            this.currentYear = "Year 2011";
        } 
        else if (key == '6') {
        	this.currentMap = this.maps.get(5);
            this.currentYear = "Year 2010";
        } 
        else if (key == '7') {
        	this.currentMap = this.maps.get(6);
            this.currentYear = "Year 2009";
        } 
        else if (key == '8') {
        	this.currentMap = this.maps.get(7);
            this.currentYear = "Year 2008";
        }
        else if (key == '9') {
        	this.currentMap = this.maps.get(8);
            this.currentYear = "Year 2006";
        }

        this.currentMarker = this.currentMap.getMarkers();
    }
   
    /**
     * Mouse move event:
     * Show mouse selected company name and companycap. 
     */ 
    @Override 
    public void mouseMoved() {
	    // one marker selected before	
        if (this.lastSelected != null) {
    		this.lastSelected.setSelected(false);
   			this.lastSelected = null;
   		}
        
        // show selected marker's comapny name and companycap.     
        selectMarkerIfHover(this.currentMarker);
    }
    
    /**
     * Mouse click event: 
     * 1. Show all markers if any marker has been clicked before. 
     * 2. Hide one marker if no marker has been clicked before.  
     */ 
	@Override
	public void mouseClicked() {
	    // one marker clicked before	
        if (this.lastClicked != null) {
			this.unhideMarkers();
			this.lastClicked = null;
		}
        // no marker clicked before
		else {
			this.hideOtherMarkers();
		}
    } 

    // ################################################### 
    // utils methods  
    // ################################################### 
    
    /**
     * Draw map key.
     * @param currentYear a string shows current year.  
     */    
    private void addKey(String currentYear) {	
		int x = 25;
		int y = 25;
    	
		fill(255, 255, 255);
		rect(x, y, 150, 230);
		
		fill(0, 0, 0);
		textAlign(LEFT);
		textSize(16);
		
		text(currentYear, x + 35, y + 30);
		textSize(12);
		text("Company Market Cap", x + 10, y + 60);
		text(">200B", x + 10, y + 90);
		text(">100B", x + 10, y + 130);
		text(">50B",  x + 10, y + 170);
		text(">1B",   x + 10, y + 210);
	    
        fill(255, 0, 0);      
        ellipse(x + 100, y + 85,  20, 20); 
        
        fill(0, 255, 0);      
        ellipse(x + 100, y + 125, 17, 17); 
        
        fill(255, 255, 0);      
        ellipse(x + 100, y + 165, 14, 14); 
        
        fill(0, 0, 255);      
        ellipse(x + 100, y + 205, 11, 11); 		
	}

    /** 
     * Initialize NUM_OF_YEARS maps 
     */ 
    private void mapSetting() {
    	Location center = new Location(37.530368, -122.218159);
        for (int i = 0; i < this.NUM_OF_YEARS; i++) {
            this.maps.add(new UnfoldingMap(this, 200, 25, 675, 550, new Google.GoogleMapProvider())); 
        } 
        
        for (UnfoldingMap map : this.maps) {
            MapUtils.createDefaultEventDispatcher(this, map);
            map.zoomAndPanTo(10, center);
            map.setPanningRestriction(center, 40);
            map.setZoomRange(10, 13);    	
        } 
    }
    
    /** 
     * 1. Parse company information from CSV file 
     * to get (NUM_OF_YEARS - 1) years' company marketcap ( - 2014).
     * 2. Add all current day's companycaps to companyinfoByYear (2015).
     * 3. Add all company markers to all maps.
     * 4. Set current map, marker and year.  
     */ 
    private void mapGetCompanyInfo() { 
        int yearIndex = 0;
        List<CompanyInfo> temp = new ArrayList<CompanyInfo> (); 
		
        CSVparser csvparser = new CSVparser();
		csvparser.cleanUpData();
		this.companyinfoByYear = csvparser.returnCompanyInfo();
        
        if (this.companyinfoByYear != null) {
            for (CompanyInfo info : this.companyinfoByYear.get(0)) {
        	    temp.add(new CompanyInfo(info));
        	}
        }
        
        // get companies' current companycap 
        for (CompanyInfo item : temp) {
            item.setCompanyMarketCap(getCompanyMarketCapBySymbol(item.getCompanySymbol()));
        } 
        
        // update companyinfoByYear and add updated company markers to corresponding maps 
        this.companyinfoByYear.add(0, temp);
        for (UnfoldingMap map : this.maps) {
            this.addMapMarkers(yearIndex++);
            map.addMarkers(this.companyMarker);  
        }
        
        // set default current map, year and marker 
        this.currentMap = this.maps.get(0);
        this.currentYear = "Year 2015";
        this.currentMarker = this.currentMap.getMarkers();
    }
    
    /**
     * Add company markers to maps.
     */
    private void addMapMarkers(int index) {
		companyMarker = new ArrayList<Marker> ();
		for (CompanyInfo item : this.companyinfoByYear.get(index)) {
			this.companyMarker.add(new StaticMarker(new Location(this.getLatitude(item), this.getLongitude(item)), item));
		}
	}
    
    /**
     * Get company's latitude.
     */ 
    private double getLatitude(CompanyInfo companyInfo) {
    	return Double.parseDouble(companyInfo.getCompanyLocation().split(",")[0]);
    }
    
    /**
     * Get company's longitude.
     */ 
    private double getLongitude(CompanyInfo companyInfo) {
    	return Double.parseDouble(companyInfo.getCompanyLocation().split(",")[1]);
    }   
    
    /**
     * Set marker to be selected if marker is not selected and 
     * mouse is in marker's location.
     */ 
    private void selectMarkerIfHover(List<Marker> markers) {
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			StaticMarker marker = (StaticMarker)m;
			if (marker.isInside(this.currentMap,  mouseX, mouseY)) {
				lastSelected = marker;
				lastSelected.setSelected(true);
				return;
			}
		}
	}
    
    /**
     * Show all markers.
     */ 
    private void unhideMarkers() {
        List<Marker> temp = new ArrayList<Marker> ();

        // valid in all maps 
        for (UnfoldingMap map : this.maps) {
            temp = map.getMarkers(); 
    	    
            for (Marker marker : temp) {
    		    marker.setHidden(false);
            }
    	}
    }
    
    /**
     * Hide all other markers except clicked one.
     */
    private void hideOtherMarkers() {
        List<Marker> markerList= new ArrayList<Marker> ();
        
        // valid in all maps 
        for (UnfoldingMap map : this.maps) {
            markerList = map.getMarkers(); 
    	    
            for (Marker marker : markerList) {
                if (!marker.isHidden() && marker.isInside(this.currentMap, mouseX, mouseY)) {
                    this.lastClicked = (StaticMarker) marker; 
                    
                    for (Marker temp : markerList) {
                        if (temp.getLocation() != this.lastClicked.getLocation()) {
                            temp.setHidden(true); 
                        }
                    }
                    break; 
                } 
            }
        }
    }
    
    /**
     * Get companycap from Yahoo Finance.
     * @param companySymbol Company's stock symbol.
     * @return companyMarketCap Company's current markert cap. 
     */
    public float getCompanyMarketCapBySymbol(String companySymbol) {
		float companyMarketCap = 0;
    	URL url = null;

		try {
			url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=" + companySymbol + "&f=j1");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            String marketCap = reader.readLine();
		    companyMarketCap = Float.parseFloat(marketCap.substring(0, marketCap.length() - 1)); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return companyMarketCap;
    } 
}
