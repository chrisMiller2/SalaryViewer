package com.beadando.salaryviewer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/** Ezeket valahol tárolja, ha az appot újra rakom akkor is megtalálja, creepy...
 */
public class Preferences extends AppCompatActivity{
    public static  final String SHARED_PREFS = "sharedPrefs";
    public static  final String NUMBER = "value";

    private TextView textView;
    private EditText editText;
    public Integer MonthlyMaximumValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        textView = findViewById(R.id.preferencesTextView);
        editText = findViewById(R.id.preferencesEditText);
        load();
        textView.setText(String.valueOf(MonthlyMaximumValue));
    }
    /** Ez lekérdezi az értékét value-nak, amit NUMBER-nek neveztem el, ez egy intet ad vissza,
    valószínű, ha nem éri el vagy nem találja akkor 0-t ad vissza. */
    public void load()
    {
        SharedPreferences shP = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        MonthlyMaximumValue = shP.getInt(NUMBER, 0);
    }

    /**Ez a setter-e a NUMBER-nek,
     * a putInt második paramétere egy int, ami értelem szerűen lehet egy EditText értéke is,
     * amit parseInt-el konvertálunk.
     * apply() függvény fontos, az egész változtatást az commit-olja, ha lemarad, nem menti.*/
    public void save(View view)
    {
        SharedPreferences shP = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = shP.edit();

        editor.putInt(NUMBER, Integer.parseInt(editText.getText().toString()));

        editor.apply();
    }
}
