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

response = WS.sendRequest(findTestObject('EP_Albums/Get all Albums'))

WS.verifyResponseStatusCode(response, 200)

WS.comment('To check the userId and id is equal to what it should')

int i = 0

int max = 100

int total = 0

int userId = 1

while (i < max) {
    idVerified = WS.verifyElementPropertyValue(response, "[$i].id", i + 1, FailureHandling.OPTIONAL)

    userIdVerified = WS.verifyElementPropertyValue(response, "[$i].userId", userId, FailureHandling.OPTIONAL)

    if (idVerified == true) {
        WS.comment("Id value with index $i is verified")

        if (userId == true) {
            i++

            total = i

            if ((i % 10) == 0) {
                userId += 1
            }
        } else {
			WS.comment("User Id value with index $i is invalid")
		}
    } else {
        WS.comment("Id value with index $i is invalid")

        total = i

        i = max
    }
}

WS.comment("Completed get all albums by id, The total is: $total")