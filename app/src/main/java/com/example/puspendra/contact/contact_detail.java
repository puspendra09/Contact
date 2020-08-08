package com.example.puspendra.contact;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class contact_detail extends AppCompatActivity {
    Button b1;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        b1=findViewById(R.id.d_b1);
        t1=findViewById(R.id.d_t1);
        t2=findViewById(R.id.d_t2);
        t3=findViewById(R.id.d_t3);

        SQLiteDatabase db=openOrCreateDatabase("Students",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Contact_list(name varchar (50),phone_no int ,email varchar (30))");
        String query="select * from Contact_list";
        Cursor c=db.rawQuery(query,null);
        if (c.moveToFirst())
        {
            t1.setText(c.getString(0));
            t2.setText(c.getString(1));
            t3.setText(c.getString(2));
        }
        else
            Toast.makeText(contact_detail.this, "Invalide Roll no", Toast.LENGTH_SHORT).show();

b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        finish();
    }
});

    }
}
