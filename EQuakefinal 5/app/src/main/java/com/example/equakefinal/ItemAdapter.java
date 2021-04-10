package com.example.equakefinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    // Ciara Scott S1709819
    private List<Item> mitem;
    private AdapterClickListener mAdapterClickListener;

    public ItemAdapter(List<Item> items, AdapterClickListener adapterClickListener){
        mAdapterClickListener = adapterClickListener;
        mitem = items;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Button warnBtn;
        private TextView textLocation;
        private TextView textStrength;
        private View earthquakeItemView;
        AdapterClickListener adapterClickListener;


        public ViewHolder(View itemView, AdapterClickListener adapterClickListener){
            super(itemView);

            textLocation = itemView.findViewById(R.id.textLocation);
            textStrength = itemView.findViewById(R.id.textStrength);
           warnBtn = itemView.findViewById(R.id.warningBtn);
            earthquakeItemView = itemView.findViewById(R.id.earthquake_itemView);
            this.adapterClickListener = adapterClickListener;
               warnBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            try {
                adapterClickListener.onAdapterClickListener(itemView, getAdapterPosition());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_list,  parent, false);

        return new ViewHolder(itemView, mAdapterClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = mitem.get(position);
        double strength = item.getStrength();
        Button infoButton = holder.warnBtn;
        View view = holder.earthquakeItemView;
        if (strength<=0.9){
            infoButton.setBackgroundColor(Color.GREEN);
        }
        if(strength>=1 && strength<=2){
            infoButton.setBackgroundColor(Color.YELLOW);
        }
        if(strength>2){
            infoButton.setBackgroundColor(Color.RED);
        }
        TextView txtLoc = holder.textLocation;
        txtLoc.setText("Location: " + item.getLocation());
        TextView txtStr = holder.textStrength;
        txtStr.setText("Strength: " + item.getStrength());

        infoButton.setText("Warning!!");
        infoButton.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return mitem.size();
    }

    public interface AdapterClickListener {
        void onClick(View v);

        void onAdapterClickListener(View view, int position) throws ParseException;
    }
}

// Ciara Scott S1709819
