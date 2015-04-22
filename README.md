# CarDekhoDemo
An assignment to implement clustering on Google Map

### Note:
1. Download project as zip file or you can clone this repo through git url and import in Android Studio.
2. Replace debug.keystore file with your own Android default keystore file(~.android/debug.keystore) file
otherwise Google Map will not load. Or, create your own Android project on Google API Console, enable Google Maps API V2,
and create Android API Key with (<SH1 Fingerprint of your own debug.keystore><;><com.cardekho>) and paste that API key in AndroidManifest.xml
3. I am using mockable.io for creating mock REST API to get city list json as response, So it's necessary to have internet
connection in your device while running app else you will get "Network not available" error.

### Library/Dependencies Used
1. Retrofit
2. Android AppCompat
3.Google Play Services
4. Android Map Utils

Minimum SDK version : 15

Target SDK version : 21



