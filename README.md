# UAS Kualitas Perangkat Lunak - Automation Testing

Project ini dibuat untuk memenuhi tugas akhir semester mata kuliah Kualitas Perangkat Lunak (KPL).

## Spesifikasi Pengujian
- **Framework:** Appium
- **Bahasa Pemrograman:** Java
- **Test Runner:** TestNG
- **Aplikasi Target:** Sauce Labs Sample App (My Demo App)

## Persiapan Menjalankan Test
1. Pastikan **Appium Server** sudah berjalan (jalankan perintah `appium` di terminal).
2. Pastikan **Android Emulator** sedang menyala melalui Android Studio.
3. Buka project ini di IDE pilihanmu dan jalankan perintah:
   ```bash
   mvn clean test
   ```

## Skenario Pengujian (Test Cases)
1. `TC01_LoginSuccess`: Menguji skenario login dengan data yang benar.
2. `TC02_LoginFailed_WrongPassword`: Menguji validasi login gagal.
3. `TC03_LoginFailed_LockedOut`: Menguji validasi akun yang tidak bisa diakses.
