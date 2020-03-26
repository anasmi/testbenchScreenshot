package org.vaadin.anastasia;

import com.vaadin.testbench.parallel.ParallelTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class TakeScreenShoot extends ParallelTest {

        public File takeScreenshot(String destinationFile){
                return generateImage(destinationFile);
        }

        public File takeScreenshot(String destinationFile, String URL){
                getDriver().get(URL);
                return generateImage(destinationFile);
        }

        private File generateImage(String destination){
                File file = ((TakesScreenshot) driver).getScreenshotAs(
                        OutputType.FILE);
                File destFile=new File(destination);
                try {
                        FileUtils.copyFile(file,destFile);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                file.deleteOnExit();
                return destFile;
        }
}
