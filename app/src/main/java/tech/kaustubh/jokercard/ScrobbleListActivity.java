package tech.kaustubh.jokercard;

import android.os.Bundle;
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

    private RecyclerView songListView = null;

    private SongListAdapter adapter = null;

    private Song nowPlaying = null;
    private SongDatabase db = null;

    private ArrayList<Song> songList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlistview);
        isActive = true;

        scrobbleListActivity = this;

        songList = new ArrayList<>();
        songListView = (RecyclerView) findViewById(R.id.songList);

        adapter = new SongListAdapter(songList);
        songListView.setAdapter(adapter);
        songListView.setItemAnimator(new DefaultItemAnimator());
        songListView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("at", "main");

        db = SongDatabase.getSongDatabase(this);
        if(db.getCount() > 0)
        {
            int numSong =  db.getCount();
            while (numSong-- > 0)
            {
                Song song = db.getSong();
                if(song != null)
                    songList.add(song);
            }
            adapter.notifyDataSetChanged();
        }
    }

    public void updateSongList(Song song) {
        Log.d("Called", "updatelist");

        if (nowPlaying == null) {
            nowPlaying = song;
            songList.add(song);
        }

        String title = song.getTitle();
        Log.d("Tac", title);
        Log.d(String.valueOf(nowPlaying.getTitle().length()), String.valueOf(song.getTitle().length()));

        /*if (nowPlaying.getTitle().equals(song.getTitle())) {
            Log.d("Exiting", "Update");
            return;
        }*/

        if (!nowPlaying.getTitle().equals(song.getTitle())) {
            Log.d("Should we be", "Here?");
            db.insertSong(song);
            nowPlaying = song;
            songList.add(song);
            adapter.notifyDataSetChanged();
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;

    }


    @Override
    public void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isActive = true;
    }

}
