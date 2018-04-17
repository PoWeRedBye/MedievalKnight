package com.example.maxim_ozarovskiy.medievalknight.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.adapters.ArmorItemsAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.fragment.GlovesFragmentContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.presenters.fragment.GlovesMarketplaceFragmentPresenter;

import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.GLOVES;

public class GlovesMarketplaceItemsFragment extends Fragment implements GlovesFragmentContract.View, ArmorItemsAdapter.EquipItemClickListener<ArmorItems> {

    private GlovesMarketplaceFragmentPresenter presenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ArmorItemsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.marketpace_fragment, container, false);
        initUI(v);
        presenter = new GlovesMarketplaceFragmentPresenter(getActivity(),this);
        presenter.callItemsFromArmory(GLOVES);
        return v;

    }

    private void setNewRecyclerAdapter(List<ArmorItems> newList){
        adapter = new ArmorItemsAdapter(getActivity(), newList, this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void equipItemClick(ArmorItems v, int position) {
        presenter.equipNewItem(v);
    }

    private void initUI(View view){
        recyclerView = view.findViewById(R.id.marketpalce_recycler);
    }

    @Override
    public void callbackItems(List<ArmorItems> armorItemsList) {
        setNewRecyclerAdapter(armorItemsList);
    }
}
