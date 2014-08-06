package goudarzi.ha.headytunes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity {

    TextView httpStuff;
    HttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpStuff = (TextView) findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();

    }

    public JSONObject lastPost(String number) throws ClientProtocolException, IOException, JSONException {
        return null;
    }
}
