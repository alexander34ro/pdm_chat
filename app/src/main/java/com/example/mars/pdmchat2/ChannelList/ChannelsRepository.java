package com.example.mars.pdmchat2.ChannelList;

import android.arch.lifecycle.MutableLiveData;

import com.example.mars.pdmchat2.Services.SendbirdAPI;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChannelsRepository {
    private MutableLiveData<Channels> cached;

    public MutableLiveData<Channels> loadChannels() {
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<Channels> data = new MutableLiveData<>();
        cached = data;

        SendbirdAPI.call().getAllOpenChannelData(20).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Channels>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("subscribed");
                    }

                    @Override
                    public void onSuccess(Channels openChannels) {
                        System.out.println("channels: " + openChannels.getChannels().size());
                        data.postValue(openChannels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("error");
                    }
                });
        return data;
    }
}
