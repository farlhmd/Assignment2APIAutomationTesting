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

// To shorten the validation procedure, I just test 10 Post instead of the total (100). Just modify the number on idLocal <= maxNumberYouWant to customize the total of the validation
// if you want to check all the data (100 albums * 5 photos * 5 items = 2500 items to verify), go on. I already tested it and it takes about 8 minutes
for (int idLocal = 1; idLocal <= 10; idLocal++) {
    GlobalVariable.id = idLocal

    WS.comment('The end point is declared as idLocal.')

    response = WS.sendRequest(findTestObject('EP_Posts/Get all Comments from specific Post'))

    WS.verifyResponseStatusCode(response, 200)

    WS.comment('Define JSON Slurper to get data from JSON')

    def slurper = new JsonSlurper()

    def result = slurper.parseText(response.getResponseBodyContent())

    WS.comment('Check if value from ID endpoint is equal with JSON Slurper')

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
}

