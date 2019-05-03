package com.prady.empassnews.NewsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRetrofitApi {

    public static String BASE_URL = "https://newsapi.org/";

    @GET("v2/top-headlines?country=in&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem> getTopIndianNews();

    @GET("v2/top-headlines?country=in&category=business&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem> getTopBusinessNews();

    @GET("v2/top-headlines?country=in&category=entertainment&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem> getTopEntertainmentNews();

    @GET("v2/top-headlines?country=in&category=health&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem>  getTopHealthNews();

    @GET("v2/top-headlines?country=in&category=science&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem>  getTopScienceNews();

    @GET("v2/top-headlines?country=in&category=sports&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem> getTopSportNews();

    @GET("v2/top-headlines?country=in&category=technology&apiKey=6532c604d7c8489d9925758e9c5432ac")
    Call<NewsItem> getTopTechNews();
}
