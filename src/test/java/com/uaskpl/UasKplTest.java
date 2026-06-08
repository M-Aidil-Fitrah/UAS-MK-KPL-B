package com.uaskpl;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class UasKplTest {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() throws MalformedURLException {
        // 1. Tentukan lokasi file APK di dalam project
        File appDir = new File("app");
        File app = new File(appDir, "Android-MyDemoAppRN.1.3.0.build-244.apk");

        // 2. Konfigurasi Appium untuk menyambung ke Emulator
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp(app.getAbsolutePath())
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity(".MainActivity")
                .setNoReset(false); // Selalu mulai dari awal untuk setiap test (bersihkan cache login)

        // 3. Tentukan URL dari Appium Server
        URL appiumServerUrl = new URL("http://127.0.0.1:4723");

        // 4. Inisialisasi Android Driver dan Wait
        driver = new AndroidDriver(appiumServerUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Maksimal tunggu 10 detik untuk tiap elemen
    }

    // Helper method untuk navigasi ke halaman Login dari halaman depan
    private void navigateToLogin() {
        // Klik hamburger menu di pojok kiri atas
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("open menu")));
        menu.click();
        
        // Klik menu "Log In" di sidebar
        WebElement loginMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("menu item log in")));
        loginMenu.click();
    }

    @Test(priority = 1)
    public void TC01_LoginSuccess() {
        System.out.println("Menjalankan Test Case 1: Login Sukses");
        navigateToLogin();

        // Input Username (akun standar bob)
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Username input field")));
        usernameField.clear();
        usernameField.sendKeys("bob@example.com");

        // Input Password
        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("Password input field"));
        passwordField.clear();
        passwordField.sendKeys("10203040");

        // Klik tombol Login
        WebElement loginBtn = driver.findElement(AppiumBy.accessibilityId("Login button"));
        loginBtn.click();

        // Verifikasi login berhasil (ciri-cirinya: masuk kembali ke halaman Products)
        WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Products']")));
        Assert.assertTrue(productsTitle.isDisplayed(), "Login gagal, teks 'Products' tidak ditemukan!");
    }

    @Test(priority = 2)
    public void TC02_LoginFailed_WrongPassword() {
        System.out.println("Menjalankan Test Case 2: Login Gagal (Password Salah)");
        navigateToLogin();

        // Input Username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Username input field")));
        usernameField.clear();
        usernameField.sendKeys("bob@example.com");

        // Input Password Salah
        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("Password input field"));
        passwordField.clear();
        passwordField.sendKeys("passwordsalah123");

        // Klik tombol Login
        WebElement loginBtn = driver.findElement(AppiumBy.accessibilityId("Login button"));
        loginBtn.click();

        // Verifikasi pesan error muncul di layar
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Provided credentials do not match')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Pesan error password salah tidak muncul!");
    }

    @Test(priority = 3)
    public void TC03_LoginFailed_LockedOut() {
        System.out.println("Menjalankan Test Case 3: Login Gagal (Akun Terblokir)");
        navigateToLogin();

        // Input Username akun yang dikunci (alice)
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Username input field")));
        usernameField.clear();
        usernameField.sendKeys("alice@example.com");

        // Input Password
        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("Password input field"));
        passwordField.clear();
        passwordField.sendKeys("10203040");

        // Klik tombol Login
        WebElement loginBtn = driver.findElement(AppiumBy.accessibilityId("Login button"));
        loginBtn.click();

        // Verifikasi pesan error 'locked out' muncul
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Sorry, this user has been locked out.')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Pesan error akun terblokir tidak muncul!");
    }

    @AfterTest
    public void teardown() {
        // Menutup aplikasi dan driver setelah semua test selesai
        if (driver != null) {
            driver.quit(); 
        }
    }
}
