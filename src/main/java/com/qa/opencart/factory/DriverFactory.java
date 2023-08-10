package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameWorkException;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager om;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public WebDriver initDriver(Properties prop) {
		om = new OptionsManager(prop);
		String browserName = prop.getProperty("browsername");
		highlight = prop.getProperty("highlight").trim();
		browserName = browserName.trim().toLowerCase();
		String url = prop.getProperty("url");
		System.out.println(url.trim());
		System.out.println("Browser Name: "+browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(om.getChromeOptions());	
			tlDriver.set(new ChromeDriver(om.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(om.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver());
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(om.getEdgeOptions());
			tlDriver.set(new EdgeDriver());
		}
		else {
			System.out.println("Please pass right browser name");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);
		return getDriver();
	}
	public synchronized static WebDriver getDriver() {
		return tlDriver.get(); 
	}
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fi = null;
		//mvn clean install -Denv="qa"
				String envName = System.getProperty("env");
				System.out.println("Running testcases on :"+envName);
				try {
				if(envName == null) {
					System.out.println("Running testcases on qa:");
					 fi = new FileInputStream("./src/test/resources/config/qa.config.properties");
				}
				else {
					switch(envName.toLowerCase().trim()) {
					case "qa":
					 fi = new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;
					case "dev":
					 fi = new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;
					case "stage":
						 fi = new FileInputStream("./src/test/resources/config/stage.config.properties");
						break;
					case "prod":
						 fi = new FileInputStream("./src/test/resources/config/config.properties");
						break;
					default:
						System.out.println("Wrong env is passed no need to run testcases");
						throw new FrameWorkException("Wrong env passed");
						
					}
				}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			try {
				prop.load(fi);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		return prop;
	}
	public static String getScreenshot() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile,destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
