package Selenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinksAndImages {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\msi26.DSONE\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://makemysushi.com/Recipes/how-to-make-sushi-rice");
		
//		driver.findElement(By.name("email")).sendKeys("mukeshsaini1808@gmail.com");
//		driver.findElement(By.name("password")).sendKeys("Chutiye@2");
//		driver.findElement(By.xpath("//div[text()='Login']")).click();
		
		//make a list of all links and image links
		List<WebElement> linkslist = driver.findElements(By.tagName("a"));
		List<WebElement> imglist = driver.findElements(By.tagName("img"));
		linkslist.addAll(imglist);
		System.out.println("Size of links and images: " + linkslist.size());
		
		//make a list of active links only from all links and image links
		List<WebElement> activeLinks = new ArrayList<WebElement>();
		for(int i=0;i<linkslist.size();i++){
			if(linkslist.get(i).getAttribute("href") != null && ( !linkslist.get(i).getAttribute("href").contains("javascript")) && ( !linkslist.get(i).getAttribute("href").contains("mailto"))){
				activeLinks.add(linkslist.get(i));
				System.out.println(linkslist.get(i).getAttribute("href"));
			}
		}
		System.out.println("Size of activeLinks: " + activeLinks.size());
		
		System.out.println("***********************************************************************");
		
		
		//check the href url is correct or not using HttpURLConnection
		for(int j=0;j<activeLinks.size();j++){
			//URL(activeLinks.get(j).getAttribute("href"))---will create url
			HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();
			
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(activeLinks.get(j).getAttribute("href") + "------" + response);
		}
		driver.close();
	}}
