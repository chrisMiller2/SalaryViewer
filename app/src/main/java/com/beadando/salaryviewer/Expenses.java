package com.beadando.salaryviewer;

import android.os.Parcel;
import android.os.Parcelable;

public class Expenses implements Parcelable {
    public String expenseName;
    public String expenseCount;

    public Expenses(String expenseName, String expenseCount) {
        this.expenseName = expenseName;
        this.expenseCount = expenseCount;
    }

    private Expenses(Parcel parcel) {
        readFromParcel(parcel);
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(String expenseCount) {
        this.expenseCount = expenseCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expenseName);
        dest.writeString(expenseCount);
    }

    private void readFromParcel(Parcel parcel) {
        expenseName = parcel.readString();
        expenseCount = parcel.readString();
    }

    public static final Parcelable.Creator<Expenses> CREATOR = new Parcelable.Creator<Expenses>() {
        @Override
        public Expenses createFromParcel(Parcel source) {
            return new Expenses(source);
        }

        @Override
        public Expenses[] newArray(int size) {
            return new Expenses[0];
        }
    };
}
