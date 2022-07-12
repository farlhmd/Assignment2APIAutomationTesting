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

response = WS.sendRequest(findTestObject('EP_Photos/Get all Photos'))

def slurper = new JsonSlurper()

WS.verifyResponseStatusCode(response, 200)

def result = slurper.parseText(response.getResponseBodyContent())

total = 0

WS.comment('To check all available indexes of data using JSON Slurper')
// I just check 10 photos out of a total of 5000 to shorten the validation procedure.
// for(int i=0; i < result.size(); i++) {
	// To check all data from the id, uncomment the code above. But if that is too much, use the code below instead (only 10 sample datas).
for(int i=0; i < 10; i++) {

	albumId = result[i].albumId	
	id = result[i].id
	title = result[i].title
	url = result[i].url
	thumbnailUrl = result[i].thumbnailUrl
	
	albumIdVerified = WS.verifyElementPropertyValue(response, "[$i].albumId", albumId, FailureHandling.OPTIONAL)
	idVerified = WS.verifyElementPropertyValue(response, "[$i].id", id, FailureHandling.OPTIONAL)
	titleVerified = WS.verifyElementPropertyValue(response, "[$i].title", title, FailureHandling.OPTIONAL)
	urlVerified = WS.verifyElementPropertyValue(response, "[$i].url", url, FailureHandling.OPTIONAL)
	thumbnailUrlVerified = WS.verifyElementPropertyValue(response, "[$i].thumbnailUrl", thumbnailUrl, FailureHandling.OPTIONAL)

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
							total++
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

WS.comment("Indexes verified: $total")

