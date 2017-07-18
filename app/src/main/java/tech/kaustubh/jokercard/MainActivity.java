package tech.kaustubh.jokercard;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity = null;

    RecyclerView songListView = null;
    SongListAdapter adapter = null;
    Song nowPlaying = null;
    ScrobblingNowNotification notification = null;
    SongDatabase db = null;


    ArrayList<Song> songList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        songList = new ArrayList<>();
        songListView = (RecyclerView) findViewById(R.id.songList);
        adapter = new SongListAdapter(songList);
        songListView.setAdapter(adapter);
        songListView.setItemAnimator(new DefaultItemAnimator());
        songListView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("at", "main");
        db = new SongDatabase(this);
        int numSongList = 10;
        while(numSongList-- > 0)
            songList.add(db.getSong());
        adapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    
    public void updateSongList(Song song)
    {
        if(nowPlaying == null) {
            nowPlaying = song;
            songList.add(song);
            adapter.notifyDataSetChanged();
        }
        String title = song.getTitle();
        Log.d("Tac", title);
        Log.d(String.valueOf(nowPlaying.getTitle().length()), String.valueOf(song.getTitle().length()));
        if (nowPlaying.getTitle().equals(song.getTitle()))
        {
            Log.d("Exiting","Update");
            return;
        }
        if (nowPlaying.getTitle() != song.getTitle())
        {
            Log.d("Should we be", "Here?");
            db.insertSong(song);
            nowPlaying = song;
            songList.add(song);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

}
