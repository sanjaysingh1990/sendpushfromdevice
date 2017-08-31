package imenufr.com.sendpushdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
public void push(View view)
{
    new GetData().execute();
}

class GetData extends AsyncTask<String,Void,String>
{


    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnectionExample httpURLConnectionExample=new HttpURLConnectionExample();
        try {
           return httpURLConnectionExample.sendPost();
        }
        catch (Exception ex)
        {
            return  ex.getMessage();
        }
        }

    @Override
    protected void onPostExecute(String s) {
     Log.e("data",s+"");
    }
}
}
