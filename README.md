# Playwright Java POM template

This is a template project for setting up a Playwright framework with Java, Maven and TestNG following the Page Object Model (POM) design pattern. It provides a starting point for automated UI testing using Playwright.

## Getting Started

These instructions will help you set up and run the project on your local machine.

### Prerequisites

- Java JDK (>= 11)

### Installing

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/maximilianschlipf/playwright-pom.git
   ```
2. Navigate to the project directory

   ```bash
   cd playwright-pom
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

### Contributing

If you'd like to contribute, please fork the repository and create a new pull request.

### License

This project is licensed under the MIT License - see the LICENSE file for details.