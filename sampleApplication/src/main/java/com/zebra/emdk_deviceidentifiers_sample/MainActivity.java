package com.zebra.emdk_deviceidentifiers_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements ISerialNumberIMEIObserver {

    public static final String TAG = "DeviceID";

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
     }

    @Override
    protected void onResume() {
        super.onResume();
        updateTextViewContent(tvIMEI, ((SNIMEIApplication)getApplication()).getIMEI());
        updateTextViewContent(tvSerialNumber,((SNIMEIApplication)getApplication()).getSerialNumber());
    }

    protected void addMessageToStatusText(String message)
    {
        Log.d(TAG, message);
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

    @Override
    public void onSerialNumberUpdate(String serialNumber) {
        updateTextViewContent(tvSerialNumber, serialNumber);
    }

    @Override
    public void onIMEINumberUpdate(String imeiNumber) {
        updateTextViewContent(tvIMEI, imeiNumber);
    }

    @Override
    public void onErrorMessage(String message) {
        addMessageToStatusText("Error: " + message);
    }

    @Override
    public void onDebugMessage(String message) {
        addMessageToStatusText("Debug: " + message);
    }
}