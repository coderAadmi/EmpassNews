package com.prady.empassnews.NewsDB;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class NewsDao_Impl implements NewsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfNews;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNews;

  public NewsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNews = new EntityInsertionAdapter<News>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `news`(`id`,`news_title`,`news_img_url`,`news_url`,`date_of_publishing`,`type`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, News value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getImageURL() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImageURL());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrl());
        }
        if (value.getPublishedAt() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPublishedAt());
        }
        stmt.bindLong(6, value.getType());
      }
    };
    this.__deletionAdapterOfNews = new EntityDeletionOrUpdateAdapter<News>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `news` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, News value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insertAll(News... news) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNews.insert(news);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(News news) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNews.insert(news);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(News news) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfNews.handle(news);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<News> getAllNews() {
    final String _sql = "Select * from news";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("news_title");
      final int _cursorIndexOfImageURL = _cursor.getColumnIndexOrThrow("news_img_url");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("news_url");
      final int _cursorIndexOfPublishedAt = _cursor.getColumnIndexOrThrow("date_of_publishing");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<News> _result = new ArrayList<News>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final News _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpImageURL;
        _tmpImageURL = _cursor.getString(_cursorIndexOfImageURL);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final String _tmpPublishedAt;
        _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _item = new News(_tmpTitle,_tmpImageURL,_tmpUrl,_tmpPublishedAt,_tmpType);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<News> getAllNewsByType(int type) {
    final String _sql = "Select * from news where type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("news_title");
      final int _cursorIndexOfImageURL = _cursor.getColumnIndexOrThrow("news_img_url");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("news_url");
      final int _cursorIndexOfPublishedAt = _cursor.getColumnIndexOrThrow("date_of_publishing");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<News> _result = new ArrayList<News>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final News _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpImageURL;
        _tmpImageURL = _cursor.getString(_cursorIndexOfImageURL);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final String _tmpPublishedAt;
        _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _item = new News(_tmpTitle,_tmpImageURL,_tmpUrl,_tmpPublishedAt,_tmpType);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<News> getAllNewsByDate(String publishedAt) {
    final String _sql = "Select * from news where date_of_publishing = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (publishedAt == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, publishedAt);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("news_title");
      final int _cursorIndexOfImageURL = _cursor.getColumnIndexOrThrow("news_img_url");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("news_url");
      final int _cursorIndexOfPublishedAt = _cursor.getColumnIndexOrThrow("date_of_publishing");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<News> _result = new ArrayList<News>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final News _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpImageURL;
        _tmpImageURL = _cursor.getString(_cursorIndexOfImageURL);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final String _tmpPublishedAt;
        _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _item = new News(_tmpTitle,_tmpImageURL,_tmpUrl,_tmpPublishedAt,_tmpType);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
