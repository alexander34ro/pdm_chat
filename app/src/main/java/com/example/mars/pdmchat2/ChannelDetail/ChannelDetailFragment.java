package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mars.pdmchat2.ChannelList.ChannelListActivity;
import com.example.mars.pdmchat2.R;
import com.example.mars.pdmchat2.Services.APIService;
import com.example.mars.pdmchat2.Services.SendbirdAPI;

/**
 * A fragment representing a single Channel detail screen.
 * This fragment is either contained in a {@link ChannelListActivity}
 * in two-pane mode (on tablets) or a {@link ChannelDetailActivity}
 * on handsets.
 */
public class ChannelDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    final APIService apiService = SendbirdAPI.call();
    private OpenChannelModel viewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChannelDetailFragment() {
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ChannelDetailFragment THIS = this;

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            Single<OpenChannel> openChannelSubscription = apiService.getOpenChannelData(getArguments().getString(ARG_ITEM_ID));
            openChannelSubscription.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<OpenChannel>() {
                        @Override
                        public void onSubscribe(Disposable d) {}
                        @Override
                        public void onSuccess(OpenChannel openChannel) {
                            mItem = openChannel;
                            Activity activity = THIS.getActivity();
                            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
                            if (appBarLayout != null) {
                                appBarLayout.setTitle(mItem.getName());
                            }
                        }
                        @Override
                        public void onError(Throwable e) {}
                    });
        }
    }
    */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url = getArguments().getString(ARG_ITEM_ID);
        viewModel = ViewModelProviders.of(this).get(OpenChannelModel.class);
        viewModel.init(url);
        viewModel.getChannel().observe(this, openChannel -> {
            CollapsingToolbarLayout appBarLayout = this.getActivity().findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(openChannel.getName());
                ((TextView) this.getView().findViewById(R.id.channel_detail)).setText(openChannel.getCustomType());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.channel_detail, container, false);

        /*
        // Show the dummy content as text in a TextView.
        Single<OpenChannel> openChannelSubscription = apiService.getOpenChannelData(getArguments().getString(ARG_ITEM_ID));
        openChannelSubscription.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OpenChannel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("connected");
                    }
                    @Override
                    public void onSuccess(OpenChannel openChannel) {
                        mItem = openChannel;
                        System.out.println(mItem.getCustomType());
                        ((TextView) rootView.findViewById(R.id.channel_detail)).setText(mItem.getCustomType());
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
                */

        return rootView;
    }
}
