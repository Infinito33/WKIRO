package com.wkiro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wkiro.R;
import com.wkiro.logic.transformStrategies.eTransformStrategy;
import com.wkiro.utils.TransformConfig;

/**
 * Activity for picking type of further transformation
 */
public class TransformSelectActivity extends AppCompatActivity {

    private Spinner transformTypeSpinner;
    private TransformConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_select);

        config = TransformConfig.GetInstance();


        InitializeConfigTextFields();

        transformTypeSpinner = (Spinner) findViewById(R.id.transformStrategySpinner);
        transformTypeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eTransformStrategy.values()));
    }

    private void InitializeConfigTextFields() {
        EditText addSubText = (EditText)findViewById(R.id.AddSubValueBox);
        addSubText.setText(Integer.toString(config.AddSubValue));

        EditText mulDivText = (EditText)findViewById(R.id.MulDivValueSpinner);
        mulDivText.setText(Double.toString(config.MulDivValue));

        EditText gammaText = (EditText)findViewById(R.id.GammaSpinner);
        gammaText.setText(Double.toString(config.GammaValue));

        EditText thresholdText = (EditText)findViewById(R.id.ThresholdSpinner);
        thresholdText.setText(Integer.toString(config.ThresholdValue));

        EditText blurKernelText = (EditText)findViewById(R.id.KernelSizeSpinner);
        blurKernelText.setText(Integer.toString(config.BlurKernelSize));

        EditText morphText = (EditText)findViewById(R.id.MorphSpinner);
        morphText.setText(Integer.toString(config.MorphOperationSize));
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
        SetTransformConfiguration();
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

    private void SetTransformConfiguration() {
        EditText addSubText = (EditText)findViewById(R.id.AddSubValueBox);
        config.AddSubValue = Integer.parseInt(addSubText.getText().toString());

        EditText mulDivText = (EditText)findViewById(R.id.MulDivValueSpinner);
        config.MulDivValue = Double.parseDouble(mulDivText.getText().toString());

        EditText gammaText = (EditText)findViewById(R.id.GammaSpinner);
        config.GammaValue = Double.parseDouble(gammaText.getText().toString());

        EditText thresholdText = (EditText)findViewById(R.id.ThresholdSpinner);
        config.ThresholdValue = Integer.parseInt(thresholdText.getText().toString());

        EditText blurKernelText = (EditText)findViewById(R.id.KernelSizeSpinner);
        config.BlurKernelSize = Integer.parseInt(blurKernelText.getText().toString());

        EditText morphText = (EditText)findViewById(R.id.MorphSpinner);
        config.MorphOperationSize = Integer.parseInt(morphText.getText().toString());
    }

    private Class<?> chooseActivity(int position) {
        switch (position) {
            case 0:
                return CameraModeActivity.class;
            case 1:
                return PhotoModeActivity.class;
            default:
                return CameraModeActivity.class;
        }
    }
}
