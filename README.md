# Overview
A set of utilities for easily modifying/accessing XML documents.<br>

## About
This project includes multiple modules for different XML implementations.<br>
The main ones planned out are:
* DOM (Document Object Model) - Almost finished.
* SAX (Simple API for XML) - Hasn't started development.
* StAX (Streaming API for XML) - Hasn't even gotten it's own subproject.

### About: DOM module
DOM depends on DOM4J and includes helpful wrapper classes for null-safe accessing of XML documents.<br>
It handles all null-checking for you to reduce if statements and make DOM manipulation easier to read.
#### Example
**XML**
```
<Root>
  <Element attrib="My attribute value."/>
</Root>
```
**Java**<br>
`doc.root().element("Element").attribute("attrib").value()` == `My attribute value.`

## To-Do:
1. Polish up DOM module.
2. Finish SAX module.
3. Supply modules with javadocs.

## Jitpack
Gradle Repository:
```groovy
maven { url 'https://jitpack.io' }
```

### Artifacts
All: `com.github.maowimpl.xml-helpers:xml-helpers-all:version`<br>
DOM: `com.github.maowimpl.xml-helpers:xml-helpers-dom:version`<br>
SAX: `com.github.maowimpl.xml-helpers:xml-helpers-sax:version` (Unfinished)<br>
