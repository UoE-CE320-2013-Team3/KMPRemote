KMPRemote
=========
Building the deployable server

From the server_work directory in the SVN repo run:

  gradlew -b buildjar.gradle jar

The above command will download gradle if it is not on your computer. After this is done simply run the resulting jar with:

  java -jar build/jar/BluetoothClient.jar
