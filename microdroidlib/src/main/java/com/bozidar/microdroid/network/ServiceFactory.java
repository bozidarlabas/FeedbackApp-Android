package com.bozidar.microdroid.network;

import retrofit.RestAdapter;

/**
 * Created by bozidar on 09.09.15..
 */
public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return restAdapter.create(clazz);
    }
}
