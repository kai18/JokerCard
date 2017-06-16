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
    public static final String ACTION_ANDROID_PLAYSTATECHANGED = "com.android.music.playstatechanged";
    public static final String ACTION_ANDROID_STOP = "com.android.music.playbackcomplete";
    public static final String ACTION_ANDROID_METACHANGED = "com.android.music.metachanged";

    public static final String PACKAGE_NAME = "com.android.music";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Player", "Logged");
        Log.d("Action", intent.getAction());
        Bundle b = intent.getExtras();
        for (String key: b.keySet()) {
            Log.d("Key", key);
        }
        if(intent.getAction() == ACTION_ANDROID_METACHANGED) {
            Song song = new Song();
            Log.d("Track", (String) b.get("track"));
            Log.d("album", (String) b.get("album"));
            song.setTitle((String) b.get("track"));
            song.setAlbum((String) b.get("album"));
            super.onReceive(context, intent);
        }
    }
}
