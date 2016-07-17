package com.example.evanscadet.flickster1.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.evanscadet.flickster1.R;
import com.example.evanscadet.flickster1.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Evans Cadet on 7/15/2016.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public static class ViewHolder{
        TextView originalTitle;
        TextView overview;
        ImageView imageMovie;

    }

    public MovieArrayAdapter(Context context, ArrayList<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        // check the existing view being reused
         ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
           convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }
        // find the image view
        viewHolder.imageMovie = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        //clear out image from convertView
        viewHolder.imageMovie.setImageResource(0);

        viewHolder.originalTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
        viewHolder.imageMovie = (ImageView) convertView.findViewById((R.id.ivMovieImage));

        viewHolder.imageMovie.setImageResource(0);
        convertView.setTag(viewHolder);



        // populate data
        viewHolder.originalTitle.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){

            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.imageMovie);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.imageMovie);
        }

        // return the view
        return convertView;
    }
}
