# UmlProject
Working Command line in Mac OS X
1. mvn package in project folder
2. java -jar umlparser-0.0.1-SNAPSHOT-jar-with-dependencies.jar FileName FileOutput

FileName is a path string to the source folder. 

Ex - /Users/Derrick/Desktop/umlparser/uml-parser-test-1 

FileOutput is a path string to output the image. Doesn't need to specify extension. 

Ex - /Users/Derrick/Desktop/umltest/test1

Full example

java -jar umlparser-0.0.1-SNAPSHOT-jar-with-dependencies.jar /Users/Derrick/Desktop/umlparser/uml-parser-test-1 /Users/Derrick/Desktop/umltest/test1



# Third party tools
JavaParser - Parse the java files https://github.com/javaparser/javaparser.

PlantUml - Create uml diagrams from plain text http://plantuml.com/.

Junits - Thinking of adding in the future to check work without looking at images

# 3/11 
Looking into implementing unit case to prevent having to look at images. 

# 4/1
Sequence diagrams and executing program in command line. Having trouble with configuring with right pom.xml

Having trouble with changing the pom to have a shorter jar name.

Had trouble with pom detecting the main manifest and finding the dependencies.

# JAR
The pom creates two jar files. One with the dependencies and one without the dependencies. The jar with the dependencies can execute because it has the JavaParser, PlantUml, and Junits.
