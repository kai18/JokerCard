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
    private static final int DB_VERSION = 7;
    static final String TITLE = "Title";
    static final String ALBUM = "Album";
    static final String ARTIST ="Artist";
    static final String ScrobbleTable = "ScrobbleTable";

    private static final String DB_CREATE = "CREATE TABLE IF NOT EXIST "+ScrobbleTable +
            "(id integer primary key autoincrement," +
            TITLE+ "varchar(255)," +
            ALBUM+ "varchar(255)," +
             "Artist varchar(255));";
    public JokerDatabaseHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        Log.d("DB", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "alter table ScrobbleTable add column Artist char(50);";
        db.execSQL(drop);
        Log.d("On","Upgrade");
    }
}
