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

public class ArmorItemsAdapter extends RecyclerView.Adapter<ArmorItemsAdapter.ViewHolder> {

    private Context context;
    private List<ArmorItems> armorItems;
    private EquipItemClickListener<ArmorItems> equipItemClickListener;

    public ArmorItemsAdapter(Context ctx, List<ArmorItems> armorItemsList, EquipItemClickListener<ArmorItems> equipItemClickListener){
        this.context = ctx;
        this.armorItems =armorItemsList;
        this.equipItemClickListener = equipItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.marketplace_item, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ArmorItemsAdapter.ViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();

        holder.itemEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipItemClickListener.equipItemClick(armorItems.get(pos),pos);
            }
        });

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
        Button itemEquip;


        public ViewHolder(View v) {
            super(v);
            itemName = v.findViewById(R.id.inventory_items_name_tv);
            itemDmgBonus = v.findViewById(R.id.inventory_damage_bonus_tv);
            itemDefBonus = v.findViewById(R.id.defence_bonus_tv);
            itemEquip = v.findViewById(R.id.equip_item_btn);
            itemIcon = v.findViewById(R.id.inventory_icon_items);
        }
    }
    public interface EquipItemClickListener<T> {
        void equipItemClick(T v, int position);
    }
}
