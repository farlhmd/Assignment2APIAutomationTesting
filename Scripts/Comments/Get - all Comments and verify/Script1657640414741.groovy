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

response = WS.sendRequest(findTestObject('EP_Comments/Get all Comments'))

def slurper = new JsonSlurper()

WS.verifyResponseStatusCode(response, 200)

def result = slurper.parseText(response.getResponseBodyContent())

total = 0

WS.comment('To check all available indexes of data using JSON Slurper')

for (int i = 0; i < result.size(); i++) {

	postId = result[i].postId
	id = result[i].id
	name = result[i].name
	email = result[i].email
	body = result[i].body
	
    postIdVerified = WS.verifyElementPropertyValue(response, "[$i].postId", postId, FailureHandling.OPTIONAL)
    idVerified = WS.verifyElementPropertyValue(response, "[$i].id", id, FailureHandling.OPTIONAL)
    nameVerified = WS.verifyElementPropertyValue(response, "[$i].name", name, FailureHandling.OPTIONAL)
	emailVerified = WS.verifyElementPropertyValue(response, "[$i].email", email, FailureHandling.OPTIONAL)
	bodyVerified = WS.verifyElementPropertyValue(response, "[$i].body", body, FailureHandling.OPTIONAL)

    WS.comment('Showing if the respond equal with the JSON Slurper')

    if (postIdVerified == true) {
        WS.comment("The index value with Post Id $postId is verified")
        total++
		if (idVerified == true) {
			WS.comment("The index value with Id $id is verified")
			if (nameVerified == true) {
				WS.comment("The index value with Name $name is verified")
				if (emailVerified == true) {
					WS.comment("The index value with Email $email is verified")
					if (bodyVerified == true) {
						WS.comment("The index value with Body $body is verified")
					} else {
						WS.comment("The index value with Body $body is invalid")
					}
				} else {
					WS.comment("The index value with Email $email is invalid")
				}
			} else {
				WS.comment("The index value with Name $name is invalid")
			}
		} else {
			WS.comment("The index value with id $id is invalid")
		}
    } else {
        WS.comment("The index value with Post Id $postId is invalid")
    }
}

WS.comment("Indexes verified: $total")

