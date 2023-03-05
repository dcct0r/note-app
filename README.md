# :thought_balloon: Note application

## :point_right: This is a demo version of the app. At the moment, text notes and voice notes have been developed, with the ability to add new ones and delete old ones. :point_left:

AudioNote       |  Note
:-------------------------:|:-------------------------:
![AudioNote](https://user-images.githubusercontent.com/111187206/222932894-7badaeff-7e1b-4c48-b17a-41380aa6b55c.jpg) | ![Note](https://user-images.githubusercontent.com/111187206/222932895-b763f984-d816-477e-9c16-1749fbf93a06.jpg)

## Required dependencies
java

dependencies {

    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.8.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    def room_version = "2.5.0"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-rxjava2:$room_version"

    def lifecycle_version = "2.1.0"
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

}

## Version's used
Android Studio: ```Electric eel 2022.1.1```
Room  database version: ```2.5.0```
## Minimal version of the Android
```Android API is 23 (6.0)```
