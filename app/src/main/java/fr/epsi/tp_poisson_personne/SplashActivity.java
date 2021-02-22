package fr.epsi.tp_poisson_personne;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

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

}
