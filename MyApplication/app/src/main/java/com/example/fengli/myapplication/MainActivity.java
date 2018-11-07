package com.example.fengli.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private  String[] data = {"Apple","Banana","Banana","Banana","Banana","Banana","Banana","Banana","Banana","Apple","Banana","Apple","Banana","Banana","Banana","Banana",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("HelloWorld","3333");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,data
        );
        Button btn = (Button) findViewById(R.id.button_1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
            }
        });
    }

    private void call() {
            try {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:100086"));
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
    }

    public void  onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResult) {
       switch (requestCode) {
           case 1:
               if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                   call();
               } else {
                   Toast.makeText(this,"You denied the  permission",Toast.LENGTH_SHORT).show();
               }
               break;
           default:
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
