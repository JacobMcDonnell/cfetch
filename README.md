# cfetch
This is a small program written in java to get the current liturgical day for the
Catholic liturgical calendar. 

The program is reliant on the api [liturgical Calendar](http://calapi.inadiutorium.cz/). The program implements the [JSON-java](https://github.com/stleary/JSON-java) library to parse the JSON output.

## Building

Cfetch is written in IntelliJ IDEA and thus should be built using it. To build a jar file, in IDEA click on the build menu and click on build artifacts.

## Running

To run cfetch, run the following command or put it in a shell script:
```
java -jar cfetch.jar
```

To run with a different day than today, run:
```
java -jar cfetch.jar 2022/12/01
```
The date must be formatted as YYYY/MM/DD.