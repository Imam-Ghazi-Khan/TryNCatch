package com.example.tryncatch;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText webName,webLink;
    Button AddWebSites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.weblist);
        webName = findViewById(R.id.editText1);
        webLink = findViewById(R.id.editText2);
        AddWebSites = findViewById(R.id.button1);

        ArrayList<String> websites = new ArrayList<String>(Arrays.asList("GeeksForGeeks", "LeetCode", "InterviewBit", "CodeForces", "CodeChef","HackerRank","HackerEarth","GitHub","FreeCodeCamp","W3School","CodingBat"));
        ArrayList<String> weblinks = new ArrayList<String>(Arrays.asList("https://geeksforgeeks.org", "https://leetcode.com", "https://interviewbit.com", "https://codechef.com", "https://codeforces.com","https://hackerrank.com","https://hackerearth.com","https://github.com","https://freecodecamp.org","https://w3schools.com","https://codingbat.com"));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, websites);
        listView.setAdapter(adapter);
        AddWebSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addWebName = webName.getText().toString();
                String addWebLink = webLink.getText().toString();
                websites.add(addWebName);
                weblinks.add(addWebLink);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedWebsite = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Opening "+selectedWebsite, Toast.LENGTH_SHORT).show();
                Uri link = Uri.parse(weblinks.get(position));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(webIntent);
            }
        });
    }
    }
