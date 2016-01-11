package com.bozidar.microdroid.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bozidar.microdroid.recyclerview.holder.MicroViewHolder;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bozidar on 20.04.15..
 */
public class MicroRecyclerAdapter extends RecyclerView.Adapter<MicroViewHolder> {

    private onMicroItemCLickListener listener;

    private ArrayList<MicroItem> items;

    private ArrayList<MicroItem> itemsCopy;

    public int colorHeader;

    public int colorDescription;

    private HashMap<String,Integer> itemColors;

    public MicroRecyclerAdapter() {
        listener = null;
        this.items = new ArrayList<>();
        itemsCopy = new ArrayList<>();
        itemColors = new HashMap<>();
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the giver type to represent an item
     *
     * @param parent   Layout root of micro_drawer_item
     * @param viewType The View type of the new View
     * @return A new ViewHolder that holds a View of the give view type
     */
    @Override
    public MicroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("pokemon", viewType + "");
        View v = LayoutInflater.from(parent.getContext()).inflate(items.get(viewType).getLayoutResource(), parent, false);
        return new MicroViewHolder(v, listener, items.get(viewType));
    }

    /**
     * Called by RecyclerView to display the data at the specified position
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at the give position in the data set
     * @param position The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(MicroViewHolder holder, int position) {
        holder.getItem().displayItem(holder.getView(), position, itemColors);
    }

    public void setOnMicroCLickListener(onMicroItemCLickListener listener){
        this.listener = listener;
    }

    /**
     * Returns the total number of items in the data set hold by the adapter
     *
     * @return The total number of items in this adapter
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface onMicroItemCLickListener{
        public void microItemClicked(View view, MicroItem item, int index);
    }

    public List<MicroItem> getItems(){
        return items;
    }

    public void setItems(ArrayList<MicroItem> items){
        this.items = items;
        this.notifyDataSetChanged();
    }

    public void addItem(MicroItem item){
        this.items.add(item);
        this.notifyDataSetChanged();
    }

    public void removeItems(){
        this.items.clear();
        this.notifyDataSetChanged();
    }

    public void removeItem(int index){
        this.items.remove(index);
        notifyItemRemoved(index);
    }


    public void addItemColor(String itemAttributeName, int color){
        itemColors.put(itemAttributeName, color);
    }

    public void search(String keyword){
        this.itemsCopy = items;
        removeItems();


    }
}

//DataModel    -> represents single item in list
//Adapter      -> hold all items and show them using ViewHolder
//ContentView  ->
//ViewHolder   -> xml layout for single item inflated into java code
//RecyclerView -> use ViewHolder to display every item

//LayoutManager -> allow me to customize how the items are placed (bellow, near...)
//ItemAnimator  -> used for animating recycler view
//ItemDecorator -> set RecyclerView in different section