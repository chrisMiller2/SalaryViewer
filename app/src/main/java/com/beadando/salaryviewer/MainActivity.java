package com.beadando.salaryviewer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Expenses> expenseList;
    EditText expenseText;
    EditText expenseCost;
    CustomAdapter adapter;
    String name;
    String cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseText = findViewById(R.id.inputExpenseID);
        expenseCost = findViewById(R.id.inputCostID);

        expenseList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, expenseList);
        listView.setAdapter(adapter);

    }

    public void updateList(View view) {
        if (!expenseText.getText().toString().equals("") && !expenseCost.getText().toString().equals("")) {
            name = expenseText.getText().toString();
            cost = expenseCost.getText().toString();

            Expenses customObject = new Expenses(name, cost);
            expenseList.add(customObject);

            adapter.notifyDataSetChanged();
            Toast.makeText(this, "item added", Toast.LENGTH_LONG).show();

            expenseText.setText("");
            expenseCost.setText("");
        } else {
            Toast.makeText(this, "Can't list nothing", Toast.LENGTH_LONG).show();
        }
    }

    public void removeFromList(View view) {
        if (expenseList.size() >= 1) {

            expenseList.remove(expenseList.size() - 1);

            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Item removed", Toast.LENGTH_LONG).show();

            expenseText.setText("");
            expenseCost.setText("");
        } else {
            Toast.makeText(this, "Nothing left to remove", Toast.LENGTH_LONG).show();
        }
    }
}