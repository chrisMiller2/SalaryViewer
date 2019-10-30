package com.beadando.salaryviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ListView listView;
    EditText expenseText;
    EditText expenseCost;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseText = findViewById(R.id.inputExpenseID);
        expenseCost = findViewById(R.id.inputCostID);

        listView = (ListView) findViewById(R.id.listID);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }

    public void updateList(View view) {
        if(!expenseText.getText().toString().equals(""))
        {
            listItems.add(expenseText.getText().toString());
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "item added", Toast.LENGTH_LONG).show();

            expenseText.setText("");
            expenseCost.setText("");
        }else{
            Toast.makeText(this, "Can't list nothing", Toast.LENGTH_LONG).show();
        }
    }

    public void removeFromList(View view) {
        if(listItems.size() >= 1){
            listItems.remove(listItems.size()-1);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Item removed", Toast.LENGTH_LONG).show();
            expenseText.setText("");
            expenseCost.setText("");
        }else{
            Toast.makeText(this, "Nothing left to remove", Toast.LENGTH_LONG).show();
        }
    }
}
