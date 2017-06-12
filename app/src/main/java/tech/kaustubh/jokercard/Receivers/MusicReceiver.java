package tech.kaustubh.jokercard.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import tech.kaustubh.jokercard.JokerDatabaseHelper;
import tech.kaustubh.jokercard.MainActivity;
import tech.kaustubh.jokercard.Song;

/**
 * Created by kaustubh on 6/12/17.
 */

public class MusicReceiver extends BroadcastReceiver {
    JokerDatabaseHelper databaseHelper;
    String track = "track";
    String title = "title";
    String album = "album";
    String artist = "artist";
    MusicReceiver()
    {
        databaseHelper = new JokerDatabaseHelper(MainActivity.mainActivity, null, null, 2);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();
        Song song = new Song();
        if (b.containsKey(track))
        {
            song.setTitle(b.getString(track));
        }
        else if(b.containsKey(title))
            song.setTitle(b.getString(title));
        if (b.containsKey(album))
            song.setAlbum(b.getString(album));
        if (b.containsKey(artist))
            song.setArtist(b.getString(artist));
        this.insertSong(song);
        Log.d("rEAD","SDKD");
    }

    public void insertSong(Song song)
    {
        SQLiteDatabase dbHandler = databaseHelper.getWritableDatabase();
    }
}
