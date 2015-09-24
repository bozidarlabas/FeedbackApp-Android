package com.example.kanta.feedbackapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.presenter.FeedbackPresenter;
import com.example.kanta.feedbackapp.mvp.presenter.impl.FeedbackPresenterImpl;
import com.example.kanta.feedbackapp.mvp.view.FeedbackView;
import com.example.kanta.feedbackapp.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by boogiepop on 23.09.15..
 */
public class SendFeedback extends AppCompatActivity implements View.OnClickListener, FeedbackView {

    Button takePicture, takeVideo, btnSend, btnCancel;
    EditText feedbackText;
    ImageView displayImage;
    RatingBar ratingBar;
    BitmapDrawable result;
    Uri videoUri,imageUri;
    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int CAMERA_VIDEO_REQUEST = 100;
    private FeedbackPresenter presenter;
    private String clickedProjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfeedback);
        takePicture = (Button) findViewById(R.id.btnTakePicture);
        displayImage = (ImageView) findViewById(R.id.displayPicture);
        takeVideo = (Button) findViewById(R.id.btnTakeVideo);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSend = (Button) findViewById(R.id.btnSendFeedback);
        feedbackText = (EditText) findViewById(R.id.feedbackMessage);
        btnCancel = (Button) findViewById(R.id.btnCancelFeedback);
        btnCancel.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        takeVideo.setOnClickListener(this);
        takePicture.setOnClickListener(this);
        presenter = new FeedbackPresenterImpl(this);


        clickedProjectId = getIntent().getExtras().getString("project_id", "0");

        String[] coordinates = getUserLocation();

        Log.d("lat", coordinates[0]);
        Log.d("long", coordinates[1]);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTakePicture) {
            displayImage.setImageDrawable(null);
            result = null;
            videoUri = null;
            imageUri = null;
            takePicture();
        }

        if (v.getId() == R.id.btnTakeVideo) {
            displayImage.setImageDrawable(null);
            result = null;
            videoUri = null;
            imageUri = null;
            takeVideo();

        }




        if (v.getId() == R.id.btnSendFeedback) {
            if(validetData())
                sendRequest();
        }


    }

    public void sendRequest() {
        String feedback = feedbackText.getText().toString();
        String rating = String.valueOf(ratingBar.getRating());

        String multimediaUri = "";
        if(videoUri != null)
            multimediaUri = getRealPathFromURI(this,videoUri);
        else if(imageUri != null)
            multimediaUri = getRealPathFromURI(this, imageUri);


        String[] userLocation = getUserLocation();
        String lat = userLocation[0];
        String lon = userLocation[1];
        String username = getUsername();



        Log.d("adsadasdratsad", feedback);
        Log.d("adsadasdratsad", lat);
        Log.d("adsadasdratsad", lon);
        Log.d("adsadasdratsad", username);
        Log.d("adsadasdratsad", clickedProjectId);

        presenter.sendFeedback(feedback, rating, lat, lon, username,multimediaUri, clickedProjectId);
    }



    private File createImageFile(Uri mUri) throws IOException {
        // Create the Image File name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName, // Prefix
                ".jpg", // Suffix
                storageDir // Directory
        );

        // Save the file, path for ACTION_VIEW intents
        //mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mUri = Uri.fromFile(image);

        return image;
    }

    public void takePicture() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(takePictureIntent,
                CAMERA_PIC_REQUEST);

    }

    public void takeVideo() {
        Intent takeVideoIntetn = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(takeVideoIntetn, CAMERA_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_PIC_REQUEST:
                if (resultCode == RESULT_OK) {

                    Bundle extras = data.getExtras();
                    Bitmap thumbnail = (Bitmap) extras.get("data");
                    imageUri = data.getData();
                    //Toast.makeText(this,getRealPathFromURI(this,videoUri1),Toast.LENGTH_SHORT).show();
                    result = new BitmapDrawable(thumbnail);

                    displayImage.setImageDrawable(result);
                    //displayImage.setImageBitmap(thumbnail);
                    //    displayImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    break;
                }
            case CAMERA_VIDEO_REQUEST:
                if (resultCode == RESULT_OK) {
                    videoUri = data.getData();
                    //Toast.makeText(this,getRealPathFromURI(this,videoUri),Toast.LENGTH_LONG).show();
                    Bitmap thumb = ThumbnailUtils.createVideoThumbnail(getRealPathFromURI(this, videoUri),
                            MediaStore.Images.Thumbnails.MINI_KIND);
                    BitmapDrawable bt = new BitmapDrawable(thumb);
                    displayImage.setImageDrawable(bt);
                    break;
                }
        }


    }

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //outState.putAll(extras);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        //extras.putAll(savedInstanceState);
    }

    private boolean validetData() {

        if (ratingBar.getRating() == 0) {
            Toast.makeText(this, "Rating not set", Toast.LENGTH_SHORT).show();
            return false;
        }
        String testFeedback = feedbackText.getText().toString();
        if (testFeedback.matches("")) {
            Toast.makeText(this, "Feedback is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        String uriFound = "";
        try {
            if(videoUri != null){
                uriFound = videoUri.toString();
                Toast.makeText(this, uriFound, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null && uriFound == "") {
            Toast.makeText(this, "You must upload a picture or a video", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onSuccess(String response) {
        Log.d("poslano", response);
        Toast.makeText(this, "Thank you for your feed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String response) {
        Log.d("error", response);
        Toast.makeText(this, "Failed to send feed", Toast.LENGTH_LONG).show();
    }

    public String[] getUserLocation(){
        String[] coordinates = new String[2];
        boolean gpsEnabled = false;
        boolean networdEnabled = false;
        LocationManager lm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        networdEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Location net_loc = null, gps_loc = null;
        if (gpsEnabled){
            gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(gps_loc != null){
                double lat = gps_loc.getLatitude();
                double lonngi = gps_loc.getLongitude();
                String stringLat = String.valueOf(lat);
                String stringLong = String.valueOf(lonngi);
                coordinates[0] = stringLat;
                coordinates[1] = stringLong;
                return coordinates;
            }

            else{
                coordinates[0] = "Unavalible";
                coordinates[1] = "Unavalible";
                return coordinates;
            }

        }

         if(networdEnabled){
            net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(net_loc != null){
                double lat = gps_loc.getLatitude();
                double lonngi = gps_loc.getLongitude();
                String stringLat = String.valueOf(lat);
                String stringLong = String.valueOf(lonngi);
                coordinates[0] = stringLat;
                coordinates[1] = stringLong;
                return coordinates;
            }

             else{
                coordinates[0] = "Unavalible";
                coordinates[1] = "Unavalible";
                return coordinates;
            }

        }

        else {
             coordinates[0] = "Unavalible";
             coordinates[1] = "Unavalible";
             return coordinates;
        }
    }

    public String getUsername(){
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(Constants.SUCCESS_LOGIN, "");
    }
}
