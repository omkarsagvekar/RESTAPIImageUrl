package com.example.restapiexample;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MySingleton {

    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    public MySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context) {
        if(instance == null){
            instance = new MySingleton(context);
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }


}


