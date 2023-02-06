package com.richardo.a2440064993_richardowijaya_finalexam.Request;

import com.richardo.a2440064993_richardowijaya_finalexam.Utils.CredentialsKey;
import com.richardo.a2440064993_richardowijaya_finalexam.Utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder().baseUrl(CredentialsKey.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi()
    {
        return movieApi;
    }
}
