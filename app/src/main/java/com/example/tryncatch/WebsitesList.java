package com.example.tryncatch;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
    DBHelper db;
    ArrayList<String> websites = new ArrayList<String>();
    ArrayList<String> weblinks = new ArrayList<String>();
    ArrayAdapter adapter;
    ListView listView;
    EditText webName, webLink;
    Button AddWebSites;

    public WebsitesList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DBHelper(getContext());

        View view = inflater.inflate(R.layout.fragment_websites_list, container, false);
        listView = view.findViewById(R.id.weblist);
        webName = view.findViewById(R.id.editText1);
        webLink = view.findViewById(R.id.editText2);
        AddWebSites = view.findViewById(R.id.button1);


        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, websites);
        listView.setAdapter(adapter);
        viewData();
        AddWebSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addWebName = webName.getText().toString();
                String addWebLink = webLink.getText().toString();
                if (db.InsertData(webName.getText().toString(), webLink.getText().toString())) {
                    webName.setText("");
                    webLink.setText("");
                    websites.clear();
                    weblinks.clear();
                    viewData();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedWebsite = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Opening " + selectedWebsite, Toast.LENGTH_SHORT).show();
                Uri link = Uri.parse(weblinks.get(position));
                //Intent webIntent = new Intent(Intent.ACTION_VIEW, link);
                Intent webIntent = new Intent(getContext(), ShowWeb.class);
                webIntent.putExtra("web", weblinks.get(position));
                startActivity(webIntent);
            }
        });
        return view;
    }

    private void viewData() {
        Cursor cursor = db.viewData();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                websites.add(cursor.getString(1));
                weblinks.add(cursor.getString(2));//index i is name,index 0 is Id
            }
            adapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, websites);
            listView.setAdapter(adapter);
        }

    }
}