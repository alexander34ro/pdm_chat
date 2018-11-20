package com.example.mars.pdmchat2.ChannelList;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mars.pdmchat2.ChannelDetail.ChannelDetailActivity;
import com.example.mars.pdmchat2.ChannelDetail.ChannelDetailFragment;
import com.example.mars.pdmchat2.ChannelDetail.OpenChannel;
import com.example.mars.pdmchat2.ChannelDetail.OpenChannelDatabase;
import com.example.mars.pdmchat2.R;
import com.example.mars.pdmchat2.Services.APIService;
import com.example.mars.pdmchat2.Services.SendbirdAPI;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * An activity representing a list of Channels. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ChannelDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ChannelListActivity extends AppCompatActivity {

    final APIService apiService = SendbirdAPI.call();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    public ChannelListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);

        if (!SendbirdAPI.hasDatabase()) {
            OpenChannelDatabase db = Room.databaseBuilder(getApplicationContext(),
                    OpenChannelDatabase.class, "channel-details-database").build();
            SendbirdAPI.setDatabase(db);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.channel_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        final View recyclerView = findViewById(R.id.channel_list);
        assert recyclerView != null;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Single<OpenChannel> newOpenChannelSubscription =
                        apiService.newOpenChannelData(new OpenChannel());

                newOpenChannelSubscription.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<OpenChannel>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                // we'll come back to this in a moment
                                Snackbar.make(view, "Creating new channel...", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }

                            @Override
                            public void onSuccess(OpenChannel openChannel) {
                                // data is ready and we can update the UI
                                System.out.println(openChannel.getName());
                            }

                            @Override
                            public void onError(Throwable e) {
                                // oops, we best show some error message
                                Snackbar.make(view, "Oops", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }
                        });
            }
        });

        ChannelListModel model = ViewModelProviders.of(this).get(ChannelListModel.class);
        model.getChannels().observe(this, openChannels -> {
            setupRecyclerView((RecyclerView) recyclerView, openChannels.getChannels());
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<OpenChannel> channels) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, channels, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ChannelListActivity mParentActivity;
        private final List<OpenChannel> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenChannel item = (OpenChannel) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ChannelDetailFragment.ARG_ITEM_ID, item.getChannelUrl());
                    ChannelDetailFragment fragment = new ChannelDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.channel_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ChannelDetailActivity.class);
                    intent.putExtra(ChannelDetailFragment.ARG_ITEM_ID, item.getChannelUrl());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ChannelListActivity parent,
                                      List<OpenChannel> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.channel_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getName());
            holder.mContentView.setText(mValues.get(position).getCustomType());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = view.findViewById(R.id.id_text);
                mContentView = view.findViewById(R.id.content);
            }
        }
    }
}
