package com.zebra.emdk_deviceidentifiers_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zebra.deviceidentifierswrapper.DIHelper;
import com.zebra.deviceidentifierswrapper.IDIResultCallbacks;

/**
 * Device Identifiers Sample
 *
 * Original Device Identifier Sample Code:
 *          - Darryn Campbell
 *          - https://github.com/darryncampbell/EMDK-DeviceIdentifiers-Sample
 *
 *  Wrapper Code:
 *          - Trudu Laurent
 *          - https://github.com/ltrudu/DeviceIdentifiersWrapper-Sample
 *
 *  (c) Zebra 2020
 */

public class MainActivity extends AppCompatActivity {

    String TAG = "DeviceID";

    static  String status = "";
    TextView tvStatus;
    TextView tvSerialNumber;
    TextView tvIMEI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tv_status);
        tvSerialNumber = (TextView) findViewById(R.id.txtSerialNumber);
        tvIMEI = (TextView) findViewById(R.id.txtImei);

        // The call is asynchronous, since we may have to register the app to
        // allow calling device identifier service, we don't wan't to get two
        // concurrent calls to it, so we will ask for the IMEI number only at
        // the end of the getSerialNumber method call (success or error)
        getSerialNumber();
     }

     private void getSerialNumber()
     {
         DIHelper.getSerialNumber(this, new IDIResultCallbacks() {
             @Override
             public void onSuccess(String message) {
                 updateTextViewContent(tvSerialNumber, message);
                 // We got the serial number, let's try the IMEI number
                 getIMEINumber();
             }

             @Override
             public void onError(String message) {
                 addMessageToStatusText(message);
                 // We had an error, but we like to play, so we try the IMEI Number
                 getIMEINumber();
             }

             @Override
             public void onDebugStatus(String message) {
                 addMessageToStatusText(message);
             }
         });
     }

    private void getIMEINumber()
    {
        DIHelper.getIMEINumber(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got an IMEI number, let's update the text view
                updateTextViewContent(tvIMEI, message);
            }

            @Override
            public void onError(String message) {
                addMessageToStatusText(message);
            }

            @Override
            public void onDebugStatus(String message) {
                addMessageToStatusText(message);
            }
        });
    }

    private void addMessageToStatusText(String message)
    {
        status += message + "\n";
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tvStatus.setText(status);
            }
        });
    }

    private void updateTextViewContent(final TextView tv, final String text)
    {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tv.setText(text);
            }
        });
    }
}