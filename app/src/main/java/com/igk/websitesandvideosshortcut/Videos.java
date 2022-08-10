package com.igk.websitesandvideosshortcut;

import android.content.Intent;
import android.database.Cursor;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;


public class Videos extends Fragment {
    DBHelper2 db;
    ArrayList<String> websites = new ArrayList<String>();
    ArrayList<String> weblinks = new ArrayList<String>();
    ArrayAdapter adapter;
    ListView listView;
    EditText webName, webLink;
    Button AddWebSites;
    private AdView mAdView;
    public Videos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        db = new DBHelper2(getContext());

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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedWebsite = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                db.delete(selectedWebsite);
                websites.clear();
                weblinks.clear();
                viewData();
                return true;
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
        }
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, websites);
        listView.setAdapter(adapter);
    }
}