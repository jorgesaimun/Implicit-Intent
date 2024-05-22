package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDial, btnSms, btnEmail, btnShare;
    EditText msz, phn, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial);
        btnSms = findViewById(R.id.btnSms);
        btnEmail = findViewById(R.id.btnEmail);
        btnShare = findViewById(R.id.btnShare);

        msz = findViewById(R.id.et_text);
        phn = findViewById(R.id.et_phnNumber);
        email = findViewById(R.id.et_email);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phn.getText().toString().trim();
                if (!phone.isEmpty()) {
                    Intent intentDail = new Intent(Intent.ACTION_DIAL);
                    intentDail.setData(Uri.parse("tel:" + phone));
                    startActivity(intentDail);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = msz.getText().toString().trim();
                String phone = phn.getText().toString().trim();
                if (!phone.isEmpty() && !message.isEmpty()) {
                    Intent intentSms = new Intent(Intent.ACTION_SENDTO);
                    intentSms.setData(Uri.parse("smsto:" + Uri.encode(phone)));
                    intentSms.putExtra("sms_body", message);
                    startActivity(intentSms);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter both phone number and message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = msz.getText().toString().trim();
                String emailAddress = email.getText().toString().trim();
                if (!emailAddress.isEmpty() && !message.isEmpty()) {
                    Intent intentEmail = new Intent(Intent.ACTION_SEND);
                    intentEmail.setType("message/rfc822");
                    intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"saifullahsaimun1505@gamil.com", "saifullahsaimun27@gamil.com", emailAddress});
                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Queries");
                    intentEmail.putExtra(Intent.EXTRA_TEXT, "Please Resolve the problem " + message);
                    startActivity(Intent.createChooser(intentEmail, "Email via"));
                } else {
                    Toast.makeText(MainActivity.this, "Please enter both email address and message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = msz.getText().toString().trim();
                if (!message.isEmpty()) {
                    Intent intentShare = new Intent(Intent.ACTION_SEND);
                    intentShare.setType("text/plain");
                    intentShare.putExtra(Intent.EXTRA_TEXT, "Please Install this app " + message);
                    startActivity(intentShare);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
