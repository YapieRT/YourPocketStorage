package com.example.yourpocketstorage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Item> items;
    Context context;


    public RecyclerViewAdapter(Context ct, List<Item> itemArrayList){
        context = ct;
        items = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_container,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id_TextView.setText(Integer.toString(items.get(position).id));
        holder.name_TextView.setText(items.get(position).name);
        holder.amount_TextView.setText(Integer.toString(items.get(position).amount));

        holder.layout_item_container.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemOverview.class);
            intent.putExtra("item",items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id_TextView, name_TextView, amount_TextView;
        ConstraintLayout layout_item_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_TextView = itemView.findViewById(R.id.layout_item_id);
            name_TextView = itemView.findViewById(R.id.layout_item_name);
            amount_TextView = itemView.findViewById(R.id.layout_item_amount);
            layout_item_container = itemView.findViewById(R.id.layout_item_container);
        }
    }
}
