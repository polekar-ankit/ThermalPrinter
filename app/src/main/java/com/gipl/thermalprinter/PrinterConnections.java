package com.gipl.thermalprinter;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

import androidx.annotation.Nullable;

import com.gipl.escposprint.connection.bluetooth.BluetoothConnection;
import com.gipl.escposprint.connection.bluetooth.BluetoothConnections;

import java.util.ArrayList;

class PrinterConnections extends BluetoothConnections {
    //    /**
    //     * Easy way to get the first bluetooth printer paired / connected.
    //     *
    //     * @return a EscPosPrinterCommands instance
    //     */
    /**
     * Get a list of bluetooth printers.
     *
     * @return an array of EscPosPrinterCommands
     */
    @Nullable
    @Override
    @SuppressLint("MissingPermission")
    public BluetoothConnection[] getList() {
        BluetoothConnection[] bluetoothDevicesList = super.getList();
        ArrayList<BluetoothConnection> printersTmp = new ArrayList<BluetoothConnection>();
        if (bluetoothDevicesList != null) {
            for (BluetoothConnection bluetoothConnection : bluetoothDevicesList) {
                BluetoothDevice device = bluetoothConnection.getDevice();
                int majDeviceCl = device.getBluetoothClass().getMajorDeviceClass();
                int deviceCl = device.getBluetoothClass().getDeviceClass();
                if ((majDeviceCl == BluetoothClass.Device.Major.IMAGING ||
                        majDeviceCl == BluetoothClass.Device.Major.UNCATEGORIZED) &&
                        (deviceCl == 1664 || deviceCl == BluetoothClass.Device.Major.IMAGING || deviceCl == BluetoothClass.Device.Major.UNCATEGORIZED
                        ) || device.getName().equals("InnerPrinter")
                ) {
                    printersTmp.add(new BluetoothConnection(device));
                }

            }
//            for (bluetoothConnection in bluetoothDevicesList) {
//                val device = bluetoothConnection.device
//                val majDeviceCl = device.bluetoothClass.majorDeviceClass
//                val deviceCl = device.bluetoothClass.deviceClass
//                if ((majDeviceCl == BluetoothClass.Device.Major.IMAGING ||
//                        majDeviceCl == BluetoothClass.Device.Major.UNCATEGORIZED) &&
//                        (deviceCl == 1664 || deviceCl == BluetoothClass.Device.Major.IMAGING || deviceCl == BluetoothClass.Device.Major.UNCATEGORIZED
//                        ) || device.name == "InnerPrinter"
//                ) {
//                    printersTmp.add(BluetoothConnection(device))
//                }
//            }

//            val bluetoothPrinters = arrayOfNulls<BluetoothConnection>(printersTmp.size)

            return printersTmp.toArray(new BluetoothConnection[printersTmp.size()]);
        }
        return super.getList();
    }
}