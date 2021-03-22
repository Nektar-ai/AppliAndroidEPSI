package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    private ImageView picture;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            getSupportActionBar().setTitle(bundle.getString("name"));
        }
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        description = findViewById(R.id.description);
        picture = findViewById(R.id.picture);
        description.setText(bundle.getString("description"));
        Picasso.get().load(bundle.getString("picture_url")).fit().centerCrop().error(R.drawable.nologo).into(picture);
    }
}