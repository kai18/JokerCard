package tech.kaustubh.jokercard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kaustubh on 6/12/17.
 */

public class JokerDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Joker";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE = "CREATE TABLE ScrobbleTable" +
            "(id integer primary key autoincrement," +
            "Title varchar(255)," +
            "Album varchar(255));";
    public JokerDatabaseHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        Log.d("DB", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
