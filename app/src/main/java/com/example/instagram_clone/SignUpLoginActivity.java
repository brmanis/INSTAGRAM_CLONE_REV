package com.example.instagram_clone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {
    private EditText edtUname,edtpWord,edtLoginUname,edtLoginPWord;
    private Button btnSignUp1,btnLogin1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_login_activity);
        edtUname=findViewById(R.id.edtUsername);
        edtpWord=findViewById(R.id.edtPassword);
        edtLoginUname=findViewById(R.id.edtLoginUsername);
        edtLoginPWord=findViewById(R.id.edtLoginPassword);
        btnSignUp1=findViewById(R.id.btnSignUp);
        btnLogin1=findViewById(R.id.btnLogin);

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtLoginUname.getText().toString(),
                        edtLoginPWord.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null && e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username")+" is logged in successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();


                        }
                    }
                });

            }
        });
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUname.getText().toString());
                appUser.setPassword(edtpWord.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is signed up successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }else{
                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });







            }
        });
    }
}
