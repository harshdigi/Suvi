package com.kalvifunlearning.suvi.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kalvifunlearning.suvi.Fragments.TeacherAddFragment;
import com.kalvifunlearning.suvi.Models.VideoInfoModel;
import com.kalvifunlearning.suvi.R;
import com.kalvifunlearning.suvi.databinding.ActivityAddVideoBinding;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.jar.Attributes;

public class AddVideoActivity extends AppCompatActivity {

    ActivityAddVideoBinding binding;
    DatabaseReference dbReference;
    FirebaseDatabase database;
    ValueEventListener listener;


    //Adapters
    ArrayAdapter<String> adapterBoards;
    ArrayAdapter<String> adapterLanguage;
    ArrayAdapter<String> adapterSubject;
    ArrayAdapter<String> adapterClass;
    ArrayAdapter<String> adapterCategory;
    ArrayAdapter<String> adapterSection;


    //Spinners
    ArrayList<String> boardsList;
    ArrayList<String> languageList;
    ArrayList<String> subjectList;
    ArrayList<String> classList;
    ArrayList<String> categoryList;
    ArrayList<String> sectionList;

//    private EditText videoNameEd, videoLinkEd, videoDescriptionEd;
//    private Spinner boardSpinnerEd, languageSpinnerEd, subjectSpinnerEd, classSpinnerEd, categorySpinnerEd, sectionSpinnerEd;
//    private RadioButton yesRadioButtonEd, noRadioButtonEd;
//    private Button addVideoButtonEd;

    VideoInfoModel videoInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();

        // Populating all the Spinners from Firebase
        boardsList = new ArrayList<>();
        languageList = new ArrayList<>();
        subjectList = new ArrayList<>();
        classList = new ArrayList<>();
        categoryList = new ArrayList<>();
        sectionList = new ArrayList<>();

        adapterBoards = new ArrayAdapter<String>(AddVideoActivity.this, android.R.layout.simple_spinner_dropdown_item, boardsList);
        adapterLanguage = new ArrayAdapter<String>(AddVideoActivity.this, android.R.layout.simple_spinner_dropdown_item, languageList);
        adapterSubject = new ArrayAdapter<String>(AddVideoActivity.this, android.R.layout.simple_spinner_dropdown_item, subjectList);
        adapterClass = new ArrayAdapter<String>(AddVideoActivity.this, android.R.layout.simple_spinner_dropdown_item, classList);
        adapterCategory = new ArrayAdapter<String>(AddVideoActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        adapterSection = new ArrayAdapter<String>(AddVideoActivity.this, android.R.layout.simple_spinner_dropdown_item, sectionList);

        binding.boardSpinner.setAdapter(adapterBoards);
        binding.languageSpinner.setAdapter(adapterLanguage);
        binding.subjectSpinner.setAdapter(adapterSubject);
        binding.classSpinner.setAdapter(adapterClass);
        binding.categorySpinner.setAdapter(adapterCategory);
        binding.sectionSpinner.setAdapter(adapterSection);

        getBoards();
        getLanguages();
        getSubject();
        getClasses();
        getCategory();
        getSection();
        // Populating Done

        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference("Videos");

        videoInfoModel = new VideoInfoModel();

        binding.addVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoName = binding.videoName.getText().toString();
                String videoLink = binding.videoLink.getText().toString();
                String videoDescription = binding.videoDescription.getText().toString();
                String boardName = binding.boardSpinner.getSelectedItem().toString();
                String videoLanguage = binding.languageSpinner.getSelectedItem().toString();
                String videoSubject = binding.subjectSpinner.getSelectedItem().toString();
                String videoClass = binding.classSpinner.getSelectedItem().toString();
                String videoCategory = binding.categorySpinner.getSelectedItem().toString();
                String videoSection = binding.sectionSpinner.getSelectedItem().toString();
                String openForAll = binding.yesRadioButton.isChecked() ? "True" : binding.noRadioButton.isChecked() ? "False" : "";

                if(TextUtils.isEmpty(videoName) || TextUtils.isEmpty(videoLink) || TextUtils.isEmpty(videoDescription) || TextUtils.isEmpty(boardName)
                        || TextUtils.isEmpty(videoLanguage) || TextUtils.isEmpty(videoSubject)
                        || TextUtils.isEmpty(videoClass) || TextUtils.isEmpty(videoCategory)
                        || TextUtils.isEmpty(videoSection) || TextUtils.isEmpty(openForAll)){
                    Toast.makeText(AddVideoActivity.this, "Please complete all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    addVideoDataToFirebase(videoName, videoLink, videoDescription, boardName, videoLanguage, videoSubject, videoClass, videoCategory, videoSection, openForAll);
                }
            }
        });


        binding.yesRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.noRadioButton.setChecked(false);
            }
        });

        binding.noRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.yesRadioButton.setChecked(false);
            }
        });
    }

    public void addVideoDataToFirebase(String videoName, String videoLink, String videoDescription, String boardName, String videoLanguage,
                                       String videoSubject, String videoClass, String videoCategory, String videoSection, String openForAll){
        videoInfoModel.setVideoName(videoName);
        videoInfoModel.setVideoLink(videoLink);
        videoInfoModel.setDescription(videoDescription);
        videoInfoModel.setBoard(boardName);
        videoInfoModel.setLanguage(videoLanguage);
        videoInfoModel.setSubject(videoSubject);
        videoInfoModel.setStandard(videoClass);
        videoInfoModel.setCategory(videoCategory);
        videoInfoModel.setSection(videoSection);
        videoInfoModel.setOpen(openForAll);


        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbReference.child(videoName).setValue(videoInfoModel);
                Toast.makeText(AddVideoActivity.this, "Video Added", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddVideoActivity.this, "Failed to Add Data, Try Again" + error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getBoards(){

        dbReference = database.getReference("Categories").child("Boards");
        listener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item: snapshot.getChildren()){
                    boardsList.add(item.getValue().toString());
                }
                adapterBoards.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getLanguages(){
        dbReference = database.getReference("Categories").child("Language");
        listener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item: snapshot.getChildren()){
                    languageList.add(item.getValue().toString());
                }
                adapterLanguage.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getSubject(){
        dbReference = database.getReference("Categories").child("Subjects");
        listener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item: snapshot.getChildren()){
                    subjectList.add(item.getValue().toString());
                }
                adapterSubject.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getClasses(){
        dbReference = database.getReference("Categories").child("Standard");
        listener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item: snapshot.getChildren()){
                    classList.add(item.getValue().toString());
                }
                adapterClass.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getCategory(){
        dbReference = database.getReference("Categories").child("Videos");
        listener = dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item: snapshot.getChildren()){
                    categoryList.add(item.getValue().toString());
                }
                adapterCategory.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getSection(){
        dbReference = database.getReference("Categories").child("Section");
        listener = dbReference.addValueEventListener(new ValueEventListener() {
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

}