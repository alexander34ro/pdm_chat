package com.example.mars.pdmchat2.Login;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("has_ever_logged_in")
    @Expose
    private Boolean hasEverLoggedIn;
    @SerializedName("discovery_keys")
    @Expose
    private List<Object> discoveryKeys = null;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("is_online")
    @Expose
    private Boolean isOnline;
    @SerializedName("last_seen_at")
    @Expose
    private Integer lastSeenAt;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param isOnline
     * @param isActive
     * @param discoveryKeys
     * @param profileUrl
     * @param nickname
     * @param lastSeenAt
     * @param hasEverLoggedIn
     * @param userId
     */
    public User(Boolean hasEverLoggedIn, List<Object> discoveryKeys, String userId, Boolean isActive, Boolean isOnline, Integer lastSeenAt, String nickname, String profileUrl) {
        super();
        this.hasEverLoggedIn = hasEverLoggedIn;
        this.discoveryKeys = discoveryKeys;
        this.userId = userId;
        this.isActive = isActive;
        this.isOnline = isOnline;
        this.lastSeenAt = lastSeenAt;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    public Boolean getHasEverLoggedIn() {
        return hasEverLoggedIn;
    }

    public void setHasEverLoggedIn(Boolean hasEverLoggedIn) {
        this.hasEverLoggedIn = hasEverLoggedIn;
    }

    public List<Object> getDiscoveryKeys() {
        return discoveryKeys;
    }

    public void setDiscoveryKeys(List<Object> discoveryKeys) {
        this.discoveryKeys = discoveryKeys;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(Integer lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}