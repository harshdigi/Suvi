package com.kalvifunlearning.suvi.Adapters.Explore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalvifunlearning.suvi.Models.Explore.ParentItemModel;
import com.kalvifunlearning.suvi.R;

import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItemModel> itemList;
    private Context context;

    public ParentItemAdapter(List<ParentItemModel> itemList,Context context)
    {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.explore_parent_item, viewGroup, false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, int position)
    {

        ParentItemModel parentItem
                = itemList.get(position);

        parentViewHolder.ParentItemTitle.setText(parentItem.getParentItemTitle());

        LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());

        ChildItemAdapter childItemAdapter = new ChildItemAdapter(parentItem.getChildItemList(),context);
        parentViewHolder.ChildRecyclerView.setHasFixedSize(true);
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }


    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView ParentItemTitle;
        private RecyclerView ChildRecyclerView;

        ParentViewHolder(final View itemView)
        {
            super(itemView);

            ParentItemTitle = itemView.findViewById(
                            R.id.parent_item_title);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }
    }
}
