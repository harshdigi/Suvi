package com.kalvifunlearning.suvi.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.kalvifunlearning.suvi.Adapters.Explore.ChildItemAdapter;
import com.kalvifunlearning.suvi.R;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.CLassViewHolder> {
    private Context context;
    public ClassesAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override

    public CLassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_card_item, parent, false);

        return new CLassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesAdapter.CLassViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CLassViewHolder extends RecyclerView.ViewHolder {
        TextView name, standard, board, language,subject, totalStudents;
        MaterialButton edit;
        public CLassViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameValue);
            standard = itemView.findViewById(R.id.standardValue);
            board = itemView.findViewById(R.id.boardValue);
            language = itemView.findViewById(R.id.langaugeValue);
            subject = itemView.findViewById(R.id.subjectValue);
            totalStudents = itemView.findViewById(R.id.totalStudentValue);
            edit = itemView.findViewById(R.id.edit_button);
        }
    }
}
