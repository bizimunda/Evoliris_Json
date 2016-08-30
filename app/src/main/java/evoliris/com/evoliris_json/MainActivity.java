package evoliris.com.evoliris_json;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import evoliris.com.evoliris_json.model.Model;
import evoliris.com.evoliris_json.task.GetAsyncTask;


public class MainActivity extends ActionBarActivity implements GetAsyncTask.GetAsynTaskCallback{

    private Button btnMainRequest;
    private ProgressBar pbMainLoading;
    private TextView tvMainCity, tvMainCountry, tvMainTemp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMainRequest= (Button) findViewById(R.id.btn_main_request);
        pbMainLoading= (ProgressBar) findViewById(R.id.pb_main);
        tvMainCity = (TextView) findViewById(R.id.tv_main_city);
        tvMainCountry = (TextView) findViewById(R.id.tv_main_country);
        tvMainTemp = (TextView) findViewById(R.id.tv_main_temp);




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
                    task.execute("http://api.openweathermap.org/data/2.5/weather?q=London,uk&units=metric&appid=9124335619b9206a59b03318f5625a3d");
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

        tvMainCountry.setText(model.getSys().getCountry());
        tvMainCity.setText(model.getName());
        tvMainTemp.setText(String.valueOf(model.getMain().getTemp()));
        tvMainCity.setVisibility(View.VISIBLE);
        pbMainLoading.setVisibility(View.GONE);

    }



}
