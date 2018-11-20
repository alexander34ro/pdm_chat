package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.lifecycle.LiveData;

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

    @Inject
    public OpenChannelRepository(OpenChannelDao openChannelDao) {
        this.openChannelDao = openChannelDao;
    }

    public LiveData<OpenChannel> getChannel(String url) {
        refreshChannel(url);
        // Returns a LiveData object directly from the database.
        return openChannelDao.load(url);
    }

    private void refreshChannel(final String url) {
        // Check if open channel data was fetched recently.
        boolean channelExists = false;//openChannelDao.load(url).;
        if (!channelExists) {
            // Refreshes the data.
            SendbirdAPI.call().getOpenChannelData(url).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<OpenChannel>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            System.out.println("connected");
                        }

                        @Override
                        public void onSuccess(OpenChannel openChannel) {
                            System.out.println("channel: " + openChannel.getCustomType());
                            openChannelDao.save(openChannel);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}
