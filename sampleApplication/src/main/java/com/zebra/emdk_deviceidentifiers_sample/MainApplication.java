package com.zebra.emdk_deviceidentifiers_sample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zebra.deviceidentifierswrapper.DIHelper;
import com.zebra.deviceidentifierswrapper.IDIResultCallbacks;

/**
 * - You can call yourself the DIHelper.getSerialNumber and DIHelper.getIMEINumber inside
 * your activity or application.
 * - Or you can inherit from SNIMEIApplication in your own code
 * to get Serial Number and EMEI Number, and implements
 * the ISerialNumberIMEIObserver interface in your Activity.
 */
public class MainApplication extends SNIMEIApplication {

}
