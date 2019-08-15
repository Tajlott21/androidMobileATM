package com.example.mobilebankingatm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    static DecimalFormat df = new DecimalFormat("####0.00");
    //Allows variable to transfer to another activity, a constant
    public static final String EXTRA_NUMBER = "com.example.mobilebankingatm.EXTRA_NUMBER";
    private TextView userBal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Creating TextView object and linking it to the correspoding XML object on the front end.
        Calling the onClickListener method on the newly made object.
        */
        TextView deposit = (TextView) findViewById(R.id.deposit);
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        TextView withdraw = (TextView) findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });




        }

    public void openActivity2() {
        /*   Creating TextView object and linking it to the correspoding XML object on the front end. Which contains user input
        * Then parsing the integer out of the user input and assigning it to the variable newBal
        * Called parseInt() method, passed userBal with the getText() method then converting the text to a string with the toString() method */
        userBal = (TextView) findViewById(R.id.userBal);
        double newBal = Integer.parseInt(userBal.getText().toString());

        /* Creating an intent called intent1 in order to allow the button to carry the user over to
        * another activity
        * Arguments passed are a self reference to the current activity: MainActivity.this, and the
        * target activity: Activity2.class
        *
        * We then use the newly made intent and refer to the putExtra() method as a vehicle to carry
        * over variables to another activity; EXTRA_NUMBER being the constant that contains said variable
        * , and the integer parsed variable, newBal */
        Intent intent1 = new Intent(MainActivity.this, Activity2.class);
        intent1.putExtra(EXTRA_NUMBER, newBal);
        startActivityForResult(intent1, 1);
    }

    public void openActivity3() {

        Intent intent2 = new Intent(MainActivity.this, Activity3.class);
        startActivityForResult(intent2, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                //Pulling result variable from Activity2 and setting it equal to userBal variable in this activity
                double result = data.getIntExtra("result", 0);
                userBal.setText("" + result);
            }
            if (resultCode == RESULT_CANCELED){

                userBal.setText( "No changes made");
            }
        }
    }
}
