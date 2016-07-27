package com.augmentis.ayp.keepwalking;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;



public class KeepWalkingFragment extends Fragment {

    private static final String KEEPWALKING_ID = "KeepWalkingFragment.KEEPWALKING_ID";

    private KeepWalking keepWalking;
    private String editTitles;
    private EditText editKeepWalkingTitleText;
    private Button keepWalkingSaveButton;
    private boolean isNewKeepWalking;


    public KeepWalkingFragment() {

    }


    public static KeepWalkingFragment newInstance(UUID keepWalkingId, String param2) {
        KeepWalkingFragment fragment = new KeepWalkingFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEEPWALKING_ID, keepWalkingId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID keepWalkingId = (UUID) getArguments().getSerializable(KEEPWALKING_ID);
        keepWalking = KeepWalkingMain.getInstance(getActivity()).getKeepWalkingById(keepWalkingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keep_walking, container, false);

        editKeepWalkingTitleText = (EditText) v.findViewById(R.id.title_edit_text);
        if(keepWalking == null){
            isNewKeepWalking = true;
            keepWalking = new KeepWalking();
            keepWalking.setTitle("");
        }else{
            isNewKeepWalking = false;
        }

        editKeepWalkingTitleText.setText(keepWalking.getTitle());

        editKeepWalkingTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editTitles = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        keepWalkingSaveButton = (Button) v.findViewById(R.id.save_button);
        keepWalkingSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeepWalkingMain keepWalkingMain = KeepWalkingMain.getInstance(getActivity());
                if(isNewKeepWalking){
                    keepWalkingMain.keepWalkingList.add(keepWalking);
                }
                keepWalking.setTitle(editTitles);

                Intent intent = new Intent(getContext(), KeepWalkingListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return v;
    }


}
