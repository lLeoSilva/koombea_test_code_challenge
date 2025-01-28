# Rick and Morty API Automation Tests

## Project Overview

This project contains automated tests for the Rick and Morty API, which can be found at [Rick and Morty API Documentation](https://rickandmortyapi.com/documentation/). It is implemented using Java, Rest-Assured, and JUnit 5. The tests cover various endpoints for **characters**, **locations**, and **episodes** and include positive and negative scenarios. The test data is parameterized using **JSON files** for flexibility and scalability.

---

## Prerequisites

1. **Java Development Kit (JDK)**:
   - Ensure JDK 8 or later is installed.
   - Verify installation:
     ```bash
     java -version
     ```

2. **Apache Maven**:
   - Used for project dependency management and build.
   - Verify installation:
     ```bash
     mvn -version
     ```

3. **IDE (Optional)**:
   - Recommended: Visual Studio Code, IntelliJ IDEA, or Eclipse.

4. **Git**:
   - Clone the repository:
     ```bash
     git clone https://github.com/lLeoSilva/koombea_code_challenge.git
     ```

---

## Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       └── com.api.services
│   │           ├── BaseService.java
│   │           ├── CharacterService.java
│   │           ├── LocationService.java
│   │           ├── EpisodeService.java
│   │           └── JsonUtils.java
│   ├── test
│   │   └── java
│   │       └── com.api.tests
│   │           ├── CharacterServiceTests.java
│   │           ├── LocationServiceTests.java
│   │           ├── EpisodeServiceTests.java
│   │           └── NegativeScenariosTests.java
│   └── resources
│       ├── character-data.json
│       ├── character-filter-data.json
│       ├── location-data.json
│       ├── location-filter-data.json
│       ├── episode-data.json
│       ├── episode-filter-data.json
│       └── negative-scenarios.json
├── pom.xml
└── README.md
```

---

## Key Features

### Test Coverage

#### **Character API**
- **Get all characters**
- **Get a single character by ID** (parameterized using JSON data)
- **Get multiple characters by IDs** (parameterized using JSON data)
- **Filter characters**:
  - Filter by `name`, `status`, `species`, `type`, and `gender`

#### **Location API**
- **Get all locations**
- **Get a single location by ID** (parameterized using JSON data)
- **Get multiple locations by IDs** (parameterized using JSON data)
- **Filter locations**:
  - Filter by `name`, `type`, and `dimension`

#### **Episode API**
- **Get all episodes**
- **Get a single episode by ID** (parameterized using JSON data)
- **Get multiple episodes by IDs** (parameterized using JSON data)
- **Filter episodes**:
  - Filter by `name` and `episode`

#### **Negative Scenarios**
- Invalid query parameters for each service.
- Verify error responses and status codes.

---

## Running the Tests

### Running with Maven

#### Run All Tests
```bash
mvn clean test
```

#### Run Specific Test Class
```bash
mvn test -Dtest=CharacterServiceTests
```

#### Run Specific Test Method
```bash
mvn test -Dtest=CharacterServiceTests#testGetAllCharacters
```

---

## Test Data Files

The test data is stored in the `src/test/resources` directory as JSON files:

1. **character-data.json**: Data for single characters.
2. **character-filter-data.json**: Filters for characters.
3. **location-data.json**: Data for single locations.
4. **location-filter-data.json**: Filters for locations.
5. **episode-data.json**: Data for single episodes.
6. **episode-filter-data.json**: Filters for episodes.
7. **negative-scenarios.json**: Data for negative test scenarios.

---

## Dependencies

The `pom.xml` file includes the following dependencies:

1. **Rest-Assured** for API automation.
2. **JUnit 5** for test execution.
3. **Hamcrest** for assertions.
4. **Jackson** for JSON parsing.

To install the dependencies, Maven will automatically download them during the build process.

## License
This project is licensed under the GNU General Public License v3.0. See the LICENSE file for more details.