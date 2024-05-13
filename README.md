# weather-api
This is a springboot app.
To build: ./gradlew build
To run: ./gradlew bootrun
# to run withjar : java -jar ./build/libs/weather-api-0.0.1-SNAPSHOT.jar

API: 
##localhost:7776/weather/station?latitude=36&longitude=55 : find stations closed to this altitude
##localhost:7776/weather/observations?station=0770W : find statioin detail data
##localhost:7776/weather/high-low?station=0770W : find low and high temerature from observation response of each day
