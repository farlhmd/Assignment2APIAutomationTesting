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

WS.comment('To change the total number of Users to check, change the idLocal <= totalIdYouWant')

for (int idLocal = 1; idLocal <= 10; idLocal++) {
    GlobalVariable.id = idLocal

    WS.comment('The end point is declared as idLocal.')

    response = WS.sendRequest(findTestObject('EP_Users/Get Specific User by Id'))

    WS.verifyResponseStatusCode(response, 200)

    WS.comment('Define JSON Slurper to get data from JSON')

    def slurper = new JsonSlurper()

    def result = slurper.parseText(response.getResponseBodyContent())

    name = result.name
    id = result.id
	username = result.username
	email = result.email
	addressStreet = result.address.street
	addressSuite = result.address.suite
	addressCity = result.address.city
	zipcode = result.address.zipcode
	geoLat = result.address.geo.lat
	geoLng = result.address.geo.lng
	phone = result.phone
	website = result.website
	companyName = result.company.name
	companyCatchPhrase = result.company.catchPhrase
	companyBs = result.company.bs

    idVerified = WS.verifyElementPropertyValue(response, "id", id, FailureHandling.OPTIONAL)
	nameVerified = WS.verifyElementPropertyValue(response, "name", name, FailureHandling.OPTIONAL)
	usernameVerified = WS.verifyElementPropertyValue(response, "username", username, FailureHandling.OPTIONAL)
	emailVerified = WS.verifyElementPropertyValue(response, "email", email, FailureHandling.OPTIONAL)
	addressStreetVerified = WS.verifyElementPropertyValue(response, "address.street", addressStreet, FailureHandling.OPTIONAL)
	addressSuiteVerified = WS.verifyElementPropertyValue(response, "address.suite", addressSuite, FailureHandling.OPTIONAL)
	addressCityVerified = WS.verifyElementPropertyValue(response, "address.city", addressCity, FailureHandling.OPTIONAL)
	zipcodeVerified = WS.verifyElementPropertyValue(response, "address.zipcode", zipcode, FailureHandling.OPTIONAL)
	geoLatVerified = WS.verifyElementPropertyValue(response, "address.geo.lat", geoLat, FailureHandling.OPTIONAL)
	geoLngVerified = WS.verifyElementPropertyValue(response, "address.geo.lng", geoLng, FailureHandling.OPTIONAL)
	phoneVerified = WS.verifyElementPropertyValue(response, "phone", phone, FailureHandling.OPTIONAL)
	websiteVerified = WS.verifyElementPropertyValue(response, "website", website, FailureHandling.OPTIONAL)
	companyNameVerified = WS.verifyElementPropertyValue(response, "company.name", companyName, FailureHandling.OPTIONAL)
	companyCatchPhraseVerified = WS.verifyElementPropertyValue(response, "company.catchPhrase", companyCatchPhrase, FailureHandling.OPTIONAL)
	companyBsVerified = WS.verifyElementPropertyValue(response, "company.bs", companyBs, FailureHandling.OPTIONAL)
	
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

