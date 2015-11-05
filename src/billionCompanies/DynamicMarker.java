package billionCompanies;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PGraphics;

public class DynamicMarker extends CompanyMarker {

	public DynamicMarker(Location location) {
		super(location);
	}

    /*public void getCompanyMarketCap() {
		URL url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=AAPL&f=j1");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        System.out.println(line);
		    }
		}
    }*/    
    
    @Override
	public void drawMarker(PGraphics pg, float x, float y) {
	}

	@Override
	public void showCompanyInfo(PGraphics pg, float x, float y) {
	}

}
