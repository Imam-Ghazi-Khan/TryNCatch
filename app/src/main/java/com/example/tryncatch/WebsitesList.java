package com.example.tryncatch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class WebsitesList extends Fragment {
    ListView listView;
    EditText webName,webLink;
    Button AddWebSites;
    public WebsitesList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_websites_list, container, false);
        listView = view.findViewById(R.id.weblist);
        webName = view.findViewById(R.id.editText1);
        webLink = view.findViewById(R.id.editText2);
        AddWebSites = view.findViewById(R.id.button1);

        ArrayList<String> websites = new ArrayList<String>(Arrays.asList("GeeksForGeeks", "LeetCode", "InterviewBit", "CodeForces", "CodeChef","HackerRank","HackerEarth","GitHub","FreeCodeCamp","W3School","CodingBat"));
        ArrayList<String> weblinks = new ArrayList<String>(Arrays.asList("https://geeksforgeeks.org", "https://leetcode.com", "https://interviewbit.com", "https://codechef.com", "https://codeforces.com","https://hackerrank.com","https://hackerearth.com","https://github.com","https://freecodecamp.org","https://w3schools.com","https://codingbat.com"));

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, websites);
        listView.setAdapter(adapter);
        AddWebSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addWebName = webName.getText().toString();
                String addWebLink = webLink.getText().toString();
                websites.add(0,addWebName);
                weblinks.add(0,addWebLink);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedWebsite = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Opening "+selectedWebsite, Toast.LENGTH_SHORT).show();
                Uri link = Uri.parse(weblinks.get(position));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(webIntent);
            }
        });
        return view;
    }
}