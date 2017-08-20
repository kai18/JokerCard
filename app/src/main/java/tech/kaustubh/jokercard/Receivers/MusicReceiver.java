package tech.kaustubh.jokercard.Receivers;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import tech.kaustubh.jokercard.JokerDatabaseHelper;
import tech.kaustubh.jokercard.MainActivity;
import tech.kaustubh.jokercard.ScrobbleListActivity;
import tech.kaustubh.jokercard.Song;
import tech.kaustubh.jokercard.SongDatabase;

/**
 * Created by kaustubh on 6/12/17.
 */

public class MusicReceiver extends BroadcastReceiver {

    private static final String nowPlayingKey = "nowplaying";
    private static final String nowPlayingDefault = "lol";
    private String track = "track";
    private String title = "title";
    private String album = "album";
    private String artist = "artist";
    private String table = "ScrobbleTable";

    private SharedPreferences sharedPref = null;
    private String currentSong = null;

    private JokerDatabaseHelper databaseHelper;

    private SongDatabase songDatabase = null;
    public MusicReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        databaseHelper = new JokerDatabaseHelper(context, null, null, 2);

        sharedPref = context.getSharedPreferences(
                "joker", Context.MODE_PRIVATE);

        songDatabase = SongDatabase.getSongDatabase(context);

        Bundle b = intent.getExtras();
        Song song = new Song();

        currentSong = sharedPref.getString(nowPlayingKey, nowPlayingDefault);
        Log.d("Current Song", currentSong);
        if (b.containsKey(track)) {
            song.setTitle(b.getString(track));
        } else if (b.containsKey(title))
            song.setTitle(b.getString(title));
        if (b.containsKey(album))
            song.setAlbum(b.getString(album));
        if (b.containsKey(artist))
            song.setArtist(b.getString(artist));
        this.insertSong(song);
    }

    public int insertSong(Song song) {
        if (currentSong.equals(song.getTitle()))
            return 0;
        else {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nowplaying", song.getTitle());
            editor.apply();
            Log.d(sharedPref.getString("nowplaying", "lol"), "Song");
            if (ScrobbleListActivity.isActive)
                ScrobbleListActivity.scrobbleListActivity.updateSongList(song);
            else {
                songDatabase.insertSong(song);
                return 0;
            }

        }
        return 1;
    }
}
