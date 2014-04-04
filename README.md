KMPRemote
=========

From the server_work directory in the SVN repo:
Server build status: [![Build Status](https://travis-ci.org/UoE-CE320-2013-Team3/KMPRemote.svg?branch=master)](https://travis-ci.org/UoE-CE320-2013-Team3/KMPRemote)

Building the deployable server:
Unix:
```
  ./gradlew clean build shadowJar
```
Windows:
```
  gradlew.bat clean build shadowJar
```
Building a project for Intellij
Unix:
```
  ./gradlew clean idea
```
Windows:
```
  gradlew.bat clean idea
```

The above command will download gradle if it is not on your computer. After this is done simply run the resulting jar with:
```
  java -jar build/distributions/server_work-unspecified-shadow.jar
```
