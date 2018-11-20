package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OpenChannelDao {
    @Insert(onConflict = REPLACE)
    void save(OpenChannel openChannel);

    @Query("SELECT * FROM openChannel WHERE channelUrl = :channelUrl")
    LiveData<OpenChannel> load(String channelUrl);
}
