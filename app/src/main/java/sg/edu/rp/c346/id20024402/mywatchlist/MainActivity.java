package sg.edu.rp.c346.id20024402.mywatchlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etReview, etRating;
    Button btnInsert, btnShowList;
    RatingBar rbStars;
    Button btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("My Watch List ~ Insert Movie");

        etName = findViewById(R.id.etName);
        etReview = findViewById(R.id.etReview);
        etRating = findViewById(R.id.etRating);
        btnInsert = findViewById(R.id.btnInsertMovie);
        btnShowList = findViewById(R.id.btnShowList);
        btnWeb = findViewById(R.id.buttonWeb);

        rbStars = findViewById(R.id.ratingBarInput);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String review = etReview.getText().toString().trim();
                String rating = etRating.getText().toString().trim();

                if (name.length() == 0 || review.length() == 0 || rating.length() == 0 ){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                int stars = (int) rbStars.getRating();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertMovie(name, review, rating, stars);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Movie inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etReview.setText("");
                    etRating.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Movie insert failed", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.allmovie.com/"));
                startActivity(intent);
            }
        });


    }

}