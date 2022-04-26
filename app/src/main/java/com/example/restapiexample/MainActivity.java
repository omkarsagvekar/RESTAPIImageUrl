package com.example.restapiexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<MainModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        String url = "https://jsonplaceholder.typicode.com/photos";
        arrayList = new ArrayList<>();
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0; i<response.length(); i++){
                        JSONObject jsonObj = response.getJSONObject(i);
                        String title = jsonObj.getString("title");
                        String imgUrl = jsonObj.getString("thumbnailUrl");
                        MainModel mainModel = new MainModel();
                        mainModel.setTitle(title);
                        mainModel.setImageUrl(imgUrl);
                        arrayList.add(mainModel);
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
                MainAdapter mainAdapter = new MainAdapter(arrayList,MainActivity.this);
                recyclerView.setAdapter(mainAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonRequest);

    }
}
