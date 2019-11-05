package com.beadando.salaryviewer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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

    Expenses expenseObject;
    String name;
    int cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseNameText = findViewById(R.id.inputExpenseID);
        expenseCostText = findViewById(R.id.inputCostID);

        expenseList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, expenseList);
        listView.setAdapter(adapter);

        //CHART
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();

        data.add(new ValueDataEntry("Heroldg", 20));
        data.add(new ValueDataEntry("Jake", 12));
        data.add(new ValueDataEntry("Peter", 10));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);

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

            expenseObject = new Expenses(name, cost);
            expenseList.add(expenseObject);

            adapter.notifyDataSetChanged();
            Toast.makeText(this, "item added", Toast.LENGTH_LONG).show();

            expenseNameText.setText("");
            expenseCostText.setText("");
        } else {
            Toast.makeText(this, "Can't list_item nothing", Toast.LENGTH_LONG).show();
        }
    }

    public void removeFromList(View view) {
        if (expenseList.size() >= 1) {

            expenseList.remove(expenseList.size() - 1);

            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Item removed", Toast.LENGTH_LONG).show();

            expenseNameText.setText("");
            expenseCostText.setText("");
        } else {
            Toast.makeText(this, "Nothing left to remove", Toast.LENGTH_LONG).show();
        }
    }
}