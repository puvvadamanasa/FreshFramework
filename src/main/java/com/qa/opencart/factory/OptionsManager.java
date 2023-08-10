package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	private Properties prop;
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println(Boolean.parseBoolean(prop.getProperty("headless").trim()));
			co = new ChromeOptions();
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co = new ChromeOptions();
			co.addArguments("--incognito");
		}
		return co;
		
	}
    public FirefoxOptions getFirefoxOptions() {
    	fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo = new FirefoxOptions();
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo = new FirefoxOptions();
			fo.addArguments("--incognito");
		}
		return fo;
		
	}
    public EdgeOptions getEdgeOptions() {
    	eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			eo = new EdgeOptions();
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			eo = new EdgeOptions();
			eo.addArguments("--incognito");
		}
		return eo;
    }
	

}
