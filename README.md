

**Given**
-----
 * web application with url http://automationpractice.com/index.php;
 * 3 [test cases](TESTCASES.md);
 * 3 automated tests.
 
initial version of tests is already present in order to save one's time on extracting locators. 

**Explore**
----
You need to improve given automated tests as much as you can by designing your own solution to develop such kinds of tests for similar applications.

Your solution can include:
* logging;
* taking screenshot on failed tests;
* generation human readable report;
* generating random values for insignificant test data, for example, for new user;
* WebDriver factory;
* encapsulation layers like test data, logic of tests, actions on web pages and so on;
* configurator:
  * run tests in parallel mode;
  * ability to run tests for different browsers/OS by configuring;
  * ability to run tests for different environments(urls) by configuring/by command-line.
* reading test data from file, for example, the name of dress, size and color in the checkout test.

**Solution**
------------------------
Solution includes:
* logging; - log4j
* taking screenshot on failed tests; - ExtendReports
* generation human readable report; - ExtendReports
* generating random values for insignificant test data, for example, for new user; - Generator File in Utils
* WebDriver factory; - using https://github.com/bonigarcia/webdrivermanager/ 
* encapsulation layers like test data, logic of tests, actions on web pages and so on; - POM MODEL
* configurator:
  * run tests in parallel mode; - TestNG
  * ability to run tests for different browsers/OS by configuring; - Environment Variable
  * ability to run tests for different environments(urls) by configuring/by command-line. 

