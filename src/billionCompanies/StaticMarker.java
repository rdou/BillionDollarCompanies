package billionCompanies;

import java.util.HashMap;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PGraphics;

public class StaticMarker extends CompanyMarker {

	public StaticMarker(Location location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	public StaticMarker(Location location, HashMap<String, Object> properties) {
		super(location, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub

	}

}
