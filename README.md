# Assignment

This project automates the process of verifying if users from the FanCode city have completed more than 50% of their todo tasks. 
It uses Cucumber for BDD, RestAssured for API interaction, Jackson for JSON parsing, and Junit for Assertion.

## Prerequisites
- Java 8+
- Maven

## Setup Instructions
1. Clone the repository:
   git clone <repository-url>
2. On Command Line go the project path
3. Run Command "mvn clean install"
4. Then run this command "mvn test"

### How It Works:
- We fetch the users and todos data from the APIs.
- We filter users that belong to the FanCode city based on latitude and longitude.
- We calculate the todo completion percentage for each user.
- If any user has a completion percentage below 50%, the test will fail.

This structure ensures code reusability and clarity while assessing logic and testing practices.