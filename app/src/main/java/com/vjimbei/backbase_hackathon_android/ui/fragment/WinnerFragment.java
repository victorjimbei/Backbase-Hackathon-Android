package com.vjimbei.backbase_hackathon_android.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinnerFragment extends DialogFragment {

    public static final String ARG_TASK_WINNER = "winner_task_arg";

    private TextView winnerMsg;
    private Button okBtn;

    private Task task;

    public static WinnerFragment newInstance(Task task) {
        WinnerFragment fragment = new WinnerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TASK_WINNER, task);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = getArguments().getParcelable(ARG_TASK_WINNER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_winner, container, false);
        winnerMsg = (TextView)view.findViewById(R.id.winner_msg);
        okBtn = (Button)view.findViewById(R.id.btn_ok_winner);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        winnerMsg.setText(String.format(getString(R.string.format_winner_msg), task.getRevenue()));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WinnerFragment.this.dismiss();
            }
        });
    }
}
