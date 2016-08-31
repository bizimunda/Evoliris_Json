package evoliris.com.evoliris_json;

import android.content.Context;
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


public class MainActivity extends ActionBarActivity implements GetAsyncTask.GetAsynTaskCallback{

    private Button btnMainRequest;
    private ProgressBar pbMainLoading;
    private TextView tvMainCity, tvMainCountry, tvMainTemp, tvMainTime;
    private ImageView imageView;
    private EditText etMainCityName;



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
        etMainCityName= (EditText) findViewById(R.id.et_main_cityName);
        imageView= (ImageView) findViewById(R.id.iv_main_icon);


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
                    task.execute("http://api.openweathermap.org/data/2.5/weather?q="+etMainCityName.getText().toString()+"&units=metric&appid=9124335619b9206a59b03318f5625a3d");
                } else {
                    Toast.makeText(MainActivity.this,"No data connection", Toast.LENGTH_SHORT).show();
                }

            }
        });


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
