package com.example.thewitcher3bestiary.model;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thewitcher3bestiary.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<ObjectBeast> beasts;

    public Adapter(Context context, List<ObjectBeast> beasts) {
        this.context = context;
        this.beasts = beasts;
    }

    @Override
    public int getCount() {
        return beasts.size();
    }

    @Override
    public Object getItem(int position) {
        return beasts.get(position);}

    @Override
    public long getItemId(int position) {
        return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        }

        ObjectBeast beast = beasts.get(position);
        TextView name = convertView.findViewById(R.id.name);
        TextView location = convertView.findViewById(R.id.location);
        TextView loot = convertView.findViewById(R.id.loot);
        TextView weakness = convertView.findViewById(R.id.weakness);
        ImageView image = convertView.findViewById(R.id.image);
        TextView desc = convertView.findViewById(R.id.desc);

        name.setText(beast.getName());
        location.setText(beast.getLocation());
        loot.setText(beast.getLoot());
        weakness.setText(beast.getWeakness());
        desc.setText(beast.getDesc());
        desc.setMovementMethod(ScrollingMovementMethod.getInstance());
        Picasso.get().load(beast.getImage()).into(image);
        return convertView;
    }

    public void addAll(List<ObjectBeast> newBeasts) {
        beasts.addAll(newBeasts);
        notifyDataSetChanged();
    }
}
