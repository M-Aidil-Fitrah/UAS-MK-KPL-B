@echo off
echo ===================================================
echo     MEMASANG SISTEM ROBOT APPIUM KE HP (MANUAL)
echo ===================================================
echo.
echo Pastikan HP sudah menyala dan kabel USB terhubung!
echo.
echo Menginstal Appium Settings...
adb install "%USERPROFILE%\.appium\node_modules\appium-uiautomator2-driver\node_modules\io.appium.settings\apks\settings_apk-debug.apk"
echo.
echo Menginstal UiAutomator2 Server...
adb install "%USERPROFILE%\.appium\node_modules\appium-uiautomator2-driver\node_modules\appium-uiautomator2-server\apks\appium-uiautomator2-server-v10.2.1.apk"
echo.
echo Menginstal UiAutomator2 Server (Test)...
adb install "%USERPROFILE%\.appium\node_modules\appium-uiautomator2-driver\node_modules\appium-uiautomator2-server\apks\appium-uiautomator2-server-debug-androidTest.apk"
echo.
echo ===================================================
echo  INSTALASI SELESAI! SILAKAN JALANKAN TEST SEKARANG
echo ===================================================
pause
