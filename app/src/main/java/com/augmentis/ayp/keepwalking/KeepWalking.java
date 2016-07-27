package com.augmentis.ayp.keepwalking;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Noppharat on 7/27/2016.
 */
public class KeepWalking {
    private UUID id;
    private String title;
    private Date keepDate;

    public KeepWalking(){
        id = UUID.randomUUID();
        keepDate = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getKeepDate() {
        return keepDate;
    }

    public void setKeepDate(Date keepDate) {
        this.keepDate = keepDate;
    }

    public String getStringDate(Date inputDate){
        DateFormat dfm = new DateFormat();
        return dfm.format("dd MMMM yyyy", inputDate).toString();
    }
}
