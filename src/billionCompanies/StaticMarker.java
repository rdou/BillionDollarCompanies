package billionCompanies;

import java.util.HashMap;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.*;

public class StaticMarker extends CompanyMarker {
    private String companyName; 
    private String companySymbol; 
    private Float companyMarketCap; 

	public StaticMarker(Location location, CompanyInfo companyinfo) {
		super(location);
        this.companyName      = companyinfo.getCompanyName();
        this.companySymbol    = companyinfo.getCompanySymbol();
        this.companyMarketCap = companyinfo.getCompanyMarketCap();	
    }

	public StaticMarker(Location location, HashMap<String, Object> properties) {
		super(location, properties);
	}
    
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		float radius = this.getRadius();
		
		if (this.companyMarketCap > 200) {
			pg.fill(255, 0, 0);
		}
		else if (this.companyMarketCap > 100) {
			pg.fill(0, 255, 0);			
		}
		else if (this.companyMarketCap > 50) {
			pg.fill(255, 255, 0);			
		}
		else {
			pg.fill(0, 0, 255);			
		}

		pg.stroke(67, 211, 227, 100);
        pg.strokeWeight(2);
		
		pg.ellipse(x, y, radius, radius);
	}
	
	@Override
	public void showCompanyInfo(PGraphics pg, float x, float y) {
		{
			String name = this.companyName;
			String marketCap = "" + this.companyMarketCap + "B";
			
			pg.fill(255,255,255);
			pg.rect(x, y - 50, pg.textWidth(name) + 20, 30, 3);
			
			pg.textAlign(PConstants.LEFT, PConstants.TOP);
			pg.fill(0, 0, 0);
			pg.text(name, x + 10 , y - 50);
			pg.text(marketCap, x + 10 , y - 37);
		}
	}
	
    private float getRadius() {
        return (float) (Math.log(this.companyMarketCap) * 8);
    }
}
