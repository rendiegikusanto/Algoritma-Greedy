package com.rendi.tutorial.algoritmagreedy;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MapView map;
    Marker awalMarker, akhirMarker;
    double nilair1,nilair2;
    EditText txt_lang;
    EditText txt_long;
    Button btn_simpan;
    GeoPoint awalPoint,titik1,titik2,titik3,titik4,titik5,titik6,titik7,titik8,titik9,titik10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        map = (MapView) findViewById(R.id.mapview);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

         awalPoint = new GeoPoint(-5.379556, 105.251722);// kota bandar Lampung


        //  gg ratu
         titik1 = new GeoPoint(-5.3795636, 105.2517164);
         titik2 = new GeoPoint(-5.37982,105.2512765 );
         titik3 = new GeoPoint(-5.3801725, 105.2504182);
         titik4 = new GeoPoint(-5.3797986, 105.2493347);
         titik5 = new GeoPoint(-5.380322, 105.2488518);
         titik6 = new GeoPoint(-5.3796918,105.2483475);
         titik7 = new GeoPoint(-5.379083, 105.2473713);
         titik8 = new GeoPoint(-5.3783886,105.2455795);
         titik9 = new GeoPoint(-5.3759319, 105.2465236);
         titik10 = new GeoPoint(-5.375355, 105.2462447);

        //=====================================jalur pertama===================================
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(awalPoint);



        //=========================================================

        RoadManager roadManager = new OSRMRoadManager(this);// manajeman jalur
        //=============================================================

        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(awalPoint);

         //=======================================

        GeoPoint akhirPoint = new GeoPoint(-5.375270, 105.246239);// pasca ubl
        waypoints.add(akhirPoint);
        waypoints.add(titik1);
        waypoints.add(titik2);
        waypoints.add(titik3);
        waypoints.add(titik4);
        waypoints.add(titik5);
        waypoints.add(titik6);
        waypoints.add(titik7);
        waypoints.add(titik8);
        waypoints.add(titik9);
        waypoints.add(titik10);




        //========================
        Road road = roadManager.getRoad(waypoints);// petunjuk arah




        awalMarker = new Marker(map);
        akhirMarker = new Marker(map);




        //===================================
        awalMarker.setPosition(awalPoint);
        awalMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(awalMarker);
        map.invalidate();
        awalMarker.setIcon(getResources().getDrawable(R.drawable.marker_destination));
        awalMarker.setTitle("Point awal");


        //===============================
        akhirMarker.setPosition(akhirPoint);
        akhirMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(akhirMarker);
        map.invalidate();
        akhirMarker.setIcon(getResources().getDrawable(R.drawable.marker_departure));
        akhirMarker.setTitle("Point Akhir");

        /***

        nilair1 = Integer.parseInt(awalPoint.toString()+Integer.parseInt(titik10.toString()));
        nilair2 = Integer.parseInt(titik1.toString())+Integer.parseInt(titik2.toString())+Integer.parseInt(titik3.toString()+Integer.parseInt(titik4.toString()+Integer.parseInt(titik5.toString()+Integer.parseInt(titik6.toString()+Integer.parseInt(titik7.toString()+Integer.parseInt(titik8.toString()+Integer.parseInt(titik9.toString()+Integer.parseInt(titik10.toString()))))))));


        if (nilair1 > nilair2){

            Toast.makeText(getApplicationContext(), "Jalur yang harus dilalui",Toast.LENGTH_LONG).show();


        }else {

            Toast.makeText(getApplication(),"Jalur tidak ditemukan",Toast.LENGTH_LONG).show();

         }
           */


        Polyline roadMap = RoadManager.buildRoadOverlay(road);
        map.getOverlays().add(roadMap);
        map.invalidate();


        txt_lang= (EditText)findViewById(R.id.txt_lang);
        txt_long = (EditText)findViewById(R.id.txt_long);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String langitude = String.valueOf(txt_lang.getText());
                String longitude = String.valueOf(txt_long.getText());

                try{
                    nilair1 = new Double(awalPoint.toString()+Double.parseDouble(titik10.toString())+1E6);
                    nilair2 = new Double(titik1.toString())+new Double(titik2.toString())+new Double(titik3.toString()+new Double(titik4.toString()+new Double(titik5.toString()+new Double(titik6.toString()+new Double(titik7.toString()+new Double(titik8.toString()+new Double(titik9.toString()+new Double(titik10.toString())))))))+1E6);

                }catch (NumberFormatException a){
                    nilair1=0;
                    nilair2=0;
                }

                if (nilair1 > nilair2){

                    Toast.makeText(getApplicationContext(), "Jalur yang harus dilalui",Toast.LENGTH_LONG).show();


                }else {

                    Toast.makeText(getApplication(),"Jalur tidak ditemukan",Toast.LENGTH_LONG).show();

                }

                txt_lang.setText(Double.toString(nilair1));

            }
        });

    }
}
