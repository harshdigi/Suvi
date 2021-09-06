package com.kalvifunlearning.suvi.Adapters.Add;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kalvifunlearning.suvi.Activities.AddVideoActivity;
import com.kalvifunlearning.suvi.Adapters.Explore.ChildItemAdapter;
import com.kalvifunlearning.suvi.Models.StudentModel;
import com.kalvifunlearning.suvi.R;

import java.util.ArrayList;
import java.util.List;

public class AddStudentAdapter extends RecyclerView.Adapter<AddStudentAdapter.AddStudentViewHolder> {

    private List<StudentModel> studentModels;
    private ArrayAdapter<String> adapterSection;
    private DatabaseReference dbReference;
    private Context context;

    private String section;
    ArrayList<String> sectionList = new ArrayList<>();
    public AddStudentAdapter(List<StudentModel> studentModels, Context context) {
        this.studentModels = studentModels;
        this.context = context;
    }

    @NonNull
    @Override
    public AddStudentAdapter.AddStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_student_item, parent, false);

        return new AddStudentAdapter.AddStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddStudentAdapter.AddStudentViewHolder holder, int position) {

        StudentModel studentItem = studentModels.get(position);
        holder.studentName.setText(studentItem.getName());
        holder.studentBoard.setText(studentItem.getBoard());
        holder.studentLanguage.setText(studentItem.getLanguage());
        holder.studentClass.setText(studentItem.getStandard());
        adapterSection= new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item, sectionList);
        holder.classSpinner.setAdapter(adapterSection);
        getSection();

        holder.maincard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.studentSelect.getVisibility()==View.GONE)
                {holder.studentSelect.setVisibility(View.VISIBLE);
                holder.submit.setVisibility(View.VISIBLE);}
                else{
                    holder.studentSelect.setVisibility(View.GONE);
                    holder.submit.setVisibility(View.GONE);
                }
            }
        });
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section = holder.classSpinner.getSelectedItem().toString();
                dbReference = FirebaseDatabase.getInstance().getReference("New/Student").child(studentItem.getUid());
                dbReference.removeValue();
                dbReference =FirebaseDatabase.getInstance().getReference("Users").child(studentItem.getUid());
                dbReference.child("section").setValue(section);
                removeAt(position);

            }
        });
        if(studentModels.size()==0){

        }

        if(studentModels.isEmpty()){
            holder.emptyText.setVisibility(View.VISIBLE);
        }
    }

    private void getSection(){
        dbReference = FirebaseDatabase.getInstance().getReference("Categories").child("Section");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item: snapshot.getChildren()){
                    sectionList.add(item.getValue().toString());
                }
                adapterSection.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModels.size();
    }
    public void removeAt(int position) {
        studentModels.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, studentModels.size());
    }
    class AddStudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentName,studentBoard,studentClass,studentLanguage;
        ConstraintLayout maincard;
        LinearLayout studentSelect;
        Spinner classSpinner;
        MaterialButton submit;
        TextView emptyText;
        AddStudentViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.nameValue);
            studentBoard = itemView.findViewById(R.id.boardValue);
            studentClass = itemView.findViewById(R.id.classValue);
            studentLanguage = itemView.findViewById(R.id.langaugeValue);
            maincard= itemView.findViewById(R.id.addStudentmain);
            studentSelect = itemView.findViewById(R.id.studentSelect);
            classSpinner = itemView.findViewById(R.id.classSpinner);
            submit = itemView.findViewById(R.id.submit_btn);
            emptyText = itemView.findViewById(R.id.studentEmptyText);
        }

    }
}
