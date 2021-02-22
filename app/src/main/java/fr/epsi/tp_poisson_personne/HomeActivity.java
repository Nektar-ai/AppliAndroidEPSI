package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button z1, z2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("EPSI");
        setContentView(R.layout.activity_home);

        z1 = findViewById(R.id.z1);
        z1 = findViewById(R.id.z2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        z1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GroupActivity.class);
                startActivity(intent);
            }
        });

        z2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}