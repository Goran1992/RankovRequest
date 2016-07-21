package com.example.dev3.notifikacijefragmenti;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity {
    Button dugme, dugme2, dugme3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "ONCREATE");
        final Intent intent=getIntent();
        setContentView(R.layout.activity_main);
        String msg = getIntent().getStringExtra("click_action");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (msg != null) {
            if (msg.equals("goToFragment1")) {
                Fragment1 fragment1 = new Fragment1();
                fragmentTransaction.replace(R.id.myFragment, fragment1);
                Log.d("FragmentTransaction", "Fragment je promenjen u onCreate!");
                fragmentTransaction.commit();
                Log.d("Create", "Kraj onCreatea");
            }
        }

        dugme = (Button) findViewById(R.id.dugme1);
        dugme2 = (Button) findViewById(R.id.subscribe);
        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                if (view == dugme) {
                    fragment = new Fragment1();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.myFragment, fragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                transaction.commit();
            }
        });


        dugme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("android");
                Log.d("Log", "Uspesno ste se pretplatili");
            }
        });
//        dugme3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1=new Intent(MainActivity.this, DrugiAktiviti.class);
//                startActivity(intent1);
//            }
//        });

    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Log.d("onNewIntent", "NewIntent");
//        handleIntentExtra(intent);
//    }
//
//    private void handleIntentExtra(Intent intent) {
//        String msg = intent.getStringExtra("action");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if (msg != null) {
//            if (msg.equals("goToFragment1")) {
//                Fragment1 fragment1 = new Fragment1();
//                fragmentTransaction.replace(R.id.myFragment, fragment1);
//                fragmentTransaction.commit();
//            }
//
//
//        }
//    }




    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "Resume");
        Intent intent = new Intent();
        String msg = getIntent().getStringExtra("action");
        Log.d("msg", "msg");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.d("FragmentTransaction", "FragmentTransaction success!");
        if (msg != null)
        {
            if (msg.equals("goToFragment1")) {
                Fragment1 fragment1 = new Fragment1();
                fragmentTransaction.replace(R.id.myFragment, fragment1);
                Log.d("FragmentTransaction", "Fragment je promenjen!");
                fragmentTransaction.commit();
                Log.d("onResume", "Kraj resuma");
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();  // Always call the superclass method first

        Log.d("onPause", "Pauza");
    }
}


