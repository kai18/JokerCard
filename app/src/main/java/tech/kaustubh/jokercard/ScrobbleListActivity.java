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
    public static boolean isActive = false;

    RecyclerView songListView = null;

    SongListAdapter adapter = null;

    Song nowPlaying = null;
    SongDatabase db = null;

    ArrayList<Song> songList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlistview);
        scrobbleListActivity = this;
        songList = new ArrayList<>();
        songListView = (RecyclerView) findViewById(R.id.songList);
        adapter = new SongListAdapter(songList);
        songListView.setAdapter(adapter);
        songListView.setItemAnimator(new DefaultItemAnimator());
        songListView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("at", "main");
        db = new SongDatabase(this);
        int numSongList = 10;
        while(numSongList-- > 0) {
            Song song = db.getSong();
            Log.d("Adding Song", song.getTitle());
            songList.add(song);
        }
        adapter.notifyDataSetChanged();

        isActive = true;
    }

    public void updateSongList(Song song)
    {
        if(nowPlaying == null) {
            nowPlaying = song;
            songList.add(song);
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
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

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
