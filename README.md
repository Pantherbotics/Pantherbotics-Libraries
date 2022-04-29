# Pantherbotics-Libraries
FRC Team 3863's Robot Code Libraries

**INSTRUCTIONS:**
1. Open your existing FRC project setup with vs code or your IDE.
2. Add the following lines to the bottom of settings.gradle. (We utilize this plugin to automatically grab the github repo.)
```
plugins {
    id 'com.alexvasilkov.git-dependencies' version '2.0.1'
}
```
3. Add the following lines to the bottom of build.gradle.
```
git {
    implementation 'https://github.com/Pantherbotics/Pantherbotics-Libraries.git', {
        commit '9b23110'
    }
}
```
5. Check that the string after commit is up to date ('9b23110'). The commit id is located in the image in the red box.
Every time there is another commit, this id will change and you'll need to update build.gradle to get the latest version. For instance, committing this readme created a new id 'e03ccd9'.

![On the main page of this repository you should see the latest code. Near the top of the files panel, to the left of the timestamp of the last commit and the previous commits, there will be the commit id. Copy that to your project if it's not up to date.](https://i.imgur.com/53oLKES.png)

