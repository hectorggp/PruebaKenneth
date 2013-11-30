package com.example.pruebakeneth.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

//more info:
//	http://developer.android.com/training/monitoring-device-state/battery-monitoring.html

public class BatteryChargeStateUtils {

	private int status, chargePlug;
	private boolean isCharging, usbCharge, acCharge;

	public BatteryChargeStateUtils(Context context) {
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.registerReceiver(null, ifilter);

		// Are we charging / charged?
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
				|| status == BatteryManager.BATTERY_STATUS_FULL;

		// How are we charging?
		int chargePlug = batteryStatus.getIntExtra(
				BatteryManager.EXTRA_PLUGGED, -1);
		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

		this.status = status;
		this.chargePlug = chargePlug;
		this.isCharging = isCharging;
		this.usbCharge = usbCharge;
		this.acCharge = acCharge;
	}

	public int getStatus() {
		return status;
	}

	public int getChargePlug() {
		return chargePlug;
	}

	public boolean isCharging() {
		return isCharging;
	}

	public boolean isUsbCharge() {
		return usbCharge;
	}

	public boolean isAcCharge() {
		return acCharge;
	}

}
