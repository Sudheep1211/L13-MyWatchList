package sg.edu.rp.c346.id20024402.mywatchlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIE = "Movie";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_REVIEW = "review";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createSongTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_REVIEW + " TEXT, "
                + COLUMN_RATING + " TEXT, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createSongTableSql);
        Log.i("info", createSongTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public long insertMovie(String name, String review, String rating, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REVIEW, review);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_MOVIE, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieslist = new ArrayList<Movie>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_REVIEW + ","
                + COLUMN_RATING + ","
                + COLUMN_STARS + " FROM " + TABLE_MOVIE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String rating = cursor.getString(3);
                int stars = cursor.getInt(4);

                Movie newMovie = new Movie(id, name, description, rating, stars);
                movieslist.add(newMovie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieslist;
    }

    public ArrayList<Movie> getAllIslandsByRating(int moviesFilter) {
        ArrayList<Movie> movieslist = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_REVIEW, COLUMN_RATING, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(moviesFilter)};

        //String selectQuery = "SELECT " + COLUMN_ID + ","
        //            + COLUMN_TITLE + ","
        //            + COLUMN_SINGERS + ","
        //            + COLUMN_YEAR + ","
        //            + COLUMN_STARS
        //            + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_MOVIE, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String review = cursor.getString(2);
                String rating = cursor.getString(3);
                int stars = cursor.getInt(4);

                Movie newMovie = new Movie(id, name, review, rating, stars);
                movieslist.add(newMovie);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return movieslist;
    }

    public int updateMovie(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_REVIEW, data.getReview());
        values.put(COLUMN_RATING, data.getRating());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }


    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }
}

