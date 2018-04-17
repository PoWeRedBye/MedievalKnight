package com.example.maxim_ozarovskiy.medievalknight.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterInfoAdapter extends RecyclerView.Adapter<CharacterInfoAdapter.ViewHolder> {

    private Context context;
    private List<ArmorItems> armorItems;

    public CharacterInfoAdapter(Context ctx, List<ArmorItems> armorItemsList){
        this.context = ctx;
        this.armorItems =armorItemsList;
    }

    @Override
    public CharacterInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_info_item, null);
        CharacterInfoAdapter.ViewHolder holder = new CharacterInfoAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CharacterInfoAdapter.ViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        Picasso.with(context).load(armorItems.get(pos).getIcon()).into(holder.itemIcon);
    }

    @Override
    public int getItemCount() {
        return armorItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemIcon;

        public ViewHolder(View v) {
            super(v);
            itemIcon = v.findViewById(R.id.character_info_equipped_item_icon);
        }
    }
}
