package billionCompanies;

import java.util.*;

import de.fhpotsdam.unfolding.examples.SimpleMapApp;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** CompanyMarker 
 * Implements a CompanyMarker for company on a CompanyMap. 
 * @author Chenchen Zhou
 * @version 1.0
 */
public abstract class CompanyMarker extends SimplePointMarker {
    /**
     * CompanyMarker constructor.
     * @param location CompanyMarker location
     */ 
    public CompanyMarker(Location location) {
        super(location); 
    }
    
    /** draw
     * Override draw method from SimplePointMarker. 
     * Only unhidden marker will be drawn on CompanyMap. 
     * @param pg PGraphics object.
     * @param x x position in outer object coordinates. 
     * @param y y position in outer object coordinates.  
     */  
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

