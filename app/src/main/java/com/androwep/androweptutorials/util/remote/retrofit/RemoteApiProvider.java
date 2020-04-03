package com.androwep.androweptutorials.util.remote.retrofit;



import com.androwep.androweptutorials.util.local.AppConstraints;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteApiProvider {


    private static final String BASE_URL = AppConstraints.AppBaseUrl.APP_URL;
    //  private static final String BASE_URL = "http://uysshopapi.herokuapp.com/api/";
    // private static final String TOKEN = "Token 8eca7331cba1d1d611cf26c9c98090abe0a39064";
    private static final String TOKEN = "Token f7fd50e67eb2d0f575589d1031ae333a847552fe";
    private static RemoteApiProvider mInstance;
    private Retrofit retrofit;
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", TOKEN)
                    .addHeader("Accept","application/json")
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    private RemoteApiProvider() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RemoteApiProvider getInstance() {
        if (mInstance == null) {
            mInstance = new RemoteApiProvider();
        }
        return mInstance;
    }


    /**
     * get remote video api for featured and most popular
     * @return retrofit create
     */
    public RemoteApiInterface getRemoteApi(){
        return retrofit.create(RemoteApiInterface.class);
    }
    public static String getBaseUrl() {
        return BASE_URL;
    }
}
