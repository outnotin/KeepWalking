package com.augmentis.ayp.keepwalking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class KeepWalkingActivity extends SingleFragmentActivity {

   protected static final String KEEPWALKING_ID = "KeepWalkingActivity.KeepWalkingId";

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, KeepWalkingActivity.class);
        intent.putExtra(KEEPWALKING_ID, id);
        return intent;
    }
    @Override
    protected Fragment onCreateFragment() {
        UUID keepWalkingId = (UUID) getIntent().getSerializableExtra(KEEPWALKING_ID);
        Fragment fragment = KeepWalkingFragment.newInstance(keepWalkingId);
        return fragment;
    }
}
