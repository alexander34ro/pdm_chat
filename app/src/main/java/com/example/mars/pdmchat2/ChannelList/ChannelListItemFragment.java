package com.example.mars.pdmchat2.ChannelList;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class ChannelListItemFragment extends Fragment {
    private static final String URL = "url";
    private ChannelListModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String userId = getArguments().getString(URL);
        viewModel = ViewModelProviders.of(this).get(ChannelListModel.class);
        //viewModel.init(userId);
    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_profile, container, false);
    }
    */
}
