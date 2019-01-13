package com.example.mars.pdmchat2.Login;

import android.arch.lifecycle.MutableLiveData;

import com.example.mars.pdmchat2.ChannelList.Channels;
import com.example.mars.pdmchat2.Services.SendbirdAPI;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsersRepository {

    public MutableLiveData<Users> getUsers() {

        final MutableLiveData<Users> data = new MutableLiveData<>();
        SendbirdAPI.call().getAllUsers(20).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Users>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("subscribed");
                    }

                    @Override
                    public void onSuccess(Users users) {
                        System.out.println("users: " + users.getUsers().size());
                        data.postValue(users);
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
