# Creating screenshots

A simple Java class utility to create screenshots from your tests. Uses Selenium with headless Chrome.

## Usage

To build the executable jar you should run: `mvn clean package`

You can then run the utility: 

```
java -jar target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar <url> <filename> <width> <height>
```

You can specify only the URL and file name. Some examples:
```
# Take screenshot in default size and use timestamped output file name.
java -jar target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar https://vaadin.com/

# Take screenshot in default size and use the given filename
java -jar target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar https://vaadin.com/ frontpage.png

# Specify all the parameters: URL, filename width and height in pixels
java -jar target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar https://vaadin.com/ frontpage.png 800 600


```
