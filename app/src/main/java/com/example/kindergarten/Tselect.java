package com.example.kindergarten;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tselect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tselect);
    }

    public void mClick2(View v){
        Intent intent = new Intent(this, ChildList.class);
        startActivity(intent);
    }

    public void mClick3(View v){
        Intent intent = new Intent(this, AddChild.class);
        startActivity(intent);
    }
}
