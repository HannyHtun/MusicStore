Steps to make an app on Firebase
======================================
**Step 1**
 * First Go to http://firebase.google.com
	* [Click on Go to Console](https://firebase.google.com/console/)
	* Click on **Create New Project**
	* Type in Project name & Country
	* Select Add Firebase to Your android app
![FireBase Step 1](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/step1.png)

**Step 2**

![FireBase Step 2](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/step2.png)

**Step 3**

 - Here you will see the google-services.json was downloaded to your machine.
 - place that file to project **app** folder.

![FireBase Step 3](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/step3.png)

**Step 4**

- add this line to project root level Gradle file (`<project>/build.gradle`)

        classpath 'com.google.gms:google-services:3.0.0'

- add this line to(`<project>/<app-module>/build.gradle`)

        apply plugin: 'com.google.gms.google-services'
- Click **FINISH**

![FireBase Step 4](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/step4.png)

**Step 5**

-you will see as following figure

![FireBase Step 5](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/step5.png)

**Step 6(Optional)**

- import Data from json

- [Click here to view the json](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/musicstore-2fb5b-export.json)

![FireBase Step 6](https://raw.githubusercontent.com/winhtaikaung/MusicStore/master/firebase/step6.png)