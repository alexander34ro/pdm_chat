package com.example.mars.pdmchat2.Services;

import com.example.mars.pdmchat2.ChannelDetail.OpenChannel;
import com.example.mars.pdmchat2.ChannelList.Channels;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String apiToken = "Api-Token: 543f73a20f0c88e70be548ddbd4169de1733e327";

    @Headers({apiToken})
    @GET("open_channels/{channel_url}")
    Single<OpenChannel> getOpenChannelData(@Path("channel_url") String channelUrl);

    @Headers({apiToken})
    @GET("open_channels")
    Single<Channels> getAllOpenChannelData(@Query("limit") Integer limit);

    @Headers({apiToken})
    @POST("open_channels")
    Single<OpenChannel> newOpenChannelData(@Body OpenChannel openChannel);

}
