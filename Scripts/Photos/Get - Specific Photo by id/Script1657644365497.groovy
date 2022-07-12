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

WS.comment('To change the total number of Photos to check, change the idLocal <= totalIdYouWant')

for (int idLocal = 1; idLocal <= 10; idLocal++) {
    GlobalVariable.id = idLocal

    WS.comment('The end point is declared as idLocal.')

    response = WS.sendRequest(findTestObject('EP_Photos/Get Specific Photo by Id'))

    WS.verifyResponseStatusCode(response, 200)

    WS.comment('Define JSON Slurper to get data from JSON')

    def slurper = new JsonSlurper()

    def result = slurper.parseText(response.getResponseBodyContent())

    albumId = result.albumId	
	id = result.id
	title = result.title
	url = result.url
	thumbnailUrl = result.thumbnailUrl
	
	albumIdVerified = WS.verifyElementPropertyValue(response, "albumId", albumId, FailureHandling.OPTIONAL)
	idVerified = WS.verifyElementPropertyValue(response, "id", id, FailureHandling.OPTIONAL)
	titleVerified = WS.verifyElementPropertyValue(response, "title", title, FailureHandling.OPTIONAL)
	urlVerified = WS.verifyElementPropertyValue(response, "url", url, FailureHandling.OPTIONAL)
	thumbnailUrlVerified = WS.verifyElementPropertyValue(response, "thumbnailUrl", thumbnailUrl, FailureHandling.OPTIONAL)

	if (idVerified == true ) {
	WS.comment("The item with ID $id is valid")
	 	if (albumIdVerified == true) {
			 WS.comment("The item with Album ID $albumId is valid")
			
			 if (titleVerified == true) {
				WS.comment("The item with title $title is valid")
			
					if (urlVerified == true) {
					WS.comment("The item with URL $url is valid")
					
						if (thumbnailUrlVerified == true) {
							WS.comment("The item with Thumbnail URL $thumbnailUrl is valid")
							
						} else {
							WS.comment("The item with Thumbnail Url: $thumbnailUrl is invalid")
						} 
					} else {
						WS.comment("The item with URL $url is invalid")
					}
			 }else {
				 WS.comment("The item with Title $title is invalid")
			 }
		 }else {
			 WS.comment("The item with Id Album $albumId is invalid")
		 }
	}else {
		WS.comment("The item with Id $id is invalid")
	}
}

