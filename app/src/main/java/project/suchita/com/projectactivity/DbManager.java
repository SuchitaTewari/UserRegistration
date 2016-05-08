package project.suchita.com.projectactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SuchitaTewari on 4/29/16.
 */
public class DbManager extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static DbManager dbManager ;
    private DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DbManager getInstance(Context context){
        if(dbManager==null)
            dbManager = new DbManager(context);
        return dbManager;

    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(FeedOrm.SQL_CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(FeedOrm.SQL_DROP_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}