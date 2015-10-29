package billionCompanies;

import java.util.*;

import de.fhpotsdam.unfolding.examples.SimpleMapApp;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

public class CompanyMarker extends SimplePointMarker {
    public CompanyMarker(Location location) {
        super(location); 
    }

    public CompanyMarker(Location location, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
	    super(location, properties);
	}

    //@Override 
    //public void draw(PGraphics pg, float x, float y) {
    //    drawMarker(pg, x, y); 
    //} 
    
    //public abstract void drawMarker(PGraphics pg, float x, float y);
}

