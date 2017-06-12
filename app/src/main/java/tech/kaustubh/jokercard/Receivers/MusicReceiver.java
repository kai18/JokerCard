package tech.kaustubh.jokercard.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import tech.kaustubh.jokercard.JokerDatabaseHelper;
import tech.kaustubh.jokercard.MainActivity;
import tech.kaustubh.jokercard.Song;

/**
 * Created by kaustubh on 6/12/17.
 */

public class MusicReceiver extends BroadcastReceiver {
    JokerDatabaseHelper databaseHelper;
    MusicReceiver()
    {
        databaseHelper = new JokerDatabaseHelper(MainActivity.mainActivity, null, null, 2);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        

    }

    public void updateDatabase(Song song)
    {
        SQLiteDatabase dbHandler = databaseHelper.getWritableDatabase();
    }
}
