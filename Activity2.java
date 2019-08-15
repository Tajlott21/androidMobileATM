package com.example.mobilebankingatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    double result;
    Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //receives intent
        Intent intent1 = getIntent();
        //receives balance from previous activity
        final double newBal =  intent1.getIntExtra(MainActivity.EXTRA_NUMBER, 0);

        //Linking Java to XML variable depositAmt, as well as parsing the integer put of the text into a new variable
        final EditText depositAmt = findViewById(R.id.depositAmt);
        final double newDepositAmt = Integer.parseInt(depositAmt.getText().toString());

        Button confirmDeposit = findViewById(R.id.confirmDeposit);
        confirmDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If the user leaves space blank, then make a Toast popup to inform them to input the correct information
                if (depositAmt.getText().toString().equals("")){
                    Toast.makeText(Activity2.this, "Please insert an amount", Toast.LENGTH_SHORT).show();
                } else
                    result = newDepositAmt + newBal;

                    resultIntent = new Intent();
                    resultIntent.putExtra("result", result);

                    setResult(RESULT_OK, resultIntent);


            }
        });
    }


}
