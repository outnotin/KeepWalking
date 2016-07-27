package com.augmentis.ayp.keepwalking;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Noppharat on 7/27/2016.
 */
public class KeepWalkingMain {

    List<KeepWalking> keepWalkingList;

    private static KeepWalkingMain instance;

    public KeepWalkingMain(){
        keepWalkingList = new ArrayList<>();

//        KeepWalking keepWalking = new KeepWalking();
//        keepWalking.setTitle("Title #1");
//
//        KeepWalking keepWalking1 = new KeepWalking();
//        keepWalking1.setTitle("Title #2");
//
//        keepWalkingList.add(keepWalking);
//        keepWalkingList.add(keepWalking1);

    }

    public static KeepWalkingMain getInstance(Context context){
        if(instance == null){
            instance = new KeepWalkingMain();
        }
        return instance;
    }

    public int getKeepWalkingPositionById(UUID uuid){
        int size = keepWalkingList.size();
        for (int i = 0; i < size ; i++){
            if (keepWalkingList.get(i).getId().equals(uuid)){
                return i;
            }
        }
        return -1;
    }

    public KeepWalking getKeepWalkingById(UUID uuid){
        for (KeepWalking keepWalking : keepWalkingList){
            if(keepWalking.getId().equals(uuid)){
                return keepWalking;
            }
        }
        return null;
    }

    public List<KeepWalking> getKeepWalkings(){
        return this.keepWalkingList;
    }

}
