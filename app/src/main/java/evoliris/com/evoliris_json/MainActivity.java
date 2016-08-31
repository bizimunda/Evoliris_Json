package evoliris.com.evoliris_json;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import evoliris.com.evoliris_json.model.Model;
import evoliris.com.evoliris_json.task.GetAsyncTask;
import evoliris.com.evoliris_json.task.GetImageAsyncTask;


public class MainActivity extends ActionBarActivity implements GetAsyncTask.GetAsynTaskCallback, LocationListener{

    private Button btnMainRequest;
    private ProgressBar pbMainLoading;
    private TextView tvMainCity, tvMainCountry, tvMainTemp, tvMainTime, tvMainLong, tvMainLat;
    private ImageView imageView;
    private EditText etMainCityName;
    private LocationManager locationManager;
    private Location location;
    private String provider;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMainRequest= (Button) findViewById(R.id.btn_main_request);
        pbMainLoading= (ProgressBar) findViewById(R.id.pb_main);
        tvMainCity = (TextView) findViewById(R.id.tv_main_city);
        tvMainCountry = (TextView) findViewById(R.id.tv_main_country);
        tvMainTemp = (TextView) findViewById(R.id.tv_main_temp);
        tvMainTime = (TextView) findViewById(R.id.tv_main_time);
        tvMainLat = (TextView) findViewById(R.id.tv_main_lat);
        tvMainLong = (TextView) findViewById(R.id.tv_main_long);
        etMainCityName= (EditText) findViewById(R.id.et_main_cityName);
        imageView= (ImageView) findViewById(R.id.iv_main_icon);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            tvMainLong.setText("Location not available");
            tvMainLat.setText("Location not available");
        }


        btnMainRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager cm = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if(isConnected) {
                    GetAsyncTask task = new GetAsyncTask(MainActivity.this);

                    //User adds city in the EditText
                    //task.execute("http://api.openweathermap.org/data/2.5/weather?q="+etMainCityName.getText().toString()+"&units=metric&appid=9124335619b9206a59b03318f5625a3d");

                    //It locates weather using locationManager
                    task.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + tvMainLat.getText() + "&lon=" + tvMainLong.getText() + "&appid=9124335619b9206a59b03318f5625a3d&units=metric");
                } else {
                    Toast.makeText(MainActivity.this,"No data connection", Toast.LENGTH_SHORT).show();
                }
                etMainCityName.setText("");

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        tvMainLong.setText(String.valueOf(location.getLongitude()));
        tvMainLat.setText(String.valueOf(location.getLatitude()));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPreGet() {
        tvMainCity.setVisibility(View.GONE);
        pbMainLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostGet(String s) {

        Gson gson= new Gson();
        Model model= gson.fromJson(s, Model.class);

        GetImageAsyncTask getImageAsyncTask= new GetImageAsyncTask(imageView);
        getImageAsyncTask.execute(model.getWeather().get(0).getIcon());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd \nHH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());



        tvMainCountry.setText(model.getSys().getCountry());
        tvMainCity.setText(model.getName());
        tvMainTemp.setText(String.valueOf(model.getMain().getTemp())+" C°");
        tvMainTime.setText("Last update at\n"+currentDateAndTime.toString());
        tvMainCity.setVisibility(View.VISIBLE);
        pbMainLoading.setVisibility(View.GONE);

    }



}
