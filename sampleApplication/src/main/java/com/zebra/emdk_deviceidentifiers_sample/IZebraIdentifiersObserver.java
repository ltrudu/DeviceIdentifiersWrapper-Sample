package com.zebra.emdk_deviceidentifiers_sample;

public interface IZebraIdentifiersObserver {

    /*
    Called when the serial number has been retrieved for the first time
    This has to be implemented in any activity/service that needs to use the serial number
     */
    void onSerialNumberUpdate(String serialNumber);
    void onIMEINumberUpdate(String imeiNumber);

    void onBTMacAddressUpdate(String btMacAddress);
    void onErrorMessage(String message);
    void onDebugMessage(String message);

}
