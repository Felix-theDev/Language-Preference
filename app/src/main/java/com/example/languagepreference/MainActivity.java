package com.example.languagepreference;
/** An app for selecting a preferred language
 * @author Felix Ogbonnaya
 * @since 2020-1-18
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    String language;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.english:
                language = "English";
                textView.setText("English selected");
                sharedPreferences.edit().putString("language", language).apply();
                return true;
            case R.id.spanish:
                language = "Spanish";
                textView.setText("Spanish selected");
                sharedPreferences.edit().putString("language", language).apply();
                return true;
            default:
                return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.country);

        sharedPreferences = this.getSharedPreferences("com.company.languagePreference", Context.MODE_PRIVATE);

        language = sharedPreferences.getString("language", "");
        if(language == "") {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Select Language!!")
                    .setMessage("Select your preferred Language")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            textView.setText("English Selected");
                            language = "English";
                            sharedPreferences.edit().putString("language", language).apply();
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            textView.setText("Spanish Selected");
                            language = "Spanish";
                            sharedPreferences.edit().putString("language", language).apply();
                        }
                    }).show();
        }else {
            textView.setText(language + " selected");
        }
    }
}