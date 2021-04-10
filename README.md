# TBR
UW-Madison CS 506 Software Engineering Undergraduate Learning Center Tutoring By Request (https://www.engr.wisc.edu/academics/student-services/ulc/tutoring-by-request/) Mobile Application Android Studio 

# Release Information
The current release to demo is Iteration 2.0 with the tag 2.0

# Running the TBR App in Android Studio
In order to run the application in Android Studio, navigate to the "Run" tab on the top of your screen and press "Run...". This will create and emulator and install the app on the emulator. From there you can see the app. Since we won't be creating users, we will be giving test credentials that will give you access to each section.

Student

    * NetId: student
    
    * Password: student
Tutor

    * NetId: tutor1
    * Password: tutor1
Student/Tutor (Stutor)
    * NetId: stutor
    * Password: stutor

# Running the TBR App testing in Android Studio
In order to run the test suite, make sure you have the app selected in the project repository. From here navigate to "TBR/app/src/androidTest/java/com.example.myapplication". From here you can right click on the "com.example.myapplication" and then click "Run" to run all of the tests.

# Known impediments
    * Right now our app runs on one local database, but we are working on getting a remote database set up. Some knowledge barriers are keeping us down, but we are getting close
    * Efficiency is very slow on the application and the app takes up a large chunk of CPU and RAM when running, but we have a feeling this is because we are storing and querying our database on the main thread. Once the remote database is set up, this will allow us to spread out work across the application.
