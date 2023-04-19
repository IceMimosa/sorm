# SORM

## 注意

公司版本使用: scala2.12.10

```xml
<repositories>
    <repository>
        <id>dd-maven-releases</id>
        <name>dd-maven-releases</name>
        <url>https://artifactory.nioint.com/artifactory/dd-maven-public-virtual</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>org.sorm-framework</groupId>
    <artifactId>sorm</artifactId>
    <version>0.3.22-NIO-SNAPSHOT</version>
</dependency>
```

## 描述

SORM is an object-relational mapping framework designed to eliminate boilerplate and maximize productivity. It is absolutely abstracted from relational side, automatically creating database tables, emitting queries, inserting, updating and deleting records. This all functionality is presented to the user with a simple API around standard Scala's case classes. 

For more info, tutorials and documentation please visit the [official site](http://sorm-framework.org).

## Supported databases

* MySQL
* PostgreSQL
* H2
* HSQLDB
* Oracle (experimental)

## Supported Scala versions

2.10, 2.11

## Maven

SORM is distributed in Maven Central, here's a dependency to the latest release version:

    <dependency>
    <groupId>org.sorm-framework</groupId>
    <artifactId>sorm</artifactId>
    <version>0.3.21</version>
    </dependency>

## SBT

    libraryDependencies += "org.sorm-framework" % "sorm" % "0.3.21"

---

[![Build Status](https://travis-ci.org/sorm/sorm.png?branch=master)](https://travis-ci.org/sorm/sorm)
