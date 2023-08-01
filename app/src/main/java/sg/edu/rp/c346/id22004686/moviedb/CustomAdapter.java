package sg.edu.rp.c346.id22004686.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context,resource,objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView title = rowView.findViewById(R.id.movieTitle);
        TextView genre = rowView.findViewById(R.id.Genre);
        TextView year = rowView.findViewById(R.id.Year);
        ImageView rating = rowView.findViewById(R.id.imageView);

        // Obtain the Android Version information based on the position
        Movie currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        title.setText(currentVersion.getMtitle());
        year.setText(currentVersion.getYear()+"");
        genre.setText(currentVersion.getMgenre());

        if (currentVersion.getRating().equals("G")){
            rating.setImageResource(R.drawable.rating_g);
        } else if (currentVersion.getRating().equals("PG")) {
            rating.setImageResource(R.drawable.rating_pg);
        } else if (currentVersion.getRating().equals("PG13")) {
            rating.setImageResource(R.drawable.rating_pg13);
        } else if (currentVersion.getRating().equals("NC16")) {
            rating.setImageResource(R.drawable.rating_nc16);
        } else if (currentVersion.getRating().equals("M18")) {
            rating.setImageResource(R.drawable.rating_m18);
        } else if (currentVersion.getRating().equals("R21")) {
            rating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }

}
