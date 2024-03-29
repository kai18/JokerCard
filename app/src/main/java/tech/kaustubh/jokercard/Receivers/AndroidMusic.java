package tech.kaustubh.jokercard.Receivers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kaustubh on 6/10/17.
 */

public class AndroidMusic extends MusicReceiver {
    public static final String ACTION_ANDROID_PLAYSTATECHANGED = "com.android.music.playstatechanged";
    public static final String ACTION_ANDROID_STOP = "com.android.music.playbackcomplete";
    public static final String ACTION_ANDROID_METACHANGED = "com.android.music.metachanged";

    public static final String PACKAGE_NAME = "com.android.music";

    public AndroidMusic() {
        super();

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Player", "Logged");
        Log.d("Action", intent.getAction());
        super.onReceive(context, intent);
    }
}
