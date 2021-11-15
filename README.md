# Friends App
## _ANDROID DESIGN PATTERN MVVM DEMO - KOTLIN_


 [![kotlin](https://img.shields.io/badge/Kotlin-1.4.xxx-brightgreen.svg)](https://kotlinlang.org/)  [![coroutines](https://img.shields.io/badge/coroutines-asynchronous-red.svg)](https://kotlinlang.org/docs/reference/coroutines-overview.html)  
 [![Dagger-Hilt](https://img.shields.io/badge/Dagger-2.xx-orange.svg)](https://google.github.io/dagger/)  [![Android-ViewBinding ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVVM ](https://img.shields.io/badge/Clean--Code-MVVM-brightgreen.svg)](https://github.com/googlesamples/android-architecture)  ![MVVM ](https://img.shields.io/badge/Clean--Code-MVP-brightgreen.svg)
[![Build Status](https://app.bitrise.io/app/b7eabce000fac983/status.svg?token=i6oJjdA4ZD4wM6NDA5cB7g&branch=master)](https://app.bitrise.io/app/b7eabce000fac983)![MVVM3](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)

![](screenshots/Screenshot_1623703809.png)
![](screenshots/Screenshot_1623703814.png)


Model-View-ViewModel (ie MVVM) is a template of a client application architecture, proposed by John Gossman as an alternative to MVC and MVP patterns when using Data Binding technology. Its concept is to separate data presentation logic from business logic by moving it into particular class for a clear distinction.
You can also check [**MVP**](https://github.com/ahmedeltaher/Android-MVP-Architecture)

**Why Promoting MVVM VS MVP:**
- ViewModel has Built in LifeCycleOwerness, on the other hand Presenter not, and you have to take this responsiblty in your side.
- ViewModel doesn't have a reference for View, on the other hand Presenter still hold a reference for view, even if you made it as weakreference.
- ViewModel survive configuration changes, while it is your own responsiblities to survive the configuration changes in case of Presenter. (Saving and restoring the UI state)

**MVVM Best Pratice:**
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.


**What is Coroutines ?**
-------------------

 **Coroutines :**
Is light wight threads for asynchronous programming, Coroutines not only open the doors to
asynchronous programming, but also provide a wealth of other possibilities such as concurrency, actors, etc.

----------

**Coroutines VS RXJava**
-------------------
They're different tools with different strengths. Like a tank and a cannon, they have a lot of overlap but are more or less desirable under different circumstances.
        - Coroutines Is light wight threads for asynchronous programming.
        - RX-Kotlin/RX-Java is functional reactive programming, its core pattern relay on
        observer design pattern, so you can use it to handle user interaction with UI while you
        still using coroutines as main core for background work.

**How does Coroutines concept work ?**
------------
 - Kotlin coroutine is a way of doing things asynchronously in a sequential manner. Creating a coroutine is a lot cheaper vs creating a thread.


**When I can choose Coroutines or RX-Kotlin to do some behaviour ?**
--------------------------
 - Coroutines : *When we have concurrent tasks , like you would fetch data from Remote connections
 , database , any background processes , sure you can use RX in such cases too, but it looks like
  you use a tank to kill ant.*
 - RX-Kotlin : *When you would to handle stream of UI actions like : user scrolling , clicks ,
 update UI upon some events .....ect .*


**What is the Coroutines benefits?**
-----------------------------

 - Writing an asynchronous code is sequential manner.
 - Costing of create coroutines are much cheaper to crate threads.
 - Don't be over engineered to use observable pattern, when no need to use it.
 - parent coroutine can automatically manage the life cycle of its child coroutines for you.


**Handle Retrofit with Coroutines**
-----------------------------

![8399](https://user-images.githubusercontent.com/1812129/68318999-e93b0680-00bd-11ea-9d76-058222c7a654.png)

 - Add Coroutines to your gradle file

>     // Add Coroutines
>      implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1'
>      implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1'
>     // Add Retrofit2
>     implementation 'com.squareup.retrofit2:retrofit:2.9.0'
>     implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
>     implementation 'com.squareup.okhttp3:okhttp:4.7.2'
>    // Hilt
>    implementation 'com.google.dagger:hilt-android:2.37'
>   kapt 'com.google.dagger:hilt-compiler:2.37'

DEMO :
![](screenshots/Screenshot_1623703809.png)
![](screenshots/Screenshot_1623703814.png)

