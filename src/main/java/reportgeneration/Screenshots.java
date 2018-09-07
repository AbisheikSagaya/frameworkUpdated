package reportgeneration;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	private WebDriver driver;
	private String path;

	public Screenshots(WebDriver d, String p) {
		driver = d;
		path = p;
	}

	public void capture(String name) throws IOException, InterruptedException {
		try 
	    { 
	        driver.switchTo().alert();
	        BufferedImage image;
			try {
				image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				 ImageIO.write(image, "png", new File((path + name + ".png")));
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    {
	    	TakesScreenshot scrShot = (TakesScreenshot) driver;
			File src = scrShot.getScreenshotAs(OutputType.FILE);
			File dsrc = new File(path + name + ".png");
			FileUtils.copyFile(src, dsrc);
	    }
		
	}

}
