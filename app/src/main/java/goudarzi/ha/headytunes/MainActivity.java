package goudarzi.ha.headytunes;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity {

    TextView httpStuff;
    HttpClient client;
    JSONObject json;

    final static String URL = "headytunes.co/?json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();
        new Read().execute("temp");
    }

    public JSONObject lastPost(String number) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        url.append(number);

        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray posts = new JSONArray(data);
            JSONObject last = posts.getJSONObject(0);
            return last;
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_LONG);
            return null;
        }
    }

    public class Read extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                json = lastPost("post");
                return json.getString(String[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
