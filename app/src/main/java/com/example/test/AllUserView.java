package com.example.test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class AllUserView extends Fragment {

    RecyclerView recyclerView;
    CompleteAdapter completeAdapter;
    List<Task> taskList;
    public AllUserView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_user_view, container, false);

        taskList =DatabaseClient.getInstance(getActivity()).getAppDatabase().taskDao().getAll();

        recyclerView=(view).findViewById(R.id.recyleviewAlluser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        completeAdapter=new CompleteAdapter(getActivity(),taskList);
        recyclerView.setAdapter(completeAdapter);
        completeAdapter.notifyDataSetChanged();

        Toast.makeText(getActivity(), "Sucessful Hello", Toast.LENGTH_SHORT).show();

        return view;
    }

}