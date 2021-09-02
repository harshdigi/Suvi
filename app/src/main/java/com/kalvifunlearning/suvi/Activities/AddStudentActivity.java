package com.kalvifunlearning.suvi.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kalvifunlearning.suvi.Adapters.Add.AddStudentAdapter;
import com.kalvifunlearning.suvi.Adapters.Explore.ParentItemAdapter;
import com.kalvifunlearning.suvi.Models.StudentModel;
import com.kalvifunlearning.suvi.R;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("New/Student");
    private List<StudentModel> studentModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        recyclerView = findViewById(R.id.addStudentRecycler);
        getStudentList();

    }

    private void getStudentList() {
        studentModelList = new ArrayList<>();
        studentRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
               if(task.isSuccessful()){
                   for(DataSnapshot snapshot : task.getResult().getChildren()){
                       String name = snapshot.child("name").getValue(String.class);
                       String email = snapshot.child("email").getValue(String.class);
                       String mobile = snapshot.child("mobile").getValue(String.class);
                       String city =snapshot.child("city").getValue(String.class);
                       String board = snapshot.child("board").getValue(String.class);
                       String standard = snapshot.child("standard").getValue(String.class);
                       String language = snapshot.child("language").getValue(String.class);
                       Uri imageUrl = snapshot.child("image_url").getValue(Uri.class);
                       StudentModel studentItem = new StudentModel(name,email,mobile,city,board,standard,language,"student",imageUrl,snapshot.getKey());
                       studentModelList.add(studentItem);;
                   }
                   putData(studentModelList);
               }
            }
        });
        if(studentModelList.isEmpty()){

        }
    }

    private void putData(List<StudentModel> studentModelList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(AddStudentActivity.this);
        AddStudentAdapter addStudentAdapter = new AddStudentAdapter(studentModelList,AddStudentActivity.this);
        recyclerView.setAdapter(addStudentAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}