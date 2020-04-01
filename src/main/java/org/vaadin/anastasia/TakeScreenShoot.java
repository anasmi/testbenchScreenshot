package org.vaadin.anastasia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TakeScreenShoot {

        private static final int DEFAULT_HEIGHT = -1;
        private static final int DEFAULT_WIDTH = -1;

        private TakeScreenShoot() {
                WebDriverManager.chromedriver().setup();
        }

        public File takeScreenshot(String destinationFile, String URL){
                return generateImage(destinationFile, URL, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }

        public File takeScreenshot(String destinationFile, String URL, int width, int height){
                return generateImage(destinationFile, URL, width, height);
        }

        private File generateImage(String destination, String URL, int width, int height) {
                Logger.getGlobal().info("URL: '"+URL+"', Output: '"+destination+"' ("+width+"x"+height+")");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--hide-scrollbars");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
                ChromeDriver driver = new ChromeDriver(options);
                driver.setLogLevel(Level.INFO);
                try {
                        driver.get(URL);

                        // Wait for Vaadin progressbar to disappear§
                        WebDriverWait wait = new WebDriverWait(driver, 15);
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.v-loading-indicator")));

                        if (width < 0 || height < 0) {
                                driver.manage().window().maximize();
                        } else {
                                driver.manage().window().setSize(new Dimension(width, height));
                        }
                        File file = ((TakesScreenshot) driver).getScreenshotAs(
                                OutputType.FILE);
                        File destFile = new File(destination);
                        try {
                                FileUtils.copyFile(file, destFile);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        file.deleteOnExit();
                        return destFile;
                } finally {
                        driver.quit();
                }
        }

        public static void main(String[] argv) {
                String url = "http://localhost:8080/";
                if (argv.length > 0) {
                        url = argv[0];
                }
                String filename = cleanUp(url + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")) +".png");
                int width = 1024;
                int height = 768;
                if (argv.length > 1) {
                        filename = argv[1];
                }
                if (argv.length >2) {
                        width = Integer.parseInt(argv[2]);
                }
                if (argv.length >3) {
                        height = Integer.parseInt(argv[3]);
                }
                new TakeScreenShoot().takeScreenshot(filename, url, width, height);
        }

        private static String cleanUp(String filename) {
                filename = filename.replace("http://","");
                filename = filename.replace("https://","");
                filename = filename.replace("/-","-");
                filename = filename.replace(":","-");
                filename = filename.replace("/", "-");
                return filename;
        }
}
