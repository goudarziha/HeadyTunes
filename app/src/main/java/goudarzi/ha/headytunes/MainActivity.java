package goudarzi.ha.headytunes;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView list;
    SongsAdaptor adapter;
    ArrayList<Songs> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.lvList);
        songsList = new ArrayList<Songs>();

        new SongsAsyncTask().execute("http://headytunes.co/?json=get_recent_posts");
    }

    public class SongsAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(params[0]);
                HttpResponse response = client.execute(post);

                //status line
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jObj = new JSONObject(data);
                    JSONArray jArray = jObj.getJSONArray("posts");

                    for (int i = 0; i < jArray.length(); i++){
                        Songs song = new Songs();

                        JSONObject jRealObject = jArray.getJSONObject(i);

                        song.setName(jRealObject.getString("title"));
                        song.setDescription(jRealObject.getString("excerpt"));
                        song.setImage(jRealObject.getString("image"));

                        songsList.add(song);
                    }
                    return true;
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (!result) {

            } else {
                SongsAdaptor adapter = new SongsAdaptor(getApplicationContext(),
                        R.layout.row ,
                        songsList);
                list.setAdapter(adapter);
            }
        }
    }
}
