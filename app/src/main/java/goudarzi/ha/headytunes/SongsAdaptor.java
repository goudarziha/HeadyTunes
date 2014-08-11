package goudarzi.ha.headytunes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class SongsAdaptor extends ArrayAdapter<Songs> {

    ArrayList<Songs> ArrayListSongs;
    int Resource;
    Context context;
    LayoutInflater vi;

    public SongsAdaptor(Context context, int resource, ArrayList<Songs> objects) {
        super(context, resource, objects);

        ArrayListSongs = objects;
        Resource = resource;
        this.context = context;

        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = vi.inflate(Resource, null);
            holder = new ViewHolder();
            holder.imageview = (ImageView) convertView.findViewById(R.id.ivImage);
            holder.name = (TextView) convertView.findViewById(R.id.tvName);
            holder.description = (TextView) convertView.findViewById(R.id.tvDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.imageview.setImageResource();
        holder.name.setText(ArrayListSongs.get(position).getName());
        holder.description.setText(ArrayListSongs.get(position).getDescription());

        return convertView;
    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView name;
        public TextView description;
        public TextView artist;
        public TextView tags;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
