package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mars.pdmchat2.Services.SendbirdAPI;

public class OpenChannelModel extends ViewModel {
    private MutableLiveData<OpenChannel> channel;
    private OpenChannelRepository repository = SendbirdAPI.repo();

    public OpenChannelModel() {
    }

    public void init(String url) {
        if (channel != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }
        channel = repository.getChannel(url);
    }

    public MutableLiveData<OpenChannel> getChannel() {
        return channel;
    }
}
