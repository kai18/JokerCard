package tech.kaustubh.jokercard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {

    RecyclerView songList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songList = (RecyclerView) findViewById(R.id.songList);
        songList.setLayoutManager(new LinearLayoutManager(this));
        SongListAdapter adapter = new SongListAdapter();
        songList.setAdapter(adapter);
        songList.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
        Log.d("at", "main");

    }
}
