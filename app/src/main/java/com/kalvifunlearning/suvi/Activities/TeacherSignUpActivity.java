package com.kalvifunlearning.suvi.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kalvifunlearning.suvi.Models.TeacherModel;
import com.kalvifunlearning.suvi.databinding.ActivityTeacherSignUpBinding;

import java.util.regex.Pattern;

public class TeacherSignUpActivity extends AppCompatActivity {

    ActivityTeacherSignUpBinding binding;
    //Firebase Variable
    private FirebaseDatabase rootNode;
    private DatabaseReference rootRefrence;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Firebase Hooks
        rootNode = FirebaseDatabase.getInstance();
        rootRefrence = rootNode.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSignUpActivity.this, LoginActivity.class));
            }
        });
        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
            }
        });
        

    }
    private Boolean validateName() {
        String val = binding.userName.getText().toString();
        if (val.isEmpty()) {
            binding.userName.setError("Field cannot be empty");
            return false;
        } else {
            binding.userName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = binding.userEmail.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if(val.isEmpty()){
            binding.userEmail.setError("Field cannot be empty");
            return false;
        }
        if(!pattern.matcher(val).matches()){
            binding.userEmail.setError("Enter Valid Email");
            return false;
        }
        binding.userEmail.setError(null);
        return true;

    }
    private Boolean validateMobile(){
        String val = binding.userMobile.getText().toString();
        if(val.isEmpty()){
            binding.userMobile.setError("Field cannot be empty");
            return false;
        }
        if(val.length()!=10){
            binding.userMobile.setError("Enter valid 10 digit Mobile Number");
            return false;
        }
        binding.userMobile.setError(null);
        return true;

    }
    private Boolean validatePassword(){
        String val = binding.userPassword.getText().toString();
        String cpass = binding.userConfirmPassword.getText().toString();
        String passPatter = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(val.isEmpty()){
            binding.userPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passPatter)) {
            binding.userPassword.setError("Password too weak, Password must be 4 characters.\nPassword must not contain whitespace.\nPassword must contain at least 1 special character. ");
            return false;
        }
        else if(!val.equals(cpass)){
            binding.userPassword.setError("Password and Confirm Password must match");
            return false;
        }
        else{
            binding.userPassword.setError(null);
            return true;
        }
    }

    private void registerUser(View v) {
        if(!validateName() | !validateEmail()  | !validateMobile() | !validatePassword()) {
            return;
        }
        else{
        String name =binding.userName.getText().toString();
        String email = binding.userEmail.getText().toString();
        String mobile = binding.userMobile.getText().toString();
        String password = binding.userMobile.getText().toString();
        String accountType ="Teacher";
        String  isVerifiedTeacher = "false";
        mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(TeacherSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Created", "createUserWithEmail:success");
                                final FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification()
                                        .addOnCompleteListener(TeacherSignUpActivity.this, new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                // Re-enable button
                                                if (task.isSuccessful()) {
                                                    TeacherModel teacherModel = new TeacherModel(name,email,mobile,accountType,isVerifiedTeacher);
                                                    rootRefrence.child(user.getUid()).setValue(teacherModel);
                                                    Toast.makeText(TeacherSignUpActivity.this,
                                                            "Verification email sent to " + user.getEmail(),
                                                            Toast.LENGTH_SHORT).show();


                                                    FirebaseAuth.getInstance().signOut();

                                                    startActivity(new Intent(TeacherSignUpActivity.this, LoginActivity.class));
                                                    finish();
                                                } else {
                                                    Log.e("Email", "sendEmailVerification", task.getException());
                                                    Toast.makeText(TeacherSignUpActivity.this,
                                                            "Failed to send verification email.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Fail", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(TeacherSignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });


        }
    }
}