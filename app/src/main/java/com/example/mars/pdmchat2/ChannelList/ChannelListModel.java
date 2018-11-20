package com.example.mars.pdmchat2.ChannelList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mars.pdmchat2.Services.SendbirdAPI;

public class ChannelListModel extends ViewModel {
    private MutableLiveData<Channels> channels;
    private ChannelsRepository repository = SendbirdAPI.listRepo();

    public MutableLiveData<Channels> getChannels() {
        return repository.loadChannels();
    }
}
