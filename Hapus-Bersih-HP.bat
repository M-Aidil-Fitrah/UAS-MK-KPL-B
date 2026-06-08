@echo off
echo ===================================================
echo      MENGHAPUS ROBOT APPIUM DARI HP SAMPAI BERSIH
echo ===================================================
echo.
echo Pastikan HP sudah menyala dan kabel USB terhubung!
echo.
echo Menghapus Appium Settings...
adb uninstall io.appium.settings
echo.
echo Menghapus UiAutomator2 Server...
adb uninstall io.appium.uiautomator2.server
echo.
echo Menghapus UiAutomator2 Server (Test)...
adb uninstall io.appium.uiautomator2.server.test
echo.
echo ===================================================
echo   PENGHAPUSAN SELESAI! HP-MU KEMBALI SEPERTI SEMULA
echo ===================================================
pause
