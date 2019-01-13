package com.example.mars.pdmchat2.Login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mars.pdmchat2.Services.SendbirdAPI;

public class UsersModel extends ViewModel {
    private MutableLiveData<Users> channels;
    private UsersRepository repository = SendbirdAPI.loginRepo();

    public MutableLiveData<Users> getUsers() {
        return repository.getUsers();
    }
}
