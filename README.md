# RxResult
Anroid ActivityResult简化处理，支持回调、Rxjava两种方式

1.需要支持Arouter

```gradle
dependencies { 
  api 'com.wkjack:RxResultX:1.1.0'
  api 'io.reactivex.rxjava2:rxjava:2.1.7'
  //Arouter
  api "com.alibaba:arouter-api:1.5.0"
  annotationProcessor "com.alibaba:arouter-compiler:1.2.2"
}
```

2.不需要支持Arouter

```gradle
dependencies { 
  api 'com.wkjack:RxResultX:1.0.0'
  api 'io.reactivex.rxjava2:rxjava:2.1.7'
}
```
