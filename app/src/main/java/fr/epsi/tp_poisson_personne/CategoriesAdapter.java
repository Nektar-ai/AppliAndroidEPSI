package fr.epsi.tp_poisson_personne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesAdapter extends ArrayAdapter {

    private ArrayList<HashMap<String, String>> items;

    public CategoriesAdapter(@NonNull Context context, @NonNull ArrayList<HashMap<String, String>> objects) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sectionlistitem, parent, false);
        }

        TextView rayonName = (TextView) convertView.findViewById(R.id.sectionName);

        rayonName.setText(hMap.get("name").toString());
        return convertView;
    }
}
