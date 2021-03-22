package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StudentActivity extends AppCompatActivity {

    private ImageView picture;
    private TextView name, mail, group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picture = findViewById(R.id.studentPicture);
        name = findViewById(R.id.studentName);
        mail = findViewById(R.id.studentMail);
        group = findViewById(R.id.studentGroup);

        if (bundle != null) {
            System.out.println("HHHHHHHHHHHHHH" + bundle.getString("photo"));
            name.setText(bundle.getString("prenom")+" "+bundle.getString("nom"));
            mail.setText(bundle.getString("email"));
            group.setText(bundle.getString("groupe"));
            Picasso.get().load(bundle.getString("photo")).into(picture);
            //picture.setImageURI(Uri.parse(bundle.getString("photo")));
        }
    }
}