# Creating screenshots

A simple Java class utility to create screenshots from your tests. Uses headless Chrome.

## Usage

To build executable jar you should run : `mvn clean package`

You can run the utility: 

```
java -jar ./target/target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar <url> <filename> <width> <height>
```

You can specify only the URL and file name. Some examples:
```
# Take screenshot in default size and use timestamped output file name.
java -jar ./target/target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar https://vaadin.com/

# Take screenshot in default size and use the given filename
java -jar ./target/target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar https://vaadin.com/ frontpage.png

# Specify all the parameters: URL, filename width and height in pixels
java -jar ./target/target/screenshootlibrary-2.0-SNAPSHOT-jar-with-dependencies.jar https://vaadin.com/ frontpage.png 800 600


```
