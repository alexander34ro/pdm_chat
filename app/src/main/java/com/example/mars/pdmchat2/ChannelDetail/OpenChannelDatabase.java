package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {OpenChannel.class}, version = 1)
public abstract class OpenChannelDatabase extends RoomDatabase {
    public abstract OpenChannelDao openChannelDao();
}