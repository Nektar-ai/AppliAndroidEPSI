package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getSupportActionBar().setTitle(bundle.getString("section"));
        } else {
            getSupportActionBar().setTitle("ERROR");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.productsList);
        getProducts(bundle.getString("products_url"));
    }

    private void getProducts(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        createList(response);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    private void createList(String response) {
        try {
            JSONObject jObject = new JSONObject(response);
            String sItems = jObject.get("items").toString();
            JSONArray jArray = new JSONArray(sItems);
            ArrayList<HashMap<String, String>> items = new ArrayList<>();

            for (int i=0;i<jArray.length();i++){
                HashMap hMap = new HashMap<String, String>();
                hMap.put("name", jArray.getJSONObject(i).getString("name"));
                hMap.put("description", jArray.getJSONObject(i).getString("description"));
                hMap.put("picture_url", jArray.getJSONObject(i).getString("picture_url"));
                items.add(hMap);
            }

            ListAdapter adapter = new ProductsAdapter(this, items);
            listView.setAdapter(adapter);
            listView.setDividerHeight(0);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ProductsActivity.this, ProductActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("name", items.get(position).get("name"));
                    bundle.putString("description", items.get(position).get("description"));
                    bundle.putString("picture_url", items.get(position).get("picture_url"));

                    intent.putExtras(bundle);
                    startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(ProductsActivity.this).toBundle());
                }
            });
        }catch (JSONException e){
            System.out.println("Oupsy " + e);
        }
    }
}