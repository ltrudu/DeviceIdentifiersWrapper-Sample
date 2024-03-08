package com.zebra.emdk_deviceidentifiers_sample;

import android.content.Context;

import com.zebra.deviceidentifierswrapper.DIHelper;
import com.zebra.deviceidentifierswrapper.IDIResultCallbacks;

/**
 * - You can call yourself the DIHelper.getSerialNumber and DIHelper.getIMEINumber inside
 * your activity or application.
 * - Or you can inherit from ZebraIdentifiersApplication in your own code
 * to get Serial Number and EMEI Number, and implements
 * the IZebraIdentifiersObserver interface in your Activity.
 */
public class MainApplication extends ZebraIdentifiersApplication {
    
}
