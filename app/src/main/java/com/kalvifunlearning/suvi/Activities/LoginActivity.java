package com.kalvifunlearning.suvi.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kalvifunlearning.suvi.databinding.ActivityLoginBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    private SharedPreferences mSahredPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
       binding.loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               loginUser();
           }
       });
        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetuserpass();
            }
        });
    }
    private Boolean validateEmail() {
        String val = binding.yourEmail.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (val.isEmpty()) {
            binding.yourEmail.setError("Field cannot be empty");
            return false;
        } else if (!pattern.matcher(val).matches()) {
            binding.yourEmail.setError("Enter Valid Email");
            return false;
        } else {
            binding.yourEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = binding.yourPassword.getText().toString();
        if (val.isEmpty()) {
            binding.yourPassword.setError("Field cannot be empty");
            return false;
        } else {
            binding.yourPassword.setError(null);
            return true;
        }
    }

    public void forgetuserpass() {
        if (!validateEmail()) {
            return;
        } else {
            String email = binding.yourEmail.getText().toString();
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,
                                        "Forget password email sent",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("Email", "sendEmailVerification", task.getException());
                                Toast.makeText(LoginActivity.this,
                                        "Failed to send forget email. User does not exist",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    public void loginUser() {
        if (!validateEmail() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String email = binding.yourEmail.getText().toString();
        final String password = binding.yourPassword.getText().toString();
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    List<String> signInMethods = result.getSignInMethods();
                    if(signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD))
                    {
                        // User can sign in with email/password
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SiginIN", "signInWithEmail:success");
                                    final FirebaseUser user = mAuth.getCurrentUser();
                                    if (!user.isEmailVerified()) {
                                        binding.verifyEmail.setVisibility(View.VISIBLE);
                                        user.sendEmailVerification();
                                        mAuth.signOut();
                                    } else {
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                                        Query checkUser = reference.orderByChild("email").equalTo(email);
                                        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                                if (snapshot.exists()) {
                                                    binding.yourEmail.setError(null);
                                                    binding.yourPassword.setError(null);
                                                    String userId = user.getUid();
                                                    mSahredPref = getSharedPreferences("userDetails", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = mSahredPref.edit();
                                                    String accountType = snapshot.child(userId).child("accountType").getValue(String.class);

                                                    editor.putString("accountType",accountType);
                                                    if(accountType.equalsIgnoreCase("Teacher")){
                                                        editor.putString("name",snapshot.child(userId).child("name").getValue(String.class));
                                                        editor.putString("email",snapshot.child(userId).child("email").getValue(String.class));
                                                        editor.putString("mobile",snapshot.child(userId).child("mobile").getValue(String.class));
                                                        editor.putString("isVerifiedTeacher",snapshot.child(userId).child("name").getValue(String.class));
                                                        editor.apply();
                                                       }
                                                    else if (accountType.equalsIgnoreCase("Student")){
                                                        editor.putString("name",snapshot.child(userId).child("name").getValue(String.class));
                                                        editor.putString("email",snapshot.child(userId).child("email").getValue(String.class));
                                                        editor.putString("mobile",snapshot.child(userId).child("mobile").getValue(String.class));
                                                        editor.putString("board",snapshot.child(userId).child("board").getValue(String.class));
                                                        editor.putString("city",snapshot.child(userId).child("city").getValue(String.class));
                                                        editor.putString("language",snapshot.child(userId).child("language").getValue(String.class));
                                                        editor.putString("standard",snapshot.child(userId).child("standard").getValue(String.class));
                                                        editor.apply();
                                                    }
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    binding.yourEmail.setError("No such user exist");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                            }
                                        });
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Fail", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }

                        });

                    }
                } else {
                    Log.e("Failed", "Error getting sign in methods for user", task.getException());
                }
            }
        });

    }
}