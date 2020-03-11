# Technical Test

## Setup

You will need the following:

[Java SE](https://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html/)

[IntelliJ IDEA](https://www.jetbrains.com/idea/)

[Firefox Webdriver](https://selenium.dev/documentation/en/webdriver/driver_requirements/) geckodriver downloaded and added to your system path


## Usage
1. Open the project in IntelliJ IDEA:
- File -> Open... -> (Select the path to the "TechnicalTest-master" folder)
2. Right click on src/test/resource/features/find_restaurants.feature and choose Run 'Feature: find_restaurants'


## FAQ

#### Class not found
Right-click the 'pom.xml' file and select Maven -> Reimport
#### Step definitions are not recognized
In the Project view inside IntelliJ:

Right-click on src/test/java and select Mark Directory as 'Test Sources Root'

Right-click on src/test/resource and select Mark Directory as 'Test Resources Root'

#### Runtime errors

Under Run -> Run Configurations:

Make sure Main class field is set to 'io.cucumber.core.cli.Main'

Glue field is set to 'stepdefs'

Feature or folder path field is set to the path of where 'find_restaurants.feature' is

Set Program Arguments to use --plugin instead of --format 
