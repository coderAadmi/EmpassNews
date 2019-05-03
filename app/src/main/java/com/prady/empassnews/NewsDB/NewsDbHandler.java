package com.prady.empassnews.NewsDB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsDbHandler{
    NewsDatabase newsDatabase;
    NewsDao newsDao;

    public NewsDbHandler(Context context) {
        newsDatabase = NewsDatabase.getDatabaseInstance(context);
        newsDao = newsDatabase.getInstance();
    }

    public void insertNews(News news)
    {
        InsertNews insertNews = new InsertNews();
        insertNews.execute(news);
    }

    public void deleteNews(News news)
    {
        DeleteNews deleteNews = new DeleteNews();
        deleteNews.execute(news);
    }

    public List<News> getAllNews()
    {
        AllNews allNews = new AllNews();
        try {
            return allNews.execute().get();
        } catch (Exception e) {
            Log.d("ALL_NEWS_METHOD","FAIL");
        }
        return null;
    }

    public List<News> getAllNewsByType(int i)
    {
        AllNewsByType allNews = new AllNewsByType();
        try {
            return allNews.execute(i).get();
        } catch (Exception e) {
            Log.d("ALL_NEWS_METHOD","FAIL");
        }
        return null;
    }

    public class InsertNews extends AsyncTask<News,Void,Void>{

        @Override
        protected Void doInBackground(News... news) {
            newsDao.insert(news[0]);
            return null;
        }
    }

    public class DeleteNews extends AsyncTask<News,Void,Void>{

        @Override
        protected Void doInBackground(News... news) {
            newsDao.insert(news[0]);
            return null;
        }
    }

    public class AllNews extends AsyncTask<Void,Void, List<News>>{

        @Override
        protected List<News> doInBackground(Void... voids) {
            return newsDao.getAllNews();
        }
    }

    public class AllNewsByType extends AsyncTask<Integer,Void,List<News>>
    {

        @Override
        protected List<News> doInBackground(Integer... integers) {
            return newsDao.getAllNewsByType(integers[0]);
        }
    }

    public class AllNewsByDate extends AsyncTask<String,Void,List<News>>{

        @Override
        protected List<News> doInBackground(String... strings) {
            return newsDao.getAllNewsByDate(strings[0]);
        }
    }
}