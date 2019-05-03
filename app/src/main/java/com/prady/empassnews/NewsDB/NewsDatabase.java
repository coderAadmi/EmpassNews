package com.prady.empassnews.NewsDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;



@Database(entities = {News.class},version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public static final String DB_NAME= "news";
    private static NewsDatabase instance;
    public abstract NewsDao getInstance();

    public synchronized static NewsDatabase getDatabaseInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context,NewsDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
