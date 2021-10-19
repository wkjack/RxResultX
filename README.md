# RxResultX
Anroid ActivityResult简化处理，支持回调、Rxjava两种方式

1. 项目的build.gradle添加引用

    ```
    buildscript {
	    repositories {
	        google()
	        mavenCentral()
	        maven { url 'https://maven.aliyun.com/repository/public' }
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:4.1.3'
        }
	}

    allprojects {
        repositories {
            google()
            mavenCentral()
            maven { url 'https://maven.aliyun.com/repository/public' }
            maven { url 'https://jitpack.io' }
        }
    }

    task clean(type: Delete) {
        delete rootProject.buildDir
    }
    ```
2. 在module的build.gradle添加依赖

	```
	dependencies {
		implementation 'com.github.wkjack:RxResultX:1.5.0'
		api 'io.reactivex.rxjava2:rxjava:2.1.7'
		
		//Arouter
		api "com.alibaba:arouter-api:1.5.0"
		annotationProcessor "com.alibaba:arouter-compiler:1.2.2"
	}
	```
