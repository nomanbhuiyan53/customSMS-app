package com.softbard.easysms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.content.pm.PackageManager;
import android.database.Cursor;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SMSBox_1 extends AppCompatActivity {
    RadioGroup gender, card,day;
    EditText amount, mobile, USD, time,duedate;
    TextView textPreview;
    Button sendButton, preview;
    String genderText="", cardText="", userName, dayName="",month;
    DBHelper myDB;
    String [] smsList = {">>Click here to select SMS category","1.Over due Alert sms","2.Promise to pay ",
            "3.Reminder sms against broken PTP","4.Customer does not pick the phone",
            "5.If customer paid partial arrears amount",
            "6.we are close of The Month ","7.Avoid Late Fee","8.Before Bill generate","9.Create Urgency on end of The Month",
            "10.(CIB)CM missed more than multiple PTP dates and not receiving phone(DPD: 90 & above)",
            "11.Before Visit","12.Before Bill generation","13.Card Temporarily Inactive/Block (after 30 DPD)",
            "14.Due date Reminder","15.reported at the next delinquent level",
            "16.Bank is initiating to pursue Legal action"
    };
    Spinner spItem;
    ArrayAdapter<String> adapter;
    String bdtpart,usdPart,dTime;
    String  sms2,i;
    String finalusd = "";
    String finalbdt = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsbox1);
        gender = findViewById(R.id.gendarGroup);
        card = findViewById(R.id.cardGroup);
        day = findViewById(R.id.day);
        amount = findViewById(R.id.amount);
        time = findViewById(R.id.timepic);
        USD = findViewById(R.id.usd);
        textPreview = findViewById(R.id.smsPreview);
        duedate = findViewById(R.id.duedate);

        mobile = findViewById(R.id.mobile);
        sendButton = findViewById(R.id.sendButton);
        preview = findViewById(R.id.previewButton);
        spItem = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,smsList);
        spItem.setAdapter(adapter);


        // user name input from data base here ...
        myDB = new DBHelper(getApplicationContext());
        Cursor res = myDB.getData();
        if (res.getCount() == 0){
            Toast.makeText(SMSBox_1.this,"No data found",Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        if (res.moveToFirst()){
            buffer.append(res.getString(1));
        }
        userName = buffer.toString();
        // new database received ----


        usdPart = USD.getText().toString().trim();
        bdtpart = amount.getText().toString().trim();

        // month name select
        Calendar cal = Calendar.getInstance();
        month = new SimpleDateFormat("MMMM").format(cal.getTime());
        // ---------------------------------
        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        i = "0";
                        break;

                    case 1:
                        // Over due Alert sms
                      i = "1";
                        break;
                    case 2:
                        //Promise to pay
                        i = "2";
                        break;
                    case 3:
                        i = "3";
                        break;
                    case 4:
                        i = "4";
                        break;
                    case 5:
                        i = "5";
                        break;

                    case 6:
                        i = "6";
                        break;
                    case 7:
                        i = "7";
                        break;

                    case 8:
                        i = "8";
                        break;
                    case 9:
                        i = "9";
                        break;
                    case 10:
                        i = "10";
                      break;
                    case 11:
                        i = "11";
                        break;
                    case 12:
                        i = "12";
                        break;
                    case 13:
                        i = "13";
                        break;
                    case 14:
                        i = "14";
                        break;
                    case 15:
                        i = "15";
                        break;
                    case 16:
                        i = "16";
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        day.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.todaybtn:
                        dayName = " Today";
                        break;
                    case R.id.tomowbtn:
                        dayName = " Tomorrow";
                }
            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.sirButton:
                        genderText = "Sir";
                        break;
                    case R.id.madamButton:
                        genderText = "Madam";
                }
            }
        });
        card.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rediovisa:
                        cardText = "VISA";
                        break;
                    case R.id.radioamex:
                        cardText = "AMEX";
                        break;
                    case R.id.agora:
                        cardText = "Agora";
                }
            }
        });


        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSMS();

                textPreview.setText(sms2);

            }
        });

    }

    public void setSMS(){

        String bd="",usd="";

        dTime ="at "+time.getText().toString().trim();
            bd = amount.getText().toString();
            usd= USD.getText().toString();
            if (!USD.getText().toString().isEmpty() && !amount.getText().toString().isEmpty()){
                finalusd = " & USD "+usd;
                finalbdt = " BDT"+bd;

            }else if (!USD.getText().toString().isEmpty()){
                finalusd =" USD "+ usd;
            }else{
                finalusd ="";
            }
            if (!amount.getText().toString().isEmpty()){
                finalbdt ="BDT "+bd;
            }else{
                finalbdt ="";
            }
            switch (i){
                case "0":
                    Toast.makeText(SMSBox_1.this,"Select Category", Toast.LENGTH_LONG).show();
                    break;
                case "1":
                    sms2 = "Dear " + genderText + ",\nYour " + cardText + " credit card's minimum due is " +
                            finalbdt + "" + finalusd + " which is payable immediately.\n" +
                            "Please disregard if already paid.\n\nRegards,\n" + userName + "\nCard Collection Unit\nThe City Bank Ltd.";
                break;
                case "2":
                    //Promise to pay
                    sms2 ="Dear "+genderText+", \n" +
                            "\n" +
                            "this is a gentle reminder to deposit your City "+cardText+" Credit Card "+finalbdt+""+finalusd+" today as per your commitment.\n" +
                            "\n" +
                            "Please disregard if already paid.\n\nRegards,\n"+userName+"\nCard Collection Unit\nThe City Bank Ltd.";


                    break;
                case "3":
                    //Reminder sms against broken PTP
                    sms2="Dear "+genderText+",\nthis is an urgent reminder about your "+cardText+" Credit card payment of minimum dues. Please deposit "+finalbdt+""+finalusd+" urgently or disregard if already paid.\n\nRegards,\n"+userName+"\nCard Collection Unit\nThe City Bank Ltd.";


                    break;
                case "4":
                    //4.Customer does not pick the phone
                    sms2= "Dear "+genderText+",\nwe are trying to contact you to discuss about your "+cardText+" card repayment. Please call back.\n\nRegards,\n"+userName+"\nCard Collection Unit\nThe City Bank Ltd.";
                    //
                    break;
                case "5":
                    //If customer paid partial arrears amount
                    sms2 = "Dear "+genderText+",\nthanks for your payment but "+finalbdt+""+finalusd+" is required to make your "+cardText+" card regular. \nPlease disregard if already paid.\n\nRegards,\n"+userName+"\nCard Collection Unit\nThe City Bank Ltd.";
                    //
                    break;

                case "6":
                    //To Create Urgency on end of The Month"
                    sms2 = "Dear "+genderText+",\n" +
                            "We are very close to the month of "+month+" closing. There for, please pay the minimum amount of at least "+finalbdt+""+finalusd+" of the total outstanding bill of your "+cardText+" Credit card by "+dayName+" urgently. kindly disregard if you have paid already.\nRegards,\n"+userName+"\nCard Collection Unit\nThe City Bank Ltd.";

                    break;
                case "7":
                    //Avoid Late Fee:
                    sms2 = "Dear "+genderText+",\nPlease make payment of your City "+cardText+" card minimum dues of "+finalbdt+""+finalusd+" to avoid late fee within "+dTime+" of "+dayName+"." +
                            "\nPlease disregard, if already paid.\nRegards,\n"+userName+"\nCard Collection Unit\nThe City Bank Ltd.";
                    break;

                case "8":
                    //Before Bill generate:
                    sms2 = "Dear "+genderText+"\nPlease be informed, another bill is going to generate tonight, please make payment of your minimum dues of "+finalbdt+""+finalusd+" before "+dTime+" of "+dayName+"." +
                            "\nPlease disregard, if already paid.\nRegards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;

                case "9":
                    // Create Urgency on end of The Month
                    sms2 = "Dear "+genderText+",\nDespite our Several Collection Reminder Still we have not received your "+cardText+" Card Minimum dues of "+finalbdt+""+finalusd+".\nPlease deposit "+finalbdt+""+finalusd+" urgently.\n" +
                            "Please disregard, if already paid.\nRegards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "10":
                    //10.CM missed more than multiple PTP dates and not receiving phone(DPD: 90 & above)",
                    sms2="Dear "+genderText+",\nwith regret we state that you have not been taking any " +
                            "repayment initiative towards settling your City Bank "+cardText+" Credit Card, " +
                            "rather you have been breaking commitments every time. Please treat " +
                            "your repayment obligation extremely seriously as it has been delinquent " +
                            "for "+month+" months and already reported at CIB of Central Bank." +
                            "\nPlease communicate to discuss and get a better plan on your repayment." +
                            " \nRegards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "11":
                    //11.Before Visit",//
                    sms2="Dear "+genderText+",\nDue to the failure of your several commitments towards your "+cardText+" Credit Card repayment, " +
                            "The City Bank Ltd has decided to initiate physical visit at your addresses. " +
                            "You are requested to pay "+finalbdt+""+finalusd+" within "+dayName+" to avoid such initiation. " +
                            "\nRegards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "12":
                    //12.Before Bill generation",
                    sms2="Dear "+genderText+",\nPlease be informed, another bill is going to generate tonight, " +
                            "please make payment of your minimum dues of "+finalbdt+""+finalusd+" before "+dTime+" "+dayName+".\nPlease disregard, if already paid\n" +
                            "Regards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "13":
                    //13.Card Temporarily Inactive/Block (after 30 DPD)", 
                    sms2="Dear "+genderText+",\nPlease be informed that your "+cardText+" credit card minimum dues is "+finalbdt+""+finalusd+" is still due, " +
                            "failing which your Credit Card account may be temporarily Blocked as per Bank’s policy.\n" +
                            "Regards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "14":
                    //14.Due date Reminder",//
                    sms2="Dear "+genderText+",\nyour "+cardText+" credit card’s payment due date is "+duedate.getText().toString()+"." +
                            "Please pay the minimum due "+finalbdt+""+finalusd+" within the mentioned date to avoid overdue fee." +
                            "\nPlease disregard if you have paid already.\n" +
                            "Regards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "15":
                    //15.reported at the next delinquent level",
                    sms2="Dear "+genderText+",\nwe regret to inform you that your "+cardText+" credit card is " +
                            "going to be reported at the next delinquent level for non-payment. " +
                            "Therefore, you are requested to pay at least amount of "+finalbdt+""+finalusd+" by "+dayName+".\n" +
                            "Regards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;
                case "16":
                    //16.Bank is initiating to pursue Legal action"
                    sms2 = "Dear "+genderText+",\nthe Bank is initiating to pursue Legal action against " +
                            "you pursuant to the provisions of N.I. Act -1881/ Artha Rin Adalat Ain- 2003 Therefore," +
                            " you are requested to contact us on urgent basis.\n" +
                            "Regards,\n"+userName+"\nCard Collection Unit.\nThe City Bank Ltd.";
                    break;

            }


    }


    public void sentbd(View view){
        int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            Mysms();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void Mysms() {
        String phone = mobile.getText().toString().trim();
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> massage = smsManager.divideMessage(sms2);
        if (!mobile.getText().toString().equals("")){
            if (gender.getCheckedRadioButtonId() != -1 && card.getCheckedRadioButtonId() != -1){
                smsManager.sendMultipartTextMessage(phone,null,massage,null,null);
                Toast.makeText(this,"SMS send successful",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Please Select card name and Gender",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"Please Enter Phone number",Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Mysms();
            } else {
                Toast.makeText(this, "You don't have permission granted", Toast.LENGTH_LONG).show();
            }
        }
    }

}