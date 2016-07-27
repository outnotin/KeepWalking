package com.augmentis.ayp.keepwalking;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class KeepWalkingListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return new KeepWalkingListFragment();
    }
}
