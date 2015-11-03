package billionCompanies;

import java.util.HashMap;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PGraphics;

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
		pg.ellipse(x, y, this.companyMarketCap / 5, this.companyMarketCap / 5);
	}
}
