# RR QA Automation Assignment

**Repository:** `rr-qa-automation-assignment`  
**Tech Stack:** Java · Selenium WebDriver · TestNG · RestAssured · ExtentReports · Log4j2 · Maven · Lombok

---

## Testing Strategy

### Objective
To validate the functional correctness of the TMDB Discover demo website [https://tmdb-discover.surge.sh](https://tmdb-discover.surge.sh)  
focusing on **filtering**, **pagination**, and **API behavior**, ensuring the platform behaves consistently across user actions.

### Approach
1. **UI Functional Tests**
   - Validate filters: Category, Type, Genre, Year, Rating.
   - Verify dynamic updates to movie lists after filter changes.
   - Test pagination navigation and consistency of results.
   - Check UI elements such as movie posters and titles.
   - Validate Search functionality filter out the movies as expected

2. **API Validation (Baisc)**
   - Validate API endpoint reachability.
   - Ensure response status and structure (HTML).

3. **Negative Testing**
   - Invalid URLs (e.g., `/xyz`)
   - Pagination beyond available pages.
   - Invalid input values for filters (future years, invalid genres, search).
   - Verify invalid search filter (eg. '@#$')

4. **Cross-verification**
   - Validate accessibility attributes (`alt`, `aria-checked`) for UI controls.

---


## Framework Overview

### Library & Purpose
- Selenium 4.25.0 -  Web UI automation
- TestNG 7.9.0 -  Test orchestration
- RestAssured 5.4.0 - API testing and JSON parsing
- ExtentReports 5.0.9 - HTML reporting with screenshots
- Log4j2 2.23.1 - Logging framework
- WebDriverManager 5.9.2 - Automatic browser driver setup
- Commons IO 2.15.1 - File and screenshot handling
- Maven - Build and dependency management

---

## Running the Tests

### Prerequisites
- JDK 17 or higher  
- Maven 3.9+  
- Chrome browser (latest)

### Run Command
```bash
mvn clean test
