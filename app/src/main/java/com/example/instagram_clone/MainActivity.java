package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {


    private EditText edtName,edtPspeed,edtPpower,edtkSpeed,edtkPower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName=findViewById(R.id.edtName);
        edtPspeed=findViewById(R.id.edtPunchSpeed);
        edtPpower=findViewById(R.id.edtPunchPower);
        edtkSpeed=findViewById(R.id.edtKickSpeed);
        edtkPower=findViewById(R.id.edtKickPower);

    }



    public void helloWorldPressed(View view){
        try{
            final ParseObject kb = new ParseObject("Kickboxer");
            kb.put("name",edtName.getText().toString());
            kb.put("punchSpeed",Integer.parseInt(edtPspeed.getText().toString()));
            kb.put("punchPower",Integer.parseInt(edtPpower.getText().toString()));
            kb.put("kickSpeed",Integer.parseInt(edtkSpeed.getText().toString()));
            kb.put("kickPower",Integer.parseInt(edtkPower.getText().toString()));
            kb.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        //Toast.makeText(MainActivity.this, kb.get("name") +" is saved successfully",Toast.LENGTH_LONG).show();
                        FancyToast.makeText(MainActivity.this,kb.get("name") +" is saved successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                    }
        }
            });
        }catch(Exception e){
            FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        }
    }


       /* ParseObject boxer = new ParseObject("Boxer");
        boxer.put("punch_speed",200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "Boxer Object is saved successfully",Toast.LENGTH_LONG).show();
                }
            }
        });*/

}