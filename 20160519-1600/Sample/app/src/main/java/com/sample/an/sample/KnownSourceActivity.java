package com.sample.an.sample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.DatabaseHelper;
import com.activeandroid.query.Select;
import com.sample.an.sample.service.db.gen.greendao.schema.DaoMaster;
import com.sample.an.sample.service.db.gen.greendao.schema.DaoSession;
import com.sample.an.sample.service.db.gen.greendao.schema.KnownSource;
import com.sample.an.sample.service.db.gen.greendao.schema.KnownSourceDao;
import com.sample.an.sample.service.db.helper.SqLiteDatabaseHelper;
import com.sample.an.sample.service.db.model.KnownSourceActiveRecordModel;
import com.sample.an.sample.service.db.model.KnownSourceORMLiteModel;
import com.sample.an.sample.service.db.service.KnownSourceORMLiteService;

import java.sql.SQLException;
import java.util.List;

import de.greenrobot.dao.database.Database;

//import de.greenrobot.dao.database.Database;


/**
 * Created by cindy on 5/13/16.
 */
public class KnownSourceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button saveORMLite, saveActiveRecord, saveGreenDao, saveLite;
    private EditText inputEditText;
    private TextView resultTextView;
    private KnownSourceORMLiteService knownSourceService;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private KnownSourceDao knownSourceDao;

    // Database Helper
    private SqLiteDatabaseHelper dbLite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sql lite
        dbLite = new SqLiteDatabaseHelper(getApplicationContext());

        /* ORM lite*/
        knownSourceService = new KnownSourceORMLiteService(this);

        /* Green Dao*/
        /*DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "greendao.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();*/
        DaoMaster.EncryptedDevOpenHelper helper = new DaoMaster.EncryptedDevOpenHelper(this, "greendao.db");
        Database db = helper.getWritableDatabase("dodol");
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        knownSourceDao = daoSession.getKnownSourceDao();

        saveORMLite = (Button) findViewById(R.id.known_source_ormlite_save_button);
        saveActiveRecord = (Button) findViewById(R.id.known_source_active_record_save_button);
        saveGreenDao = (Button) findViewById(R.id.known_source_green_dao_save_button);
        saveLite = (Button) findViewById(R.id.known_source_sql_lite_save_button);
        inputEditText = (EditText) findViewById(R.id.known_source_name_edit_text);
        resultTextView = (TextView) findViewById(R.id.known_source_name_text_view);

        // show result
        showGreenDaoResult();
        showORMLiteResult();
        showActiveRecordResult();

        saveORMLite.setOnClickListener(this);
        saveActiveRecord.setOnClickListener(this);
        saveGreenDao.setOnClickListener(this);
        saveLite.setOnClickListener(this);
    }

    private void saveLite(String name){
        if(dbLite != null){
            KnownSource model = new KnownSource();
            model.setName(name);
            dbLite.createKnownSource(model);
        }
    }

    private void showLite(){
        if(dbLite != null){
           List<KnownSource> list = dbLite.getAllKnownSource();
            if (list != null && list.size() > 0) {
                String knownSourceString = "";
                for (KnownSource model : list) {
                    knownSourceString += model.getName() + " ";
                }
                resultTextView.setText(knownSourceString);
            } else {
                resultTextView.setText("");
            }
        }
    }

    private void saveORMLite(String name) {
        if (knownSourceService != null) {
            KnownSourceORMLiteModel model = new KnownSourceORMLiteModel();
            model.setName(name);
            knownSourceService.createKnownSourceModel(model);
        }
    }

    private void showORMLiteResult() {
        try {
            List<KnownSourceORMLiteModel> list = knownSourceService.getAllKnownSource(Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (list != null && list.size() > 0) {
                String knownSourceString = "";
                for (KnownSourceORMLiteModel model : list) {
                    knownSourceString += model.getName() + " ";
                }
                resultTextView.setText(knownSourceString);
            } else {
                resultTextView.setText("");
            }
        } catch (SQLException ex) {

        }
    }

    private void saveActiveRecord(String name) {
        Select select = new Select();
        List<KnownSourceActiveRecordModel> list = select.all().from(KnownSourceActiveRecordModel.class).execute();
        KnownSourceActiveRecordModel model = new KnownSourceActiveRecordModel(name, list.size() + 1);
        model.save();
    }

    private void showActiveRecordResult() {
        Select select = new Select();
        List<KnownSourceActiveRecordModel> list = select.all().from(KnownSourceActiveRecordModel.class).execute();

        if (list != null && list.size() > 0) {
            String knownSourceString = "";
            for (KnownSourceActiveRecordModel model : list) {
                knownSourceString += model.getName() + " ";
            }
            resultTextView.setText(knownSourceString);
        } else {
            resultTextView.setText("");
        }
    }

    private void saveKnownSourceGreenDao(String name) {
//        long size = knownSourceDao.loadAll().size();
        KnownSource model = new KnownSource();
        model.setName(name);
        knownSourceDao.insert(model);
    }

    private void showGreenDaoResult() {
        List<KnownSource> list = knownSourceDao.loadAll();

        if (list != null && list.size() > 0) {
            String knownSourceString = "";
            for (KnownSource model : list) {
                knownSourceString += model.getName() + " ";
            }
            resultTextView.setText(knownSourceString);
        } else {
            resultTextView.setText("");
        }

    }

    @Override
    public void onClick(View view) {
        String name = inputEditText.getText().toString();
        switch (view.getId()) {
            case R.id.known_source_ormlite_save_button:
                if (!TextUtils.isEmpty(name)) {
                    saveORMLite(name);
                }
                showORMLiteResult();
                inputEditText.setText("");
                break;
            case R.id.known_source_active_record_save_button:
                if (!TextUtils.isEmpty(name)) {
                    saveActiveRecord(name);
                }
                showActiveRecordResult();
                inputEditText.setText("");
                break;
            case R.id.known_source_green_dao_save_button:
                if(!TextUtils.isEmpty(name)){
                    saveKnownSourceGreenDao(name);
                }
                showGreenDaoResult();
                inputEditText.setText("");
                break;
            case R.id.known_source_sql_lite_save_button:
                if(!TextUtils.isEmpty(name)){
                    saveLite(name);
                }
                showLite();
                inputEditText.setText("");
                break;
        }
    }
}
