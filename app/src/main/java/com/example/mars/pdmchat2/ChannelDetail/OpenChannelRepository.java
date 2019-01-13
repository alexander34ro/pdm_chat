package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.lifecycle.MutableLiveData;

import com.example.mars.pdmchat2.ChannelList.Channels;
import com.example.mars.pdmchat2.Services.SendbirdAPI;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class OpenChannelRepository {
    private final OpenChannelDao openChannelDao;
    private MutableLiveData<OpenChannel> cached;

    @Inject
    public OpenChannelRepository(OpenChannelDao openChannelDao) {
        this.openChannelDao = openChannelDao;
    }

    public MutableLiveData<OpenChannel> getChannel(String url) {
        //refreshChannel(url);
        // Returns a LiveData object directly from the database.
        //return openChannelDao.load(url);

        if (cached != null) {
            return cached;
        }

        final MutableLiveData<OpenChannel> data = new MutableLiveData<>();
        cached = data;

        SendbirdAPI.call().getOpenChannelData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OpenChannel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("subscribed");
                    }

                    @Override
                    public void onSuccess(OpenChannel openChannel) {
                        System.out.println("channel: " + openChannel.getCustomType());
                        data.postValue(openChannel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("error");
                    }
                });
        return data;
    }

    private void refreshChannel(final String url) {
        // Runs in a background thread.
        SendbirdAPI.getExecutor().execute(() -> {
            // Check if open channel data was fetched recently.
            final MutableLiveData<OpenChannel> data = new MutableLiveData<>();
            boolean channelExists = openChannelDao.load(url) != null;
            if (!channelExists) {
                // Refreshes the data
                SendbirdAPI.call().getOpenChannelData(url).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<OpenChannel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("connected");
                            }

                            @Override
                            public void onSuccess(OpenChannel openChannel) {
                                //System.out.println("channel: " + openChannel.getCustomType());
                                data.postValue(openChannel);
                                openChannelDao.save(data.getValue());
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        });

                openChannelDao.save(data.getValue());
                System.out.println("dun");
            }
        });
    }
}
