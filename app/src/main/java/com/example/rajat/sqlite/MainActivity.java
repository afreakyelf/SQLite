package com.example.rajat.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,password,updateold,updatenew,delete;
    myDatabase mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editName);
        password =  findViewById(R.id.editPass);
        updateold = findViewById(R.id.editText3);
        updatenew =  findViewById(R.id.editText5);
        delete =  findViewById(R.id.editText6);

        mdatabase = new myDatabase(this);

    }

    public void addUser(View v){
        String t1 = name.getText().toString();
        String t2 = password.getText().toString();

        if(t1.isEmpty() || t2.isEmpty()){
            Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show();
        }else {
            long id =  mdatabase.insertData(t1,t2);
            if(id<=0){
                Toast.makeText(this, "insertion unsuccessful", Toast.LENGTH_SHORT).show();
                name.setText("");
                password.setText("");
            }else {
                Toast.makeText(this, "insertion successful", Toast.LENGTH_SHORT).show();
                name.setText("");
                password.setText("");

            }
        }

    }


    public void viewdata(View view){
        String data =mdatabase.getData();
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    public void update(View view){
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if (u1.isEmpty() || u2.isEmpty()) {
            Toast.makeText(this, "Fill data", Toast.LENGTH_SHORT).show();
        } else {
            int a = mdatabase.updatename(u1,u2);
            if(a<=0) {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
                updatenew.setText("");
                updateold.setText("");
            }else {

                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                updatenew.setText("");
                updateold.setText("");
            }


        }
    }


    public void delete(View view){
        String uname = delete.getText().toString();
        if (uname.isEmpty()) {
            Toast.makeText(this, "Fill data", Toast.LENGTH_SHORT).show();
        } else {
            int a = mdatabase.delete(uname);
            if(a<=0) {
                Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                updatenew.setText("");
                updateold.setText("");
            }else {
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                updatenew.setText("");
                updateold.setText("");
            }


        }
    }

}
