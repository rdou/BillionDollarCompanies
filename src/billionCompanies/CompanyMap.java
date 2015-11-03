package billionCompanies;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

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
    private UnfoldingMap map;
    private List<Marker> companyMarker = new ArrayList<Marker> (); 
    private List<List<CompanyInfo>> companyinfoByYear = new ArrayList<List<CompanyInfo>> ();

    private void mapSetting() {
        map = new UnfoldingMap(this, 200, 25, 675, 550, new Google.GoogleMapProvider());
        map.zoomAndPanTo(10, new Location(37.530368, -122.218159));
        map.setZoomRange(10, 12);    	
    }

    public void setup() {
        size(900, 600, OPENGL); 
        this.mapSetting();
        MapUtils.createDefaultEventDispatcher(this, map);

		CSVparser csvparser = new CSVparser();
		csvparser.parseCSVFile("/home/rdou/rdou_work/ITU/CS518/Project/src/billionCompanies/top10.csv");   
		csvparser.cleanUpData();
		this.companyinfoByYear = csvparser.returnCompanyInfo();
		
		for (CompanyInfo item : this.companyinfoByYear.get(4)) {
			companyMarker.add(new StaticMarker(new Location(this.getLatitude(item), this.getLongitude(item)), item));
		}

        map.addMarkers(companyMarker);
    }

    public double getLatitude(CompanyInfo companyInfo) {
    	return Double.parseDouble(companyInfo.getCompanyLocation().split(",")[0]);
    }
    
    public double getLongitude(CompanyInfo companyInfo) {
    	return Double.parseDouble(companyInfo.getCompanyLocation().split(",")[1]);
    }
    
	public void draw() {
		background(0);
		map.draw();
	}	
}
