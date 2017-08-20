package tech.kaustubh.jokercard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getSharedPreferences(JokerPreference.jokerPreferenceFile
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(JokerPreference.nowPlayingKey, JokerPreference.nowPlayingDefault);
        editor.apply();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void login(View view) {
        EditText userId = (EditText) findViewById(R.id.userId);
        String id = String.valueOf(userId.getText());
        SharedPreferences sharedPref = this.getSharedPreferences(
                "nowplaying", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userId", id);
        editor.apply();

        this.startActivity(new Intent(this, ScrobbleListActivity.class));
    }


}
