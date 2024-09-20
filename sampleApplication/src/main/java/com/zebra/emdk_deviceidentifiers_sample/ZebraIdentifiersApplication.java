package com.zebra.emdk_deviceidentifiers_sample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zebra.deviceidentifierswrapper.DIHelper;
import com.zebra.deviceidentifierswrapper.IDIResultCallbacks;

public class ZebraIdentifiersApplication extends Application {
    private String sIMEI = null;
    private String sSerialNumber = null;
    private String sBtMacAddress = null;
    private String sProductModel = null;
    private String sIdentityDeviceID = null;
    private String sWIFIMacAddress = null;
    private String sWifiAPMAcAddress = null;
    private String sWifiSSID = null;
    private String sEthernetMacAddress = null;


    private IZebraIdentifiersObserver iZebraIdentifiersObserver = null;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if(activity != null && activity instanceof IZebraIdentifiersObserver)
                    iZebraIdentifiersObserver = (IZebraIdentifiersObserver)activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if(iZebraIdentifiersObserver == activity)
                    iZebraIdentifiersObserver = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if(iZebraIdentifiersObserver == activity)
                    iZebraIdentifiersObserver = null;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if(iZebraIdentifiersObserver == activity)
                    iZebraIdentifiersObserver = null;
            }
        });
        retrieveSerialNumber();
    }

    public String getIMEI()
    {
        return sIMEI;
    }

    public String getSerialNumber()
    {
        return sSerialNumber;
    }

    public String getBtMacAddress() { return sBtMacAddress; }

    public String getProductModel () { return sProductModel; }

    public String getIdentityDeviceID () { return sIdentityDeviceID; }

    public String getWifiMacAddress () { return sWIFIMacAddress; }

    public String getWifiAPMacAddress () { return sWifiAPMAcAddress; }

    public String getWifiSSID () { return sWifiSSID; }

    public String getEthernetMacAddress () { return sEthernetMacAddress; }

    private void retrieveSerialNumber()
    {
        DIHelper.getSerialNumber(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                sSerialNumber = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onSerialNumberUpdate(message);
                // We got the serial number, let's try the IMEI number
                retrieveIMEINumber();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                // We had an error, but we like to play, so we try the IMEI Number
                retrieveIMEINumber();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveIMEINumber()
    {
        DIHelper.getIMEINumber(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got an IMEI number, let's update the text view
                sIMEI = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onIMEINumberUpdate(message);
                retrieveBTMacAddress();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveBTMacAddress();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveBTMacAddress()
    {
        DIHelper.getBtMacAddress(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sBtMacAddress = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onBTMacAddressUpdate(message);
                retrieveProductModel();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveProductModel();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveProductModel()
    {
        DIHelper.getProductModel(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sProductModel = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onProductModelUpdate(message);
                retrieveIdentityDeviceID();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveIdentityDeviceID();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveIdentityDeviceID()
    {
        DIHelper.getIdentityDeviceID(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sIdentityDeviceID = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onIdentityDeviceIDUpdate(message);
                retrieveWifiMacAddress();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveWifiMacAddress();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveWifiMacAddress()
    {
        DIHelper.getWifiMacAddress(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sWIFIMacAddress = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onWifiMacAddressUpdate(message);
                retrieveWifiAPMacAddress();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveWifiAPMacAddress();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveWifiAPMacAddress()
    {
        DIHelper.getWifiAPMacAddress(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sWifiAPMAcAddress = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onWifiAPMacAddressUpdate(message);
                retrieveWifiSSID();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveWifiSSID();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveWifiSSID()
    {
        DIHelper.getWifiSSID(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sWifiSSID = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onWifiSSIDUpdate(message);
                retrieveEthernetMacAddress();
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
                retrieveEthernetMacAddress();
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

    private void retrieveEthernetMacAddress()
    {
        DIHelper.getEthernetMacAddress(this, new IDIResultCallbacks() {
            @Override
            public void onSuccess(String message) {
                // We've got the bt mac address, let's update the text view
                sEthernetMacAddress = message;
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onEthernetMacAddressUpdate(message);
            }

            @Override
            public void onError(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onErrorMessage(message);
            }

            @Override
            public void onDebugStatus(String message) {
                if(iZebraIdentifiersObserver != null)
                    iZebraIdentifiersObserver.onDebugMessage(message);
            }
        });
    }

}
