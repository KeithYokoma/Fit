# Fit

Framework for dispatching various procedure on update application.

# Attention

This library is under development so API may be changed drastically until the first major release.

# Usage

Prepare `VersionModule` to declare what to do when your application is updated.

```java
public class MyModule implements VersionModule {
    @OnVersion({1, 2, 3}) // foo() is called when the app is updated to version code = 1, 2 and 3
    public void foo() {

    }

    @OnVersion(4) // bar() is called when the app is updated to version code = 4
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

