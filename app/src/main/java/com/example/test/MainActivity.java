package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextInputEditText textInputLayout;
    MaterialCheckBox cbactive, cbcompleted;
    MaterialButton save;
    ViewPager viewPager;
    String descriptions;
    TextView all, actives, complete, count, clear;
    Task task;
    List<Task> tasks;
    List<Task> tasksall = new ArrayList<>();
    PageViewAdater pageViewAdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InItValues();
        tasksall = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().getAllComplete();
    }

    private void InItValues() {
        textInputLayout = findViewById(R.id.editText_descriotion);
        cbactive = findViewById(R.id.active);
        cbcompleted = findViewById(R.id.Complted);
        save = findViewById(R.id.save);
        all = findViewById(R.id.all);
        actives = findViewById(R.id.activeuser);
        complete = findViewById(R.id.completedtask);
        count = findViewById(R.id.count);
        clear = findViewById(R.id.cleartask);
        viewPager = findViewById(R.id.viewpager);


        tasks = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().getAllactive();
        for (int j = 0; j <= tasks.size(); j++) {
            count.setText(String.valueOf(j));
        }

        pageViewAdater = new PageViewAdater(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdater);
        viewPager.setOffscreenPageLimit(2);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                FragmentManager fm = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.commit(); // save the changes
            }
        });

        actives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                FragmentManager fm = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.commit();
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                FragmentManager fm = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.commit();
            }
        });

        int a = 0;
        if (a <= tasksall.size()) {
            clear.setTextColor(Color.BLACK);
            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().getAllDeleteCompleteStatus();
                }
            });
        } else {
            clear.setTextColor(Color.GRAY);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {
                changTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                descriptions = textInputLayout.getText().toString();

                if (descriptions.isEmpty()) {
                    Toast.makeText(MainActivity.this, "write description", Toast.LENGTH_SHORT).show();
                } else {
                    insertData();
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changTab(int position) {

        if (position == 0) {
            all.setTextColor(getApplication().getColor(R.color.purple_500));
            actives.setTextColor(getApplication().getColor(R.color.black));
            complete.setTextColor(getApplication().getColor(R.color.black));
        }

        if (position == 1) {
            all.setTextColor(getApplication().getColor(R.color.black));
            actives.setTextColor(getApplication().getColor(R.color.purple_500));
            complete.setTextColor(getApplication().getColor(R.color.black));
        }

        if (position == 2) {
            all.setTextColor(getApplication().getColor(R.color.black));
            actives.setTextColor(getApplication().getColor(R.color.black));
            complete.setTextColor(getApplication().getColor(R.color.purple_500));
        }
    }


    private void insertData() {

        StringBuffer stringBuffer = new StringBuffer();
        if (cbactive.isChecked() && !cbcompleted.isChecked()) {
            stringBuffer.append(cbactive.getText().toString());
        } else if (cbcompleted.isChecked() && !cbactive.isChecked()) {
            stringBuffer.append(cbcompleted.getText().toString());
        }

        task = new Task();
        task.setDescrition(descriptions);
        task.setActive(String.valueOf(stringBuffer));

        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().insert(task);

        textInputLayout.setText("");
        cbcompleted.setChecked(false);
        cbactive.setChecked(false);

        Toast.makeText(this, "Sucessfully Inserted", Toast.LENGTH_SHORT).show();
    }
}
