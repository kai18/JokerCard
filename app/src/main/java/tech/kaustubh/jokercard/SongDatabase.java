package tech.kaustubh.jokercard;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kaustubh on 7/13/17.
 */

class SongDatabase {
    JokerDatabaseHelper helper;
    SQLiteDatabase databaseWriter;
    SQLiteDatabase databaseReader;
    public SongDatabase(Context mainActivitycontext) {
        helper = new JokerDatabaseHelper(mainActivitycontext, null, null, 1);
        databaseWriter = helper.getWritableDatabase();
        databaseReader = helper.getReadableDatabase();
    }

    /* public Song getSong()
    {


    }*/

    public void putSong(Song song)
    {
        ContentValues songValues = new ContentValues();
        songValues.put(JokerDatabaseHelper.ALBUM, song.getAlbum());
        songValues.put(JokerDatabaseHelper.ARTIST, song.getArtist());
        songValues.put(JokerDatabaseHelper.TITLE,song.getTitle());
        databaseWriter.insert(JokerDatabaseHelper.ScrobbleTable, null, songValues);
    }
}
