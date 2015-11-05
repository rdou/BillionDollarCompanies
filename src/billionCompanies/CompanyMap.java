package billionCompanies;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

import processing.core.PApplet;


public class CompanyMap extends PApplet{
    private List<UnfoldingMap> maps = new ArrayList<UnfoldingMap> (); 
    private List<List<CompanyInfo>> companyinfoByYear = new ArrayList<List<CompanyInfo>> ();
    private List<Marker> companyMarker;
    private StaticMarker lastSelected = null;
    
    private UnfoldingMap currentMap; 
    private String currentYear = "";
    private List<Marker> currentMarker;
    private final int NUM_OF_YEARS = 8;
    
    public void setup() {
        int index = 0;

        size(900, 600, OPENGL); 
        this.mapSetting();
		CSVparser csvparser = new CSVparser();
		csvparser.parseCSVFile("/home/rdou/rdou_work/ITU/CS518/Project/src/billionCompanies/top10.csv");   
		csvparser.cleanUpData();
		this.companyinfoByYear = csvparser.returnCompanyInfo();
		
        for (UnfoldingMap map : this.maps) {
            this.addMapMarkers(index++);
            map.addMarkers(this.companyMarker);  
        }

        this.currentMap = this.maps.get(0);
        this.currentYear = "Year 2015";
        this.currentMarker = this.currentMap.getMarkers();
		background(0);
	}
    
    public void draw() {	
        this.addKey(this.currentYear);	
        this.currentMap.draw();
	}	
    
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
        this.currentMarker = this.currentMap.getMarkers();
    }
    
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

    @Override 
    public void mouseMoved() {
    	// clear the last selection
    	if (this.lastSelected != null) {
    		this.lastSelected.setSelected(false);
   			this.lastSelected = null;
   		}
    	
    	selectMarkerIfHover(this.currentMarker);
    }
    
    // utils methods
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
    
    private void addMapMarkers(int index) {
        this.companyMarker = new ArrayList<Marker> ();
		for (CompanyInfo item : this.companyinfoByYear.get(index)) 
			this.companyMarker.add(new StaticMarker(new Location(this.getLatitude(item), this.getLongitude(item)), item));
    } 
     
    private double getLatitude(CompanyInfo companyInfo) {
    	return Double.parseDouble(companyInfo.getCompanyLocation().split(",")[0]);
    }
    
    private double getLongitude(CompanyInfo companyInfo) {
    	return Double.parseDouble(companyInfo.getCompanyLocation().split(",")[1]);
    }   
    
    private void selectMarkerIfHover(List<Marker> markers) {
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			StaticMarker marker = (StaticMarker)m;
			if (marker.isInside(this.currentMap,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
}
