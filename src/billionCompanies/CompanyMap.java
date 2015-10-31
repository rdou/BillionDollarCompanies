package billionCompanies;

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
//import yahoofinance.Stock;

public class CompanyMap extends PApplet{
    private UnfoldingMap map;
    
    private void mapSetting() {
        map = new UnfoldingMap(this, 200, 25, 675, 550, new Google.GoogleMapProvider());
        map.zoomAndPanTo(10, new Location(37.530368, -122.218159));
        map.setZoomRange(9, 10);    	
    }

    private List<Marker> companyMarker = new ArrayList<Marker> (); 

    public void setup() {
        size(900, 600, OPENGL); 
        this.mapSetting();
        MapUtils.createDefaultEventDispatcher(this, map);
        CompanyMarker apple = new CompanyMarker(new Location(37.330552, -122.029653));
        companyMarker.add(apple);
        map.addMarkers(companyMarker);
    }

	public void draw() {
		background(0);
		map.draw();
	}	
}
