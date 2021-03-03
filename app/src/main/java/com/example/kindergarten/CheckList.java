package com.example.kindergarten;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckList extends AppCompatActivity {
    ListView listView;
    Adapter adapter;
    String name;
    String phone;
    String idValue;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        listView = (ListView)findViewById(R.id.absenceList);
        adapter = new Adapter();

        mDatabase.child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    String appear = postSnapShot.child("appear").getValue().toString();
                    String attend = postSnapShot.child("attend").getValue().toString();
                    if(appear.equals("0") && attend.equals("1")) {
                        name = postSnapShot.child("name").getValue().toString();
                        phone = postSnapShot.child("phone").getValue().toString();
                        adapter.addItem(new SingerItem(name));
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone,null,"아이가 등원하지 않았습니다",null,null);
                    Toast.makeText(CheckList.this, "전송되었습니다", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(CheckList.this, "전송실패하였습니다", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public void mClick8(View view){
        mDatabase.child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    idValue=postSnapShot.getKey();
                    mDatabase.child("info").child(idValue).child("attend").setValue(0);
                    mDatabase.child("info").child(idValue).child("appear").setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(CheckList.this, "초기화 되었습니다", Toast.LENGTH_SHORT).show();

    }

    public void mClick9(View view){
        finishAffinity();
        System.runFinalization();
        System.exit(0);
    }

    class Adapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.getName());
            return view;
        }
    }
}
