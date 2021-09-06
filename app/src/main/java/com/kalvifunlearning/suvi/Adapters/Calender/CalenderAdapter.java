package com.kalvifunlearning.suvi.Adapters.Calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalvifunlearning.suvi.Classes.ClassesAdapter;
import com.kalvifunlearning.suvi.R;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder> {
    private Context context;
    public CalenderAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_class_item, parent, false);

        return new CalenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderAdapter.CalenderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CalenderViewHolder extends RecyclerView.ViewHolder {
        public CalenderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
