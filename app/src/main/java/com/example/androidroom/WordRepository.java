package com.example.androidroom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private WordDao mWorDao;
    private LiveData<List<Word>> mAllWords;


    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWorDao = db.wordDao();
        mAllWords = mWorDao.getAlphabetizedWords();
    }

    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(() ->{
            mWorDao.insert(word);
        });
    }

}
