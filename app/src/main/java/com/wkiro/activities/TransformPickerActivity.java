package com.wkiro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wkiro.R;
import com.wkiro.logic.transformStrategies.eTransformStrategy;

/**
 * Activity for picking type of further transformation
 */
public class TransformPickerActivity extends AppCompatActivity {

    private Spinner transformTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_picker);

        transformTypeSpinner = (Spinner) findViewById(R.id.transformStrategySpinner);
        transformTypeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eTransformStrategy.values()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transform_picker, menu);
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
        int activityType = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityType = extras.getInt("activityType");
        }
        Intent intent = new Intent(this, chooseActivity(activityType));
        String selectedItemString = transformTypeSpinner.getSelectedItem().toString();
        intent.putExtra("transform_strategy", selectedItemString);
        startActivity(intent);
    }

    private Class<?> chooseActivity(int position) {
        switch (position) {
            case 0:
                return PicturePreviewActivity.class;
            case 1:
                return ChoosePictureActivity.class;
            default:
                return PicturePreviewActivity.class;
        }
    }
}
