# Test Summary Document
**Project:** RR QA Automation Assignment  
**Website:** [https://tmdb-discover.surge.sh](https://tmdb-discover.surge.sh)  
**Framework:** Java · Selenium · TestNG · RestAssured · ExtentReports · Log4j2 · Maven  
**Prepared By:** Girish Kumar S  
**Date:** 3-4 November 2025

---

## 1. Objective
To validate the functionality, reliability, and stability of the TMDB Discover demo application, focusing on filtering, pagination, and API behavior using automated tests.

---

## 2. Test Scope
| Category | In Scope                                                              | Out of Scope |
|-----------|-----------------------------------------------------------------------|--------------|
| **UI Functional** | ✅ Filters, Search, pagination, movie list rendering, rating selection | ❌ Login, user preferences (not available) |
| **API Testing** | ✅ Endpoint reachability and content type                              | ❌ Backend DB validation |
| **Reporting** | ✅ Extent HTML reports with screenshots                                | ❌ External dashboard |

---

## 3. Test Strategy
- **UI Functional Tests:** Selenium + TestNG validating filters, search, genre, type, year, and pagination.
- **API Tests:** RestAssured to verify endpoint availability and status codes.
- **Negative Tests:** Slug refreshes, invalid filters, pagination beyond limits.
- **Reporting & Logging:** ExtentReports with screenshots, Log4j2 for execution logs.
- **Assertions:** SoftAssert for grouped verifications in UI; Hard Assert for API.


---

## 4. Key Tests Executed

| Test ID | Test Name                      | Objective                               | Result                |
|---------|--------------------------------|-----------------------------------------|-----------------------|
| TC-01   | Verify Category Filter         | Filter updates movie list               | ✅ Pass                |
| TC-02   | Verify Type Filter             | Select “TV Shows” category              | ✅ Pass                |
| TC-03   | Verify Genre Filter            | Apply “Comedy” genre                    | ✅ Pass                |
| TC-04   | Verify Year Filter             | Filter by year 2022                     | ✅ Pass                |
| TC-05   | Verify Rating Filter           | Select 4★ movies                        | ✅ Pass                |
| TC-06   | Verify Pagination              | Navigate through pages                  | ✅ Pass (partial)      |
| TC-07   | Verify Page Slugs              | Refresh with `/popular`                 | ❌ Fail (known issue)  |
| TC-08   | Validate API `/`               | Returns HTML shell                      | ✅ Pass (expected)     |
| TC-09   | Validate `/popular` endpoint   | Check 200/404                           | ✅ Pass (returns HTML) |
| TC-10   | UI Element Validation          | Posters, titles visible                 | ✅ Pass                |
| TC-11   | Screenshot Capture             | Validate failure screenshot             | ✅ Pass                |
| TC-12   | Report Generation              | Validate ExtentReport output            | ✅ Pass                |
| TC-13   | verify filter and search input | Validate multiple filters functionality | ❌ Fail (New issue)    |

---

## 5. Defects Found

| ID   | Title                                                                                    | Steps to Reproduce                                                                                                                              | Expected Result                                                | Actual Result                           | Severity | Status |
|------|------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------|-----------------------------------------|----------|--------|
| D-01 | `[UI] Filters becomes unresponsive in search results page`                               | 1. Open Discover site <br>2. search "WAR" and select any filter except "TYPE" and observe                                                       | Movies updated as per filter                                   | List unchanged                          | High     | Open   |
| D-02 | `[API] /popular returns HTML instead of JSON`                                            | 1. Call `GET /popular`                                                                                                                          | JSON response                                                  | Returns `index.html` (React app)        | Low      | open   |
| D-03 | `[UI] Movie title is missing in the search results when there is active filter in place` | 1. Open Discover site <br>2.apply any filters <br>3. Search for any movie name and observe                                                      | Movie names should present in the search results               | No movie name in search result          | High     | Open   |
| D-04 | `[UI] Search input remains in the input fields throught all navigation and filters`      | 1. Open Discover site <br>2.Search for any movie <br>3. Once page loads with search result, perform any option through out the site and observe | Search field should cleared after navigating to different page | Search input remains in the input field | Medium   | Open   |
| D-05 | `[Routing] Refresh on /popular slug causes blank page`                                   | 1. Go to `/popular` <br>2. Refresh                                                                                                              | Page reloads normally                                          | Blank page / crash                      | High     | Open   |

---

## 6. Root Cause Summary
- **API limitation:** The `/popular` route serves HTML (React SPA), not JSON, making API validation limited.
- **Front-end bug:** filter selection doesn’t trigger re-render.
- **Slug refresh issue:** Static hosting configuration missing fallback route.

---

## 7. Test Design Techniques
| Technique | Used For                                     | Example                              |
|------------|----------------------------------------------|--------------------------------------|
| **Equivalence Partitioning** | Year / Rating filters                        | 2020–2024 valid range                |
| **Boundary Value Analysis** | Ratings (1★–5★) and year                              | 1, 5                                 |
| **Decision Table Testing** | Filter combinations                          | Category × Type × Genre x search     |
| **Error Guessing** | Refresh `/popular`                           | Expect routing error                 |
| **State Transition Testing** | Filter → Clear → Reapply and page navigation | Verify reset and navigation behavior |

---

## 8. Conclusion
All critical UI flows were successfully automated and validated.  
Known issues are due to demo site limitations (static SPA behavior).  
Framework demonstrated:
- Clean modular design (POM + Utility + Reporting)
- CI/CD compatibility
- Readable, maintainable automation structure

**Overall Result:** ❌ **Automation Framework Failed Validation (with known demo limitations and Newly identified issues)**

---

## Prepared By
**Girish Kumar S**  
Quality Assurance Engineer  
girish261998@gmail.com 
🔗 [LinkedIn Profile](https://www.linkedin.com/in/girish-kumar-s-86378014a/)

---
