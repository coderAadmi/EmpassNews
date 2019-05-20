package com.prady.empassnews;
import android.app.Activity;
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
import retrofit2.converter.gson.GsonConverterFactory;

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
        newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
    }


    public void setNewsList(ArrayList<News> newsList)
    {
        this.newsList = newsList;
        ArrayList<News> temp = newsList;
        int l = temp.size();
        this.newsList = new ArrayList<News>();
        for(int i =0;i<l;i++)
        {
            this.newsList.add(temp.get(l-i-1));
        }
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

    @Override
    public void onStop() {
        super.onStop();
        Log.d("on_STOP"+name,"STOPEED");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("on_PAuse"+name,"PAUSED");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("on_Start"+name,"STARTED");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("on_Resume"+name,"RESUMED");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("on_CREATE"+name,"CREATED");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("on_Destroy"+name,"DESTROYED");
    }
}
