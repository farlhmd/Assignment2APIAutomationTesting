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
import groovy.json.JsonSlurper as JsonSlurper

response = WS.sendRequest(findTestObject('EP_Albums/Get all Albums'))
def slurper = new JsonSlurper()
WS.verifyResponseStatusCode(response, 200)

def result = slurper.parseText(response.getResponseBodyContent())
total = 0
WS.comment('To check all available indexes of data using JSON Slurper')
for(int i=0; i < result.size(); i++) {
	
    WS.verifyElementPropertyValue(response, "[$i].id", i + 1, FailureHandling.OPTIONAL)
	userIdVerified = WS.verifyElementPropertyValue(response, "[$i].userId", result[i].userId, FailureHandling.OPTIONAL)
	
	idVerified = WS.verifyElementPropertyValue(response, "[$i].id", result[i].id, FailureHandling.OPTIONAL)
	
	titleVerified = WS.verifyElementPropertyValue(response, "[$i].title", result[i].title, FailureHandling.OPTIONAL)
	
	WS.comment('Showing if the respond equal with the JSON Slurper')
	if ((userIdVerified && idVerified && titleVerified == true)) {
    WS.comment("The index value with id $i is verified")
	total++
	} else {
		WS.comment("The index value with id $i is invalid")
	}
}
WS.comment("Indexes verified: $total")


// These lines of code below is verifying data manually (using 3 samples)

//WS.comment('To check if there are 100 indexes of data')
//
//int i = 0
//while(i<100) {
//    WS.verifyElementPropertyValue(response, "[$i].id", i + 1, FailureHandling.OPTIONAL)
//	i++
//}
//
//WS.comment('1. Verify if response from the first index is equal with what it should.')
//
//userIdVerified = WS.verifyElementPropertyValue(response, '[0].userId', '1', FailureHandling.OPTIONAL)
//
//idVerified = WS.verifyElementPropertyValue(response, '[0].id', '1', FailureHandling.OPTIONAL)
//
//titleVerified = WS.verifyElementPropertyValue(response, '[0].title', 'quidem molestiae enim', FailureHandling.OPTIONAL)
//
//if ((userIdVerified && idVerified) && (titleVerified == true)) {
//    WS.comment('The first index value is verified')
//
//    WS.comment('2. Verify if response from middle of the index is equal with what it should.')
//
//    userIdVerified = WS.verifyElementPropertyValue(response, '[49].userId', '5', FailureHandling.OPTIONAL)
//
//    idVerified = WS.verifyElementPropertyValue(response, '[49].id', '50', FailureHandling.OPTIONAL)
//
//    titleVerified = WS.verifyElementPropertyValue(response, '[49].title', 'sed qui sed quas sit ducimus dolor', FailureHandling.OPTIONAL)
//
//    if ((userIdVerified && idVerified) && (titleVerified == true)) {
//        WS.comment('The middle index value is verified')
//
//        WS.comment('3. Verify if response from the last index is equal with what it should.')
//
//        userIdVerified = WS.verifyElementPropertyValue(response, '[99].userId', '10', FailureHandling.OPTIONAL)
//
//        idVerified = WS.verifyElementPropertyValue(response, '[99].id', '100', FailureHandling.OPTIONAL)
//
//        titleVerified = WS.verifyElementPropertyValue(response, '[99].title', 'enim repellat iste', FailureHandling.OPTIONAL)
//
//        if ((userIdVerified && idVerified) && (titleVerified == true)) {
//            WS.comment('All of the index value is verified')
//        } else {
//            WS.comment('The last index value is invalid')
//        }
//    } else {
//        WS.comment('The middle index value is invalid')
//    }
//} else {
//    WS.comment('The first index value is invalid')
//}