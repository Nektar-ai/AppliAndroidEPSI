package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class GroupActivity extends AppCompatActivity {

    private ListView students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        getSupportActionBar().setTitle("Students");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteDatabase sqlDB = openOrCreateDatabase("db",MODE_NO_LOCALIZED_COLLATORS,null);
        Cursor stuRes = sqlDB.rawQuery("Select * from "+FeedReaderContract.FeedEntry.TABLE_NAME,null);

        ListAdapter adapter = new GroupAdapter(
                this, R.layout.studentslistitem, stuRes, 0 );

        students = findViewById(R.id.studentslist);

        students.setAdapter(adapter);

        students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(GroupActivity.this, StudentActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("nom", stuRes.getString(stuRes.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NOM)));
                bundle.putString("prenom", stuRes.getString(stuRes.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PRENOM)));
                bundle.putString("email", stuRes.getString(stuRes.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL)));
                bundle.putString("groupe", stuRes.getString(stuRes.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_GROUPE)));

                intent.putExtras(bundle);

                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(GroupActivity.this).toBundle());

            }
        });

    }
}