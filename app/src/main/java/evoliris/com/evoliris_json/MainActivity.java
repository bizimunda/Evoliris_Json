package evoliris.com.evoliris_json;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import evoliris.com.evoliris_json.task.GetAsyncTask;


public class MainActivity extends ActionBarActivity implements GetAsyncTask.GetAsynTaskCallback{

    private Button btnMainRequest;
    private ProgressBar pbMainLoading;
    private TextView tvMainResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMainRequest= (Button) findViewById(R.id.btn_main_request);
        pbMainLoading= (ProgressBar) findViewById(R.id.pb_main);
        tvMainResult= (TextView) findViewById(R.id.tv_main_result);

        btnMainRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAsyncTask task= new GetAsyncTask(MainActivity.this);
                task.execute("http://www.google.be");

            }
        });
    }


    @Override
    public void onPreGet() {
        tvMainResult.setVisibility(View.GONE);
        pbMainLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPostGet(String s) {
        tvMainResult.setText(s);
        tvMainResult.setVisibility(View.VISIBLE);
        pbMainLoading.setVisibility(View.GONE);

    }
}
