package tech.kaustubh.jokercard.Receivers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;

import tech.kaustubh.jokercard.JokerDatabaseHelper;
import tech.kaustubh.jokercard.MainActivity;
import tech.kaustubh.jokercard.R;
import tech.kaustubh.jokercard.ScrobbleListActivity;
import tech.kaustubh.jokercard.Song;

/**
 * Created by kaustubh on 6/12/17.
 */

public class MusicReceiver extends BroadcastReceiver {

    private String track = "track";
    private String title = "title";
    private String album = "album";
    private String artist = "artist";
    private static final String nowPlayingKey  = "nowplaying";
    private static final String nowPlayingDefault  = "lol";
    private String table ="ScrobbleTable";

    private SharedPreferences sharedPref = null;
    private String currentSong = null;

    private JokerDatabaseHelper databaseHelper;



    MusicReceiver()
    {
        databaseHelper = new JokerDatabaseHelper(MainActivity.mainActivity, null, null, 2);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        sharedPref = context.getSharedPreferences(
                "joker", Context.MODE_PRIVATE);

        Bundle b = intent.getExtras();
        Song song = new Song();

        currentSong= sharedPref.getString(nowPlayingKey, nowPlayingDefault);
        Log.d("Current SOmg", currentSong);
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
    }

    public int insertSong(Song song)
    {
        if(currentSong.equals(song.getTitle()))
            return 0;
        else
        {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nowplaying", song.getTitle());
            editor.commit();
            Log.d(sharedPref.getString("nowplaying", "lol"), "SOng");
            if(ScrobbleListActivity.isActive)
                ScrobbleListActivity.scrobbleListActivity.updateSongList(song);
            else
            {
                SQLiteDatabase dbHandler = databaseHelper.getWritableDatabase();
                ContentValues songValues = new ContentValues();
                songValues.put("Album", song.getAlbum());
                songValues.put("Artist", song.getArtist());
                songValues.put("Title", song.getTitle());
                long result = dbHandler.insert(table, null, songValues);
                if(result == -1)
                    Toast.makeText(MainActivity.mainActivity, "Unsuccessful",Toast.LENGTH_LONG);
                else
                    Toast.makeText(MainActivity.mainActivity, "successful", Toast.LENGTH_LONG);
                Log.d("Result", String.valueOf(result));
                return 0;
            }

        }
        return 1;
    }
}
