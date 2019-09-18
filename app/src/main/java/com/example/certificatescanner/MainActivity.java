package com.example.certificatescanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView barcodeResult;
    String bhai_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barcodeResult = findViewById(R.id.barcode_content);
        isCameraPermissionGranted();

    }

    private boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();

                return true;
            } else {
                Toast.makeText(this, "PERMISSION RE-GRANTED", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                return false;

            }

        }
        return true;

    }




    public void scanBarcode(View view){
        Intent intent = new Intent(MainActivity.this,BarcodeCaptureActivity.class);
        startActivityForResult(intent,0);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == 0)
        {
            if (resultCode == CommonStatusCodes.SUCCESS)
            {
                if (data !=null)
                {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    bhai_value = barcode.displayValue;
                    barcodeResult.setText("Barcode Value::"+ bhai_value);
                    Toast.makeText(this, "bhai_value", Toast.LENGTH_SHORT).show();

                }
                else{
                     barcodeResult.setText("No Barcode Captured");
                }
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }


    }
}














