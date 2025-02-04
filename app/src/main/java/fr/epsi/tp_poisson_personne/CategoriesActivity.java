package fr.epsi.tp_poisson_personne;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoriesActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setTitle("Sections");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.sectionsList);
        getCategories();
    }

    private void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://djemam.com/epsi/categories.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        createList(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(CategoriesActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void createList(String response){
        try {
            JSONObject jObject = new JSONObject(response);
            String sItems = jObject.get("items").toString();
            JSONArray jArray = new JSONArray(sItems);
            ArrayList<HashMap<String, String>> items = new ArrayList<>();

            for (int i=0;i<jArray.length();i++){
                HashMap hMap = new HashMap<String, String>();
                try {
                    byte[] nameBytes = jArray.getJSONObject(i).getString("title").getBytes("ISO-8859-1");
                    hMap.put("name",new String(nameBytes));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                hMap.put("url", jArray.getJSONObject(i).getString("products_url"));
                items.add(hMap);
            }

            ListAdapter adapter = new CategoriesAdapter(this, items);
            listView.setAdapter(adapter);
            listView.setDividerHeight(0);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = items.get(position).get("url");
                    String rayon = items.get(position).get("name");
                    Intent intent = new Intent(CategoriesActivity.this, ProductsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("section", rayon);
                    bundle.putString("products_url", url);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }catch (JSONException e){
            System.out.println("Oupsy " + e);
        }
    }
}