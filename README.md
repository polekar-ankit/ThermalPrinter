### This Library is taken from  https://github.com/DantSu/ESCPOS-ThermalPrinter-Android and made changes according to my requirement.

[![](https://jitpack.io/v/polekar-ankit/ThermalPrinter.svg)](https://jitpack.io/#polekar-ankit/ThermalPrinter)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Android library for ESC/POS Thermal Printer

Useful library to help Android developers to print with (Bluetooth, TCP, USB) ESC/POS thermal printers.

## ✨ Supporting

⭐ Star this repository to support this project. You will contribute to increase the visibility of this library 🙂

## Table of contents

- [Android version](#android-version)
- [Tested printers](#tested-printers)
- [Test It !](#test-it-)
- [Installation](#installation)
- [Bluetooth](#bluetooth)
  - [Bluetooth permission](#bluetooth-permission)
  - [Bluetooth code example](#bluetooth-code-example)
- [TCP](#tcp)
  - [TCP permission](#tcp-permission)
  - [TCP code example](#tcp-code-example)
- [USB](#usb)
  - [USB permission](#usb-permission)
  - [USB code example](#usb-code-example)
- [Charset encoding](#charset-encoding)
- [Formatted text : syntax guide](#formatted-text--syntax-guide)
- [Class list](#class-list)
  - [BluetoothPrintersConnections](#user-content-class--comdantsuescposprinterconnectionbluetoothbluetoothprintersconnections)
  - [UsbPrintersConnections](#user-content-class--comdantsuescposprinterconnectionusbusbprintersconnections)
  - [EscPosPrinter](#user-content-class--comdantsuescposprinterescposprinter)
  - [PrinterTextParserImg](#user-content-class--comdantsuescposprintertextparserprintertextparserimg)
  - [EscPosCharsetEncoding](#user-content-class--comdantsuescposprinterescposcharsetencoding)
- [Projects using this library](#projects-using-this-library)
- [Contributing](#contributing)


## Android version

Developed for SDK version 16 (Android 4.1 Jelly Bean) and above.


## Tested printers

1. [HOIN HOP H58 Thermal Printer ESC/POS](https://www.gearbest.com/printers/pp_662658.html).
2. [XPRINTER XP-P300](https://xprinter.vn/xprinter-xp-p300-may-in-hoa-don-di-dong-bluetooth/).
3. [MUNBYN IMP001](https://www.munbyn.com/collections/portable-receipt-printer/products/58mm-bluetooth-thermal-printer-imp001).
4. [JP-Q2 POS Terminal PDA](https://www.aliexpress.com/item/32971775060.html) (Embedded printer is configured as Bluetooth device)
5. [MUNBYN ITPP047](https://www.munbyn.com/products/munbyn-itpp047-wifi-thermal-printer) (tested over USB)

## Test it !

To test this library, it's pretty simple !

- Create a directory and open a terminal inside
- Run `git clone https://github.com/DantSu/ESCPOS-ThermalPrinter-Android.git .`
- Open the directory with Android Studio
- Test it !

## Installation

**Step 1.** Add the repository to your build file. Add it in your root `/settings.gradle.kts` :

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven { url = uri("https://jitpack.io") }
        maven{
            url = uri("https://maven.pkg.github.com/polekar-ankit/ThermalPrinter")
            credentials {
                username = "polekar-ankit"
                password = "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"
            }
        }
    }
}
```

**Step 2.** Add the dependency in `/app/build.gradle` :

```
dependencies {
	        implementation 'com.gipl.escposprint:escposprint:4.1.1'
	}
```

## Bluetooth

### Bluetooth permission

Be sure to have `<uses-permission android:name="android.permission.BLUETOOTH" />`, `<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />`, `<uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />`, `<uses-permission android:name="android.permission.BLUETOOTH_SCAN" />` in your `AndroidMenifest.xml`.

Also, you have to check the bluetooth permission in your app like this :

```java
if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, MainActivity.PERMISSION_BLUETOOTH);
} else if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, MainActivity.PERMISSION_BLUETOOTH_ADMIN);
} else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, MainActivity.PERMISSION_BLUETOOTH_CONNECT);
} else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, MainActivity.PERMISSION_BLUETOOTH_SCAN);
} else {
    // Your code HERE
}
```

### Bluetooth code example

The code below is an example to write in your activity :

```java
EscPosPrinter printer = new EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32);
printer
    .printFormattedText(
        "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer, this.getApplicationContext().getResources().getDrawableForDensity(R.drawable.logo, DisplayMetrics.DENSITY_MEDIUM))+"</img>\n" +
        "[L]\n" +
        "[C]<u><font size='big'>ORDER N°045</font></u>\n" +
        "[L]\n" +
        "[C]================================\n" +
        "[L]\n" +
        "[L]<b>BEAUTIFUL SHIRT</b>[R]9.99e\n" +
        "[L]  + Size : S\n" +
        "[L]\n" +
        "[L]<b>AWESOME HAT</b>[R]24.99e\n" +
        "[L]  + Size : 57/58\n" +
        "[L]\n" +
        "[C]--------------------------------\n" +
        "[R]TOTAL PRICE :[R]34.98e\n" +
        "[R]TAX :[R]4.23e\n" +
        "[L]\n" +
        "[C]================================\n" +
        "[L]\n" +
        "[L]<font size='tall'>Customer :</font>\n" +
        "[L]Raymond DUPONT\n" +
        "[L]5 rue des girafes\n" +
        "[L]31547 PERPETES\n" +
        "[L]Tel : +33801201456\n" +
        "[L]\n" +
        "[C]<barcode type='ean13' height='10'>831254784551</barcode>\n" +
        "[C]<qrcode size='20'>http://www.developpeur-web.dantsu.com/</qrcode>"
    );
```

Below a picture of the receipt printed with the code above :

![Example of a printed receipt](http://www.developpeur-web.dantsu.com/files/librairie/receipt-thermal-printer.png?1)

## TCP

### TCP permission

Be sure to have `<uses-permission android:name="android.permission.INTERNET"/>` in your `AndroidMenifest.xml`.

### TCP code example

The code below is an example to write in your activity :

```java
new Thread(new Runnable() {
    public void run() {
        try {
            EscPosPrinter printer = new EscPosPrinter(new TcpConnection("192.168.1.3", 9300, 15), 203, 48f, 32);
            printer
                .printFormattedText(
                    "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer, getApplicationContext().getResources().getDrawableForDensity(R.drawable.logo, DisplayMetrics.DENSITY_MEDIUM)) + "</img>\n" +
                    "[L]\n" +
                    "[C]<u><font size='big'>ORDER N°045</font></u>\n" +
                    "[L]\n" +
                    "[C]================================\n" +
                    "[L]\n" +
                    "[L]<b>BEAUTIFUL SHIRT</b>[R]9.99e\n" +
                    "[L]  + Size : S\n" +
                    "[L]\n" +
                    "[L]<b>AWESOME HAT</b>[R]24.99e\n" +
                    "[L]  + Size : 57/58\n" +
                    "[L]\n" +
                    "[C]--------------------------------\n" +
                    "[R]TOTAL PRICE :[R]34.98e\n" +
                    "[R]TAX :[R]4.23e\n" +
                    "[L]\n" +
                    "[C]================================\n" +
                    "[L]\n" +
                    "[L]<font size='tall'>Customer :</font>\n" +
                    "[L]Raymond DUPONT\n" +
                    "[L]5 rue des girafes\n" +
                    "[L]31547 PERPETES\n" +
                    "[L]Tel : +33801201456\n" +
                    "[L]\n" +
                    "[C]<barcode type='ean13' height='10'>831254784551</barcode>\n" +
                    "[C]<qrcode size='20'>http://www.developpeur-web.dantsu.com/</qrcode>"
                );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}).start();
```

## USB

### USB permission

Be sure to have `<uses-feature android:name="android.hardware.usb.host" />` in your `AndroidMenifest.xml`.

You have to check the USB permission in your app like this :

```java
private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (MainActivity.ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    if (usbManager != null && usbDevice != null) {
                        // YOUR PRINT CODE HERE
                    }
                }
            }
        }
    }
};

public void printUsb() {
    UsbConnection usbConnection = UsbPrintersConnections.selectFirstConnected(this);
    UsbManager usbManager = (UsbManager) this.getSystemService(Context.USB_SERVICE);
    if (usbConnection != null && usbManager != null) {
        PendingIntent permissionIntent = PendingIntent.getBroadcast(
            this,
            0,
            new Intent(MainActivity.ACTION_USB_PERMISSION),
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : 0
        );
        IntentFilter filter = new IntentFilter(MainActivity.ACTION_USB_PERMISSION);
        registerReceiver(this.usbReceiver, filter);
        usbManager.requestPermission(usbConnection.getDevice(), permissionIntent);
    }
}
```

### USB code example

The code below is an example to write in your activity :

```java
EscPosPrinter printer = new EscPosPrinter(new UsbConnection(usbManager, usbDevice), 203, 48f, 32);
printer
    .printFormattedText(
        "[C]<img>" + PrinterTextParserImg.bitmapToHexadecimalString(printer, this.getApplicationContext().getResources().getDrawableForDensity(R.drawable.logo, DisplayMetrics.DENSITY_MEDIUM))+"</img>\n" +
        "[L]\n" +
        "[C]<u><font size='big'>ORDER N°045</font></u>\n" +
        "[L]\n" +
        "[C]================================\n" +
        "[L]\n" +
        "[L]<b>BEAUTIFUL SHIRT</b>[R]9.99e\n" +
        "[L]  + Size : S\n" +
        "[L]\n" +
        "[L]<b>AWESOME HAT</b>[R]24.99e\n" +
        "[L]  + Size : 57/58\n" +
        "[L]\n" +
        "[C]--------------------------------\n" +
        "[R]TOTAL PRICE :[R]34.98e\n" +
        "[R]TAX :[R]4.23e\n" +
        "[L]\n" +
        "[C]================================\n" +
        "[L]\n" +
        "[L]<font size='tall'>Customer :</font>\n" +
        "[L]Raymond DUPONT\n" +
        "[L]5 rue des girafes\n" +
        "[L]31547 PERPETES\n" +
        "[L]Tel : +33801201456\n" +
        "[L]\n" +
        "[C]<barcode type='ean13' height='10'>831254784551</barcode>\n" +
        "[C]<qrcode size='20'>http://www.developpeur-web.dantsu.com/</qrcode>"
    );
```


## Charset encoding

To change charset encoding of the printer, use `EscPosCharsetEncoding` class :

```java
EscPosPrinter printer = new EscPosPrinter(deviceConnection, 203, 48f, 32, new EscPosCharsetEncoding("windows-1252", 16));
```

`escPosCharsetId` may change with printer model.
[Follow this link to find `escPosCharsetId` that works with many printers](https://www.epson-biz.com/modules/ref_escpos/index.php?content_id=32)

## Formatted text : syntax guide

### New line

Use `\n` to create a new line of text.

### Text alignment and column separation

Add an alignment tag on a same line of text implicitly create a new column.

Column alignment tags :

- `[L]` : left side alignment
- `[C]` : center alignment
- `[R]` : right side alignment

Example :

- `[L]Some text` : One column aligned to left
- `[C]Some text` : One column aligned to center
- `[R]Some text` : One column aligned to right
- `[L]Some text[L]Some other text` : Two columns aligned to left. `Some other text` starts in the center of the paper.
- `[L]Some text[R]Some other text` : Two columns, first aligned to left, second aligned to right. `Some other text` is printed at the right of paper.
- `[L]Some[R]text[R]here` : Three columns.
- `[L][R]text[R]here` : Three columns. The first is empty but it takes a third of the available space.

### Font

#### Size

`<font></font>` tag allows you to change the font size and color. Default size is `normal` / `black`.

- `<font size='normal'>Some text</font>` : Normal size
- `<font size='wide'>Some text</font>` : Double width of medium size
- `<font size='tall'>Some text</font>` : Double height of medium size
- `<font size='big'>Some text</font>` : Double width and height of medium size
- `<font size='big-2'>Some text</font>` : 3 x width and height
- `<font size='big-3'>Some text</font>` : 4 x width and height
- `<font size='big-4'>Some text</font>` : 5 x width and height
- `<font size='big-5'>Some text</font>` : 6 x width and height
- `<font size='big-6'>Some text</font>` : 7 x width and height

- `<font color='black'>Some text</font>` : black text - white background
- `<font color='bg-black'>Some text</font>` : white text - black background
- `<font color='red'>Some text</font>` : red text - white background (Not working on all printer)
- `<font color='bg-red'>Some text</font>` : white text - red background (Not working on all printer)

#### Bold

`<b></b>` tag allows you to change the font weight.

- `<b>Some text</b>`

#### Underline

`<u></u>` tag allows you to underline the text.

- `<u>Some text</u>` text underlined
- `<u type='double'>Some text</u>` text double-strike (Not working on all printer)

### Image

`<img></img>` tag allows you to print image. Inside the tag you need to write a hexadecimal string of an image.

Use `PrinterTextParserImg.bitmapToHexadecimalString` to convert `Drawable`, `BitmapDrawable` or `Bitmap` to hexadecimal string.

- `<img>`hexadecimal string of an image`</img>`

**⚠ WARNING ⚠** : This tag has several constraints :

- A line that contains `<img></img>` can have only one alignment tag and it must be at the beginning of the line.
- `<img>` must be directly preceded by nothing or an alignment tag (`[L][C][R]`).
- `</img>` must be directly followed by a new line `\n`.
- You can't write text on a line that contains `<img></img>`.
- Maximum height of printed image is 256px, If you want to print larger bitmap. Please refer to this solution: [#70](https://github.com/DantSu/ESCPOS-ThermalPrinter-Android/issues/70#issuecomment-714390014)

### Barcode

`<barcode></barcode>` tag allows you to print a barcode. Inside the tag you need to write the code number to print.

- `<barcode>451278452159</barcode>` : **(12 numbers)**  
Prints a EAN13 barcode (height: 10mm, width: ~70% printer width, text: displayed below).
- `<barcode type='ean8'>4512784</barcode>` : **(7 numbers)**  
Prints a EAN8 barcode (height: 10mm, width: ~70% printer width, text: displayed below).
- `<barcode type='upca' height='20'>4512784521</barcode>` : **(11 numbers)**  
Prints a UPC-A barcode (height: 20mm, width: ~70% printer width, text: displayed below).
- `<barcode type='upce' height='25' width='50' text='none'>512789</barcode>` : **(6 numbers)**  
Prints a UPC-E barcode (height: 25mm, width: ~50mm, text: hidden).
- `<barcode type='128' width='40' text='above'>DantSu</barcode>` : **(string)**  
Prints a barcode 128 (height: 10mm, width: ~40mm, text: displayed above).

**⚠ WARNING ⚠** : This tag has several constraints :

- A line that contains `<barcode></barcode>` can have only one alignment tag and it must be at the beginning of the line.
- `<barcode>` must be directly preceded by nothing or an alignment tag (`[L][C][R]`).
- `</barcode>` must be directly followed by a new line `\n`.
- You can't write text on a line that contains `<barcode></barcode>`.

### QR Code

`<qrcode></qrcode>` tag allows you to print a QR code. Inside the tag you need to write the QR code data.

- `<qrcode>http://www.developpeur-web.dantsu.com/</qrcode>` :
Prints a QR code with a width and height of 20 millimeters.
- `<qrcode size='25'>123456789</qrcode>` :
Prints a QR code with a width and height of 25 millimeters.

**⚠ WARNING ⚠** : This tag has several constraints :

- A line that contains `<qrcode></qrcode>` can have only one alignment tag and it must be at the beginning of the line.
- `<qrcode>` must be directly preceded by nothing or an alignment tag (`[L][C][R]`).
- `</qrcode>` must be directly followed by a new line `\n`.
- You can't write text on a line that contains `<qrcode></qrcode>`.

## Class list

### Class : `com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections`

#### **Static** Method : `selectFirstPaired()`
Easy way to get the first bluetooth printer paired / connected.
- **return** `BluetoothConnection`

#### Method : `getList()`
Get a list of bluetooth printers.
- **return** `BluetoothConnection[]`

⚠️ If the arrray returned by `getList()` does not contain you printer or if `selectFirstPaired()` does not return your printer. Read this issue : https://github.com/DantSu/ESCPOS-ThermalPrinter-Android/issues/80#issuecomment-729759832

### Class : `com.dantsu.escposprinter.connection.tcp.TcpConnection`

#### Constructor : `TcpConnection(String address, int port[, int timeout])`
- **param** `String address` : Targeted ip address
- **param** `int port` : Targeted tcp port
- **param** `int timeout` *(optional)* : Connection timeout (default : 30)

### Class : `com.dantsu.escposprinter.connection.usb.UsbPrintersConnections`

#### **Static** Method : `selectFirstConnected()`
Easy way to get the first USB printer connected.
- **return** `UsbConnection`

#### Method : `getList()`
Get a list of USB printers.
- **return** `UsbConnection[]`

### Class : `com.dantsu.escposprinter.EscPosPrinter`

#### Constructor : `EscPosPrinter(DeviceConnection printer, int printerDpi, float printingWidthMM, int nbrCharactersPerLine [, EscPosCharsetEncoding charsetEncoding])`
- **param** `DeviceConnection printer` : Instance of a connected printer
- **param** `int printerDpi` : DPI of the connected printer
- **param** `float printerWidthMM` : Printing width in millimeters
- **param** `int printerNbrCharactersPerLine` : The maximum number of medium sized characters that can be printed on a line.
- **param** `EscPosCharsetEncoding charsetEncoding` *(optional)* : Set the charset encoding.

#### Method : `disconnectPrinter()`
Close the connection with the printer.
- **return** `Printer` : Fluent interface

#### Method : `getNbrCharactersPerLine()`
Get the maximum number of characters that can be printed on a line.
- **return** `int`

#### Method : `getPrinterWidthMM()`
Get the printing width in millimeters
- **return** `float`

#### Method : `getPrinterDpi()`
Get the printer DPI
- **return** `int`

#### Method : `getPrinterWidthPx()`
Get the printing width in dot
- **return** `int`

#### Method : `getPrinterCharSizeWidthPx()`
Get the number of dot that a printed character contain
- **return** `int`

#### Method : `mmToPx(float mmSize)`
Convert the mmSize variable from millimeters to dot.
- **param** `float mmSize` : Distance in millimeters to be converted
- **return** `int` : Dot size of mmSize.

#### Method : `useEscAsteriskCommand(boolean enable)`
Active "ESC *" command for image printing.
- **param** `boolean enable` : true to use "ESC *", false to use "GS v 0"
- **return** `Printer` : Fluent interface

#### Method : `printFormattedText(String text)`
Print a formatted text and feed paper (20 millimeters). Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedTextAndCut(String text)`
Print a formatted text, feed paper (20 millimeters) and cut the paper. Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedText(String text, float mmFeedPaper)`
Print a formatted text and feed paper (`mmFeedPaper` millimeters). Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **param** `float mmFeedPaper` : Millimeter distance feed paper at the end.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedTextAndCut(String text, float mmFeedPaper)`
Print a formatted text, feed paper (`mmFeedPaper` millimeters) and cut the paper. Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **param** `float mmFeedPaper` : Millimeter distance feed paper at the end.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedTextAndOpenCashBox(String text, float mmFeedPaper)`
Print a formatted text, feed paper (`mmFeedPaper` millimeters), cut the paper and open the cash box. Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **param** `float mmFeedPaper` : Millimeter distance feed paper at the end.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedText(String text, int dotsFeedPaper)`
Print a formatted text and feed paper (`dotsFeedPaper` dots). Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **param** `int dotsFeedPaper` : Distance feed paper at the end.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedTextAndCut(String text, int dotsFeedPaper)`
Print a formatted text, feed paper (`dotsFeedPaper` dots) and cut the paper. Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **param** `int dotsFeedPaper` : Distance feed paper at the end.
- **return** `Printer` : Fluent interface

#### Method : `printFormattedTextAndOpenCashBox(String text, int dotsFeedPaper)`
Print a formatted text, feed paper (`dotsFeedPaper` dots), cut the paper and open the cash box. Read the ["Formatted Text : Syntax guide" section](#formatted-text--syntax-guide) for more information about text formatting options.
- **param** `String text` : Formatted text to be printed.
- **param** `int dotsFeedPaper` : Distance feed paper at the end.
- **return** `Printer` : Fluent interface

#### Method : `bitmapToBytes(Bitmap bitmap)`
Convert Bitmap object to ESC/POS image.
- **param** `Bitmap bitmap` : Instance of Bitmap
- **return** `byte[]` : Bytes contain the image in ESC/POS command

### Class : `com.dantsu.escposprinter.textparser.PrinterTextParserImg`

#### **Static** Method : `bitmapToHexadecimalString(Printer printer, Drawable drawable)`
Convert Drawable instance to a hexadecimal string of the image data.
- **param** `Printer printer` : A Printer instance that will print the image.
- **param** `Drawable drawable` : Drawable instance to be converted.
- **return** `String` : A hexadecimal string of the image data. Empty string if Drawable cannot be cast to BitmapDrawable.

#### **Static** Method : `bitmapToHexadecimalString(Printer printer, BitmapDrawable bitmapDrawable)`
Convert BitmapDrawable instance to a hexadecimal string of the image data.
- **param** `Printer printer` : A Printer instance that will print the image.
- **param** `BitmapDrawable bitmapDrawable` : BitmapDrawable instance to be converted.
- **return** `String` : A hexadecimal string of the image data.

#### **Static** Method : `bitmapToHexadecimalString(Printer printer, Bitmap bitmap)`
Convert Bitmap instance to a hexadecimal string of the image data.
- **param** `Printer printer` : A Printer instance that will print the image.
- **param** `Bitmap bitmap` : Bitmap instance to be converted.
- **return** `String` : A hexadecimal string of the image data.

#### **Static** Method : `bytesToHexadecimalString(byte[] bytes)`
Convert byte array to a hexadecimal string of the image data.
- **param** `byte[] bytes` : Bytes contain the image in ESC/POS command.
- **return** `String` : A hexadecimal string of the image data.

#### **Static** Method : `hexadecimalStringToBytes(String hexString)`
Convert hexadecimal string of the image data to bytes ESC/POS command.
- **param** `String hexString` : Hexadecimal string of the image data.
- **return** `byte[]` : Bytes contain the image in ESC/POS command.

### Class : `com.dantsu.escposprinter.EscPosCharsetEncoding`

#### Constructor : `EscPosCharsetEncoding(String charsetName, int escPosCharsetId)`
- **param** `charsetName` Name of charset encoding (Ex: ISO-8859-1)
- **param** `escPosCharsetId` Id of charset encoding for your printer (Ex: 6)

## Projects using this library

- [AllInOneYT/react-native-thermal-printer : A React Native bridge](https://github.com/AllInOneYT/react-native-thermal-printer)
- [paystory-de/thermal-printer-cordova-plugin : A Cordova / Ionic bridge](https://github.com/paystory-de/thermal-printer-cordova-plugin)
- [asukiaaa/react-native-escpos-android : A React Native bridge](https://github.com/asukiaaa/react-native-escpos-android)
- [android_bluetooth_printer : A Flutter bridge](https://pub.dev/packages/android_bluetooth_printer)

## Contributing

Please fork this repository and contribute back using pull requests.

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated but will be thoroughly reviewed
