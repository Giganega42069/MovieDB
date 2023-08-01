package sg.edu.rp.c346.id22004686.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate,btnDelete,btnCancel;
    TextView tvID;
    EditText title,genre,year;
    Spinner rating;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        title = findViewById(R.id.editMovietitle);
        genre = findViewById(R.id.editGenre);
        year = findViewById(R.id.editYear);
        rating = findViewById(R.id.spinnerRating1);
        tvID = findViewById(R.id.editMovieID);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("datas");
        tvID.setText("ID: " + data.getId());

        title.setText(data.getMtitle());
        genre.setText(data.getMgenre());
        String yearvalue = String.valueOf(data.getYear());
        year.setText(yearvalue);
        rating.setSelection(rating.getSelectedItemPosition());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setMtitle(title.getText().toString());
                data.setMgenre(genre.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                data.setRating(rating.getSelectedItem().toString());
                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteMovie(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
