package com.example.kanta.feedbackapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by boogiepop on 23.09.15..
 */
public class SendFeedback extends AppCompatActivity implements View.OnClickListener {

    Button takePicture, takeVideo;
    ImageView displayImage;
    private static String root = null;
    private static String imageFolderPath = null;
    private String imageName = null;
    private static Uri fileUri = null;
    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int CAMERA_VIDEO_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfeedback);
        takePicture = (Button)findViewById(R.id.btnTakePicture);
        displayImage = (ImageView) findViewById(R.id.displayPicture);
        takeVideo = (Button) findViewById(R.id.btnTakeVideo);
        takeVideo.setOnClickListener(this);
        takePicture.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTakePicture){
            displayImage.setImageDrawable(null);
            takePicture();
        }

        if(v.getId() == R.id.btnTakeVideo){
            displayImage.setImageDrawable(null);
            takeVideo();

        }
    }

    public void takePicture(){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(takePictureIntent,
                CAMERA_PIC_REQUEST);

    }

    public void takeVideo(){
        Intent takeVideoIntetn = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(takeVideoIntetn, CAMERA_VIDEO_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case CAMERA_PIC_REQUEST:
                if(resultCode==RESULT_OK){

                    Bundle extras = data.getExtras();
                    Bitmap thumbnail = (Bitmap) extras.get("data");

                    BitmapDrawable result = new BitmapDrawable(thumbnail);

                    displayImage.setImageDrawable(result);
                    //displayImage.setImageBitmap(thumbnail);
                    //    displayImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    break;
                }
            case CAMERA_VIDEO_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri videoUri = data.getData();
                    Bitmap thumb = ThumbnailUtils.createVideoThumbnail(getRealPathFromURI(this, videoUri),
                            MediaStore.Images.Thumbnails.MINI_KIND);
                    BitmapDrawable bt = new BitmapDrawable(thumb);
                    displayImage.setImageDrawable(bt);
                    break;
                }
        }


    }

    private int dpToPx(int dp)
    {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }



    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
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
}
