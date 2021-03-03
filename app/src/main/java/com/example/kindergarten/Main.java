package com.example.kindergarten;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mClick(View v){
        Intent intent = new Intent(this, Tselect.class);
        startActivity(intent);
    }

    public void mClick4(View view){
        Intent intent = new Intent(this, Parent.class);
        startActivity(intent);
    }
}
