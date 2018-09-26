package com.example.wasee.practice1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class signup extends AppCompatActivity {

    EditText Email, Password, RePass, name, mobile;
    Button SignUpbtn;

    RadioButton btn;
    DatePicker dob;
    RadioGroup grp;

    ProgressDialog pd;
    FirebaseAuth fa;
    DatabaseReference dr;

    String email, password, repass, nameF, mobileF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dob=findViewById(R.id.dob);
        name = findViewById(R.id.SignupName);
        grp=findViewById(R.id.grp);
        mobile = findViewById(R.id.signupPhoneNumber);
        SignUpbtn = findViewById(R.id.SignUpButton);
        Email = findViewById(R.id.SignupEmail);
        Password = findViewById(R.id.signupPass);
        RePass = findViewById(R.id.pass);
        pd = new ProgressDialog(this);

        fa=FirebaseAuth.getInstance();
        dr= FirebaseDatabase.getInstance().getReference();

        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = Password.getText().toString().trim();
                repass = RePass.getText().toString().trim();

                if (password.compareTo(repass)!=0) {
                    Toast.makeText(getApplicationContext(), "Passwords doesn't Match", Toast.LENGTH_SHORT).show();
                } else {
                    createAccount();
                }

            }
        });

    }

    void createAccount() {
        email = Email.getText().toString().trim();
        nameF = name.getText().toString();
        mobileF = mobile.getText().toString();
        password = Password.getText().toString().trim();
        repass = RePass.getText().toString().trim();

        int year=dob.getYear();
        int month=dob.getMonth()+1;
        int day=dob.getDayOfMonth();

        // Date date=new Date(year,month,day);
        //  SimpleDateFormat simple=new SimpleDateFormat("dd-mm-yyyy");

        // final String ndate=simple.format(date);
        final String mdate=new StringBuilder().append(year).append("-")
                .append(month).append("-")
                .append(day).append("").toString();




        if (!email.isEmpty() && !password.isEmpty()) {
            pd.setMessage("please wait");
            pd.show();
            fa.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                // Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = fa.getCurrentUser();
                                String userId = user.getUid();
                                int id=grp.getCheckedRadioButtonId();
                                btn=findViewById(id);
                                String gender=btn.getText().toString();

                                userinfo ui = new userinfo(nameF, mobileF, email, password,gender,mdate);
                                dr.child(userId).setValue(ui);
                                //Toast.makeText(MainActivity.this,"successful",Toast.LENGTH_SHORT).show();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                // updateUI(null);
                            }
                            pd.dismiss();

                            // ...
                        }
                    });
        }
    }
}

