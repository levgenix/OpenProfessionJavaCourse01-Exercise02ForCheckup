/*
 * Created by Evgeny V. Lobach on 13.10.18 23:29
 * Copyright (c) 2018 | www.itstudio.app | All rights reserved.
 * Last modified 13.10.18 23:29
 */

package app.itstudio.exercise02forcheckup;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.Objects;

public class SearchHelper implements SearchSystem {

    private static SearchHelper instance;
    private static SearchSystem mHelper;
    private static SharedPreferences mSharedPreferences;

    private final static String SHARED_PREF_NAME = "app.itstudio.exercise02forcheckup.shared_pref_name";
    private final static String SEARCH_SYSTEM_HELPER = "app.itstudio.exercise02forcheckup.search_system_helper";

    public static class GoogleSearchHelper implements SearchSystem {
        public final static String NAME = "Google";
        public final static String SEARCH_STRING = "https://www.google.com/search?q=";

        public Uri getUri(String searchString) {
            return Uri.parse(SEARCH_STRING + searchString);
        }

        @Override
        public String getName() {
            return NAME;
        }
    }

    public static class YandexSearchHelper implements SearchSystem {
        public final static String NAME = "Yandex";
        public final static String SEARCH_STRING = "https://yandex.ru/search/?text=";

        public Uri getUri(String searchString) {
            return Uri.parse(SEARCH_STRING + searchString);
        }

        @Override
        public String getName() {
            return NAME;
        }
    }
    
    public static class BingSearchHelper implements SearchSystem {
        public final static String NAME = "Bing";
        public final static String SEARCH_STRING = "https://www.bing.com/search?q=";

        public Uri getUri(String searchString) {
            return Uri.parse(SEARCH_STRING + searchString);
        }

        @Override
        public String getName() {
            return NAME;
        }
    }

    private SearchHelper(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String currentSearchSystem = mSharedPreferences.getString(SEARCH_SYSTEM_HELPER, null);
        if (null == currentSearchSystem) {
            mSharedPreferences.edit().putString(SEARCH_SYSTEM_HELPER, YandexSearchHelper.NAME).apply();
        }

        switch (Objects.requireNonNull(mSharedPreferences.getString(SEARCH_SYSTEM_HELPER, null))) {
            case "Google":
                mHelper = new GoogleSearchHelper();
                break;
            case "Yandex":
                mHelper = new YandexSearchHelper();
                break;
            default:
                mHelper = new BingSearchHelper();
                break;
        }
        
        //Prevent form the reflection api.
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public synchronized static SearchHelper getInstance(@NonNull Context context) {
        if (instance == null) { //if there is no instance available... create new one
            instance = new SearchHelper(context);
        }
        return instance;
    }

    public Uri getUri(String searchString) {
        return mHelper.getUri(searchString);
    }

    public String getName() {
        return mHelper.getName();
    }

    public void setSearchSystem(String systemName) {
        switch (systemName) {
            case GoogleSearchHelper.NAME:
                mHelper = new GoogleSearchHelper();
                mSharedPreferences.edit().putString(SEARCH_SYSTEM_HELPER, GoogleSearchHelper.NAME).apply();
                break;
            case YandexSearchHelper.NAME:
                mHelper = new YandexSearchHelper();
                mSharedPreferences.edit().putString(SEARCH_SYSTEM_HELPER, YandexSearchHelper.NAME).apply();
                break;
            case BingSearchHelper.NAME:
                mHelper = new BingSearchHelper();
                mSharedPreferences.edit().putString(SEARCH_SYSTEM_HELPER, BingSearchHelper.NAME).apply();
                break;
        }
    }
}