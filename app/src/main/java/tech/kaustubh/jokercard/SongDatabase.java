package tech.kaustubh.jokercard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kaustubh on 7/13/17.
 */

class SongDatabase {
    JokerDatabaseHelper helper;
    SQLiteDatabase databaseWriter;
    SQLiteDatabase databaseReader;
    Cursor songCursor;
    public SongDatabase(Context mainActivitycontext) {
        helper = new JokerDatabaseHelper(mainActivitycontext, null, null, 1);
        databaseWriter = helper.getWritableDatabase();
        databaseReader = helper.getReadableDatabase();
        String songQuery = "Select * From "+ JokerDatabaseHelper.ScrobbleTable;
        songCursor = databaseReader.rawQuery(songQuery, null);
        songCursor.moveToFirst();
    }

    private void getSongCursor()
    {


    }

    public Song getSong()
    {
        String projection[] ={JokerDatabaseHelper.TITLE, JokerDatabaseHelper.ALBUM,
                JokerDatabaseHelper.ARTIST};
        Song song = new Song();
        Log.d("Database", String.valueOf(songCursor.getColumnCount()));
        song.setArtist(songCursor.getString(2));
        song.setAlbum(songCursor.getString(1));
        song.setTitle(songCursor.getString(1));
        songCursor.moveToNext();
        return song;

    }

    public void insertSong(Song song)
    {
        ContentValues songValues = new ContentValues();
        songValues.put(JokerDatabaseHelper.ALBUM, song.getAlbum());
        songValues.put(JokerDatabaseHelper.ARTIST, song.getArtist());
        songValues.put(JokerDatabaseHelper.TITLE,song.getTitle());
        databaseWriter.insert(JokerDatabaseHelper.ScrobbleTable, null, songValues);
    }
}
