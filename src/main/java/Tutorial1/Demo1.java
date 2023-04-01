package Tutorial1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Demo1 {
	
	public static WebDriver driver;
	static int validLinkCounter=0;
	static int brokenLinkCounter=0;
	static int otherDomainUrl=0;
	static String keyword ="redbus";
	static URLConnection urlc;
	static HttpURLConnection huc;
	static int a;
	

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.edge.driver", "C:\\Users\\Shree\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		 driver=new EdgeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
				
		driver.get("https://www.redbus.in/");
		
		Thread.sleep(7000);
		
		List<WebElement>ls=driver.findElements(By.tagName("a"));
		
		System.out.println("total links-----"+ ls.size());
	
		for (WebElement abc : ls) {
			String weburl =abc.getAttribute("href");
			
			if (!weburl.contains(keyword)) {
				otherDomainUrl++;
				continue;
				
			}
			
			if (weburl == null || weburl.isEmpty()) {
				continue;
			}
			
			try {
				URL url =new URL(weburl);
				urlc =url.openConnection();
				huc =(HttpURLConnection) urlc;
				
			}catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				a=huc.getResponseCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (a>=400) {
				brokenLinkCounter++;
			}
			
			else {
				validLinkCounter++;
			}
			
						
		}
	System.out.println("other domain url" + otherDomainUrl);
	System.out.println("valid link" + validLinkCounter);
	System.out.println("broken link"+ brokenLinkCounter);
	
	
	
	}

}























