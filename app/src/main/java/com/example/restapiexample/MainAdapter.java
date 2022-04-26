package com.example.restapiexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<MainModel> arrayList;
    private Context context;
    private Bitmap bitmap;
    public MainAdapter(ArrayList<MainModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getTitle());

        String imageUrl = arrayList.get(position).getImageUrl();
        if (holder.imageView != null) {
            /*-------------fatching image------------*/;
            Picasso.get().load(imageUrl).into(holder.imageView);
            holder.imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.txt_title);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
