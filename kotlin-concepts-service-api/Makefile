#makefile for purplelab-api-medicalconcepts
compile:
	@gradle build -x test

clean:
	@gradle clean

execute:
	@java -jar -Dspring.profiles.active=dev build/libs/puj-api-medicalconcepts-2.0.0-SNAPSHOT.jar
