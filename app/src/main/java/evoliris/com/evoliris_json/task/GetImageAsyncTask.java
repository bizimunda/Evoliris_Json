package evoliris.com.evoliris_json.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by temp on 31/08/2016.
 */
public class GetImageAsyncTask extends AsyncTask <String, Void, Bitmap> {

    private ImageView imageView;

    public GetImageAsyncTask(ImageView imageView){
        this.imageView=imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap image= null;
        InputStream is=null;

        try{
            String strUrl = "http://openweathermap.org/img/w/" + params[0] + ".png";
            URL url= new URL(strUrl);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.connect();
            is= connection.getInputStream();

            image= BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } if(is!=null){
            try{
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return image;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
