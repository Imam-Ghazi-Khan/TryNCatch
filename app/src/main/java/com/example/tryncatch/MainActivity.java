package com.example.tryncatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.weblist);
        String arr[] = {"GeeksForGeeks","LeetCode","InterviewBit","CodeForces","CodeChef"};
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2,arr);
        listView.setAdapter(adapter);
    }

    public void gfg(View view)
    {
        Uri gfglink = Uri.parse("https://www.geeksforgeeks.org");
        Intent gfgIntent = new Intent(Intent.ACTION_VIEW, gfglink);
        startActivity(gfgIntent);
    }
    public void leetCode(View view)
    {
        Uri leetcodelink = Uri.parse("https://www.leetcode.com");
        Intent leetIntent = new Intent(Intent.ACTION_VIEW,leetcodelink);
        startActivity(leetIntent);
    }
    public void interviewBit(View view)
    {
        Uri interviewbitlink = Uri.parse("https://www.interviewbit.com");
        Intent interviewbitIntent = new Intent(Intent.ACTION_VIEW,interviewbitlink);
        startActivity(interviewbitIntent);
    }
    public void codeChef(View view)
    {
        Uri codeCheflink = Uri.parse("https://www.codechef.com");
        Intent codeChefIntent = new Intent(Intent.ACTION_VIEW,codeCheflink);
        startActivity(codeChefIntent);
    }
    public void codeForces(View view)
    {
        Uri codeForceslink = Uri.parse("https://codeforces.com");
        Intent codeForcesIntent = new Intent(Intent.ACTION_VIEW,codeForceslink);
        startActivity(codeForcesIntent);
    }
}