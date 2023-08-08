package sg.edu.rp.c346.id22004686.moviedb;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amrdeveloper.lottiedialog.LottieDialog;
import com.google.android.material.button.MaterialButton;

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

//                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
//                myBuilder.setTitle("Danger");
//                myBuilder.setMessage("Are you sure you want to delete the movie "+data.getMtitle());
//                myBuilder.setCancelable(false);
//
//                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DBHelper dbh = new DBHelper(EditActivity.this);
//                        dbh.deleteMovie(data.getId());
//                        finish();
//                    }
//                });
//
//                myBuilder.setNeutralButton("CANCEL",null);
//                AlertDialog myDialog = myBuilder.create();
//                myDialog.show();
                LottieDialog dialog = new LottieDialog(EditActivity.this)
                        .setAnimation(R.raw.delete_animation)
                        .setAnimationRepeatCount(LottieDialog.INFINITE)
                        .setAutoPlayAnimation(true)
                        .setTitle("DELETE")
                        .setTitleColor(Color.WHITE)
                        .setMessage("Are you sure you want to delete the movie "+data.getMtitle())
                        .setMessageColor(Color.WHITE)
                        .setDialogBackground(Color.WHITE)
                        .setCancelable(false);

                Button okButton = new Button(EditActivity.this);
                okButton.setText("DELETE");
                okButton.setOnClickListener(view -> {
                    DBHelper dbh = new DBHelper(EditActivity.this);
                    dbh.deleteMovie(data.getId());
                    finish();
                });

                Button cancelButton = new Button(EditActivity.this);
                cancelButton.setText("CANCEL");
                cancelButton.setOnClickListener(view -> dialog.dismiss());

                dialog.addActionButton(cancelButton);
                dialog.addActionButton(okButton);
                dialog.show();
            }


        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
//                myBuilder.setTitle("Danger");
//                myBuilder.setMessage("Are you sure you want to discard the changes");
//                myBuilder.setCancelable(false);
//
//                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//
//                myBuilder.setNeutralButton("DO NOT DISCARD",null);
//                AlertDialog myDialog = myBuilder.create();
//                myDialog.show();

                LottieDialog dialog = new LottieDialog(EditActivity.this)
                        .setAnimation(R.raw.delete_animation)
                        .setAnimationRepeatCount(LottieDialog.INFINITE)
                        .setAutoPlayAnimation(true)
                        .setTitle("DELETE")
                        .setTitleColor(Color.WHITE)
                        .setMessage("Are you sure you want to delete the movie "+data.getMtitle())
                        .setMessageColor(Color.WHITE)
                        .setDialogBackground(Color.WHITE)
                        .setCancelable(false);

                Button okButton = new Button(EditActivity.this);
                okButton.setText("DISCARD");
                okButton.setOnClickListener(view -> {
                    finish();
                });

                Button cancelButton = new Button(EditActivity.this);
                cancelButton.setText("DO NOT DISCARD");
                cancelButton.setOnClickListener(view -> dialog.dismiss());

                dialog.addActionButton(cancelButton);
                dialog.addActionButton(okButton);
                dialog.show();
            }
        });
    }
}
