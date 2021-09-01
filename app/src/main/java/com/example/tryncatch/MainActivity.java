package com.example.tryncatch;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.weblist);
        String websites[] = {"GeeksForGeeks", "LeetCode", "InterviewBit", "CodeForces", "CodeChef","HackerRank","HackerEarth","GitHub","FreeCodeCamp","W3School","CodingBat"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, websites);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedWebsite = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Opening "+selectedWebsite, Toast.LENGTH_SHORT).show();
                    String weblinks[] = {"https://geeksforgeeks.org", "https://leetcode.com", "https://interviewbit.com", "https://codechef.com", "https://codeforces.com","https://hackerrank.com","https://hackerearth.com","https://github.com","https://freecodecamp.org","https://w3schools.com","https://codingbat.com"};
                    Uri link = Uri.parse(weblinks[position]);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, link);
                    startActivity(webIntent);
            }
        });
    }
    }
