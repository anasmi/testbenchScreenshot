# Creating screenshots

A simple class utility to create screenshots from your tests. Currently, there are two methods:

- `public File takeScreenshot(String destinationFile)`
- `File takeScreenshot(String destinationFile, String URL)`

## Using the class

To build jar you should run : `mvn package`
After that you can import the class to your repo via mave using : 

```
<dependency>
    <groupId>org.vaadin.anastasia</groupId>
    <artifactId>screenshootlibrary</artifactId>
    <type>test-jar</type>
    <version>2.0-SNAPSHOT</version>
    <classifier>tests</classifier>
    <scope>system</scope>
    <systemPath>Path_to_jar/screenshootlibrary-2.0-SNAPSHOT.jar</systemPath>
</dependency>
```
Usage example :
```
public class TakeScreenshots  extends TakeScreenShoot {

        @Before
        public void init() {
                getDriver().get("http://localhost:8080");
        }

        @Test
        public void makeScreen(){
                super.takeScreenshot("screenshot/image.png");
        }

        @BrowserConfiguration
        public List<DesiredCapabilities> getBrowserConfiguration() {
                return Arrays.asList(BrowserUtil.chrome());
        }
}

```

## More Information

- [Vaadin Flow](https://vaadin.com/flow) documentation
- [Using Vaadin and Spring](https://vaadin.com/docs/v14/flow/spring/tutorial-spring-basic.html) article

