package steps;

import POM.ContactUsPOM;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseDriver;

public class ContactUsSteps {
    public WebDriver driver = BaseDriver.getDriver();
    WebDriverWait wait=new WebDriverWait(driver,10);
    ContactUsPOM contact = new ContactUsPOM();

    @Given("^Navigate to the website$")
    public void navigate_to_the_website() {
//        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php ");
    }
    @When("^I use legit username and password$")
    public void i_use_legit_username_and_password() {
        contact.waitAndClick(By.cssSelector(".login"));
        contact.waitAndSendKeys(By.id("email"), "qwerty0123@gmail.com");
        contact.waitAndSendKeys(By.id("passwd"), "123456789");
        contact.waitAndClick(By.cssSelector(".icon-lock.left"));
    }
    @Then("^I am logged in$")
    public void i_am_logged_in() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("My account - My Store"));
    }

    @Given("^Navigate to Contact us$")
    public void navigateToContactUs() throws InterruptedException {
        contact.waitAndClick(By.cssSelector("a[title='Contact Us']"));
        Thread.sleep(4000);
    }

    @When("^Fill out the message box \"([^\"]*)\", \"([^\"]*)\", and \"([^\"]*)\"$")
    public void fillOutTheMessageBoxAnd(String arg0, String arg1, String arg2) {
        Select select1=new Select(driver.findElement(By.id("id_contact")));
        select1.selectByVisibleText(arg0);
        WebElement id_order = driver.findElement(By.name("id_order"));
        Select select2=new Select(id_order);
        select2.selectByValue(arg1);
        WebElement product = driver.findElement(By.cssSelector("select[style='display: inline-block;']"));
        Select select3=new Select(product);
        select3.selectByIndex(1);
        contact.waitAndSendKeys(By.id("message"),"sfs");
        contact.waitAndClick(By.id("submitMessage"));

    }

    @Then("^\"([^\"]*)\" has been successfully \"([^\"]*)\" to our team\\.$")
    public void hasBeenSuccessfullyToOurTeam(String arg0, String arg1){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
        String actual = driver.findElement(By.cssSelector(".alert.alert-success")).getText();
        Assert.assertEquals(actual, arg0 + " has been successfully " + arg1+" to our team.");
        contact.waitAndClick(By.cssSelector("a[title='Log me out']"));
    }


}
