# platformkit

![CI](https://github.com/bvfnbk/platformkit/actions/workflows/gradle.yml/badge.svg?branch=main)
![DETEKT](https://github.com/bvfnbk/platformkit/actions/workflows/detekt.yml/badge.svg?branch=main)


Operating system and CPU architecture detection; biased. This (very) little API can be used to detect the operating
system and CPU architecture the JVM is running on. Its focus is not to detect _all_ possible operating systems but is
limited to the main desktop application environments

* Linux,
* Mac OS X and
* Windows.

> **Open Issues:**
>
> Document/implement architecture "detection".

## Why not use `System.getProperty("os.name")`?

Yes, by all means, do this. In the end it provides greater flexibility. The selection made by this library most
certainly _is_ too limited for a generic use case.

## Dependency

_write this when releasing is done_

## Usage

```kotlin
import com.github.bvfnbk.platformkit.OperatingSystem

OperatingSystem.get()
```

## Resources

* https://github.com/openjdk/jdk18u/blob/master/src/java.base/windows/native/libjava/java_props_md.c (last accessed:
  07/03/2022)
