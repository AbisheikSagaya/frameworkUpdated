package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Exceptions.AlertMessageMismatchException;
import Exceptions.ElementCountMismatchException;
import Exceptions.TabsCountMismatchException;
import Exceptions.TitleMismatchException;
import Exceptions.URLMismatchException;

public class Browser {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private WebElement ele;
	private List<WebElement> eleList;
	private Select dplist;
	
	private static int datanum=4;
	
	public Browser() {
		String exepath = "D:\\framework\\framework\\resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exepath);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
	}
	public WebDriver getDriver()
	{
		return driver;
	}
	public void openBrowser(ArrayList<String> data)
	{
		driver.get(data.get(3));
		driver.manage().window().maximize();
		
	}
	public void closeBrowser(ArrayList<String> data)
	{
		driver.quit();
	}
	public void goBack(ArrayList<String> data)
	{
		driver.navigate().back();
	}
	public WebElement find(String path)
	{
		ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		return ele;
	}
	public List<WebElement> findall(String path)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		eleList=driver.findElements(By.xpath(path));
		return eleList;
	}
	public void clickOn(ArrayList<String> data)
	{
		ele=this.find(data.get(datanum));
		ele.click();
	}
	public void sendData(ArrayList<String> data)
	{
		ele=this.find(data.get(datanum));
		ele.sendKeys(data.get(datanum+1));
	}
	public void selectElement(ArrayList<String> data)
	{
		ele=this.find(data.get(datanum));
		dplist= new Select(ele);
		dplist.selectByIndex(Integer.parseInt(data.get(datanum+1)));
	}
	public void verifyTabsCount(ArrayList<String> data) throws TabsCountMismatchException
	{
		int i=driver.getWindowHandles().size();
		if( ( Integer.parseInt(data.get(3)) ) == i )
		{
			return;
		}
		throw new TabsCountMismatchException();
	}
	public void reload(ArrayList<String> data)
	{
		driver.navigate().refresh();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verifyTitle(ArrayList<String> data) throws TitleMismatchException
	{
		String s=driver.getTitle();
		if(s.equals(data.get(3)))
		{
			return;
		}
		throw new TitleMismatchException();
	}
	public void verifyUrl(ArrayList<String> data) throws URLMismatchException
	{
		String s=driver.getCurrentUrl();
		if(!(s.equals(data.get(3))))
		{
			System.out.println(s+" != "+data.get(3));
			throw new URLMismatchException();
		}
		
	}
	public void verifyAlert(ArrayList<String> data) throws AlertMessageMismatchException
	{
		if(!(driver.switchTo().alert().getText().equals(data.get(3))))
		{
			System.out.println(driver.switchTo().alert().getText()+"!="+data.get(3));
			throw new AlertMessageMismatchException();
		}
	}
	public void wait(ArrayList<String> data) 
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verifyElementCount(ArrayList<String> data) throws ElementCountMismatchException 
	{
		if((this.findall(data.get(4))).size()!=Integer.parseInt(data.get(5)))
		{
			System.out.println(data.get(4)+" "+this.findall(data.get(4)).size()+" "+Integer.parseInt(data.get(5)));
			throw new ElementCountMismatchException();
		}
	}
	
}
