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


public class Videos extends Fragment {
    ListView listView;
    EditText webName,webLink;
    Button AddWebSites;
    public Videos() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        listView = view.findViewById(R.id.weblist);
        webName = view.findViewById(R.id.editText1);
        webLink = view.findViewById(R.id.editText2);
        AddWebSites = view.findViewById(R.id.button1);

        ArrayList<String> websites = new ArrayList<String>(Arrays.asList("Slidenerd's android dev","C programming with CodewithHarry","Jenny's lecture on C programming","NickWhite's leetcode series","Anuj Bhaiya's DSAONE series","SmartProgramming in Java","TakeUForward","LoveBabbar","Recursion with Aditya Verma "));
        ArrayList<String> weblinks = new ArrayList<String>(Arrays.asList("https://www.youtube.com/watch?v=hzrGAZnMOMQ&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa","https://www.youtube.com/watch?v=7Dh73z3icd8&list=PLu0W_9lII9aiXlHcLx-mDH1Qul38wD3aR","https://www.youtube.com/watch?v=EjavYOFoJJ0&list=PLdo5W4Nhv31a8UcMN9-35ghv8qyFWD9_S","https://www.youtube.com/watch?v=U6-X_QOwPcs&list=PLU_sdQYzUj2keVENTP0a5rdykRSgg9Wp-","https://www.youtube.com/watch?v=N89PN_uyelU&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p","https://www.youtube.com/c/SmartProgramming/playlists","https://www.youtube.com/c/takeUforward","https://www.youtube.com/c/LoveBabbar1","https://www.youtube.com/watch?v=kHi1DUhp9kM&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY"));

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