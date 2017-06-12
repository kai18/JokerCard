package tech.kaustubh.jokercard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity = null;

    RecyclerView songListView = null;
    SongListAdapter adapter = null;

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
        JokerDatabaseHelper helper = new JokerDatabaseHelper(this, null, null, 1);
        helper.getWritableDatabase();
    }

    public void updateSongList(Song song)
    {
        String title = song.getTitle();
        Log.d("Tac", title);
        songList.add(song);
        adapter.notifyDataSetChanged();
    }
}
