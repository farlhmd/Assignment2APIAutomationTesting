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

response = WS.sendRequest(findTestObject('EP_Todos/Get all Todos'))

def slurper = new JsonSlurper()

WS.verifyResponseStatusCode(response, 200)

def result = slurper.parseText(response.getResponseBodyContent())

total = 0

WS.comment('To check all available indexes of data using JSON Slurper')

// I just check 10 Posts out of a total of 200 to shorten the validation procedure.
// for(int i=0; i < result.size(); i++) {
// To check all data from the id, uncomment the code above. But if that is too much, use the code below instead (only 10 sample datas).
for (int i = 0; i < 10; i++) {
    userId = result[i].userId

    id = result[i].id

    title = result[i].title

    completed = result[i].completed

    userIdVerified = WS.verifyElementPropertyValue(response, "[$i].userId", userId, FailureHandling.OPTIONAL)

    idVerified = WS.verifyElementPropertyValue(response, "[$i].id", id, FailureHandling.OPTIONAL)

    titleVerified = WS.verifyElementPropertyValue(response, "[$i].title", title, FailureHandling.OPTIONAL)

    completedVerified = WS.verifyElementPropertyValue(response, "[$i].completed", completed, FailureHandling.OPTIONAL)

    if (idVerified == true) {
        WS.comment("The item with ID $id is valid")

        if (userIdVerified == true) {
            WS.comment("The item with User ID $userId is valid")

            if (titleVerified == true) {
                WS.comment("The item with Title $title is valid")

                if (completedVerified == true) {
                    WS.comment("The item with Body $completed is valid")

                    total++
                } else {
                    WS.comment("The item with Body $completed is invalid")
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

WS.comment("Indexes verified: $total")

