package com.example.wasee.practice1;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button signIn,showInfo;
    TextView signUp;

    ProgressDialog pd;
    FirebaseAuth fa;
    DatabaseReference dr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        signIn=findViewById(R.id.SignIn);
        signUp=findViewById(R.id.SignUp);
        showInfo=findViewById(R.id.ShowInfo);

        pd=new ProgressDialog(this);
        fa=FirebaseAuth.getInstance();
        dr= FirebaseDatabase.getInstance().getReference();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b=new Intent(getApplicationContext(),signup.class);
                startActivity(b);

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SignIn();
                //startActivity(c);

            }
        });

        showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final  Intent ec=new Intent(getApplicationContext(),retrieve.class);
                        startActivity(ec);
                    }
                }).setNegativeButton("No",null);

                AlertDialog alert=builder.create();
                alert.show();

            }
        });
    }

    void SignIn()
    {
        String  Email=email.getText().toString().trim();
        String  Password=password.getText().toString().trim();
        if(!Email.isEmpty()&&!Password.isEmpty()) {
            pd.setMessage("Please Wait!!");
            pd.show();

            fa.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Successful",
                                Toast.LENGTH_SHORT).show();


                    } else {

                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }
        else
        {

        }






    }
}

