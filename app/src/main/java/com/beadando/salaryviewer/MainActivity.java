package com.beadando.salaryviewer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Expenses> expenseList;
    CustomAdapter adapter;

    EditText expenseNameText;
    EditText expenseCostText;
    TextView sumText;

    AnyChartView anyChartView;
    Pie pie;
    List<DataEntry> data;

    Expenses expenseObject;
    String name;
    int cost;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseNameText = findViewById(R.id.inputExpenseID);
        expenseCostText = findViewById(R.id.inputCostID);
        sumText = findViewById(R.id.sumID);


        expenseList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, expenseList);
        listView.setAdapter(adapter);

        //SWIPE SIDEWAYS TO REMOVE LIST ELEMENT
        /*final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(listView),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {
                                adapter.remove(position);
                            }
                        });

        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Position " + position, LENGTH_SHORT).show();
                }
            }
        });*/
    }

    public void updateList(View view) {
        if (!expenseNameText.getText().toString().equals("") && !expenseCostText.getText().toString().equals("")) {
            name = expenseNameText.getText().toString();
            cost = Integer.parseInt(String.valueOf(expenseCostText.getText()));

            //summing costs
            sum += cost;
            sumText.setText(String.valueOf(sum));

            //adding items to the list
            expenseObject = new Expenses(name, cost);
            expenseList.add(expenseObject);

            //apply changes
            adapter.notifyDataSetChanged();
//            data.add(new ValueDataEntry(name, cost));
//            pie.data(data);
//            anyChartView.refreshDrawableState();
            Toast.makeText(this, "item added", Toast.LENGTH_LONG).show();

            expenseNameText.setText("");
            expenseCostText.setText("");
        } else {
            Toast.makeText(this, "Can't add nothing to the list", Toast.LENGTH_LONG).show();
        }
    }

    public void removeFromList(View view) {
        if (expenseList.size() >= 1) {

            //decrease sum
            sum -= expenseList.get(expenseList.size() - 1).getExpenseCount();

            //remove last list item
            expenseList.remove(expenseList.size() - 1);

            //apply changes
            sumText.setText(String.valueOf(sum));
            adapter.notifyDataSetChanged();
//            data.remove(expenseList.size()-1);
//            pie.data(data);
            Toast.makeText(this, "Item removed", Toast.LENGTH_LONG).show();

            expenseNameText.setText("");
            expenseCostText.setText("");
        } else {
            Toast.makeText(this, "Nothing left to remove", Toast.LENGTH_LONG).show();
        }
    }

    public void openCharts(View view) {
        Intent i = new Intent(this, Charts.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", expenseList);
        i.putExtras(bundle);
        startActivity(i);
    }
}