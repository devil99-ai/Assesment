# Selenium Grid Framework – UI & API Test Automation

This project is a **Java Maven automation framework** built using **Selenium, TestNG, Rest Assured, Page Object Model (POM), Apache POI, Extent Reports, and WebDriverManager**. It automates UI workflows for the OrangeHRM demo application and API validation for the Swagger PetStore User APIs.

The framework supports:

- UI automation using Selenium WebDriver
- Cross-browser execution on Chrome, Edge, and Firefox
- TestNG-based test execution and grouping
- Excel-driven test data using Apache POI
- API testing using Rest Assured
- HTML execution reports using Extent Reports
- Screenshot capture on test failure
- Local execution with configurable browser and application URL

---

## Table of Contents

- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Framework Structure](#framework-structure)
- [How to Run the Tests](#how-to-run-the-tests)
- [Configuration](#configuration)
- [Test Data](#test-data)
- [Reports](#reports)
- [Dependencies Used](#dependencies-used)
- [Troubleshooting](#troubleshooting)

---

# Project Overview

The framework contains both **UI** and **API** automation in a single Maven project.

### UI Automation

The UI tests automate the OrangeHRM demo application using Selenium and the Page Object Model pattern.

Covered workflow includes:

1. Login
2. Create employee/user
3. Search employee
4. Edit employee details
5. Verify employee details
6. Delete employee
7. Logout

### API Automation

The API layer validates Swagger PetStore User APIs using Rest Assured.

Implemented operations include:

- Create User (`POST /user`)
- Get User (`GET /user/{username}`)
- Update User (`PUT /user/{username}`)
- Delete User (`DELETE /user/{username}`)

---

# Prerequisites

Install the following before executing the project.

| Software | Recommended |
|---|---|
| Java JDK | 11 or later |
| Maven | 3.8+ |
| Eclipse IDE / IntelliJ IDEA | Latest version |
| TestNG Plugin | Required for IDE execution |
| Google Chrome | Latest |
| Microsoft Edge | Latest |
| Mozilla Firefox | Latest |

> WebDriverManager is included in the project dependencies, although the current browser initialization directly creates ChromeDriver, EdgeDriver, and FirefoxDriver. Modern Selenium browser management should therefore be available in a compatible Selenium setup, but the installed browser must still be present on the machine.

---

# Setup Instructions

## 1. Extract the Project

Extract the project ZIP and open the root folder:

```text
seleiumFramework
```

## 2. Import as Maven Project

### Eclipse

1. Open Eclipse.
2. Go to **File → Import**.
3. Select **Existing Maven Projects**.
4. Browse to the `seleiumFramework` folder.
5. Select the project and click **Finish**.
6. Wait for Maven dependencies to download.

### IntelliJ IDEA

1. Open IntelliJ.
2. Select **Open**.
3. Choose the `seleiumFramework` folder.
4. Import as a Maven project.
5. Allow Maven to download dependencies.

## 3. Update Maven Dependencies

From the project root run:

```bash
mvn clean install -DskipTests
```

This downloads all required dependencies and compiles the project without executing tests.

## 4. Verify Configuration

Open:

```text
src/test/java/environmentVariables/config.properties
```

Update values if required.

Example:

```properties
execution_env = local
browser = firefox
os = Windows
url = https://opensource-demo.orangehrmlive.com/
hub_url = http://localhost:4444/wd/hub
```

The browser and OS values in the TestNG XML files are currently used during execution because the test setup receives them through TestNG parameters.

---

# Framework Structure

```text
seleiumFramework
│
├── pom.xml
├── grouping.xml
├── crossbrowser.xml
├── docker.jpg
├── Image/
│   └── cicd_jenkins_thumbnail.png
│
├── reports/
│   └── Generated Extent HTML Reports
│
├── testData/
│   └── UserData.xlsx
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── selenium/gridFramework/
│   │           └── App.java
│   │
│   └── test/
│       └── java/
│           │
│           ├── basePackage/
│           │   └── baseClass.java
│           │
│           ├── testLayer/
│           │   └── testLogin.java
│           │
│           ├── POMPackage/
│           │   ├── POMlogin.java
│           │   ├── POMregisterUser.java
│           │   ├── SearchUser.java
│           │   ├── POMDeleteUser.java
│           │   └── POMLogout.java
│           │
│           ├── Endpoints/
│           │   ├── Routes.java
│           │   └── UserEndpoint.java
│           │
│           ├── Payload/
│           │   └── User.java
│           │
│           ├── dataProvider/
│           │   └── excelDataProvider.java
│           │
│           ├── utility/
│           │   ├── XLUtilities.java
│           │   └── TimeUtils.java
│           │
│           ├── reports/
│           │   ├── ExtentManager.java
│           │   └── ExtentReportManager.java
│           │
│           └── environmentVariables/
│               ├── config.properties
│               └── excel.xlsx
│
└── test-output/
    └── TestNG Generated Reports
```

## Package Responsibilities

| Package / File | Responsibility |
|---|---|
| `basePackage.baseClass` | WebDriver initialization, browser setup, configuration loading, screenshot utility |
| `testLayer.testLogin` | Main TestNG test class containing UI and API test cases |
| `POMPackage` | Page Object classes for Login, Register, Search, Delete, and Logout workflows |
| `Endpoints` | Rest Assured endpoint methods and API routes |
| `Payload` | POJO model used for API request payloads |
| `dataProvider` | Excel-based TestNG Data Providers |
| `utility` | Excel utilities and timeout configuration |
| `reports` | Extent Report creation and TestNG listener implementation |
| `environmentVariables` | Application configuration and Excel test data |
| `testData` | API user data workbook |

---

# Test Execution Flow

The execution sequence implemented in `testLayer.testLogin` is:

```text
TestNG Suite
      │
      ▼
Load config.properties
      │
      ▼
Launch Browser
      │
      ▼
Login to OrangeHRM
      │
      ▼
Create Employee
      │
      ▼
Search & Edit Employee
      │
      ▼
Delete Employee
      │
      ▼
Logout
      │
      ▼
Create API User
      │
      ▼
Delete API User
      │
      ▼
Generate Extent Report
```

The UI and API test methods are prioritized from **1 to 6** in the same TestNG class.

---

# How to Run the Tests

## Option 1: Run the Default Test Suite from Eclipse

This is the simplest execution method for the current project.

1. Right-click `grouping.xml`.
2. Select **Run As → TestNG Suite**.
3. TestNG launches Chrome on Windows according to the XML parameters.
4. After execution, the Extent HTML report is generated inside the `reports` folder.

The default suite currently executes:

```text
testLayer.testLogin
```

with:

```xml
browser = chrome
os = Windows
```

---

## Option 2: Run Cross Browser Suite

Open `crossbrowser.xml` and execute it as a TestNG Suite.

The suite is configured for:

| Browser | OS |
|---|---|
| Chrome | Windows |
| Edge | Windows |
| Firefox | Windows |

Execution is configured with:

```xml
parallel="tests"
thread-count="5"
```

This allows TestNG to execute browser tests in parallel.

### Important

The current `crossbrowser.xml` references this listener:

```xml
utility.ExtentReportManager
```

However, the listener class present in the project is:

```text
reports.ExtentReportManager
```

Before running `crossbrowser.xml`, update the listener entry to:

```xml
<listener class-name="reports.ExtentReportManager"/>
```

This ensures the suite points to the available listener implementation.

---

## Option 3: Run from Command Line

### Compile the project

```bash
mvn clean test-compile
```

### Execute the TestNG suite

Because the project uses TestNG XML suites and does not currently define a Maven Surefire suite configuration, execute the suite using the TestNG runner after building the classpath.

#### Windows (Command Prompt)

```bat
mvn dependency:build-classpath -Dmdep.includeScope=test -Dmdep.outputFile=cp.txt
set /p CP=<cp.txt
java -cp "%CP%;target\test-classes;target\classes" org.testng.TestNG grouping.xml
```

#### Linux / macOS

```bash
mvn dependency:build-classpath -Dmdep.includeScope=test -Dmdep.outputFile=cp.txt
CP=$(cat cp.txt):target/test-classes:target/classes
java -cp "$CP" org.testng.TestNG grouping.xml
```

Generated reports will be available after execution.

---

# Running Individual Tests

From Eclipse or IntelliJ:

1. Open `src/test/java/testLayer/testLogin.java`.
2. Right-click the class.
3. Select **Run As → TestNG Test**.

You can also execute individual methods such as:

- `login()`
- `createUser()`
- `DeleteuserFromUI()`
- `LogoutFromUI()`
- `APITestPostuser()`
- `APITestDeleteuser()`

> Since later UI tests depend on earlier workflow steps, running them individually may require valid application state and suitable test data.

---

# Configuration

Configuration is maintained in:

```text
src/test/java/environmentVariables/config.properties
```

Available properties include:

| Property | Purpose |
|---|---|
| `execution_env` | Execution environment setting |
| `browser` | Default browser value stored in config |
| `os` | Operating system value |
| `url` | OrangeHRM application URL |
| `hub_url` | Selenium Grid hub URL |
| `username` | Optional application username |
| `password` | Optional application password |
| `employeeName` | Optional employee value |
| `newPassword` | Optional password value |
| `port` | SMTP port |
| `emailHostname` | SMTP server hostname |

### Browser Initialization

Browser selection is handled inside `baseClass.java` using TestNG parameters.

Supported browsers:

- Chrome
- Edge
- Firefox

---

# Test Data

## UI Test Data

Excel workbook:

```text
src/test/java/environmentVariables/excel.xlsx
```

Data Providers use:

| Sheet | Used For |
|---|---|
| Sheet1 | Login credentials |
| Sheet2 | Employee creation and edit data |

## API Test Data

Workbook:

```text
testData/UserData.xlsx
```

Used for:

- API User creation
- API Username deletion

---

# Reports

The framework generates two types of reports.

## Extent Report

Generated in:

```text
reports/
```

File format:

```text
ExtentReport_yyyyMMdd_HHmmss.html
```

Features include:

- Passed tests
- Failed tests
- Skipped tests
- Exception details
- Screenshots attached on failure
- System information

## TestNG Report

Generated automatically in:

```text
test-output/
```

Open:

```text
test-output/index.html
```

for the TestNG execution summary.

---

# Dependencies Used

All dependencies are defined in `pom.xml`.

| Dependency | Version | Purpose |
|---|---:|---|
| Selenium Java | 4.27.0 | UI automation |
| Selenium Server | 4.0.0-alpha-2 | Selenium server/grid support |
| Selenium Remote Driver | 4.27.0 | Remote WebDriver support |
| TestNG | 7.10.2 | Test execution framework |
| Rest Assured | 5.5.1 | API testing |
| Apache POI | 5.3.0 | Excel file handling |
| Apache POI OOXML | 5.3.0 | `.xlsx` support |
| Apache POI Scratchpad | 5.3.0 | Additional POI components |
| Apache POI Excelant | 5.3.0 | Excel validation utilities |
| Extent Reports | 5.1.2 | HTML reporting |
| Extent PDF Report | 2.12.0 | PDF report integration library |
| Commons Email | 1.6.0 | Email utility support |
| Commons IO | 2.18.0 | File operations |
| Log4j API | 2.24.3 | Logging API |
| Log4j Core | 2.24.3 | Logging implementation |
| WebDriverManager | 5.9.2 | Browser driver management |

---

# Framework Design

The project follows the **Page Object Model (POM)** architecture.

```text
                TestNG Test Layer
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
   Page Object Model          API Endpoint Layer
          │                         │
          ▼                         ▼
 Selenium WebDriver         Rest Assured Requests
          │                         │
          └────────────┬────────────┘
                       ▼
               Application / APIs
                       │
                       ▼
                Extent Reporting
```

### Benefits

- Better code maintainability
- Reusable page actions
- Separation of UI and API logic
- Data-driven execution
- Easier report generation
- Scalable browser support

---

# Troubleshooting

## Maven dependencies are not downloading

Run:

```bash
mvn clean install -DskipTests
```

Then refresh the Maven project inside your IDE.

## Browser does not launch

Verify:

- Chrome, Edge, or Firefox is installed.
- The browser name in the TestNG XML is correct.
- Selenium dependencies are downloaded successfully.

## TestNG suite does not execute

Ensure the TestNG plugin is installed in Eclipse or IntelliJ and run the XML file as a **TestNG Suite**.

## Cross-browser suite listener error

Change:

```xml
utility.ExtentReportManager
```

to:

```xml
reports.ExtentReportManager
```

inside `crossbrowser.xml`.

## Reports are not generated

Confirm that the `reports` folder exists and that the TestNG listener `reports.ExtentReportManager` is configured in the executing suite.

---

# Execution Summary

### Recommended execution steps

```text
1. Import project as Maven project
2. Run mvn clean install -DskipTests
3. Verify config.properties
4. Open grouping.xml
5. Run As → TestNG Suite
6. Review reports/ExtentReport_*.html
7. Review test-output/index.html
```

---

# Notes

- The project combines **UI automation** and **API automation** in one TestNG test class.
- Test data is maintained in Excel files and consumed through TestNG Data Providers.
- Browser execution is controlled through TestNG XML parameters rather than only the `browser` value in `config.properties`.
- The Selenium Grid hub URL is stored in `config.properties`, but the current browser initialization method uses local browser drivers in `baseClass.java`.

