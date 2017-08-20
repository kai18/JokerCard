package tech.kaustubh.jokercard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kaustubh on 7/13/17.
 */

public class SongDatabase {
    private static SongDatabase songDatabase;
    private JokerDatabaseHelper helper;
    private SQLiteDatabase databaseWriter;
    private SQLiteDatabase databaseReader;
    private Cursor cursor;

    private SongDatabase(Context mainActivitycontext) {

        helper = new JokerDatabaseHelper(mainActivitycontext, null, null, 1);
        databaseWriter = helper.getWritableDatabase();
        databaseReader = helper.getReadableDatabase();

        String projection[] = {"id", JokerDatabaseHelper.TITLE, JokerDatabaseHelper.ALBUM,
                JokerDatabaseHelper.ARTIST};
        String sortColumn = "id";
        String sortOrder = "Desc";

        cursor = databaseReader.query(JokerDatabaseHelper.ScrobbleTable,
                projection, null, null, null, null, sortColumn + " " + sortOrder);

        if (cursor == null)
            Log.d("Cursor", "is null");

        cursor.moveToFirst();
    }

    public static SongDatabase getSongDatabase(Context context) {
        if (songDatabase == null)
            songDatabase = new SongDatabase(context);
        return songDatabase;
    }

    public Song getSong() {

        Song song = new Song();

        if (cursor.getCount() > 0) {

            Log.d("Database count", String.valueOf(cursor.getColumnCount()));
            //Log.d("Song id", String.valueOf(cursor.getString(0)));

            song.setArtist(cursor.getString(3));
            song.setAlbum(cursor.getString(2));
            song.setTitle(cursor.getString(1));
            cursor.moveToNext();
        }

        else
            song = null;
        return song;

    }

    public int getCount()
    {

        return cursor.getCount();
    }


    public void insertSong(Song song) {
        ContentValues songValues = new ContentValues();
        songValues.put(JokerDatabaseHelper.ALBUM, song.getAlbum());
        songValues.put(JokerDatabaseHelper.ARTIST, song.getArtist());
        songValues.put(JokerDatabaseHelper.TITLE, song.getTitle());
        databaseWriter.insert(JokerDatabaseHelper.ScrobbleTable, null, songValues);
    }
}
