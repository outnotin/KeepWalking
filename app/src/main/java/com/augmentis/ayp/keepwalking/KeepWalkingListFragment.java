package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class KeepWalkingListFragment extends Fragment {
    private static final int REQUEST_UPDATE_KEEP_WALKING = 1234;
    private RecyclerView _keepWalkingRecyclerView;
    private KeepWalkingAdapter _adapter;
    protected static final String TAG = "KEEP_WALKING_LIST";
    private Integer[] keepPos;
    private Button keepWalkingAddButton;
    private KeepWalking _keepWalking;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_UPDATE_KEEP_WALKING){
            if(resultCode == Activity.RESULT_OK){
                keepPos = (Integer[]) data.getExtras().get("position");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keep_walking_list, container, false);


        keepWalkingAddButton = (Button) v.findViewById(R.id.add_button);
        keepWalkingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _keepWalking = new KeepWalking();
                Intent intent = KeepWalkingActivity.newIntent(getActivity(), _keepWalking.getId());
                startActivityForResult(intent, REQUEST_UPDATE_KEEP_WALKING);
            }
        });

        _keepWalkingRecyclerView = (RecyclerView) v.findViewById(R.id.keep_walking_recycler_view);
        _keepWalkingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return v;
    }

    private void updateUI(){
        KeepWalkingMain keepWalkingMain = KeepWalkingMain.getInstance(getActivity());
        List<KeepWalking> keepWalkings = keepWalkingMain.getKeepWalkings();
        if(_adapter == null){
            _adapter = new KeepWalkingAdapter(keepWalkings);
            _keepWalkingRecyclerView.setAdapter(_adapter);
        }else {
            _adapter.notifyDataSetChanged();

        }
    }
    private class KeepWalkingHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView _titleTextView;
        public TextView _dateTextView;

        KeepWalking _keepWalking;


        public KeepWalkingHolder(View itemView) {
            super(itemView);

            _titleTextView = (TextView) itemView.findViewById(R.id.list_item_keep_walking_title_text_view);
            _dateTextView = (TextView) itemView.findViewById(R.id.list_item_keep_walking_date_text_view);

            itemView.setOnClickListener(this);
        }

        public void bind(KeepWalking keepWalking, int position){
            _keepWalking = keepWalking;
            _titleTextView.setText(_keepWalking.getTitle());
            _dateTextView.setText(_keepWalking.getKeepDate().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = KeepWalkingActivity.newIntent(getActivity(), _keepWalking.getId());
            startActivityForResult(intent, REQUEST_UPDATE_KEEP_WALKING);
        }
    }

    private class KeepWalkingAdapter extends RecyclerView.Adapter<KeepWalkingHolder>{
        private List<KeepWalking> _keepWalking;

        public KeepWalkingAdapter(List<KeepWalking> keepWalkings){
            _keepWalking = keepWalkings;
        }

        @Override
        public KeepWalkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_keep_walking, parent, false);
            return new KeepWalkingHolder(v);
        }

        @Override
        public void onBindViewHolder(KeepWalkingHolder holder, int position) {
            KeepWalking keepWalking = _keepWalking.get(position);
            holder.bind(keepWalking, position);
        }

        @Override
        public int getItemCount() {

                return _keepWalking.size();


        }
    }
}
