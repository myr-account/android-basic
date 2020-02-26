package com.example.flighttime;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.FormatException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText t1Id,t2Id,stpId,changeId,intId,windId;
    int y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1Id = (EditText)findViewById(R.id.t1Id);
        t2Id = (EditText)findViewById(R.id.t2Id);
        stpId = (EditText)findViewById(R.id.stpId);
        changeId = (EditText)findViewById(R.id.changeId);
        intId = (EditText)findViewById(R.id.intId);
        windId = (EditText)findViewById(R.id.windId);
    }

    public void onClick(View v){

            TextView outId = findViewById(R.id.outId);
            TextView outId1 = findViewById(R.id.outId1);
            try{
            double dist = Double.parseDouble(t1Id.getText().toString());
            double speed = Double.parseDouble(t2Id.getText().toString());
            int stops = Integer.parseInt(stpId.getText().toString());
            int change = Integer.parseInt(changeId.getText().toString());
            int wind = Integer.parseInt(windId.getText().toString());
            String inter = intId.getText().toString();

            if (inter.equalsIgnoreCase("yes")) {
                y = 3;
            } else if (inter.equalsIgnoreCase("no")) {
                y = 0;
            }
            if (!(speed>100 && speed<1200)){
                outId.setText("speed is not in range. kindly enter \nbetween 100 m/h and 1200 m/h");
                outId1.setText(" ");
                return;
            }
//            double x = Math.round(dist * 100 / (speed + wind)) / 100.0;
            double x = dist/(speed+wind);
            if (speed+wind < 0) {
                outId1.setText(String.format("\t\tSpeed %.2f isn't positive", speed+wind));
                outId.setText(" ");
                return;
            } else if (speed+wind > 0 && dist>0) {
                outId1.setText(String.format("Distance %.2f is positive and Speed %.2f is positive", dist,speed+wind));
            }
            outId.setText(String.format("Trip time \u231A is %.2f \u231B hours", x + stops + (change * 2) + y));
        }
        catch (NumberFormatException e){
            outId.setText("Make sure that all the fileds are filled");
            outId1.setText(" ");
        }

    }
}
