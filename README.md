# Fit

[![Gitter](http://img.shields.io/badge/Gitter-Join%20Chat-brightgreen.svg?style=flat)](https://gitter.im/KeithYokoma/Fit?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Fit-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1174)
[![License](http://img.shields.io/badge/License-Apache%202-brightgreen.svg?style=flat)](https://github.com/KeithYokoma/Fit/blob/master/LICENSE.md)
[![CircleCI](https://circleci.com/gh/KeithYokoma/Fit.svg?style=shield)](https://circleci.com/gh/KeithYokoma/Fit.svg?style=shield)

Framework for dispatching various procedure on update application.

![Fit](https://farm3.staticflickr.com/2780/4285120077_2eae97a495_o_d.jpg)
[Photo License CC by NC-ND](https://creativecommons.org/licenses/by-nc-nd/2.0/)

# Attention

This library is under development so API may be changed drastically until the first major release.

# Usage

Prepare `VersionModule` to declare what to do when your application is upgraded or newly installed.

```java
public class MyModule implements VersionModule {
    @VersionCode({1, 2, 3}) // foo() is called when the app is updated to version code = 1, 2 and 3
    public void foo() {

    }

    @VersionCode(4) // bar() is called when the app is updated to version code = 4
    public void bar() {

    }
}
```

If you like to have them executed only when it is an upgrade, use `UpgradeOnly` annotation.

```java
public class MyModule implements VersionModule {
    @VersionCode({1, 2, 3}) // foo() is called when the app is updated to version code = 1, 2 and 3
    public void foo() {

    }

    @VersionCode(4)
    @UpgradeOnly // bar() is called when the app is updated to version code = 4, and not called when the app is newly installed
    public void bar() {

    }
}
```

Register your `VersionModule` when `Application#onCreate()` called.

```java
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fit.initialize(this, new MyModule());
    }
}
```

And execute update procedure like this.

```java
Fit.getInstance().execute();
```

Currently `Fit` has a synchronous call interface only.
Asynchronous call will be introduced in the near future.

# ProGuard Settings

Set configuration for ProGuard to keep annotation and annotated methods.

```java
-keepattributes *Annotation*
-keepclassmembers class * {
    @jp.yokomark.fit.VersionCode *;
}
```

# License

Apache License v2

```
Copyright (C) 2014 KeithYokoma, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
```

