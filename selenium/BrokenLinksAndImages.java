/*
print href with response code for all active tags and images.
*/

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

/*
Output:
Size of links and images: 275
https://makemysushi.com/Recipes/how-to-make-sushi-rice#navigation
https://makemysushi.com/
https://makemysushi.com/
https://makemysushi.com/sushi-university
https://makemysushi.com/sushi-recipes
https://makemysushi.com/sushi-essentials
https://makemysushi.com/store
https://makemysushi.com/author/osowakki
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comments
https://www.amazon.com/Nishiki-Medium-Grain-Rice-Pound/dp/B00852ZN2U/ref=as_li_ss_tl?keywords=nishiki+rice&qid=1560712190&s=gateway&sprefix=nishiki&sr=8-4&linkCode=ll1&tag=inpage-sushirice-us-20&linkId=7297379b266af459c861d0a75f57d4cf&language=en_US
http://tapwater.in/
https://makemysushi.com/Recipes/how-to-make-sushi-rice#calc
https://www.amazon.com/Nishiki-Medium-Grain-Rice-Pound/dp/B005M1PBX8/ref=as_li_ss_tl?keywords=nishiki+rice&qid=1560712190&s=gateway&sprefix=nishiki&sr=8-4&linkCode=ll1&tag=calcrice-20&linkId=7297379b266af459c861d0a75f57d4cf&language=en_US
https://www.amazon.com/Marukan-Seasoned-Rice-Vinegar-ounce/dp/B00UR6HALY/ref=as_li_ss_tl?dchild=1&keywords=rice+vinegar&qid=1586279425&refresh=1&sr=8-4&linkCode=ll1&tag=calcvin-20&linkId=b3f30fb82525eca8e8726ed196cda9a6&language=en_US
https://www.amazon.com/Tamanoi-Sushinoko-Sushi-Powder-Vinegared/dp/B0009ZGJCY/?tag=ricepowder-20
https://makemysushi.com/wp-content/uploads/2020/10/Perfect-sushi-rice.mp4
https://makemysushi.com/Recipes/how-to-make-sushi-rice#recipe-tabs-1
https://makemysushi.com/Recipes/how-to-make-sushi-rice#recipe-tabs-2
https://makemysushi.com/Recipes/how-to-make-sushi-rice#recipe-tabs-3
https://makemysushi.com/recipe-type/sushi-preparations
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10657
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10657
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10470
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10470
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10369
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10369
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10317
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10317
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10301
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10301
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10994
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10994
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-11116
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-11116
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10993
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10993
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10996
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10996
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9568
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9568
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9636
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9636
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10995
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10995
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10981
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10981
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10991
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10991
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10982
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10982
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10983
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10983
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10992
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10992
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10984
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10984
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10985
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10985
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10986
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10986
http://jim@pdxprint.com/
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10987
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10987
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10990
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10990
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10988
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10988
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10989
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10989
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10997
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10997
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10998
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10998
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9917
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9917
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9841
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9841
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9818
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9818
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9815
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9815
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9793
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9793
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9765
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9765
https://ichitokyomn.com/
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9702
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9702
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9691
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9691
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9664
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9664
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9665
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9665
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9614
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9614
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9590
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9590
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9633
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9633
http://ccarreras.myctfocbd.com/
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9587
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9587
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9634
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9634
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9579
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9579
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9635
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9635
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9567
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9567
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9637
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9637
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9558
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9558
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9554
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9554
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9522
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9522
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9509
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9509
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9638
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9638
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9493
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9493
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9439
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9439
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9440
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9440
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9428
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9428
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9441
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9441
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9459
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9459
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9404
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9404
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9392
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9392
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9260
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9260
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9529
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9529
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9204
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9204
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9280
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9280
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10321
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10321
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-3#comments
https://makemysushi.com/Recipes/how-to-make-sushi-rice#respond
https://akismet.com/privacy/
https://makemysushi.com/Recipes/how-to-make-sushi-rice
https://makemysushi.com/Recipes/how-to-make-sushi-rice
https://makemysushi.com/Recipes/how-to-make-spicy-tuna-roll
https://makemysushi.com/Recipes/how-to-make-spicy-tuna-roll
https://makemysushi.com/Recipes/how-to-make-california-sushi-rolls
https://makemysushi.com/Recipes/how-to-make-california-sushi-rolls
https://makemysushi.com/Recipes/company-roll
https://makemysushi.com/Recipes/company-roll
https://makemysushi.com/Recipes/dragon-roll
https://makemysushi.com/Recipes/dragon-roll
https://makemysushi.com/Recipes/tuna-wrapped-gunkan-maki
https://makemysushi.com/Recipes/tuna-wrapped-gunkan-maki
https://makemysushi.com/Recipes/epic-sushi-roll-with-bacon
https://makemysushi.com/Recipes/epic-sushi-roll-with-bacon
https://makemysushi.com/Recipes/sushi-leftovers-dinner
https://makemysushi.com/Recipes/sushi-leftovers-dinner
https://makemysushi.com/Recipes/swimmers-roll-sushi-recipe
https://makemysushi.com/Recipes/swimmers-roll-sushi-recipe
https://makemysushi.com/How-to-make-sushi/maki-roll
https://makemysushi.com/How-to-make-sushi/maki-roll
https://makemysushi.com/How-to-make-sushi/nigiri-sushi
https://makemysushi.com/How-to-make-sushi/nigiri-sushi
https://makemysushi.com/How-to-make-sushi/spicy-mayonnaise
https://makemysushi.com/How-to-make-sushi/spicy-mayonnaise
https://makemysushi.com/How-to-make-sushi/inside-out-roll
https://makemysushi.com/How-to-make-sushi/inside-out-roll
https://makemysushi.com/How-to-make-sushi/sushi-grade-fish
https://makemysushi.com/How-to-make-sushi/sushi-grade-fish
https://makemysushi.com/How-to-make-sushi/sushi-sandwich
https://makemysushi.com/How-to-make-sushi/sushi-sandwich
https://makemysushi.com/Sushi-Essentials/rolling-mat
https://makemysushi.com/Sushi-Essentials/rolling-mat
https://makemysushi.com/Sushi-Essentials/nori
https://makemysushi.com/Sushi-Essentials/nori
https://makemysushi.com/Sushi-Essentials/unagi-sauce-kabayaki
https://makemysushi.com/Sushi-Essentials/unagi-sauce-kabayaki
https://makemysushi.com/Sushi-Essentials/kewpie-mayonnaise-japanese-mayo
https://makemysushi.com/Sushi-Essentials/kewpie-mayonnaise-japanese-mayo
https://makemysushi.com/Recipes/how-to-make-sushi-rice#
https://www.facebook.com/Makemysushi/
https://www.instagram.com/explore/tags/makemysushi/
https://makemysushi.com/privacy-policy
https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public
https://makemysushi.com/Recipes/how-to-make-sushi-rice#
https://makemysushi.com/privacy-policy
https://makemysushi.com/Sushi-share/contact-us
https://makemysushi.com/Sushi-share/about-us
Size of activeLinks: 199
***********************************************************************
https://makemysushi.com/Recipes/how-to-make-sushi-rice#navigation------OK
https://makemysushi.com/------OK
https://makemysushi.com/------OK
https://makemysushi.com/sushi-university------OK
https://makemysushi.com/sushi-recipes------OK
https://makemysushi.com/sushi-essentials------OK
https://makemysushi.com/store------OK
https://makemysushi.com/author/osowakki------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comments------OK
https://www.amazon.com/Nishiki-Medium-Grain-Rice-Pound/dp/B00852ZN2U/ref=as_li_ss_tl?keywords=nishiki+rice&qid=1560712190&s=gateway&sprefix=nishiki&sr=8-4&linkCode=ll1&tag=inpage-sushirice-us-20&linkId=7297379b266af459c861d0a75f57d4cf&language=en_US------Service Unavailable
http://tapwater.in/------Forbidden
https://makemysushi.com/Recipes/how-to-make-sushi-rice#calc------OK
https://www.amazon.com/Nishiki-Medium-Grain-Rice-Pound/dp/B005M1PBX8/ref=as_li_ss_tl?keywords=nishiki+rice&qid=1560712190&s=gateway&sprefix=nishiki&sr=8-4&linkCode=ll1&tag=calcrice-20&linkId=7297379b266af459c861d0a75f57d4cf&language=en_US------Service Unavailable
https://www.amazon.com/Marukan-Seasoned-Rice-Vinegar-ounce/dp/B00UR6HALY/ref=as_li_ss_tl?dchild=1&keywords=rice+vinegar&qid=1586279425&refresh=1&sr=8-4&linkCode=ll1&tag=calcvin-20&linkId=b3f30fb82525eca8e8726ed196cda9a6&language=en_US------Service Unavailable
https://www.amazon.com/Tamanoi-Sushinoko-Sushi-Powder-Vinegared/dp/B0009ZGJCY/?tag=ricepowder-20------Service Unavailable
https://makemysushi.com/wp-content/uploads/2020/10/Perfect-sushi-rice.mp4------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#recipe-tabs-1------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#recipe-tabs-2------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#recipe-tabs-3------OK
https://makemysushi.com/recipe-type/sushi-preparations------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10657------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10657------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10470------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10470------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10369------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10369------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10317------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10317------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10301------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10301------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10994------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10994------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-11116------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-11116------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10993------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10993------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10996------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10996------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9568------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9568------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9636------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9636------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10995------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10995------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10981------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10981------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10991------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10991------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10982------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10982------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10983------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10983------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10992------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10992------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10984------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10984------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10985------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10985------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10986------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10986------OK
http://jim@pdxprint.com/------Moved Permanently
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10987------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10987------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10990------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10990------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10988------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10988------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10989------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10989------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10997------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10997------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10998------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10998------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9917------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9917------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9841------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9841------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9818------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9818------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9815------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9815------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9793------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9793------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9765------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9765------OK
https://ichitokyomn.com/------Forbidden
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9702------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9702------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9691------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9691------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9664------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9664------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9665------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9665------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9614------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9614------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9590------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9590------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9633------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9633------OK
http://ccarreras.myctfocbd.com/------Moved Permanently
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9587------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9587------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9634------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9634------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9579------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9579------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9635------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9635------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9567------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9567------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9637------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9637------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9558------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9558------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9554------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9554------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9522------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9522------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9509------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9509------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9638------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9638------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9493------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9493------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9439------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9439------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9440------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9440------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9428------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9428------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9441------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9441------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9459------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9459------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9404------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9404------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9392------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9392------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9260------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9260------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9529------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9529------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9204------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9204------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-9280------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-9280------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-4#comment-10321------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#comment-10321------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice/comment-page-3#comments------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#respond------OK
https://akismet.com/privacy/------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice------OK
https://makemysushi.com/Recipes/how-to-make-spicy-tuna-roll------OK
https://makemysushi.com/Recipes/how-to-make-spicy-tuna-roll------OK
https://makemysushi.com/Recipes/how-to-make-california-sushi-rolls------OK
https://makemysushi.com/Recipes/how-to-make-california-sushi-rolls------OK
https://makemysushi.com/Recipes/company-roll------OK
https://makemysushi.com/Recipes/company-roll------OK
https://makemysushi.com/Recipes/dragon-roll------OK
https://makemysushi.com/Recipes/dragon-roll------OK
https://makemysushi.com/Recipes/tuna-wrapped-gunkan-maki------OK
https://makemysushi.com/Recipes/tuna-wrapped-gunkan-maki------OK
https://makemysushi.com/Recipes/epic-sushi-roll-with-bacon------OK
https://makemysushi.com/Recipes/epic-sushi-roll-with-bacon------OK
https://makemysushi.com/Recipes/sushi-leftovers-dinner------OK
https://makemysushi.com/Recipes/sushi-leftovers-dinner------OK
https://makemysushi.com/Recipes/swimmers-roll-sushi-recipe------OK
https://makemysushi.com/Recipes/swimmers-roll-sushi-recipe------OK
https://makemysushi.com/How-to-make-sushi/maki-roll------OK
https://makemysushi.com/How-to-make-sushi/maki-roll------OK
https://makemysushi.com/How-to-make-sushi/nigiri-sushi------OK
https://makemysushi.com/How-to-make-sushi/nigiri-sushi------OK
https://makemysushi.com/How-to-make-sushi/spicy-mayonnaise------OK
https://makemysushi.com/How-to-make-sushi/spicy-mayonnaise------OK
https://makemysushi.com/How-to-make-sushi/inside-out-roll------OK
https://makemysushi.com/How-to-make-sushi/inside-out-roll------OK
https://makemysushi.com/How-to-make-sushi/sushi-grade-fish------OK
https://makemysushi.com/How-to-make-sushi/sushi-grade-fish------OK
https://makemysushi.com/How-to-make-sushi/sushi-sandwich------OK
https://makemysushi.com/How-to-make-sushi/sushi-sandwich------OK
https://makemysushi.com/Sushi-Essentials/rolling-mat------OK
https://makemysushi.com/Sushi-Essentials/rolling-mat------OK
https://makemysushi.com/Sushi-Essentials/nori------OK
https://makemysushi.com/Sushi-Essentials/nori------OK
https://makemysushi.com/Sushi-Essentials/unagi-sauce-kabayaki------OK
https://makemysushi.com/Sushi-Essentials/unagi-sauce-kabayaki------OK
https://makemysushi.com/Sushi-Essentials/kewpie-mayonnaise-japanese-mayo------OK
https://makemysushi.com/Sushi-Essentials/kewpie-mayonnaise-japanese-mayo------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#------OK
https://www.facebook.com/Makemysushi/------OK
https://www.instagram.com/explore/tags/makemysushi/------OK
https://makemysushi.com/privacy-policy------OK
https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public------OK
https://makemysushi.com/Recipes/how-to-make-sushi-rice#------OK
https://makemysushi.com/privacy-policy------OK
https://makemysushi.com/Sushi-share/contact-us------OK
https://makemysushi.com/Sushi-share/about-us------OK
*/
