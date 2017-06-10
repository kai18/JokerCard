package tech.kaustubh.jokercard.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by kaustubh on 6/10/17.
 */

public class AndroidMusic extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Player", "Logged");
        Log.d("Action", intent.getAction());
        Bundle b = intent.getExtras();
        for (String key: b.keySet()) {
            Log.d("Key", key);

        }
    }
}
