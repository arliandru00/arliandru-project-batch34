import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Menentukan test data
TestData loginData = findTestData('UserSaucedemo')

// Loop melalui semua baris dalam test data
for (def rowNum = 1; rowNum <= loginData.getRowNumbers(); rowNum++) {
    // Mendapatkan nilai dari kolom Username, Password, dan Message
    def username = loginData.getValue('username', rowNum)

    def password = loginData.getValue('password', rowNum)

    def message = loginData.getValue('message', rowNum)

    // Melakukan langkah-langkah untuk login
    WebUI.openBrowser('https://www.saucedemo.com/')

    WebUI.maximizeWindow()

    WebUI.setText(findTestObject('Saucedemo/inputUsername'), username)

    WebUI.setText(findTestObject('Saucedemo/inputPassword'), password)

    WebUI.click(findTestObject('Saucedemo/loginButton'))

    // Menunggu hingga halaman berikutnya dimuat setelah login
    WebUI.delay(5 // Sesuaikan dengan waktu tunggu yang dibutuhkan
        )

    // Memeriksa apakah login berhasil dengan memeriksa keberadaan elemen yang muncul hanya jika login berhasil
    //boolean Products = WebUI.verifyElementPresent(findTestObject('Saucedemo/VerifyProductsLabel'), 5, FailureHandling.OPTIONAL)

    if (!(loginSuccess)) {
        // Jika login gagal, verifikasi pesan error
        WebUI.verifyElementText(findTestObject('Saucedemo/ErrorMessageTextLabel'), message)
    }
    
    // Menutup browser setelah setiap iterasi
    WebUI.closeBrowser()
}

