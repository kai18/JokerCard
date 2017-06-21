package tech.kaustubh.jokercard;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kaustubh on 6/21/17.
 */

public class ScrobbleListActivity extends AppCompatActivity {
    public static ScrobbleListActivity scrobbleListActivity = null;
    RecyclerView songListView = null;
    SongListAdapter adapter = null;
    Song nowPlaying = null;
    ArrayList<Song> songList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrobbleListActivity = this;
        songList = new ArrayList<>();
        songListView = (RecyclerView) findViewById(R.id.songList);
        adapter = new SongListAdapter(songList);
        songListView.setAdapter(adapter);
        songListView.setItemAnimator(new DefaultItemAnimator());
        songListView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("at", "main");
        JokerDatabaseHelper helper = new JokerDatabaseHelper(this, null, null, 1);
        helper.getWritableDatabase();
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
            nowPlaying = song;
            songList.add(song);
            adapter.notifyDataSetChanged();
        }
    }
}
