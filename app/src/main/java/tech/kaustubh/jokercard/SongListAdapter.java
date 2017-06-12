package tech.kaustubh.jokercard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kaustubh on 6/7/17.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongHolder> {

    ArrayList<Song> songList = null;

    public SongListAdapter(ArrayList<Song> songList) {
        this.songList = songList;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.songview, parent, false);
        Log.d("inside", "songlistadpater");
        return new SongHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        holder.text.setText((CharSequence) songList.get(position).getTitle());
        Log.d("inside", "songlistadpater");

    }


    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView text;

        public SongHolder(View itemView) {
            super(itemView);
            Log.d("inside", "songlistadpater");
            text = (TextView) itemView.findViewById(R.id.songView);
        }
    }
}
