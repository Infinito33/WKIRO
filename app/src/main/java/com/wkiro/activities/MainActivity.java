package com.wkiro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wkiro.R;
import com.wkiro.appLogic.transformStrategies.eTransformStrategy;

/**
 * Główna klasa pełniąca rolę dashboardu.
 *
 * @author tomasz.huchro, 2016-03-18
 */
public class MainActivity extends AppCompatActivity {

    Spinner transformTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transformTypeSpinner = (Spinner) findViewById(R.id.transformStrategySpinner);
        transformTypeSpinner.setAdapter(new ArrayAdapter<eTransformStrategy>(this, android.R.layout.simple_list_item_1, eTransformStrategy.values()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Otwarcie okna z domyślnie załadowanym obrazem.
     *
     * @param view View.
     */
    public void startPicturePreview(View view) {
        Intent intent = new Intent(this, PicturePreviewActivity.class);
        String selectedItemString = transformTypeSpinner.getSelectedItem().toString();
        intent.putExtra("transform_strategy", selectedItemString);
        startActivity(intent);
    }
}
