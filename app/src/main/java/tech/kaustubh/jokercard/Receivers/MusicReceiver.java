package tech.kaustubh.jokercard.Receivers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
    String table ="ScrobbleTable";
    Song nowPlaying = null;
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

    public int insertSong(Song song)
    {
        if (nowPlaying != null && nowPlaying.getTitle() == song.getTitle())
            return 1;
        nowPlaying = song;
        SQLiteDatabase dbHandler = databaseHelper.getWritableDatabase();
        ContentValues songValues = new ContentValues();
        songValues.put("Album", song.getAlbum());
        songValues.put("Artist", song.getArtist());
        songValues.put("Title", song.getTitle());
       /* long result = dbHandler.insert(table, null, songValues);
        if(result == -1)
            Toast.makeText(MainActivity.mainActivity, "Unsuccessful",Toast.LENGTH_LONG);
        else
            Toast.makeText(MainActivity.mainActivity, "successful",Toast.LENGTH_LONG);
        Log.d("Result", String.valueOf(result));*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            MainActivity.mainActivity.updateSongList(song);
        }
        return 0;
    }
}
