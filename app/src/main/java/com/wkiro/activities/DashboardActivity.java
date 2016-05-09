package com.wkiro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.wkiro.R;
import com.wkiro.utils.ImageAdapter;

/**
 * Główna klasa pełniąca rolę dashboardu.
 *
 * @author tomasz.huchro, 2016-03-18
 */
public class DashboardActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        GridView gridView = (GridView) findViewById(R.id.dashboard_grid);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(this);

        // Hack to disable GridView scrolling
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        if(position == 0 || position == 1) {
            Intent intent = new Intent(this, TransformPickerActivity.class);
            intent.putExtra("activityType", position);
            startActivity(intent);
        } else if(position == 3) {
            System.exit(0);
        } else {
            showAboutUs();
        }
    }

    private void showAboutUs() {
        Toast.makeText(DashboardActivity.this, "Autorzy: Tomasz Huchro & Tomasz Depta", Toast.LENGTH_SHORT).show();
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

}
