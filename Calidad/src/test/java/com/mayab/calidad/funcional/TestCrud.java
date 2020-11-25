package com.mayab.calidad.funcional;
import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCrud {
	private static WebDriver driver;
	  private static String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  String name,email,age,gender;
	  int tableRowNum=0;
	 

	  @BeforeClass
	  public static void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\power\\Downloads\\WebDriver\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    baseUrl = "https://mern-crud.herokuapp.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  private void pause(long mils) {try {Thread.sleep(mils);}
	    catch(Exception e){e.printStackTrace();}}
	  @Test
	  public void testAdd() throws Exception {
		  driver.get("https://mern-crud.herokuapp.com/");
		  if(isAlreadyAdded()) {
			  deleteAdded();
			  testAdd();
		  }else {
			  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
			    driver.findElement(By.name("name")).click();
			    driver.findElement(By.name("name")).clear();
			    driver.findElement(By.name("name")).sendKeys("Fredy Guzman");
			    driver.findElement(By.name("email")).click();
			    driver.findElement(By.name("email")).clear();
			    driver.findElement(By.name("email")).sendKeys("omar@gmail.com");
			    driver.findElement(By.name("age")).click();
			    driver.findElement(By.name("age")).clear();
			    driver.findElement(By.name("age")).sendKeys("21");
			    driver.findElement(By.xpath("//div[3]/div[2]/div/div")).click();
			    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
			    driver.findElement(By.xpath("//form/button")).click();
			    pause(1500);
		    String addedText=
		    	    driver.findElement(By.xpath("//div[4]")).getText();
		    assertEquals("Nice one!\n" + 
		    		"Successfully added!",addedText);
		  }
		  }
		    
	  @Test
	  public void testfailAddEmailTaken() throws Exception {
		  driver.get("https://mern-crud.herokuapp.com/");
	   setFirstInfo();
	   createCopy();
	    String addedText=
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]")).getText();
	    pause(500);
	    assertEquals("Woah!\n" + "That email is already taken.",addedText);
	  }
	  @Test
	  public void testModify() throws Exception {
		    driver.get("https://mern-crud.herokuapp.com/");
		    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click();
		    driver.findElement(By.name("name")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys("Fredy Rios");
		    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
		    pause(2000);
		    String modifiedText=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
		    assertEquals("Successfully updated!",modifiedText);
		    deleteFirst();
	  }
	  @Test
	  public void testDeleteFirst() throws Exception {
		    driver.get("https://mern-crud.herokuapp.com/");
		  	List table1 = driver.findElements(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		    tableRowNum=table1.size();
		    System.out.println(tableRowNum);
		    setFirstInfo();
		    deleteFirst();
		    List table2 = driver.findElements(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		    createCopy();
		    pause(1000);
		    assertEquals((tableRowNum-1),table2.size());
		  }

	  @AfterClass
	  public static void tearDown() throws Exception { 
	      driver.quit();
	  }
	  
	  public void deleteFirst() {
		  driver.navigate().refresh();
		  driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).click();
		  pause(1500);
		  driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();	
		  pause(500);
	  }
	  
	  public void createCopy() {
		  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
		  driver.findElement(By.name("name")).click();
		  driver.findElement(By.name("name")).clear();
		  driver.findElement(By.name("name")).sendKeys(name);
		  driver.findElement(By.name("email")).click();
		  driver.findElement(By.name("email")).clear();
		  driver.findElement(By.name("email")).sendKeys(email);
		  driver.findElement(By.name("age")).click();
		  driver.findElement(By.name("age")).clear();
		  driver.findElement(By.name("age")).sendKeys(age);
		  if(gender.equals("m")) {
			  driver.findElement(By.xpath("//div[3]/div[2]/div/div")).click();
			  driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]")).click();
		  }else if(gender.equals("f")) {
			  driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
			  driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[2]")).click();
		  }
		  driver.findElement(By.xpath("//form/button")).click();
		    pause(500);
	  }
	  
	  public void setFirstInfo() {
		this.name=driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]")).getText();
  		this.email=driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[2]")).getText();
  		this.age=driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[3]")).getText();
  		this.gender=driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[4]")).getText();
	  }
	  
	  public boolean isAlreadyAdded() {
		  List table = driver.findElements(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		  boolean a=false;
		  for(int i=1;i<table.size();i++) {
			  WebElement emailCol = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr["+i+"]/td[2]"));
			  if(emailCol.getText().equals("omar@gmail.com")) {
				  a=true;
			  }
		  }
		  System.out.println(a);
		  return a;
	  }
	  
	  public void deleteAdded() {
		  driver.navigate().refresh();
		  List table = driver.findElements(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		  for(int i=1;i<table.size();i++) {
			  WebElement emailCol = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr["+i+"]/td[2]"));
			  if(emailCol.getText().equals("omar@gmail.com")) {
				  driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr["+i+"]/td[5]/button[2]")).click();
				  pause(1500);
				  driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();	
				  pause(500); 
			  }
		  }
		  driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).click();
		  pause(1500);
		  driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();	
		  pause(500);
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
