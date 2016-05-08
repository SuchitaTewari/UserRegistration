package project.suchita.com.projectactivity; /**
 * Created by SuchitaTewari on 5/1/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FeedOrm {
    private static final String TAG = "FeedOrm";

    private static final String TABLE_NAME = "Items";

    private static final String COMMA_SEP = ", ";

    private static final String COLUMN_ID_TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String COLUMN_ID = "Id";

    private static final String COLUMN_Item_NAME_TYPE = "TEXT";
    private static final String COLUMN_Item_NAME = "ItemName";

    private static final String COLUMN_Description_TYPE = "TEXT";
    private static final String COLUMN_Description_NAME = "Description";

    private static final String COLUMN_Unit_TYPE = "INTEGER";
    private static final String COLUMN_Unit = "Unit";

    private static final String COLUMN_Price_TYPE = "TEXT";
    private static final String COLUMN_Price = "Price";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " (" + COLUMN_ID + " " + COLUMN_ID_TYPE
            + COMMA_SEP + COLUMN_Item_NAME + " " + COLUMN_Item_NAME_TYPE
            + COMMA_SEP + COLUMN_Description_NAME + " " + COLUMN_Description_TYPE
            + COMMA_SEP + COLUMN_Unit + " " + COLUMN_Unit_TYPE
            + COMMA_SEP + COLUMN_Price + " " + COLUMN_Price_TYPE
            + ")";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public static void insertFeed(Context context, Feed feed) {
        DbManager databaseWrapper = DbManager.getInstance(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        ContentValues values = feedToContentValues(feed);

        long postId = database.insert(FeedOrm.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted new feed with ID: " + feed.getId());
    }

    public static Feed getFeedById(Context context, String id) {
        DbManager databaseWrapper = DbManager.getInstance(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM "
                + FeedOrm.TABLE_NAME + " WHERE " + COLUMN_ID
                + " = " + id + "", null);
        Feed feed = null;

        Log.i(TAG, "Loaded " + cursor.getCount() + " id ...");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            feed = cursorToFeed(cursor);

            cursor.moveToNext();

            Log.i(TAG, "Customer loaded successfully.");
        }

        return feed;
    }

    public static int updateFeed(Context context, Feed feed) {
        DbManager databaseWrapper = DbManager.getInstance(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        String where = (FeedOrm.COLUMN_ID + " = " + feed
                .getId());
        ContentValues values = new ContentValues();
        values.put(FeedOrm.COLUMN_Item_NAME, feed.getItem_name());
        values.put(FeedOrm.COLUMN_Description_NAME, feed.getDescription());

        values.put(FeedOrm.COLUMN_Unit, feed.getUnit());
        values.put(FeedOrm.COLUMN_Price, feed.getPrice());

        int updated = database.update(FeedOrm.TABLE_NAME, values, where,
                null);
        if (updated == 1) {
            Log.i(TAG, "Updated Customer : " + feed.getId());
        }

        return updated;
    }

    public static List<Feed> getFeedList(Context context) {
        DbManager databaseWrapper = DbManager.getInstance(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM "
                + FeedOrm.TABLE_NAME, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Address...");
        List<Feed> feeds = new ArrayList<Feed>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Feed feed = cursorToFeed(cursor);
                feeds.add(feed);
                cursor.moveToNext();
            }
            Log.i(TAG, "addressList loaded successfully.");
        }

        return feeds;
    }

    public static boolean deleteAddressById(Context context, String Id) {
        DbManager databaseWrapper = DbManager.getInstance(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        return database.delete(TABLE_NAME, COLUMN_ID + "= " + Id
                + " ", null) > 0;
    }

    private static Feed cursorToFeed(Cursor cursor) {
        Feed feed = new Feed();
        feed.setId(cursor.getInt(cursor
                .getColumnIndex(COLUMN_ID)));
        feed.setItem_name(cursor.getString(cursor
                .getColumnIndex(COLUMN_Item_NAME)));
        feed.setDescription(cursor.getString(cursor
                .getColumnIndex(COLUMN_Description_NAME)));
        feed.setUnit(cursor.getInt(cursor
                .getColumnIndex(COLUMN_Unit)));
        feed.setPrice(Double.parseDouble(cursor.getString(cursor
                .getColumnIndex(COLUMN_Price))));


        return feed;
    }

    private static ContentValues feedToContentValues(Feed feed) {
        ContentValues values = new ContentValues();
        values.put(FeedOrm.COLUMN_Item_NAME, feed.getItem_name());
        values.put(FeedOrm.COLUMN_Description_NAME, feed.getDescription());
        values.put(FeedOrm.COLUMN_Unit, feed.getUnit());
        values.put(FeedOrm.COLUMN_Price, feed.getPrice());

        return values;
    }

    public static boolean deleteAllAddress(Context context) {

        DbManager databaseWrapper = DbManager.getInstance(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        return database.delete(TABLE_NAME, null, null) > 0;
    }

}
