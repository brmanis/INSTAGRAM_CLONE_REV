package com.example.instagram_clone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity{
    private EditText edtEmail, edtUname,edtpWord,edtLoginUname,edtLoginPWord;
    private Button btnSignUp1,btnLogin1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_login_activity);
        setTitle("SIGN UP");
        edtEmail=findViewById(R.id.edtEmail);
        edtUname=findViewById(R.id.edtUsername);
        edtpWord=findViewById(R.id.edtPassword);
        edtpWord.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN){
                            onCLick(btnSignUp1);
                }

                return false;
            }
        });

        btnSignUp1=findViewById(R.id.btnSignUpIn);
        btnLogin1=findViewById(R.id.btnLogin);

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(SignUpLoginActivity.this, LoginActivity.class);
               startActivity(intent);

            }
        });
        if(ParseUser.getCurrentUser()!=null){
            transitionToSocialMediaActivity();
        }
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().equals("") ||
                        edtUname.getText().toString().equals("") ||
                        edtpWord.getText().toString().equals("")){
                    FancyToast.makeText(SignUpLoginActivity.this,
                            "Email, Username and password are required to signup",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();

                }else{
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUname.getText().toString());
                    appUser.setPassword(edtpWord.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(SignUpLoginActivity.this);
                    progressDialog.setMessage("Signing Up" +edtUname.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is signed up successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                            }else{
                                FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    public void rootTapped(View view){
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch(Exception e){
            e.printStackTrace();
        }




    }
    private void onCLick(Button btnSignUp1) {
    }

    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(SignUpLoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }


}
