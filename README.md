# UITests


### Suported browsers

* firefox
* chrome

### How to run tests
* to run all tests via command line use command: mvn clean test
* use such command with tag @smoke: mvn clean test -Dcucumber.options="--tags @smoke"

### How to generate Allure report after running tests
    * enter in IDEA terminal command: "mvn allure:report"
    * report will be generated in folder target/site/allure-maven-plugin