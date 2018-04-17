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

public class InventoryItemsAdapter extends RecyclerView.Adapter<InventoryItemsAdapter.ViewHolder> {

    private Context context;
    private List<ArmorItems> armorItems;

    public InventoryItemsAdapter(Context ctx, List<ArmorItems> armorItemsList){
        this.context = ctx;
        this.armorItems =armorItemsList;
    }

    @Override
    public InventoryItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, null);
        InventoryItemsAdapter.ViewHolder holder = new InventoryItemsAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(InventoryItemsAdapter.ViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();

        holder.itemName.setText(armorItems.get(position).getItemName());
        holder.itemDmgBonus.setText(String.valueOf(armorItems.get(position).getAttackBonus()));
        holder.itemDefBonus.setText(String.valueOf(armorItems.get(position).getDefenceBonus()));
        Picasso.with(context).load(armorItems.get(position).getIcon()).into(holder.itemIcon);


    }

    @Override
    public int getItemCount() {
        return armorItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemIcon;
        TextView itemName;
        TextView itemDmgBonus;
        TextView itemDefBonus;


        public ViewHolder(View v) {
            super(v);
            itemName = v.findViewById(R.id.inventory_items_name_tv);
            itemDmgBonus = v.findViewById(R.id.inventory_damage_bonus_tv);
            itemDefBonus = v.findViewById(R.id.inventory_defence_bonus_tv);
            itemIcon = v.findViewById(R.id.inventory_icon_items);
        }
    }
}
