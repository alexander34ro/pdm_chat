package com.example.mars.pdmchat2.ChannelDetail;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class OpenChannel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("participant_count")
    @Expose
    private Integer participantCount;
    @SerializedName("custom_type")
    @Expose
    private String customType;
    @SerializedName("is_ephemeral")
    @Expose
    private Boolean isEphemeral;
    @SerializedName("channel_url")
    @Expose
    @android.support.annotation.NonNull
    @PrimaryKey
    private String channelUrl;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("cover_url")
    @Expose
    private String coverUrl;
    @SerializedName("freeze")
    @Expose
    private Boolean freeze;
    @SerializedName("max_length_message")
    @Expose
    private Integer maxLengthMessage;
    @SerializedName("data")
    @Expose
    private String data;

    /**
     * No args constructor for use in serialization
     */
    public OpenChannel() {
    }

    /**
     * @param coverUrl
     * @param channelUrl
     * @param customType
     * @param freeze
     * @param createdAt
     * @param name
     * @param data
     * @param participantCount
     * @param operators
     * @param maxLengthMessage
     * @param isEphemeral
     */
    public OpenChannel(String name, Integer participantCount, String customType, Boolean isEphemeral, String channelUrl, Integer createdAt, String coverUrl, Boolean freeze, Integer maxLengthMessage, String data, List<Object> operators) {
        super();
        this.name = name;
        this.participantCount = participantCount;
        this.customType = customType;
        this.isEphemeral = isEphemeral;
        this.channelUrl = channelUrl;
        this.createdAt = createdAt;
        this.coverUrl = coverUrl;
        this.freeze = freeze;
        this.maxLengthMessage = maxLengthMessage;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public Boolean getIsEphemeral() {
        return isEphemeral;
    }

    public void setIsEphemeral(Boolean isEphemeral) {
        this.isEphemeral = isEphemeral;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }

    public Integer getMaxLengthMessage() {
        return maxLengthMessage;
    }

    public void setMaxLengthMessage(Integer maxLengthMessage) {
        this.maxLengthMessage = maxLengthMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}