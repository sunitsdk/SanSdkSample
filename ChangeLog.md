# Changelog

### 3.1.0.2 (May 11,2021)
- Configure custom Maven repository

#### 1. Download the [sunit-sdk.zip](https://github.com/sunitsdk/SanSdkSample/files/6456503/sunit-sdk.zip). Create a sunit_sdk folder in your project's top-level directory, and place the entire contents of the zip in that folder. Your directory structure should look like <project_root_dir>/sunit_sdk/com/sunit/...., can refrence [SanSdkSample](https://github.com/sunitsdk/SanSdkSample/tree/master/sunit-sdk)

#### 2. Open your app's project level build.gradle file.
```
allprojects {
    repositories {
        maven {
            url "${project.rootDir}/sunit_sdk"
        }
        google()
        jcenter()

    }
}
```
#### 3. Reference it as a dependency in your app-level build.gradle file: Open the app-level build.gradle file and add the line in bold below to the dependencies section.
    //San open sdk
    implementation "com.sunit:mopub-san:3.1.0.2"


### 3.0.3.0 (April 21,2021)

- Opt performance of video ad
- Opt UI for Interstitial Ad

### 3.0.2.2 (March 16,2021)

- Fix image load error.


### 3.0.1.8 (January 19,2021)

- Rename package and sdk name.


### 3.0.0.0 (January 8,2021)

- OpenSDK initial release
