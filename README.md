# RxResultX
Anroid ActivityResult简化处理，支持回调、Rxjava两种方式

1. 项目的build.gradle添加引用

	```
	buildscript {
		repositories {
			google()
			jcenter()
  		}
		dependencies {
        		classpath 'com.android.tools.build:gradle:4.1.1'
		}
	}
	
	allprojects {
		repositories {
			google()
			jcenter()
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
		api 'com.wkjack:RxResultX:1.2.0'
		api 'io.reactivex.rxjava2:rxjava:2.1.7'
		
		//Arouter
		api "com.alibaba:arouter-api:1.5.0"
		annotationProcessor "com.alibaba:arouter-compiler:1.2.2"
	}
	```
