package com.ace.classtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Intent classesIntent,classSetIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classesIntent = new Intent(MainActivity.this,Classes.class);
        classSetIntent = new Intent(MainActivity.this,ClassSet.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(1,1,1,"add class");
        menu.add(1,2,2,"config class");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1) {
            MainActivity.this.startActivity(classesIntent);
        } else if (item.getItemId() == 2) {
            MainActivity.this.startActivity(classSetIntent);
        }
        return false;
    }

}
