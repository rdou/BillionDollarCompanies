package billionCompanies;

import java.util.*;

import de.fhpotsdam.unfolding.examples.SimpleMapApp;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class CompanyMarker extends SimplePointMarker {
    public CompanyMarker(Location location) {
        super(location); 
    }
    
    @Override 
    public void draw(PGraphics pg, float x, float y) {
        if (!this.hidden) {
	    	drawMarker(pg, x, y);
	        if (this.selected) {
	        	showCompanyInfo(pg, x, y);
			}
        }
    } 
    
    public abstract void drawMarker(PGraphics pg, float x, float y);
    public abstract void showCompanyInfo(PGraphics pg, float x, float y);
}

