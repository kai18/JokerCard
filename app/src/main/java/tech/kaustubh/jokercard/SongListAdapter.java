package tech.kaustubh.jokercard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kaustubh on 6/7/17.
 */

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongHolder> {
    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.songview, parent, false);
        //itemView.toString();
        Log.d("inside", "songlistadpater");
        return new SongHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        String trmp = "heelo";
        holder.text.setText("hello");
        Log.d("inside", "songlistadpater");

    }


    @Override
    public int getItemCount() {
        return 6;
    }

    public static class SongHolder extends RecyclerView.ViewHolder {
        TextView text;

        public SongHolder(View itemView) {
            super(itemView);
            Log.d("inside", "songlistadpater");
            text = (TextView) itemView.findViewById(R.id.songView);
        }


    }

}
