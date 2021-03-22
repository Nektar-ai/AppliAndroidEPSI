package fr.epsi.tp_poisson_personne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsAdapter extends ArrayAdapter {

    private ArrayList<HashMap<String, String>> items;

    public ProductsAdapter(@NonNull Context context, @NonNull ArrayList<HashMap<String, String>> objects) {
        super(context, 0, objects);
        items = objects;
    }

    @Override
    public HashMap<String, String> getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HashMap hMap = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.productslistitem, parent, false);
        }

        TextView productName = (TextView) convertView.findViewById(R.id.name);
        TextView productDesc = (TextView) convertView.findViewById(R.id.description);
        ImageView picture =  convertView.findViewById(R.id.picture);

        productName.setText(hMap.get("name").toString());
        productDesc.setText(hMap.get("description").toString());
        Picasso.get().load(hMap.get("picture_url").toString()).fit().centerCrop().error(R.drawable.nologo).into(picture);

        return convertView;
    }
}
