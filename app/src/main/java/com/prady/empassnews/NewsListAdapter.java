package com.prady.empassnews;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prady.empassnews.NewsApi.NewsItem;
import com.prady.empassnews.NewsDB.News;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{

    private ArrayList<News> newsList;
    private Context context;

    public NewsListAdapter(ArrayList<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_news,viewGroup,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        if(newsList== null)
            newsViewHolder.newsTitle.setText("NEWSSSSSSS");
        else
            newsViewHolder.newsTitle.setText(newsList.get(i).getTitle());

           Glide.with(context)
                   .load(newsList.get(i).getImageURL())
                   .error(R.drawable.ic_placeholder)
                   .override(300,500)
                   .fitCenter()
                   .into(newsViewHolder.newsIcon);
    }

    @Override
    public int getItemCount() {
        if(newsList== null)
            return 5;
        else
        {
            return newsList.size();
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitle;
        ImageView newsIcon;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTextView);
            newsIcon = itemView.findViewById(R.id.newsImageView);
        }
    }
}
