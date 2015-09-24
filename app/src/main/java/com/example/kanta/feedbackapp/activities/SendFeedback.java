package com.example.kanta.feedbackapp.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.view.FeedbackView;

/**
 * Created by boogiepop on 23.09.15..
 */
public class SendFeedback extends AppCompatActivity implements View.OnClickListener, FeedbackView {

    Button takePicture, takeVideo, btnSend;
    EditText feedbackText;
    ImageView displayImage;
    RatingBar ratingBar;
    BitmapDrawable result;
    Uri videoUri;
    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int CAMERA_VIDEO_REQUEST = 100;

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
        btnSend.setOnClickListener(this);
        takeVideo.setOnClickListener(this);
        takePicture.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTakePicture) {
            displayImage.setImageDrawable(null);
            result = null;
            videoUri = null;
            takePicture();
        }

        if (v.getId() == R.id.btnTakeVideo) {
            displayImage.setImageDrawable(null);
            result = null;
            videoUri = null;
            takeVideo();

        }

        if (v.getId() == R.id.btnSendFeedback) {
            if(validetData())
                sendRequest();
        }
    }

    public void sendRequest() {

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

                    result = new BitmapDrawable(thumbnail);

                    displayImage.setImageDrawable(result);
                    //displayImage.setImageBitmap(thumbnail);
                    //    displayImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    break;
                }
            case CAMERA_VIDEO_REQUEST:
                if (resultCode == RESULT_OK) {
                    videoUri = data.getData();
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
            uriFound = videoUri.toString();
            Toast.makeText(this, uriFound, Toast.LENGTH_SHORT).show();
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
    public void onSuccess() {
        Toast.makeText(this, "Thank you for your feed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure() {

    }

    public void getUserLocation(){

    }
}
