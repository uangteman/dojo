package com.sample.an.sample.service.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sample.an.sample.service.db.model.KnownSourceORMLiteModel;

/**
 * Created by cindy on 5/13/16.
 */
public class OrmLiteRepositoryHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = OrmLiteRepositoryHelper.class.getName();

   /* private static final String DATABASE_NAME = "preload.db";
    private static final String DATABASE_PATH = "/data/data/com.dai.uangteman/databases/";*/


    private Dao<KnownSourceORMLiteModel, Integer> knownSourceDao;

    public OrmLiteRepositoryHelper(Context context) {
        super(context, "ormlite.db", null, 1);

        /*boolean dbexist = checkdatabase();
        if (!dbexist) {

            // If database did not exist, try copying existing database from assets folder.
            try {
                File dir = new File(DATABASE_PATH);
                dir.mkdirs();
                InputStream databaseInput = context.getAssets().open(DATABASE_NAME);
                String outfilename = DATABASE_PATH + DATABASE_NAME;
                Log.i(OrmLiteRepositoryHelper.class.getName(), "DB Path : " + outfilename);
                OutputStream databaseOutput = new FileOutputStream(outfilename);
                byte[] buffer = new byte[1024];
                int length;
                for(int i=1;i<10;i++){
                    databaseInput = context.getAssets().open("preload.db.00"+i);
                    while ((length = databaseInput.read(buffer)) > 0) {
                        databaseOutput.write(buffer, 0, length);
                        databaseOutput.flush();
                    }
                    databaseInput.close();
                }
                databaseOutput.flush();
                databaseOutput.close();
                databaseOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connection) {
        try {
            TableUtils.createTable(connection, KnownSourceORMLiteModel.class);
        } catch (java.sql.SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connection, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connection, KnownSourceORMLiteModel.class, true);
            onCreate(database, connection);
        } catch (java.sql.SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void close() {
        super.close();
        knownSourceDao = null;
    }

    public Dao<KnownSourceORMLiteModel, Integer> getKnownSourceDao()
            throws java.sql.SQLException {

        if (knownSourceDao == null)
            knownSourceDao = getDao(KnownSourceORMLiteModel.class);
        return knownSourceDao;

    }

    /*
    * Check whether or not database exist
    */
   /* private boolean checkdatabase() {
        boolean checkdb = false;

        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();

        Log.i(OrmLiteRepositoryHelper.class.getName(), "DB Exist : " + checkdb);

        return checkdb;
    }*/
}
