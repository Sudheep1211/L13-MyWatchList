package sg.edu.rp.c346.id20024402.mywatchlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> MovieList;

    public CustomAdapter (Context context, int resource, ArrayList<Movie> objects){
        super (context, resource, objects);

        parent_context = context;
        layout_id = resource;
        MovieList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvRating = rowView.findViewById(R.id.tvRating);
        TextView tvReview = rowView.findViewById(R.id.tvReview);

        RatingBar rbStars = rowView.findViewById(R.id.ratingBarStars);


        Movie currentMovie = MovieList.get(position);


        tvName.setText(currentMovie.getName());
        tvReview.setText(currentMovie.getReview());
        tvRating.setText(currentMovie.getRating());

        if(currentMovie.getStars() == 1){
            rbStars.setRating(1);

        } if (currentMovie.getStars() == 2){
            rbStars.setRating(2);

        } if (currentMovie.getStars() == 3){
            rbStars.setRating(3);

        } if (currentMovie.getStars() == 4){
            rbStars.setRating(4);

        } if (currentMovie.getStars() == 5){
            rbStars.setRating(5);
        }

        ImageView ivNew = rowView.findViewById(R.id.imageViewRating);

        if(currentMovie.getRating().equalsIgnoreCase("PG13")){
            ivNew.setImageResource(R.drawable.pg13_rating);
        } else if(currentMovie.getRating().equalsIgnoreCase("NC16")){
            ivNew.setImageResource(R.drawable.nc16_rating);
        } else if(currentMovie.getRating().equalsIgnoreCase("M18")){
            ivNew.setImageResource(R.drawable.m18_rating);
        } else if(currentMovie.getRating().equalsIgnoreCase("R21")){
            ivNew.setImageResource(R.drawable.r21_rating);
        }

        return rowView;
    }
}
