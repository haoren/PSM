package extension;

import psm.taxi.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class LapisanMap extends com.google.android.maps.Overlay{
	GeoPoint location = null;
	Resources res;
	
	public LapisanMap(GeoPoint location){
		super();
		this.location = location;
	}

	@Override
	public void draw(Canvas canvas, MapView map, boolean shadow){
		super.draw(canvas, map, shadow);
		//translate the screen pixels
		Point screenPoint = new Point();
		map.getProjection().toPixels(this.location, screenPoint);

		//add the image
		canvas.drawBitmap(BitmapFactory.decodeResource(res, R.drawable.icon_map),	screenPoint.x, screenPoint.y , null); //Setting the image & location on the screen (x,y).
		}
}