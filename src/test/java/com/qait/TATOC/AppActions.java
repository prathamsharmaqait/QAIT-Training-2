package com.qait.TATOC;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;



public class AppActions 
{
	
	
	static WebDriver driver;
	
	By RepaintBox2_link = By.linkText("Repaint Box 2");
	//By ParentFrame = By.xpath("//iframe[@id="child""]);
	By Proceed_link = By.linkText("Proceed");
	By Box1 = By.xpath("//div[text()='Box 1']");
	By Box2 = By.xpath("//div[text()='Box 2']");
	By Main_Frame = By.xpath("//iframe[@id = 'main']");
	By Child_Frame = By.xpath("//iframe[@id='child']");
	
	
	By DragBox = By.xpath("//div[@id='dragbox']");
	By DropBox = By.xpath("//div[@id='dropbox']");
	
	By LaunchPopupWindow_link = By.linkText("Launch Popup Window");
	By nameTextBox = By.xpath("//input[@id='name']");
	By SubmitButton = By.id("submit");
	
	By GenerateToken_link = By.linkText("Generate Token");
	By TokenSpan = By.id("token");
	
	By BasicCourse_link = By.linkText("Basic Course");
	By GreenBox = By.xpath("//div[@class ='greenbox']");
	
	
	
	public WebElement Element(String elementToken) throws IOException{
		FileReader File = new FileReader("TestData/Locators.txt");
		BufferedReader br = new BufferedReader(File);
		String line;
		ArrayList<String> elementLines = new ArrayList<String>();
		
		while ((line = br.readLine())!= null){
			elementLines.add(line);
		}
		
		br.close();
		
		String[] actualElementline = null;
		
		for (String elementLine : elementLines) {
			if (elementLine.startsWith(elementToken)) {
				actualElementline = elementLine.split(" ", 3);
			}
		}
		
		actualElementline[2] = actualElementline[2].replaceAll("\\$\\{.+\\}", "");
		System.out.println("Check Point 1: " + actualElementline[2].toString());
		System.out.println("Check Point 2: " + actualElementline[1].toString());
		System.out.println("Check Point 3: " + actualElementline[0].toString());
		By _WebelementBy = getBy(actualElementline[1].trim(), actualElementline[2].trim());
	        
	    WebElement we = null;
	     we = driver.findElement(_WebelementBy);
	    
		return we;
	}
	
    public enum Locators {
		id, name, classname, css, xpath, linktext;
	}
	
	private By getBy(String locatorType, String locatorValue) {
		
        switch (Locators.valueOf(locatorType)) {
            case id:
            {
            	System.out.println("Check point 1 inside getby");
                return By.id(locatorValue);
               
            }
            case xpath:
                return By.xpath(locatorValue);
            case css:
                return By.cssSelector(locatorValue);
            case name:
                return By.name(locatorValue);
            case classname:
                return By.className(locatorValue);
            case linktext:
                return By.linkText(locatorValue);
            default:
                return By.id(locatorValue);
        }
	}
	
	
	public void test_Locators() throws IOException{
		
		driver.switchTo().defaultContent();
    	driver.switchTo().frame(0);
    	WebElement weBox1 = Element("Box1");
    	 String _box1Color = getElementColor(weBox1);
    	 
    	 driver.switchTo().frame("child");
    	 WebElement weBox2 = Element("Box2");
    	 String _box2Color = getElementColor(weBox2);
    	 
    	 if (_box1Color.equals(_box2Color))
     	{
     		driver.switchTo().parentFrame();
     		clickElement(Proceed_link);
     	}
     	else{
     		driver.switchTo().parentFrame();
     	clickElement(RepaintBox2_link);
     	test_Locators();
     	}
    	 
	//System.out.println(Element("Box1").toString());
	}
	
	public WebElement findElement(By _element){
		return driver.findElement(_element);
	}
		
	public void clickElement(By _element){
		findElement(_element).click();
	}
	
	public AppActions(){
		driver = new FirefoxDriver();
		LaunchWebsite();
		
	}
	
    public void LaunchWebsite()
    {
    	driver.get("http://10.0.1.86/tatoc/basic/frame/dungeon");
    	driver.manage().window().maximize();
    }
    
    public void reloadPage(){
    	driver.navigate().refresh();
    }
    
    public String getElementColor(WebElement we){
    	return we.getCssValue("background-color");
    	
    }
    
    public void testFrameSwitch(){
    	driver.switchTo().frame(0);	
    }
    
    
    public void repaintBox2(){
    	driver.switchTo().defaultContent();
    	driver.switchTo().frame(0);
     WebElement weBox1 = findElement(Box1) ;
     String _box1Color = getElementColor(weBox1);
     
      	driver.switchTo().frame("child");
     WebElement weBox2 = findElement(Box2) ;   
     String _box2Color = getElementColor(weBox2);
    	
    	if (_box1Color.equals(_box2Color))
    	{
    		driver.switchTo().parentFrame();
    		clickElement(Proceed_link);
    	}
    	else{
    		driver.switchTo().parentFrame();
    	clickElement(RepaintBox2_link);
    	repaintBox2();
    	}
    	
    }
    
    public void dragDrop(){
    	Actions act = new Actions(driver);
    	WebElement _DragBox = findElement(DragBox);
    	WebElement _DropBox = findElement(DropBox);
    	
    	act.dragAndDrop(_DragBox, _DropBox).build().perform();
    	clickElement(Proceed_link);
    }
    
    public void PopupWindows() {
    	clickElement(LaunchPopupWindow_link);
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String winHandleBefore = driver.getWindowHandle();
    	
    	for(String winHandle : driver.getWindowHandles()){
    	    driver.switchTo().window(winHandle);
    	}
    	System.out.println("Check URL:"+driver.getCurrentUrl());

    	
    	findElement(nameTextBox).sendKeys("Test Name");
    	clickElement(SubmitButton);
    	
    	driver.switchTo().window(winHandleBefore);
    	clickElement(Proceed_link);
    }
    
    public void CookieHandling(){
    	clickElement(GenerateToken_link);
    	String _text = findElement(TokenSpan).getText();
    	
    	String[] output = _text.split(" ");
    	String _tokenValue = output[1];

    	Cookie _cookie = new Cookie("Token", _tokenValue);
    	driver.manage().addCookie(_cookie);
    	clickElement(Proceed_link);
    	
    }
    
    public void GridGate(){
    	clickElement(BasicCourse_link);
    	clickElement(GreenBox);
    }
    
    public void LaunchHRIS(){
    	driver.get(getUrl());
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    }
    
    public void HrisLogin() throws IOException{
    	String _username = getUsername();
    	String _password = getPassword();
    	
    	Element("username_TextBox").sendKeys(_username);
    	Element("password_TextBox").sendKeys(_password);
    	
    	
    }
    
    private String getPassword() {
    	String _password = readDataFromFile("password");
    	System.out.println(_password);
		return _password;
	}

	public String getUrl(){
    	String _url = readDataFromFile("url");
    	System.out.println(_url);
		return _url;
    	
    }
    
    public String getUsername(){
    	String username = readDataFromFile("username");
    	System.out.println(username);
		return username;
    	
    }
    
    public static String readDataFromFile(String Property) {
        try {
            Properties prop = loadProperties("TestData/TestData.txt");
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

	private static Properties loadProperties(String filePath) throws IOException {
		  Properties properties = null;
	      InputStream inputStream = null;
	      File file = new File(filePath);
          inputStream = new FileInputStream(file);
          if (inputStream != null) {
              properties = new Properties();
              properties.load(inputStream);
          }
          return properties;
	}
    
}
