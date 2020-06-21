package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {
    private EditText edtLoginUname,edtLoginPWord;
    private Button btnSignUp2,btnLogin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");
        edtLoginUname=findViewById(R.id.edtLoginUname);
        edtLoginPWord=findViewById(R.id.edtLoginPWord);

        btnSignUp2=findViewById(R.id.btnSignUpIn);
        btnLogin2=findViewById(R.id.btnLoginIn);

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  ParseUser.logInInBackground(edtLoginUname.getText().toString(),
                        edtLoginPWord.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null && e==null){
                            FancyToast.makeText(LoginActivity.this,user.get("username")+" is logged in successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            transitionToSocialMediaActivity();
                        }else{
                            FancyToast.makeText(LoginActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();


                        }
                    }
                });
            }
        });
        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpLoginActivity.class);
                startActivity(intent);

            }
        });
    }
    public void rootLoginTapped(View view){

        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }catch(Exception e){
            e.printStackTrace();
        }




    }

    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}