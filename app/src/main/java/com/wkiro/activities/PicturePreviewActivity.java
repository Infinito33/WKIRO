package com.wkiro.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.wkiro.R;
import com.wkiro.logic.ImageTransformer;
import com.wkiro.logic.ImageTransformerFactory;
import com.wkiro.logic.transformStrategies.eTransformStrategy;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Klasa umożliwiająca pobieranie na żywo obrazu z kamery i przetwarzanie ramek w metodzie {@link #onCameraFrame(CvCameraViewFrame)}.
 *
 * @author tomasz.huchro, 2016-03-18
 */
public class PicturePreviewActivity extends AppCompatActivity implements OnTouchListener, CvCameraViewListener2 {
    private static final String TAG = "Picture preview";

    private CameraBridgeViewBase mOpenCvCameraView;
    private Mat mRgba;

    private ImageTransformer imageTransformer;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                    loadImageStrategy();
                    mOpenCvCameraView.enableView();
                    mOpenCvCameraView.enableFpsMeter();
                    mOpenCvCameraView.setOnTouchListener(PicturePreviewActivity.this);
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    private void loadImageStrategy() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String transformStrategyString = extras.getString("transform_strategy");
            eTransformStrategy strategy = eTransformStrategy.valueOf(transformStrategyString);
            imageTransformer = ImageTransformerFactory.CreateStrategyFromEnum(strategy);
        } else {
            imageTransformer = ImageTransformerFactory.CreateNoActionTransformer();
        }
    }

    public PicturePreviewActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_picture_preview);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.picturePreviewActivity);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture_preview, menu);
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

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    /**
     * Inicjaliazcja obiektu do przechowywania ramek.
     *
     * @param width  -  the width of the frames that will be delivered
     * @param height - the height of the frames that will be delivered
     */
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    /**
     * Zwolnienie zasobu w momencie wyjścia z kamery.
     */
    public void onCameraViewStopped() {
        mRgba.release();
    }

    /**
     * Wywołanie akcji w momencie dotknięcia ekranu.
     *
     * @param view  Widok.
     * @param event Zdarzenie.
     * @return boolean.
     */
    public boolean onTouch(View view, MotionEvent event) {
        return false; // don't need subsequent touch events
    }

    /**
     * Odczyt każdej ramki w czasie rzeczywistym
     *
     * @param inputFrame Ramka wejściowa pobrana z kamery.
     * @return Mat Ramka wyjściowa po obróbce.
     */
    public Mat onCameraFrame(final CvCameraViewFrame inputFrame) {

        mRgba = inputFrame.rgba();

        System.out.println(mRgba.type());
        System.out.println(mRgba.channels());
        System.out.println(mRgba.depth());

        //PRZETWARZANIE RAMEK
        mRgba = imageTransformer.transform(mRgba);

        return mRgba;
    }

}
