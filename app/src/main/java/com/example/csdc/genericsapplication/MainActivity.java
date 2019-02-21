package com.example.csdc.genericsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private MyAdapter mAdapter;
    private List<Student> mStudents;


    private String[] names = new String[]{"大声道","湿哒哒所","大萨达"};
    private int[] ages = new int[]{12,23,43};
    private String[] classes = new String[]{"六年一班","九年一班","八年一班"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStudents = new ArrayList<>();
        mListView = findViewById(R.id.listView);
        initData();
        setAdapter();
    }

    private void setAdapter(){
        mListView.setAdapter(new MyAdapter<Student>(mStudents,R.layout.student_layout_item) {
            @Override
            public void bindView(ViewHolder viewHolder, Student obj) {
                viewHolder.setText(R.id.textView,obj.getName())
                        .setText(R.id.textView2,obj.getAge()+"")
                        .setText(R.id.textView3,obj.getClassName());
            }
        });
    }

    private void initData(){
        for (int i = 0; i < 3; i++) {
            mStudents.add(new Student(ages[i],names[i],classes[i]));
        }
    }
}
