package tech.kaustubh.jokercard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity = null;

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        SharedPreferences sharedPref = this.getSharedPreferences(
                "nowplaying", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("nowplaying", "lol");
        editor.commit();

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

    public void login(View view)
    {
        EditText userId = (EditText) findViewById(R.id.userId);
        EditText password = (EditText) findViewById(R.id.password);
        String id = String.valueOf(userId.getText());
        String pass = String.valueOf(password.getText());
        SharedPreferences sharedPref = this.getSharedPreferences(
                "nowplaying", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userId", id);
        editor.commit();

        startActivity(new Intent(this, ScrobbleListActivity.class));

    }


}
