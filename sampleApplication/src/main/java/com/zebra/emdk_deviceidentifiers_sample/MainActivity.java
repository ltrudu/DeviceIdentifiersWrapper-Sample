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

 *  TODO: inherit from ZebraIdentifiersApplication and implements IZebraIdentifiersObserver
 *  TODO: or do your own call inside your Activity ;)
 *
 *  (c) Zebra 2020
 */

public class MainActivity extends AppCompatActivity implements IZebraIdentifiersObserver {

    public static final String TAG = "DeviceID";

    static  String status = "";
    TextView tvStatus;
    TextView tvSerialNumber;
    TextView tvIMEI;
    TextView tvBtMacAddress;
    TextView tvProductModel;
    TextView tvIdentityDeviceID;
    TextView tvWifiMacAddress;
    TextView tvWifiAPMacAddress;
    TextView tvWifiSSID;
    TextView tvEthernetMacAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tv_status);
        tvSerialNumber = (TextView) findViewById(R.id.txtSerialNumber);
        tvIMEI = (TextView) findViewById(R.id.txtImei);
        tvBtMacAddress = (TextView) findViewById(R.id.txtBtMacAddress);
        tvProductModel = (TextView) findViewById(R.id.txtProductModel);
        tvIdentityDeviceID = (TextView) findViewById(R.id.txtIdentityDeviceID);
        tvWifiMacAddress = (TextView) findViewById(R.id.txtWifiMacAddress);
        tvWifiAPMacAddress = (TextView) findViewById(R.id.txtWifiAPMacAddress);
        tvWifiSSID = (TextView) findViewById(R.id.txtWifiSSID);
        tvEthernetMacAddress = (TextView) findViewById(R.id.txtEthernetMacAddress);
     }

    @Override
    protected void onResume() {
        super.onResume();
        updateTextViewContent(tvIMEI, ((ZebraIdentifiersApplication)getApplication()).getIMEI());
        updateTextViewContent(tvSerialNumber,((ZebraIdentifiersApplication)getApplication()).getSerialNumber());
        updateTextViewContent(tvBtMacAddress,((ZebraIdentifiersApplication)getApplication()).getBtMacAddress());
        updateTextViewContent(tvProductModel,((ZebraIdentifiersApplication)getApplication()).getProductModel());
        updateTextViewContent(tvIdentityDeviceID,((ZebraIdentifiersApplication)getApplication()).getIdentityDeviceID());
        updateTextViewContent(tvWifiMacAddress,((ZebraIdentifiersApplication)getApplication()).getWifiMacAddress());
        updateTextViewContent(tvWifiAPMacAddress,((ZebraIdentifiersApplication)getApplication()).getWifiAPMacAddress());
        updateTextViewContent(tvWifiSSID,((ZebraIdentifiersApplication)getApplication()).getWifiSSID());
        updateTextViewContent(tvEthernetMacAddress,((ZebraIdentifiersApplication)getApplication()).getEthernetMacAddress());
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
    public void onBTMacAddressUpdate(String btMacAddress) { updateTextViewContent(tvBtMacAddress, btMacAddress); }

    @Override
    public void onProductModelUpdate(String productModel) {
        updateTextViewContent(tvProductModel, productModel);
    }

    @Override
    public void onIdentityDeviceIDUpdate(String identityDeviceID) {
        updateTextViewContent(tvIdentityDeviceID, identityDeviceID);
    }

    @Override
    public void onWifiMacAddressUpdate(String wifiMacAddress) {
        updateTextViewContent(tvWifiMacAddress, wifiMacAddress);
    }

    @Override
    public void onWifiAPMacAddressUpdate(String wifiAPMacAddress) {
        updateTextViewContent(tvWifiAPMacAddress, wifiAPMacAddress);
    }

    @Override
    public void onWifiSSIDUpdate(String wifiSSID) {
        updateTextViewContent(tvWifiSSID, wifiSSID);
    }

    @Override
    public void onEthernetMacAddressUpdate(String ethernetMacAddress) {
        updateTextViewContent(tvEthernetMacAddress, ethernetMacAddress);
    }

    @Override
    public void onErrorMessage(String message) {
        addMessageToStatusText("Error: " + message);
    }

    @Override
    public void onDebugMessage(String message) {
        // We do not print the processing message event because it contains the whole XML
        // making the status unreadable. Instead, we print it in the logCat
        if(message.contains("Processing profile") == false)
            addMessageToStatusText("Debug: " + message);
        else
            Log.d(TAG, message);
    }
}