package com.uaskpl;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class UasKplTest {
    private AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        // 1. Tentukan lokasi file APK di dalam project
        File appDir = new File("app");
        // PENTING: Pastikan nama file di bawah ini sama persis dengan yang kamu copy ke folder app/
        File app = new File(appDir, "Android-MyDemoAppRN.1.3.0.build-244.apk"); 

        // 2. Konfigurasi Appium untuk menyambung ke Emulator
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554") // Nama default emulator Android Studio
                .setApp(app.getAbsolutePath())
                // Package dan Activity dari Sauce Labs Sample App
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity(".MainActivity")
                .setNoReset(false); // Selalu install ulang aplikasi agar kondisi selalu fresh saat ditest

        // 3. Tentukan URL dari Appium Server (default port: 4723)
        URL appiumServerUrl = new URL("http://127.0.0.1:4723");

        // 4. Inisialisasi Android Driver
        driver = new AndroidDriver(appiumServerUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testAppLaunches() {
        // Ini adalah test kosong sementara (Hanya untuk Tahap 3)
        // Jika aplikasi terbuka di emulator tanpa error, berarti konfigurasi sukses!
        System.out.println("Aplikasi berhasil terbuka di Emulator!");
    }

    @AfterTest
    public void teardown() {
        // Menutup aplikasi dan driver setelah test selesai
        if (driver != null) {
            driver.quit(); 
        }
    }
}
