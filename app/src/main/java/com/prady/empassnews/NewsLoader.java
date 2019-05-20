package com.prady.empassnews;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.prady.empassnews.NewsApi.Articles;
import com.prady.empassnews.NewsApi.NewsApiInstance;
import com.prady.empassnews.NewsApi.NewsItem;
import com.prady.empassnews.NewsApi.NewsRetrofitApi;
import com.prady.empassnews.NewsDB.News;
import com.prady.empassnews.NewsDB.NewsDao;
import com.prady.empassnews.NewsDB.NewsDatabase;
import com.prady.empassnews.NewsDB.NewsDbHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsLoader extends AsyncTask<Void,Void,Void> {

    private NewsDbHandler newsDbHandler;
    private NewsItem[] newsList;
    int pos;
    boolean isDone;
    private int count;

    private Context context;

    public NewsLoader(Context context) {
        this.context = context;
        newsDbHandler = new NewsDbHandler(context);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra("NEWS",newsList);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        AppCompatActivity activity = (AppCompatActivity)context;
        activity.finish();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            newsDbHandler.deleteAllNews();
            count = 0;
            for(int i=0;i<8;i++) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(NewsRetrofitApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                NewsRetrofitApi api = retrofit.create(NewsRetrofitApi.class);
                Call<NewsItem> call = null;
                pos = i;
                switch (i) {
                    case 0:
                        call = api.getTopIndianNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 0);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_0", news.getTitle() + " Type: 0");
                                    setCount();
                                }
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_0", t.getMessage());
                            }
                        });
                        break;
                    case 1:
                        call = api.getTopSportNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                //newsList[pos] = response.body();
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 1);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_1", news.getTitle() + " Type: 1");
                                    setCount();
                                }

                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                                // Log.d("NEWS "+pos,"DOwnloaded");
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_1", t.getMessage());
                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            }
                        });
                        break;
                    case 2:
                        call = api.getTopBusinessNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                //newsList[pos] = response.body();
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 2);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_2", news.getTitle() + " Type: 2");
                                    setCount();
                                }

                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                                // Log.d("NEWS "+pos,"DOwnloaded");
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_2", t.getMessage());
                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            }
                        });
                        break;
                    case 3:
                        call = api.getTopHealthNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                //newsList[pos] = response.body();
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 3);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_3", news.getTitle() + " Type: 3");
                                    setCount();
                                }

                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                                // Log.d("NEWS "+pos,"DOwnloaded");
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_3", t.getMessage());
                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            }
                        });
                        break;
                    case 4:
                        call = api.getTopScienceNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                //newsList[pos] = response.body();
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 4);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_4", news.getTitle() + " Type: 4");
                                    setCount();
                                }

                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                                // Log.d("NEWS "+pos,"DOwnloaded");
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_4", t.getMessage());
                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            }
                        });
                        break;
                    case 5:
                        call = api.getTopTechNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                //newsList[pos] = response.body();
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 5);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_5", news.getTitle() + " Type: 5");
                                    setCount();
                                }

                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                                // Log.d("NEWS "+pos,"DOwnloaded");
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_5", t.getMessage());
                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            }
                        });
                        break;
                    case 6:
                        call = api.getTopEntertainmentNews();
                        call.enqueue(new Callback<NewsItem>() {
                            @Override
                            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                                //newsList[pos] = response.body();
                                Articles[] articles = response.body().getArticles();
                                for (int i = 0; i < articles.length; i++) {
                                    News news = new News(articles[i].getTitle(), articles[i].getUrlToImage(), articles[i].getUrl(), articles[i].getPublishedAt(), 6);
                                    newsDbHandler.insertNews(news);
                                    Log.d("NEWS_DB_6", news.getTitle() + " Type: 6");
                                    setCount();
                                }

                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                                // Log.d("NEWS "+pos,"DOwnloaded");
                            }

                            @Override
                            public void onFailure(Call<NewsItem> call, Throwable t) {
                                Log.d("NEWS_FAIL_6", t.getMessage());
                                // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            }
                        });
                        break;
                    case 7:
                        break;
                }
            }

            while(count<7);
            Log.d("COMP","DONE");
            isDone = true;
        }
        else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Please check your Internet connection!", Toast.LENGTH_SHORT).show();
                }
            });
            isDone = false;
        }
        return null;
    }

    private void setCount()
    {
        count++;
    }

}

