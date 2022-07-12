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

WS.comment('To change the total number of Comments to check, change the idLocal <= totalIdYouWant')
for (int idLocal = 1; idLocal <= 10; idLocal++) {
    GlobalVariable.id = idLocal

    WS.comment('The end point is declared as idLocal.')

    response = WS.sendRequest(findTestObject('EP_Comments/Get Specific Comment by Id'))

    WS.verifyResponseStatusCode(response, 200)

    WS.comment('Define JSON Slurper to get data from JSON')

    def slurper = new JsonSlurper()

    def result = slurper.parseText(response.getResponseBodyContent())

    postId = result.postId
	id = result.id
	name = result.name
	email = result.email
	body = result.body
	
    postIdVerified = WS.verifyElementPropertyValue(response, "postId", postId, FailureHandling.OPTIONAL)
    idVerified = WS.verifyElementPropertyValue(response, "id", id, FailureHandling.OPTIONAL)
    nameVerified = WS.verifyElementPropertyValue(response, "name", name, FailureHandling.OPTIONAL)
	emailVerified = WS.verifyElementPropertyValue(response, "email", email, FailureHandling.OPTIONAL)
	bodyVerified = WS.verifyElementPropertyValue(response, "body", body, FailureHandling.OPTIONAL)

    WS.comment('Showing if the respond equal with the JSON Slurper')

    if (postIdVerified == true) {
		
        WS.comment("The index value with Post Id $postId is verified")
		
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

