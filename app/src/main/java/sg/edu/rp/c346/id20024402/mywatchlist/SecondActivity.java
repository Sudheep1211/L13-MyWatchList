package sg.edu.rp.c346.id20024402.mywatchlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Movie> MovieList;
    // ArrayAdapter adapter;
    CustomAdapter caMovie;
    String moduleCode;
    int requestCode = 9;
    Button btn5Stars;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        MovieList.clear();
        MovieList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle("My Watch List ~ Show Movies");

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        MovieList = dbh.getAllMovies();
        dbh.close();

        caMovie = new CustomAdapter(this, R.layout.row, MovieList);
        lv.setAdapter(caMovie);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("movie", MovieList.get(position));
                startActivityForResult(i, requestCode);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                MovieList.clear();
                MovieList.addAll(dbh.getAllIslandsByRating(5));
                caMovie.notifyDataSetChanged();
            }
        });


    }
}