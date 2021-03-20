
package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CompleteFragment extends Fragment {

    RecyclerView recyclerView;
    CompleteAdapter completeAdapter;
    List<Task> tasks;

    public CompleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_complete, container, false);

        recyclerView=(view).findViewById(R.id.recycleviewComplete);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tasks =DatabaseClient.getInstance(getActivity()).getAppDatabase().taskDao().getAllComplete();

        completeAdapter=new CompleteAdapter(getActivity(),tasks);
        recyclerView.setAdapter(completeAdapter);
        completeAdapter.notifyDataSetChanged();
        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }

}