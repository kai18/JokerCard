package tech.kaustubh.jokercard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity = null;

    RecyclerView songList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        songList = (RecyclerView) findViewById(R.id.songList);
        SongListAdapter adapter = new SongListAdapter();
        songList.setAdapter(adapter);
        songList.setItemAnimator(new DefaultItemAnimator());
        songList.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
        Log.d("at", "main");

    }
}
