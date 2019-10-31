package com.beadando.salaryviewer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Expenses> {

    public CustomAdapter(Context context, List<Expenses> objects) {
        super(context, -1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            view = View.inflate(getContext(), R.layout.list, null);
            viewHolder = new ViewHolder();
            viewHolder.setView(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.expenseName.setText(getItem(position).getExpenseName());
        viewHolder.expenseCount.setText(getItem(position).getExpenseCount());
        return view;
    }

    private class ViewHolder {

        public TextView expenseName;
        public TextView expenseCount;

        public void setView(View view) {
            expenseName = (TextView) view.findViewById(R.id.nameTextViewID);
            expenseCount = (TextView) view.findViewById(R.id.infoTextViewID);
            view.setTag(this);
        }

    }
}
