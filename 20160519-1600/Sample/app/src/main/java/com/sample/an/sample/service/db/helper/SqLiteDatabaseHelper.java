package com.sample.an.sample.service.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sample.an.sample.service.db.gen.greendao.schema.KnownSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cindy on 5/18/16.
 */
public class SqLiteDatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sqllite.db";

    // Table Names
    private static final String TABLE_KNOWN_SOURCE = "known_source";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_KNOWN_SOURCE = "CREATE TABLE "
            + TABLE_KNOWN_SOURCE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT);";

    public SqLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_KNOWN_SOURCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KNOWN_SOURCE);

        // create new tables
        onCreate(db);
    }

    // ------------------------ table methods ----------------//

    /**
     * Creating tag
     */
    public long createKnownSource(KnownSource knownSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, knownSource.getId());
        values.put(KEY_NAME, knownSource.getName());

        // insert row
        long id = db.insert(TABLE_KNOWN_SOURCE, null, values);

        return id;
    }

    /**
     * getting all tags
     * */
    public List<KnownSource> getAllKnownSource() {
        List<KnownSource> tags = new ArrayList<KnownSource>();
        String selectQuery = "SELECT  * FROM " + TABLE_KNOWN_SOURCE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                KnownSource t = new KnownSource();
                t.setId(c.getLong((c.getColumnIndex(KEY_ID))));
                t.setName(c.getString(c.getColumnIndex(KEY_NAME)));

                // adding to tags list
                tags.add(t);
            } while (c.moveToNext());
        }
        return tags;
    }

    /**
     * Updating a tag
     */
    public int updateTag(KnownSource knownSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, knownSource.getName());

        // updating row
        return db.update(TABLE_KNOWN_SOURCE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(knownSource.getId()) });
    }

    /**
     * Deleting a tag
     */
    public void deleteTag(KnownSource knownSource, boolean should_delete_all_tag_todos) {
        SQLiteDatabase db = this.getWritableDatabase();
  // now delete the tag
        db.delete(TABLE_KNOWN_SOURCE, KEY_ID + " = ?",
                new String[] { String.valueOf(knownSource.getId()) });
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
