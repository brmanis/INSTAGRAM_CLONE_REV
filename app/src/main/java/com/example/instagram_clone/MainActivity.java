package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName,edtPspeed,edtPpower,edtkSpeed,edtkPower;
    private TextView txtGetData;
    private Button getAllData, btnTransition;
    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName=findViewById(R.id.edtName);
        edtPspeed=findViewById(R.id.edtPunchSpeed);
        edtPpower=findViewById(R.id.edtPunchPower);
        edtkSpeed=findViewById(R.id.edtKickSpeed);
        edtkPower=findViewById(R.id.edtKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        getAllData = findViewById(R.id.btnGetAllData);
        btnTransition=findViewById(R.id.btnNextAcvitiy);

        txtGetData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kickboxer");
                parseQuery.getInBackground("FLaxQJyTZ1", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object !=null && e == null){
                             txtGetData.setText(object.get("name") + " - " + "Punch Power " +object.get("punchPower"));
                        }
                    }
                });
            }
        });

        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allKickBoxers="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");
                //queryAll.whereGreaterThan("punchPower",1000);
                queryAll.whereGreaterThanOrEqualTo("punchPower",3000);
                //queryAll.setLimit(1);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size() >0){
                                for(ParseObject kickBoxer:objects){
                                    allKickBoxers=allKickBoxers +kickBoxer.get("name") + "\n";

                                }
                                FancyToast.makeText(MainActivity.this,allKickBoxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                            }else{
                                FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                            }
                        }
                    }
                });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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