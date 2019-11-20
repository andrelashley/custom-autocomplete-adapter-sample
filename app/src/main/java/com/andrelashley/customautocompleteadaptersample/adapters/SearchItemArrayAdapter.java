package com.andrelashley.customautocompleteadaptersample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrelashley.customautocompleteadaptersample.R;
import com.andrelashley.customautocompleteadaptersample.models.Photo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SearchItemArrayAdapter extends ArrayAdapter<Photo>
{
    private Photo item;
    private TextView autoItem;
    private List<Photo> photoList;
    private Context mContext;

    public SearchItemArrayAdapter(Context context, int textViewResourceId,
                                  ArrayList<Photo> objects)
    {
        super(context, textViewResourceId, objects);
        photoList = objects;
        mContext = context;
    }

    @Override
    public int getCount()
    {
        return this.photoList.size();
    }

    @Override
    public Photo getItem(int position)
    {
        Photo photo = photoList.get(position);
        return photo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (row == null)
        {
            row = inflater.inflate(R.layout.autocomplete_layout, parent, false);
        }

        item = photoList.get(position);
        String searchItem = item.getTitle();
        autoItem = row.findViewById(R.id.title);
        autoItem.setText(searchItem);

        Glide.with(mContext)
                .load(photoList.get(position).getThumbnailUrl())
                .into((ImageView) row.findViewById(R.id.thumbnail));

        return row;
    }
}
