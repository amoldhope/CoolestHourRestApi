# Introduction
getTomorrowCoolestHour - This application will return tomorrows coolest hours based on the zipcode given by user.

# Source of Data
Weather data is read from http://openweathermap.org/ using API http://api.openweathermap.org/data/2.5/weather?q={citycode}&appid={apiKey}


# How to run
This app has embedded tomcat server. In order to run this website execute below maven command
mvn spring-boot:run

It will start the embedded tomcat server on default port 8080

# How to Use
- Access the website using http://localhost:8080/{citycode}
- Enter the city zipcode
- Hit Submit button
- It will display the coolst hour for tomorrow


# Technologies used
- RestTemplate
- Spring Boot
- Maven
- Embedded Tomcat Server



