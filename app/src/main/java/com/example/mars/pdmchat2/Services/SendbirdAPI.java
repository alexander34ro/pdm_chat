package com.example.mars.pdmchat2.Services;

import com.example.mars.pdmchat2.ChannelDetail.OpenChannelDao_Impl;
import com.example.mars.pdmchat2.ChannelDetail.OpenChannelDatabase;
import com.example.mars.pdmchat2.ChannelDetail.OpenChannelRepository;
import com.example.mars.pdmchat2.ChannelList.ChannelsRepository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendbirdAPI {
    private static final SendbirdAPI ourInstance = new SendbirdAPI();
    private static APIService apiService;
    private static OpenChannelRepository openChannelRepository = null;
    private static ChannelsRepository channelsRepository;

    private SendbirdAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.sendbird.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // create an instance of the ApiService
        apiService = retrofit.create(APIService.class);

        channelsRepository = new ChannelsRepository();
    }

    public static SendbirdAPI getInstance() {
        return ourInstance;
    }

    public static APIService call() {
        return apiService;
    }

    public static OpenChannelRepository repo() {
        return openChannelRepository;
    }

    public static ChannelsRepository listRepo() {
        return channelsRepository;
    }

    public static boolean hasDatabase() {
        return openChannelRepository != null;
    }

    public static void setDatabase(OpenChannelDatabase db) {
        openChannelRepository = new OpenChannelRepository(new OpenChannelDao_Impl(db));
    }
}
