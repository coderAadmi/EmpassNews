package com.prady.empassnews;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prady.empassnews.NewsApi.Articles;
import com.prady.empassnews.NewsApi.NewsApiInstance;
import com.prady.empassnews.NewsApi.NewsItem;
import com.prady.empassnews.NewsApi.NewsRetrofitApi;
import com.prady.empassnews.NewsDB.News;
import com.prady.empassnews.NewsDB.NewsDbHandler;
import com.prady.pradyform.PrettyUserForm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsFragment extends Fragment {

    private RecyclerView newsView;
    private View view;
    private String name;
    ArrayList<News> newsList;
    private NewsDbHandler newsDbHandler;
    public void setName(String name)
    {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news,container,false);;
        newsView = view.findViewById(R.id.newsRecyclerView);
        newsView.setLayoutManager(new LinearLayoutManager(getContext()));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NewsDbHandler newsDbHandler = new NewsDbHandler(getContext());
        newsList = (ArrayList<News>) newsDbHandler.getAllNewsByType(Integer.parseInt(name));
        if(newsList==null)
            Toast.makeText(getContext(),"Null",Toast.LENGTH_SHORT).show();
        //this.newsList = new ArrayList<News>();
        for(int i=0;i<10;i++)
        {
            News news = new News("I am just not good", "data:image/jpeg;base64,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", "aaaaa", "222", Integer.parseInt(name));
            newsList.add(news);
        }
        newsView.setAdapter(new NewsListAdapter(newsList,getContext()));

    }


    public void setNewsList(ArrayList<News> newsList)
    {
        this.newsList = newsList;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_subscribe_us:
                Intent intent = new Intent(getActivity().getApplicationContext(),PrettyUserForm.class);
                startActivity(intent);
                break;

            case R.id.menu_rate_us:
                Toast.makeText(getContext(),"OOPS! We are not on Play store yet.",Toast.LENGTH_SHORT).show();break;

            case R.id.menu_contact_us:
                Toast.makeText(getContext(),"7011421277 is my no.",Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }

}
