package com.course.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class test {
    public static void main(String[] args) {
        //F:\springcloud项目

        System.setProperty("webdriver.chrome.driver","F:\\springcloud项目\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        String tilte = driver.getTitle();
        System.out.println(tilte);
        driver.close();
    }
}
