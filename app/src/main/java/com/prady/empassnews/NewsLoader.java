package com.prady.empassnews;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsLoader extends AsyncTask<Void,Void,Void> {

    private NewsDbHandler newsDbHandler;
    private NewsItem[] newsList;
    int pos;
    boolean isDone;

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
            for(int i=0;i<8;i++)
            {
                Retrofit retrofit = NewsApiInstance.getInstance();
                NewsRetrofitApi api = retrofit.create(NewsRetrofitApi.class);
                Call<NewsItem> call = null;
                pos =i;
                switch (i)
                {
                    case 0:call = api.getTopIndianNews();break;
                    case 1:call = api.getTopSportNews();break;
                    case 2:call = api.getTopBusinessNews();break;
                    case 3:call = api.getTopHealthNews();break;
                    case 4:call = api.getTopScienceNews();break;
                    case 5:call = api.getTopTechNews();break;
                    case 6:call = api.getTopEntertainmentNews();break;
                    case 7:break;
                }
                if(i<7)
                {
                    call.enqueue(new Callback<NewsItem>() {
                        @Override
                        public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                            //newsList[pos] = response.body();
                            Articles[] articles = response.body().getArticles();
                            for(int i=0;i<articles.length;i++)
                            {
                                News news = new News(articles[i].getTitle(),articles[i].getUrlToImage(),articles[i].getUrl(),articles[i].getPublishedAt(),pos);
                                newsDbHandler.insertNews(news);
                            }

                            // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                            Log.d("NEWS "+pos,"DOwnloaded");
                        }

                        @Override
                        public void onFailure(Call<NewsItem> call, Throwable t) {
                            Log.d("NEWS_FAIL",t.getMessage());
                            // newsView.setAdapter(new NewsListAdapter(newsList,getContext()));
                        }
                    });
                }
            }
            isDone = true;
        }
        else {
            Toast.makeText(context, "Please check your Internet connection!", Toast.LENGTH_SHORT).show();
            isDone = false;
        }
        return null;
    }
}
