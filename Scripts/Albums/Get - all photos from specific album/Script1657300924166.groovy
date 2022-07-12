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



WS.comment('To check all photos from 100 albums, use for loop (Variable declared) on Global Variables')

// To shorten the validation procedure, I just test 10 albums instead of the total (100). Just modify the number on idLocal <= maxNumberYouWant to customize the total of the validation
// if you want to check all the data (100 albums * 50 photos * 5 items = 25000 items to verify), go on. I already tested it and it takes about 20 minutes
for (int idLocal = 1; idLocal <= 10; idLocal++) {
	GlobalVariable.id = idLocal
WS.comment('The end point is declared as idLocal.')
	response = WS.sendRequest(findTestObject('EP_Albums/Get all photos from specific album'))

	WS.verifyResponseStatusCode(response, 200)
	WS.comment('Define JSON Slurper to get data from JSON')

	def slurper = new JsonSlurper()
	def result = slurper.parseText(response.getResponseBodyContent())

	WS.comment('Check if value from ID endpoint is equal with JSON Slurper')
	
// for(int i=0; i < result.size(); i++) {
	// I just check 10 photos out of a total of 50 to shorten the validation procedure.
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
}
