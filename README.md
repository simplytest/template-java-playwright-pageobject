# Template for Java with Playwright, TestNG and Allure

This is a template project for setting up a Playwright framework with Java, Maven and TestNG following the Page Object Model (POM) design pattern. It provides a starting point for automated UI testing using Playwright.

## Getting Started

### Installation

1. Create a new repository using this template or clone the repository to your local machine:

   ```bash
   git clone https://github.com/simplytest/template-java-playwright-pageobject.git
   ```
2. Navigate to the project directory

   ```bash
   cd template-java-playwright-pageobject
   ```
3. Install dependencies

    ```bash
    mvn clean install
    ```
4. Set the base URL in src/test/resources/config/config.properties 

### Running Tests

To run the tests, use the following Maven command:

```bash
mvn test
```

This project is set up to generate Allure reports for better visualization of test results. After running the tests, you can generate the Allure report using the following command:

```bash
allure serve allure-results
```

### Contributing

If you'd like to contribute, please fork the repository and create a new pull request.

### License

This project is licensed under the MIT License - see the LICENSE file for details.