# Selenium_Java

STEP 1 : Init Maven project

- Create new Java Maven project (Java version 17)

STEP 2 : Install Maven dependencies

- Go to https://mvnrepository.com/ , search for required dependencies, copy code and paste in pom.xml
Rebuild Maven to download dependencies
  - Cucumber Java
  - Cucumber JUnit
  - JUnit
  - Selenium Java

STEP 3 : Create test/resources/features folder

STEP 4 : Create the first feature file

STEP 5 : Install Cucumber Java plugins

STEP 6 : Write the first feature file
- "Feature" keyword          : Determine this is where contains and run scenarios 
- "Background" keyword       : Determine steps that run before every scenario in this feature file
- "Scenario" keyword         : Determine test cases in the feature file
- "Scenario Outline" keyword : Determine test cases that can run multiple data set
- "Example" keyword          : Determine data set
- "Given" keyword            : Test steps represent for pre-conditions
- "When" keyword             : Test steps represent for user's actions
- "Then" keyword             : Test steps represent for validate and verify expected results

STEP 7 : Try to run feature file 
- Use the IDE : Click run icon in feature file
- Use command line : 

STEP 8 : Add step definition files

STEP 9 : Add runner class
- @RunWith(Cucumber.class)
- Set up @CucumberOptions (feature directory and glue code)
(JUnit and Cucumber Junit dependencies are mandatory to set up)
- Here is reason why we need JUnit:

  Feature Files  (.feature)
  ↓
  Step Definitions (Java)
  ↓
  TestRunner.java  (@RunWith from JUnit)
  ↓
  Maven runs JUnit
  ↓
  JUnit calls Cucumber
  ↓
  Selenium interacts with the browser

  **- To point your test compile to your test runner, take a look on set up in pom.xml file**

STEP 10: Run the command line 

- mvn clean test -Dcucumber.filter.tags=@your_tag (make sure your terminal is CMD)

STEP 11: Set up "page" folder (naming as u wish)

This folder contains base page, which declare multiple common method and is an abstract class,
and other classes are represent for specific web page

STEP 12: Create hooks file
- Install WebDriverManager dependency, which allow automate manage and install lastest version 
of web driver
- Use @Before and @After annotations of Cucumber to create setup and teardown method

STEP 13:

