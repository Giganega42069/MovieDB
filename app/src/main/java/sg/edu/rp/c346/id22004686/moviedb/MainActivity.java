package sg.edu.rp.c346.id22004686.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insert,getmovie;
    EditText title,genre,year;
    Spinner rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        getmovie = findViewById(R.id.btnShow);
        title = findViewById(R.id.editMovietitle);
        genre = findViewById(R.id.editGenre);
        year = findViewById(R.id.editYear);
        rating = findViewById(R.id.spinnerRating);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertMovie(title.getText().toString(),genre.getText().toString(), Integer.parseInt(year.getText().toString()),rating.getSelectedItem().toString());
                db.close();

                Toast.makeText(MainActivity.this, "Movie " + title.getText().toString() + " has been inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        getmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MovieList.class);
                startActivity(intent);
            }
        });
    }
}