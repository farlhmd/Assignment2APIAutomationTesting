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

WS.comment('To check all of 100 albums, use for loop (Variable declared) on Global Variables')

for (int idLocal = 1; idLocal <= 100; idLocal++) {
    GlobalVariable.id = idLocal
WS.comment('The end point is declared as idLocal.')
    response = WS.sendRequest(findTestObject('EP_Albums/Get Specific Album by Id'))

    WS.verifyResponseStatusCode(response, 200)

    WS.comment('Define JSON Slurper to get data from JSON')

    def slurper = new JsonSlurper()

    def result = slurper.parseText(response.getResponseBodyContent())
	userId = result.userId
	
	id = result.id
	
	title = result.title

    WS.comment('Check if value from ID endpoint is equal with JSON Slurper')

    WS.verifyElementPropertyValue(response, 'userId', userId, FailureHandling.OPTIONAL)

    WS.verifyElementPropertyValue(response, 'id', id, FailureHandling.OPTIONAL)

    WS.verifyElementPropertyValue(response, 'title', title, FailureHandling.OPTIONAL)

	println('This data is verified: ')
    println('Id: ' + id)

    println('User Id :' + userId)

    println('Title :' + title)
}