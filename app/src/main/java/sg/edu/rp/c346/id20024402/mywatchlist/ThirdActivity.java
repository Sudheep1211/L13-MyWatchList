package sg.edu.rp.c346.id20024402.mywatchlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etReview, etRating;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle("My Watch List ~ Modify Movie");

        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        rg = (RadioGroup) findViewById(R.id.rgStars);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.editTextName);
        etRating = (EditText) findViewById(R.id.editTextRating);
        etReview = (EditText) findViewById(R.id.editTextReview);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        etID.setText(currentMovie.getId()+"");
        etName.setText(currentMovie.getName());
        etReview.setText(currentMovie.getReview());
        etRating.setText(currentMovie.getRating());

        switch (currentMovie.getStars()){
            case 5: rb5.setChecked(true);
                break;
            case 4: rb4.setChecked(true);
                break;
            case 3: rb3.setChecked(true);
                break;
            case 2: rb2.setChecked(true);
                break;
            case 1: rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentMovie.setName(etName.getText().toString().trim());
                currentMovie.setReview(etReview.getText().toString().trim());
                currentMovie.setRating(etRating.getText().toString().trim());

                int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);
                currentMovie.setStars(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateMovie(currentMovie);
                if (result > 0){
                    Toast.makeText(ThirdActivity.this, "Movie updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Movie update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie\n" + etName.getText().toString());
                myBuilder.setCancelable(true);

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteMovie(currentMovie.getId());
                        if (result>0){
                            Toast.makeText(ThirdActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Movie delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setPositiveButton("CANCEL", null);
                AlertDialog alertDialog = myBuilder.create();
                myBuilder.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(true);

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                AlertDialog alertDialog = myBuilder.create();
                alertDialog.show();
            }
        });

    }


}