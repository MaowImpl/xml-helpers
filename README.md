![safexml](https://raw.githubusercontent.com/MaowImpl/safexml/master/logo.png)
<!-- Shields.io -->
![License](https://img.shields.io/github/license/maowimpl/safexml?style=flat-square)
![Open Issues](https://img.shields.io/github/issues/maowimpl/safexml?style=flat-square)
![Open PRs](https://img.shields.io/github/issues-pr/maowimpl/safexml?style=flat-square)
<!---->
**A set of utilities for safely and easily manipulating XML documents.**

## About
This project includes multiple modules for different XML interfaces.

* [DOM]   : Document Object Model
* [SAX]   : Simple API for XML      *(Hasn't started development)*
* [StAX]  : Streaming API for XML   *(Hasn't started development)*

### To-Do:
1. Finish SAX module.
2. Finish StAX module.
3. Supply modules with finished javadocs.

## About Module: DOM
DOM depends on DOM4J and includes wrapper classes for null-safe manipulation of XML documents.<br>
It allows you to use fluent builder syntax that handles all null checking.

### Example

**XML**
```xml
<Root>
  <Element attrib="My attribute value."/>
</Root>
```

**Java**<br>
`doc.root().element("Element").attribute("attrib").value()` == `My attribute value.`

## Installation

### Gradle (Maven Central)

![safexml-dom version](https://img.shields.io/maven-central/v/io.github.maowimpl/safexml-dom?style=flat-square)

```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.maowimpl:safexml-dom:<version>'
}
```
