package fr.epsi.tp_poisson_personne;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public class SplashActivity extends AppCompatActivity {

    private int timer = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startHome();
                    }
                },2000);
    }

    private void startHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void verifyDB() throws JSONException {
        //Create or Open Database and check if user is connected

        SQLiteDatabase myDB = openOrCreateDatabase("db",MODE_NO_LOCALIZED_COLLATORS,null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL + " TEXT," +
                FeedReaderContract.FeedEntry.COLUMN_NAME_GROUPE + " TEXT," +
                FeedReaderContract.FeedEntry.COLUMN_NAME_NOM + " TEXT," +
                FeedReaderContract.FeedEntry.COLUMN_NAME_PRENOM + " TEXT)");

        Cursor resultSet = myDB.rawQuery("Select * from students",null);
        int r = resultSet.getCount();


        if(r == 0){
            ContentValues student1 = new ContentValues();
            student1.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NOM, "VARDEN");
            student1.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRENOM, "Laurent");
            student1.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, "laurent.varden@epsi.fr");
            student1.put(FeedReaderContract.FeedEntry.COLUMN_NAME_GROUPE, "1");

            ContentValues student2 = new ContentValues();
            student2.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NOM, "ROPGOP");
            student2.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRENOM, "Léon");
            student2.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, "leon.ropgop@epsi.fr");
            student2.put(FeedReaderContract.FeedEntry.COLUMN_NAME_GROUPE, "1");

            ContentValues student3 = new ContentValues();
            student3.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NOM, "DAMHEC");
            student3.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRENOM, "Donovan");
            student3.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, "donovan.damhec@epsi.fr");
            student3.put(FeedReaderContract.FeedEntry.COLUMN_NAME_GROUPE, "1");

            myDB.insert("students", null, student1);
            myDB.insert("students", null, student2);
            myDB.insert("students", null, student3);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startHome();
            }
        }, timer);
    }
}
