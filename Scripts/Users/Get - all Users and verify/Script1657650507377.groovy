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

response = WS.sendRequest(findTestObject('EP_Users/Get all Users'))

def slurper = new JsonSlurper()

WS.verifyResponseStatusCode(response, 200)

def result = slurper.parseText(response.getResponseBodyContent())

total = 0

WS.comment('To check all available indexes of data using JSON Slurper')

 for(int i=0; i < result.size(); i++) {

// for (int i = 0; i < 10; i++) {
	name = result[i].name
    id = result[i].id
	username = result[i].username
	email = result[i].email
	addressStreet = result[i].address.street
	addressSuite = result[i].address.suite
	addressCity = result[i].address.city
	zipcode = result[i].address.zipcode
	geoLat = result[i].address.geo.lat
	geoLng = result[i].address.geo.lng
	phone = result[i].phone
	website = result[i].website
	companyName = result[i].company.name
	companyCatchPhrase = result[i].company.catchPhrase
	companyBs = result[i].company.bs

    idVerified = WS.verifyElementPropertyValue(response, "[$i].id", id, FailureHandling.OPTIONAL)
	nameVerified = WS.verifyElementPropertyValue(response, "[$i].name", name, FailureHandling.OPTIONAL)
	usernameVerified = WS.verifyElementPropertyValue(response, "[$i].username", username, FailureHandling.OPTIONAL)
	emailVerified = WS.verifyElementPropertyValue(response, "[$i].email", email, FailureHandling.OPTIONAL)
	addressStreetVerified = WS.verifyElementPropertyValue(response, "[$i].address.street", addressStreet, FailureHandling.OPTIONAL)
	addressSuiteVerified = WS.verifyElementPropertyValue(response, "[$i].address.suite", addressSuite, FailureHandling.OPTIONAL)
	addressCityVerified = WS.verifyElementPropertyValue(response, "[$i].address.city", addressCity, FailureHandling.OPTIONAL)
	zipcodeVerified = WS.verifyElementPropertyValue(response, "[$i].address.zipcode", zipcode, FailureHandling.OPTIONAL)
	geoLatVerified = WS.verifyElementPropertyValue(response, "[$i].address.geo.lat", geoLat, FailureHandling.OPTIONAL)
	geoLngVerified = WS.verifyElementPropertyValue(response, "[$i].address.geo.lng", geoLng, FailureHandling.OPTIONAL)
	phoneVerified = WS.verifyElementPropertyValue(response, "[$i].phone", phone, FailureHandling.OPTIONAL)
	websiteVerified = WS.verifyElementPropertyValue(response, "[$i].website", website, FailureHandling.OPTIONAL)
	companyNameVerified = WS.verifyElementPropertyValue(response, "[$i].company.name", companyName, FailureHandling.OPTIONAL)
	companyCatchPhraseVerified = WS.verifyElementPropertyValue(response, "[$i].company.catchPhrase", companyCatchPhrase, FailureHandling.OPTIONAL)
	companyBsVerified = WS.verifyElementPropertyValue(response, "[$i].company.bs", companyBs, FailureHandling.OPTIONAL)
	
    if (idVerified == true) {
        WS.comment("The item with ID $id is valid")

        if (nameVerified == true) {		
            WS.comment("The item with Name $name is valid")
			
			if (usernameVerified == true) {				
				WS.comment("The item with Username $username is valid")
				
				if (emailVerified == true) {					
					WS.comment("The item with Email $email is valid")
					
					if (addressStreetVerified == true) {					
						WS.comment("The item with Address Street $addressStreet is valid")
						
						if (addressSuiteVerified == true) {
							WS.comment("The item with Address Suite $addressSuite is valid")
							
							if (addressCityVerified == true) {
								WS.comment("The item with Address City $addressCity is valid")
								
								if (zipcodeVerified == true) {
									WS.comment("The item with Zipcode $zipcode is valid")
									
									if (geoLatVerified == true) {
										WS.comment("The item with Latitude $geoLat is valid")
										
										if (geoLngVerified == true) {
											WS.comment("The item with Latitude $geoLng is valid")
											
											if (phoneVerified == true) {
												WS.comment("The item with Phone $phone is valid")
												
												if (websiteVerified == true) {
													WS.comment("The item with Website $website is valid")
													
													if (companyNameVerified == true) {
														WS.comment("The item with Company Name $companyName is valid")
														
														if (companyCatchPhraseVerified == true) {
															WS.comment("The item with Company Catch Phrase $companyCatchPhrase is valid")
															
															if (companyBsVerified == true) {
																WS.comment("The item with Company BS $companyBs is valid")
																
																total++
															} else {
																WS.comment("The item with Company BS $companyBs is invalid")
															}
														} else {
															WS.comment("The item with Company Catch Phrase $companyCatchPhrase is invalid")
														}
													} else {
														WS.comment("The item with Company Name $companyName is invalid")
													}
												} else {
													WS.comment("The item with Website $website is invalid")
												}
											} else {
												WS.comment("The item with Phone $phone is invalid")
											}
										} else {
											WS.comment("The item with Latitude $geoLng is invalid")
										}
									} else {
										WS.comment("The item with Latitude $geoLat is invalid")
									}
								} else {
									WS.comment("The item with Zipcode $zipcode is invalid")
								}
							} else {
								WS.comment("The item with Address City $addressCity is invalid")
							}
						} else {
							WS.comment("The item with Address Suite $addressSuite is invalid")
						}
					} else {
						WS.comment("The item with Address Street $addressStreet is invalid")
					}
				} else {
					WS.comment("The item with Email $email is invalid")
				}
			} else {
				WS.comment("The item with Username $username is invalid")
			}
        } else {
            WS.comment("The item with Name $name is invalid")
        }
    } else {
        WS.comment("The item with Id $id is invalid")
    }
}

WS.comment("Indexes verified: $total")

