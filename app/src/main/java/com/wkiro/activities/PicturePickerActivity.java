package com.wkiro.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wkiro.R;
import com.wkiro.logic.ImageTransformer;
import com.wkiro.logic.ImageTransformerFactory;
import com.wkiro.logic.transformStrategies.eTransformStrategy;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PicturePickerActivity extends Activity {

    private ImageTransformer imageTransformer;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("openCv", "OpenCV loaded successfully");
                    loadImageStrategy();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_picker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture_picker, menu);
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
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
    }

    public void pickPhoto(View view) {
        final ImageView img = (ImageView) findViewById(R.id.image);
        img.setImageResource(android.R.color.transparent);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Wybierz zdjęcie"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        Bitmap transformedFinalImage = Bitmap.createBitmap(selectedImage.getWidth(), selectedImage.getHeight(), Bitmap.Config.RGB_565);
                        Mat transformedImage = new Mat();
                        Mat resultImage = new Mat();

                        Utils.bitmapToMat(selectedImage, transformedImage);

                        resultImage = imageTransformer.transform(transformedImage);

                        Utils.matToBitmap(resultImage, transformedFinalImage);

                        ImageView image = (ImageView) findViewById(R.id.image);
                        image.setImageBitmap(transformedFinalImage);

                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
        }
    }
}
