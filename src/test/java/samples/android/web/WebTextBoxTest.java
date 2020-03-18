package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTextBoxTest extends AndroidWebTest {
    @Test(groups = "web")
    public void testTextBoxInteraction() {
        AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) AqualityServices.getApplication().getDriver();
        driver.get("https://wikipedia.org");
        ITextBox txbSearch = AqualityServices.getElementFactory().getTextBox(By.id("searchInput"), "Search");
        txbSearch.state().waitForClickable();
        txbSearch.click();
        Assert.assertTrue(driver.isKeyboardShown(), "Keyboard should be shown when click successful");
        txbSearch.unfocus();
        Assert.assertFalse(driver.isKeyboardShown(), "Keyboard should not be shown when unfocus successful");
        txbSearch.focus();
        Assert.assertTrue(driver.isKeyboardShown(), "Keyboard should be shown when focus successful");
        final String valueToSubmit = "quality assurance";
        txbSearch.type(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clear();
        Assert.assertEquals("", txbSearch.getValue(), "Value should be cleared");
        txbSearch.typeSecret(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clearAndType(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clearAndTypeSecret(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.sendKeys(Keys.ENTER);
        Assert.assertTrue(txbSearch.state().waitForNotDisplayed(), "text field should disappear after the submit");
    }
}