package com.quizapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupabaseClient {
    private static final String PROJECT_ID = "jdhcfmmksurpmzrxastu";
    private static final String BASE_URL = "https://" + PROJECT_ID + ".supabase.co";
    private static final String STORAGE_URL = BASE_URL + "/storage/v1/object/public/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getImageUrl(String bucket, String path) {
        return STORAGE_URL + bucket + "/" + path;
    }
}