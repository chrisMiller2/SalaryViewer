package com.beadando.salaryviewer;

import android.os.Parcel;
import android.os.Parcelable;

public class Expenses implements Parcelable {
    public String expenseName;
    public int expenseCount;

    public Expenses(String expenseName, int expenseCount) {
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

    public int getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(int expenseCount) {
        this.expenseCount = expenseCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expenseName);
        dest.writeInt(expenseCount);
    }

    private void readFromParcel(Parcel parcel) {
        expenseName = parcel.readString();
        expenseCount = parcel.readInt();
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
