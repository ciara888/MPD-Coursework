package com.example.equakefinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.AdapterClickListener {
// Ciara Scott S1709819
    private ItemAdapter adapter;
    private String result;
    private RecyclerView itemrv;
    private final String url1 = "";
    private final  String urlSource = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    MapsFragment fragment = new MapsFragment();
    Handler handler = new Handler();
    FilterFragment filterFragment = new FilterFragment();
    Context context = this;
    ItemAdapter.AdapterClickListener adapterContext = this;
    ArrayList<Item> itemList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemrv = findViewById(R.id.rvitem);
        periodicUpdate.run();

        //BottomNavigation

        // assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.Home);
        // perform Itemselctedlistner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext()
                                , com.example.equakefinal.MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Map:
                        startActivity(new Intent(getApplicationContext()
                                , Map.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Filter:
                        startActivity(new Intent(getApplicationContext()
                                , FilterActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

        Log.e("MyTag", "in onCreate");
    }

    @Override
    public void onClick(View v) {
        Log.e("MyTag", "in onClick");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", itemList);
        fragment.setArguments(bundle);
        filterFragment.setArguments(bundle);
        Log.e("MyTag", "after startProgress");
    }

    private final Runnable periodicUpdate = new Runnable() {
        public void run() {
            AsyncTask<Void, Integer, Void> runTask = new GetXMLAsyncTask();
            handler.postDelayed(periodicUpdate, 10 * 60000);
            Log.e("MyTag", "Update");
            runTask.execute();
        }
    };

    @Override
    public void onAdapterClickListener(View view, int position) throws ParseException {
        Item item = itemList.get(position);


    }


    public static ArrayList<Item> parseData(String dataToParse) {
        Item item = null;
        ArrayList<Item> itemList = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(dataToParse));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("rss")) {
                        itemList = new ArrayList<>();

                    } else if (xpp.getName().equalsIgnoreCase("channel")) {

                    } else if (xpp.getName().equalsIgnoreCase("item")) {
                        item = new Item();

                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        String temp = xpp.nextText();

                        if (item != null) {
                            item.setTitle(temp);
                        }
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        String temp = xpp.nextText();

                        if (item != null) {
                            item.setDescription(temp);
                        }
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        String temp = xpp.nextText();

                        if (item != null) {
                            item.setLink(temp);
                        }
                    } else if (xpp.getName().equalsIgnoreCase("pubdate")) {
                        String temp = xpp.nextText();

                        if (item != null) {
                            item.setPubDate(temp);
                        }
                    } else if (xpp.getName().equalsIgnoreCase("category")) {
                        String temp = xpp.nextText();

                        if (item != null) {
                            item.setCategory(temp);
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        if (item != null) {

                            itemList.add(item);
                        }
                    } else if (xpp.getName().equalsIgnoreCase("channel")) {
                        int size;
                        size = itemList.size();

                    } else if (xpp.getName().equalsIgnoreCase("rss")) {

                    }
                }

                eventType = xpp.next();

            }
        } catch (XmlPullParserException ae1) {
            Log.e("MyTag", "Parsing error" + ae1.toString());
        } catch (IOException ae1) {
            Log.e("MyTag", "IO error during parsing");
        }



        return itemList;
    }


    private class GetXMLAsyncTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";

            Log.e("MyTag", "in run");

            try {
                Log.e("MyTag", "in try");
                aurl = new URL(urlSource);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                Log.e("MyTag", "after ready");

                while ((inputLine = in.readLine()) != null) {
                    if (result != null) {
                        result = result + inputLine;
                    } else {
                        result = inputLine;
                    }
                }
                in.close();

                itemList = parseData(result);
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception in run");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            itemrv.setLayoutManager(new LinearLayoutManager(context));
            adapter = new ItemAdapter(itemList, adapterContext);
            itemrv.setAdapter(adapter);
        }
    }
}