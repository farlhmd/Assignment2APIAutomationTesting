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

WS.comment('To change the total number of Posts to check, change the idLocal <= totalIdYouWant')

for (int idLocal = 1; idLocal <= 10; idLocal++) {
    GlobalVariable.id = idLocal

    WS.comment('The end point is declared as idLocal.')

    response = WS.sendRequest(findTestObject('EP_Posts/Get Specific Post by Id'))

    WS.verifyResponseStatusCode(response, 200)

    WS.comment('Define JSON Slurper to get data from JSON')

    def slurper = new JsonSlurper()

    def result = slurper.parseText(response.getResponseBodyContent())

    userId = result.userId

    id = result.id

    title = result.title

    body = result.body


    userIdVerified = WS.verifyElementPropertyValue(response, "userId", userId, FailureHandling.OPTIONAL)

    idVerified = WS.verifyElementPropertyValue(response, "id", id, FailureHandling.OPTIONAL)

    titleVerified = WS.verifyElementPropertyValue(response, "title", title, FailureHandling.OPTIONAL)

    bodyVerified = WS.verifyElementPropertyValue(response, "body", body, FailureHandling.OPTIONAL)


    if (idVerified == true) {
        WS.comment("The item with ID $id is valid")

        if (userIdVerified == true) {
            WS.comment("The item with User ID $userId is valid")

            if (titleVerified == true) {
                WS.comment("The item with Title $title is valid")

                if (bodyVerified == true) {
                    WS.comment("The item with Body $body is valid")
                } else {
                    WS.comment("The item with Body $body is invalid")
                }
            } else {
                WS.comment("The item with Title $title is invalid")
            }
        } else {
            WS.comment("The item with Id User $userId is invalid")
        }
    } else {
        WS.comment("The item with Id $id is invalid")
    }
}

