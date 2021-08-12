package sg.edu.rp.c346.id20024402.mywatchlist;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String name;
    private String review;
    private String rating;
    private int stars;

    public Movie(String name, String review, String rating, int stars) {
        this.name = name;
        this.review = review;
        this.rating = rating;
        this.stars = stars;
    }

    public Movie(int id, String name, String review, String rating, int stars) {
        this.id = id;
        this.name = name;
        this.review = review;
        this.rating = rating;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public String getRating() {
        return rating;
    }

    public int getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String starString = "";

        for(int i = 0; i < stars; i++){
            starString += "*";
        }
        return name + "\n" + review + " - " + rating + "\n" + starString;

    }
}
