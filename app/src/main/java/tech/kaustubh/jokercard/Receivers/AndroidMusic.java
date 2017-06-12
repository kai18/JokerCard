package tech.kaustubh.jokercard.Receivers;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import tech.kaustubh.jokercard.MainActivity;
import tech.kaustubh.jokercard.Song;

/**
 * Created by kaustubh on 6/10/17.
 */

public class AndroidMusic extends MusicReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Player", "Logged");
        Log.d("Action", intent.getAction());
        Bundle b = intent.getExtras();
        for (String key: b.keySet()) {
            Log.d("Key", key);

        }
        Song song = new Song();
        Log.d("Track", (String) b.get("track"));
        Log.d("album", (String) b.get("album"));
        song.setTitle((String) b.get("track"));
        song.setAlbum((String) b.get("album"));
        super.onReceive(context, intent);

    }
}
