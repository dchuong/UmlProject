# UmlProject
Working Command line in Mac OS X
1. mvn package in project folder
2. java -jar umlparser-0.0.1-SNAPSHOT-jar-with-dependencies.jar FileName FileOutput

FileName is a path string to the source folder. 

Ex - /Users/Derrick/Desktop/umlparser/uml-parser-test-1 

FileOutput is a path string to output the image. Don't need to specify extension. 

Ex - /Users/Derrick/Desktop/umltest/test1

Full example

java -jar umlparser-0.0.1-SNAPSHOT-jar-with-dependencies.jar /Users/Derrick/Desktop/umlparser/uml-parser-test-1 /Users/Derrick/Desktop/umltest/test1

Having trouble with changing the pom to have a shorter jar name.

# Third party tools
JavaParser - Parse the java files https://github.com/javaparser/javaparser.

PlantUml - Create uml diagrams from plain text http://plantuml.com/.

# 3/11 
Looking into implementing unit case to prevent having to look at images. 

# 4/1
Sequence diagrams and executing program in command line.

Had trouble with pom detecting the main manifest and finding the dependencies.

# 4/22

Fixed the pom.xml
# JAR
The pom creates two jar files. One with the dependencies and one without the dependencies. The jar with the dependencies can execute because it has the JavaParser, PlantUml, and Junits.
