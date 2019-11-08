package com.beadando.salaryviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Charts extends AppCompatActivity {
    AnyChartView anyChartView;
    Pie pie;
    List<DataEntry> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        //getting data from previous activity
        Bundle bundle = getIntent().getExtras();
        ArrayList<Expenses> expenseList = bundle.getParcelableArrayList("list");

        //CHART
        pie = AnyChart.pie();

        data = new ArrayList<>();
        //add list item to chart
        for (Expenses exp : expenseList) {
            data.add(new ValueDataEntry(exp.getExpenseName(), exp.getExpenseCount()));
        }
        pie.data(data);
        anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);

    }
}
