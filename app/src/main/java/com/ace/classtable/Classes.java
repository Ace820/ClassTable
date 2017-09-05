package com.ace.classtable;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/9/5.
 */

public class Classes extends AppCompatActivity {

    private ListView listView;
    private List<String> data;
    private Button addNewClass;
    final int ADD = 0;
    final int DELETE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<String>();
        initClasses();
        listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data));
        setContentView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("classtable","get id ="+i+",max line=" + data.size());
                if((i + 1) == data.size()){
                    configClass(null,false);
                } else {
                    final String className = data.get(i);
                    AlertDialog.Builder configBuilder = new AlertDialog.Builder(Classes.this);
                    LinearLayout configClassLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.item_config,null);
                    configBuilder.setView(configClassLayout);
                    configBuilder.setCancelable(true);
                    TextView editClass = (TextView)configClassLayout.findViewById(R.id.editClass);
                    editClass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            configClass(className,false);
                        }
                    });
                    TextView deleteClass = (TextView)configClassLayout.findViewById(R.id.deleteClass);
                    deleteClass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteFromDB(className);
                            restartActivity();
                        }
                    });
                    AlertDialog dialog = configBuilder.create();
                    dialog.show();
                }
            }
        });
    }

    private void configClass(String name,boolean delete){
        final String getName = name;
        final Boolean getdel = delete;
        AlertDialog.Builder builder = new AlertDialog.Builder(Classes.this);
        LinearLayout addNewClassLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.add_new_class,null);
        builder.setView(addNewClassLayout);
        builder.setCancelable(false);
        final EditText editText = (EditText)addNewClassLayout.findViewById(R.id.addNewClass);
        if (name != null)
            editText.setText(name);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (getdel){
                    deleteFromDB(getName);
                }
                String newName = editText.getText().toString();
                saveToDB(newName);
                Toast.makeText(Classes.this,newName,Toast.LENGTH_SHORT).show();
                restartActivity();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void restartActivity(){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }
    public void initClasses(){
        //read DB and run data.add(singalClass);
        data.add("Math");
        data.add("English");
        data.add("Java");
        data.add("Ruby");
        data.add("King Honor");
        data.add("Add new class");
    }
    protected void deleteFromDB(String name){
        Toast.makeText(Classes.this,"delete item:"+name,Toast.LENGTH_SHORT).show();
        //TODO
    }
    protected void saveToDB(String name){
        Toast.makeText(Classes.this,"save item:"+name,Toast.LENGTH_SHORT).show();
        //TODO
    }
}
