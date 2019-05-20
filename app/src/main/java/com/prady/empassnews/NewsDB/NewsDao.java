package com.prady.empassnews.NewsDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import retrofit2.http.GET;


@Dao
public interface NewsDao {

    @Query("Select * from news")
    List<News> getAllNews();

    @Query("Select * from news where type = :type")
    List<News> getAllNewsByType(int type);

    @Insert
    void insertAll(News... news);

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Query("Select * from news where date_of_publishing = :publishedAt")
    List<News> getAllNewsByDate(String publishedAt);

    @Query("Delete from news")
    void deleteAll();


}
