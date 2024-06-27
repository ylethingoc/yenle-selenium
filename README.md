# Selenium Automation Framework

### Author: [Yen Le](ngocyenle0@gmail.com)

## Overview

This repository contains a Selenium-based automation framework using Java, TestNG, Log4j, and follows the Page Object
Model (POM). The framework is designed for automated testing of web applications, focusing on modularity, scalability,
and ease of maintenance.

This addition clarifies that the framework utilizes the POM design pattern, which enhances code
maintainability and readability by separating page-specific elements from test code.

## Features

- **Selenium WebDriver:** Automation tool for web browsers.
- **TestNG:** Testing framework for organizing and running tests.
- **Log4j:** Logging framework for capturing and managing detailed logs.
- **POM:** Design pattern for enhancing test maintenance and readability.
- **Test Data Management:** Support for managing test data through external files (e.g., json, properties files).

## Project Structure

```
yenle-selenium
├── src
│   ├── main
│   │   ├── java
│   │   │     └── com.saferailway
│   │   │         ├── locators
│   │   │         │   ├── BasePageLocators.java
│   │   │         │   └── ...
│   │   │         ├── pages
│   │   │         │   ├── BasePage.java
│   │   │         │   └── ...
│   │   │         └── utils      
│   │   └── resources
│   │       └── log4j2.xml
│   └── test
│       ├── java
│       │   └── com.saferailway
│       │       ├── listeners
│       │           └── TestNGListener.java 
│       │       └── tests 
│       │           ├── TestPage.java
│       │           └── ...     
│       └── resources
│           ├── log4j2.xml
│           └── testdata
│               └── testdata.json
├── .gitignore
├── config.properties
├── pom.xml
├── README.md
└── testng.xml
```

- **`src/main/java`:** Contains utility classes (`Log.java`, `ConfigParser.java`, etc.) and main application code.
- **`src/main/resources`:** Configuration files (`log4j2.xml`, `config.properties`) for the main application.
- **`src/test/java`:** Test classes (`FTTC01.java`, `FTTC02.java`, etc.) using TestNG for test execution.
- **`src/test/resources`:** Test-specific resources including test data (`testdata/testdata.json`) and logging
  configuration (`log4j2.xml`).
- **`testng.xml`:** TestNG configuration file for specifying test suites and parameters.
- **`config.properties`:** Configuration file containing project settings.
- **`pom.xml`:** Dependency management files for building and running the project.
- **`.gitignore`:** is listed as a file in the root directory, which typically contains patterns to exclude files or
  directories from being tracked by Git.

## Notes:
- **`BasePageLocator.java`:** This file defines locators (By locators or WebElement definitions) used across multiple pages, enhancing maintainability in the POM.
- **`PageBase.java`:** This serves as a base class for page objects, encapsulating common methods and interactions with web elements, promoting code reuse and readability in tests.
- **`TestBase.java`:** Contains setup and teardown methods (@Before and @After annotations), handles configuration settings (config.properties), and provides utilities for tests (WebDriver initialization, logging setup), ensuring consistency and efficiency in test execution.
- **`TestNGListener.java`:** This custom TestNG listener enhances the framework’s functionality by allowing custom actions during test execution, such as logging and reporting.
- **`testdata.json`:** This file contains test data in JSON format used by test classes (FTTC01.java, FTTC02.java, etc.) during test execution.

## Setup Instructions

### Prerequisites

- A project's zip file
- IntelliJ IDEA or Eclipse IDE

## Installation

1. **Open the Project:**
    - Open your preferred IDE.
    - Navigate to `File` > `Open` and select the project directory.

2. **Build the Project:**
    - Wait for all dependencies to be automatically downloaded by your build tool (e.g., Maven or Gradle). This step
      ensures all necessary libraries are available for compilation and execution.

3. **Configure `config.properties`:**
    - Locate `config.properties` in the project root or `src/main/resources` directory.
    - Update `config.properties` according to your specific requirements or use the default settings provided.

4. **Run Tests:**
    - Adjust `testng.xml` to specify test cases, parallel execution settings.
    - Trigger the tests using `testng.xml`.

## Extending the Framework

- **Page Object Model (POM):**
    - Enhance the framework by adding more page classes following the Page Object Model pattern for improved code
      organization and reusability.

- **Parallel Execution:**
    - To run tests concurrently, configure TestNG in `testng.xml` to specify thread counts and parallel modes suitable
      for your testing environment.

## Acknowledgments

- Selenium WebDriver
- Maven
- TestNG
- Log4j