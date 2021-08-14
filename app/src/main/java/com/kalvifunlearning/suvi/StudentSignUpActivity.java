package com.kalvifunlearning.suvi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kalvifunlearning.suvi.databinding.ActivityStudentSignUpBinding;

import java.util.regex.Pattern;

public class StudentSignUpActivity extends AppCompatActivity {

    ActivityStudentSignUpBinding binding;
    //Firebase Variable
    private  FirebaseDatabase rootNode;
    private DatabaseReference rootRefrence;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatAccount(v);
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSignUpActivity.this, LoginActivity.class));
            }
        });
        //Firebase Hooks
       rootNode = FirebaseDatabase.getInstance();
       rootRefrence = rootNode.getReference("Users");
        mAuth = FirebaseAuth.getInstance();

    }
    private Boolean validateName() {
        String val = binding.yourName.getText().toString();
        if (val.isEmpty()) {
            binding.yourName.setError("Field cannot be empty");
            return false;
        } else {
            binding.yourName.setError(null);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = binding.yourEmail.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if(val.isEmpty()){
            binding.yourEmail.setError("Field cannot be empty");
            return false;
        }
        if(!pattern.matcher(val).matches()){
            binding.yourEmail.setError("Enter Valid Email");
            return false;
        }
        binding.yourEmail.setError(null);
        return true;

    }
    private Boolean validateMobile(){
        String val = binding.yourPhone.getText().toString();
        if(val.isEmpty()){
            binding.yourPhone.setError("Field cannot be empty");
            return false;
        }
        if(val.length()!=10){
            binding.yourPhone.setError("Enter valid 10 digit Mobile Number");
            return false;
        }
        binding.yourPhone.setError(null);
        return true;

    }
    private Boolean validatePassword(){
        String val = binding.yourPassword.getText().toString();
        String cpass = binding.yourConfirmPassword.getText().toString();
        String passPatter = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(val.isEmpty()){
            binding.yourPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passPatter)) {
            binding.yourPassword.setError("Password too weak, Password must be 4 characters.\nPassword must not contain whitespace.\nPassword must contain at least 1 special character. ");
            return false;
        }
        else if(!val.equals(cpass)){
            binding.yourPassword.setError("Password and Confirm Password must match");
            return false;
        }
        else{
            binding.yourPassword.setError(null);
            return true;
        }
    }
    private Boolean validateCity() {
        String val = binding.yourCity.getText().toString();
        if (val.isEmpty()) {
            binding.yourCity.setError("Field cannot be empty");
            return false;
        } else {
            binding.yourCity.setError(null);
            return true;
        }
    }

    private void creatAccount(View view) {
        if(!validateName() | !validateEmail()  | !validateMobile() | !validatePassword() | !validateCity()) {
            return;
        }
        else{
            final String name = binding.yourName.getText().toString();
            final String email = binding.yourEmail.getText().toString();
            final String mobile = binding.yourPhone.getText().toString();
            final String password = binding.yourPassword.getText().toString();
            final String city =binding.yourCity.getText().toString();
            final String board = binding.yourBoard.getSelectedItem().toString();
            final String standard = binding.yourClass.getSelectedItem().toString();
            final String language = binding.yourLanguage.getSelectedItem().toString();
            final String accountType = "Student";
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(StudentSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Created", "createUserWithEmail:success");
                                final FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification()
                                        .addOnCompleteListener(StudentSignUpActivity.this, new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                // Re-enable button
                                                if (task.isSuccessful()) {
                                                    StudentModel studentModel = new StudentModel(name,email,mobile,city,board,standard,language,accountType);
                                                    rootRefrence.child(user.getUid()).setValue(studentModel);
                                                    Toast.makeText(StudentSignUpActivity.this,
                                                            "Verification email sent to " + user.getEmail(),
                                                            Toast.LENGTH_SHORT).show();

                                                    FirebaseAuth.getInstance().signOut();
                                                } else {
                                                    Log.e("Email", "sendEmailVerification", task.getException());
                                                    Toast.makeText(StudentSignUpActivity.this,
                                                            "Failed to send verification email.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Fail", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(StudentSignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }
    }
}