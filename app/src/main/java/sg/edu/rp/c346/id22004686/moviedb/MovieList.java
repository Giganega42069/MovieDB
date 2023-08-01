package sg.edu.rp.c346.id22004686.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    Button btnfilter,resetz;
    ListView lv;
    ArrayList<Movie> al;
    CustomAdapter adapter;
    Spinner filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movielist);

        lv = findViewById(R.id.lv);
        btnfilter = findViewById(R.id.btnFilter);
        resetz = findViewById(R.id.reseting);
        filter = findViewById(R.id.spinnerFilter);

        DBHelper db = new DBHelper(MovieList.this);

        al = db.getMovies();
        db.close();
        adapter = new CustomAdapter(MovieList.this, R.layout.row,al);
        lv.setAdapter(adapter);

        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Movie> newMovie = new ArrayList<>();
                for (int i = 0; i < db.getMovies().size(); i++) {
                    if (al.get(i).getRating().equals(filter.getSelectedItem().toString()) ) {
                        newMovie.add(al.get(i));
                    }
                    CustomAdapter adapter = new CustomAdapter(MovieList.this,R.layout.row,newMovie);
                    lv.setAdapter(adapter);
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = al.get(position);
                Intent i = new Intent(MovieList.this,
                        EditActivity.class);
                i.putExtra("datas", data);
                startActivity(i);
            }
        });

        resetz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MovieList.this);
                al.clear();
                al.addAll(dbh.getMovies());
                CustomAdapter adapter = new CustomAdapter(MovieList.this,R.layout.row,al);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        resetz.performClick();
    }
}
