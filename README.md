# Overview
A set of utilities for safely and easily manipulating XML documents.

## About
This project includes multiple modules for different XML interfaces.

* [DOM]   : Document Object Model
* [SAX]   : Simple API for XML      *(Hasn't started development)*
* [StAX]  : Streaming API for XML   *(Hasn't started development)*

### To-Do:
1. Finish SAX module.
2. Supply modules with finished javadocs.

## About Module: DOM
DOM depends on DOM4J and includes helpful wrapper classes for null-safe manipulation of XML documents.<br>
It handles all null-checking for you to reduce if statements and make DOM manipulation easier to read.

### Example

**XML**
```xml
<Root>
  <Element attrib="My attribute value."/>
</Root>
```

**Java**<br>
`doc.root().element("Element").attribute("attrib").value()` == `My attribute value.`

# Installation

## Gradle (Maven Central)

```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.maowimpl:safexml-dom:<version>'
}
```
