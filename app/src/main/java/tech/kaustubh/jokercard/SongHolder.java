package tech.kaustubh.jokercard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kaustubh on 6/10/17.
 */

public class SongHolder extends RecyclerView.ViewHolder {
    TextView text;

    public SongHolder(View itemView) {
        super(itemView);
        Log.d("inside", "songlistadpater");
        text = (TextView) itemView.findViewById(R.id.songView);
    }


}