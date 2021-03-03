package com.example.kindergarten;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddChild extends AppCompatActivity {

    private EditText child_ID, child_name, child_phone;
    private ImageButton sendData;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    String id;
    String name;
    String phone;
    Integer attend;
    Integer appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        child_ID = (EditText)findViewById(R.id.editID);
        child_name = (EditText) findViewById(R.id.editName);
        child_phone = (EditText)findViewById(R.id.editPhone);
        sendData = (ImageButton)findViewById(R.id.completeBtn);

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = child_ID.getText().toString();
                name = child_name.getText().toString();
                phone = child_phone.getText().toString();
                attend = 0;
                appear = 0;

                writeChild(id, name, phone, attend, appear);

                Toast.makeText(AddChild.this, "추가되었습니다", Toast.LENGTH_SHORT).show();
                child_ID.setText("");
                child_name.setText("");
                child_phone.setText("");
            }
        });
    }

    private void writeChild(String id, String name, String phone, Integer attend, Integer appear){
        FirebasePost firebasePost = new FirebasePost(name, phone, attend, appear);

        mDatabase.child("info").child(id).setValue(firebasePost);
    }

}
