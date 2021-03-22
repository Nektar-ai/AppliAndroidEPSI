package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setTitle("Sections");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        System.out.println("YO");
    }

}