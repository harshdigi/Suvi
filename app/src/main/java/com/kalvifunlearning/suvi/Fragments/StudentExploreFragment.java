package com.kalvifunlearning.suvi.Fragments;

import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationAttributes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kalvifunlearning.suvi.Adapters.Explore.ParentItemAdapter;
import com.kalvifunlearning.suvi.Models.Explore.ChildItemModel;
import com.kalvifunlearning.suvi.Models.Explore.ParentItemModel;
import com.kalvifunlearning.suvi.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;


public class StudentExploreFragment extends Fragment {
    private MaterialCardView classCard;
    private DatabaseReference categoriesRef = FirebaseDatabase.getInstance().getReference("Categories/Videos");

    private RecyclerView ParentRecyclerViewItem;
    private List<ChildItemModel>  itemListChild = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_student_explore, container, false);
        classCard = root.findViewById(R.id.classCard);
        classCard.setBackgroundDrawable(getResources().getDrawable(R.drawable.student_bg));
        ParentRecyclerViewItem = root.findViewById(R.id.explore_parent_recyclerview);
        getParentList();
        return root;
    }

    private void getParentList() {
        List<ParentItemModel> parentItemList = new ArrayList<>();
       categoriesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DataSnapshot> taskParent) {
               if(!taskParent.isSuccessful()){}
               else{
                for(DataSnapshot dataSnapshotParent: taskParent.getResult().getChildren()){
                    List<ChildItemModel> childItemModelList =new ArrayList<>();
                    DatabaseReference videosRef = FirebaseDatabase.getInstance().getReference("Videos");
                    videosRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> taskChild) {
                             if(!taskChild.isSuccessful()){
                                Log.e("firebase","No data");
                             }
                            else{
                                 for(DataSnapshot dataSnapshotChild: taskChild.getResult().getChildren()){
                                     String  videoName = dataSnapshotChild.child("videoName").getValue(String.class);
                                     String  videoLink = dataSnapshotChild.child("videoLink").getValue(String.class);
                                     String  subject = dataSnapshotChild.child("subject").getValue(String.class);
                                     String  standard = dataSnapshotChild.child("standard").getValue(String.class);
                                     String  section = dataSnapshotChild.child("section").getValue(String.class);
                                     String  language = dataSnapshotChild.child("language").getValue(String.class);
                                     String  description = dataSnapshotChild.child("description").getValue(String.class);
                                     String  category = dataSnapshotChild.child("category").getValue(String.class);
                                     String  board = dataSnapshotChild.child("board").getValue(String.class);
                                     Boolean  open = Boolean.valueOf(dataSnapshotChild.child("open").getValue(String.class));
                                    ChildItemModel item = new ChildItemModel(videoName,videoLink,subject,section,language,description,standard,category,board,open);
                                    if(dataSnapshotChild.child("category").getValue(String.class).equalsIgnoreCase(dataSnapshotParent.getValue(String.class)) && Boolean.valueOf(dataSnapshotChild.child("open").getValue(String.class)))
                                    childItemModelList.add(item);
                                }
                                 if(!childItemModelList.isEmpty()){
                                ParentItemModel parentItemModel = new ParentItemModel(dataSnapshotParent.getValue(String.class),childItemModelList);
                                 parentItemList.add(parentItemModel);}
                                 putDataInParent(parentItemList);
                            }
                        }
                    });

                }

               }
           }
       });
    }

    private void putDataInParent(List<ParentItemModel> itemList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(itemList,getContext());
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
    }
//    public interface MyCallback{
//        void getChildList(List<ChildItemModel> childItemModelList);
//    }


//    public void getChildData(MyCallback myCallback){
//        List<ChildItemModel> childItemModelList =new ArrayList<>();
//        videosRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(!task.isSuccessful()){
//                    Log.e("firebase","No data");
//                }
//                else{
//                    for(DataSnapshot dataSnapshot: task.getResult().getChildren()){
//                        ChildItemModel item = new ChildItemModel(dataSnapshot.child("videoName").getValue(String.class));
//                        childItemModelList.add(item);
//                    }
//                    myCallback.getChildList(childItemModelList);
//                }
//            }
//        });
//    }


}