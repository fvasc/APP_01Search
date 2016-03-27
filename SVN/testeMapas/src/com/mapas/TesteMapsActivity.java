package com.mapas;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;

public class TesteMapsActivity extends MapActivity {
	
	private MapView mapView;
	private boolean visao_satelite_controle;
	private GeoPoint p;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.map_view);
    	mapView.setBuiltInZoomControls(true);
    	
    	mapView.setSatellite(true);
    	
    	List<Overlay> mapOverlays = mapView.getOverlays();
    	Drawable drawable = this.getResources().getDrawable(R.drawable.img);
    	DroidOverlay itemizedoverlay = new DroidOverlay(drawable, this);
    	
    	GeoPoint point = new GeoPoint(-3733030, -38498932);
    	OverlayItem overlayitem = new OverlayItem(point, "Olá, Mundo!", "Estou em Fortaleza!");
    	
    	itemizedoverlay.addOverlay(overlayitem);
    	mapOverlays.add(itemizedoverlay);
    	
    	
    	//mapView.setStreetView(true);
    	//mapView.setHorizontalScrollBarEnabled(true);
    	
        
        /*MapView mapa = new MapView(this, "0DFMKB0k17z8mfPDGKbmbq1iqwTiOGpxDHKnyMA");
        setContentView(mapa);
        */
        
        
    	/*
        mapView = (MapView) findViewById(R.id.map_view) ;
        mapView.setClickable(true) ;
         
        MyLocationOverlay mlo = new MyLocationOverlay(this, mapView) ;
        mlo.enableCompass() ;
        mlo.enableMyLocation() ;
        mapView.getOverlays().add(mlo) ;
        */
        
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// mc �� o objeto que faz o controle do mapa
    	MapController mc = mapView.getController();
 
        switch (keyCode)
        {
        	//Se apertar a tecla 1
            case KeyEvent.KEYCODE_1:
                mc.zoomIn();
                return true;
 
            //Se apertar a tecla 2
            case KeyEvent.KEYCODE_2:
 
            	mapView.setSatellite(visao_satelite_controle);
            	visao_satelite_controle =!visao_satelite_controle;
            	return true;
 
            //Se apertar a tecla 3
            case KeyEvent.KEYCODE_3:
 
            	mc.zoomOut();
                return true;
 
            //Se apertar a tecla de abaixar volume
            case KeyEvent.KEYCODE_5:
            //case KeyEvent.KEYCODE_VOLUME_DOWN:
 
                p = new GeoPoint(-3733030, -38498932);
                mc.animateTo(p);
                mc.setZoom(17);
                mapView.invalidate();
                return true;
 
            //Se apertar a tecla de aumentar volume
            case KeyEvent.KEYCODE_VOLUME_UP:
 
                p = new GeoPoint(35410000, 139460000);
                mc.animateTo(p);
                mc.setZoom(17);
                mapView.invalidate();
                return true;
 
            //Se apertar a tecla L
            case KeyEvent.KEYCODE_L:
 
                p = new GeoPoint(p.getLatitudeE6(), p.getLongitudeE6()+1000);
                mc.animateTo(p);
                mc.setZoom(17);
                mapView.invalidate();
                return true;
 
            //Se apertar a telca O
            case KeyEvent.KEYCODE_O:
 
            	p = new GeoPoint(p.getLatitudeE6(), p.getLongitudeE6()-1000);
                mc.animateTo(p);
                mc.setZoom(17);
                mapView.invalidate();
                return true;
        }
        return super.onKeyDown(keyCode, event);
	}
    
    
}