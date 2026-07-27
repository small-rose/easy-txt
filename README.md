<div align="center">

<a href="README_CN.md">中文版</a>

![](logo.jpg)

# easy-txt: Delimited Text File Reader/Writer for Java

**Read & write delimited text files like EasyExcel. Annotation-based Bean mapping, 2G+ large file support, batch processing.**

[![Maven Central](https://img.shields.io/maven-central/v/com.small/easy-txt?color=brightgreen)](https://search.maven.org/artifact/com.small/easy-txt)
[![Java CI](https://img.shields.io/github/actions/workflow/status/sm-rose/easy-txt/maven.yml?logo=github&label=build)](https://github.com/sm-rose/easy-txt/actions)
[![Java](https://img.shields.io/badge/Java-1.8+-blue)](https://java.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-green)](LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/sm-rose/easy-txt)](https://github.com/sm-rose/easy-txt/releases)
[![GitHub Stars](https://img.shields.io/github/stars/sm-rose/easy-txt?logo=github)](https://github.com/sm-rose/easy-txt)
[![Blog](https://img.shields.io/badge/blog-@small.rose-ff69b4)](https://zhangxiaocai.cn)

</div>

```java
// Read: 3 lines
EasyTxt.read(file, UserBean.class, ",", new BeanReadListener()).doRead();

// Write: 3 lines
EasyTxt.write(file, UserBean.class, ",", pageNo, pageSize, this::queryPage).doWrite();
```

---

## Features

- **Annotation-based Bean mapping** — `@TxtFiled`, `@DateFormatFiled`, `@NumberFormatFiled`
- **Two index modes** — Fixed-index & auto-ascending index
- **Batch read/write** — Paginated callbacks for large datasets
- **2G+ large file support** — 111s write / 105s read for 2.52GB (see benchmark below)
- **Rich type conversion** — String, int, long, BigDecimal, Date, LocalDate, LocalDateTime, and more
- **Custom separator** — CSV, pipe (`|`), tab, or any delimiter
- **Zero heavy framework** — No Spring, pure Java + Hutool + Commons IO

### Comparison

| Feature | easy-txt | OpenCSV | Apache Commons CSV |
|---------|----------|---------|-------------------|
| Annotation Bean mapping | ✅ | ❌ (via MappingStrategy) | ❌ |
| Batch page callback | ✅ | ❌ | ❌ |
| 2G+ large file | ✅ | ✅ | ✅ |
| Custom separator | ✅ | ✅ | ✅ |
| Type converters built-in | ✅ (15+ types) | ⚠️ (basic) | ❌ |
| Date/Number format annotations | ✅ | ❌ | ❌ |

---

## Installation

### Maven

```xml
<dependency>
    <groupId>com.small</groupId>
    <artifactId>easy-txt-core</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.small:easy-txt-core:1.1.0'
```

---

## Quick Start

### 1. Define Bean

```java
@Data
public class UserBean {
    @TxtFiled(index = 0)
    private String name;

    @TxtFiled(index = 1)
    private int age;

    @TxtFiled(index = 2)
    private BigDecimal salary;

    @TxtFiled(index = 3)
    @DateFormatFiled("yyyy-MM-dd")
    private Date birth;
}
```

### 2. Read File

```java
// Line-by-line via listener
EasyTxt.read(file, UserBean.class, ",", new BeanReadListener()).doRead();

// Batch via consumer
EasyTxt.read(file, BeanTestVO.class, "\\|", pageSize, pageList -> {
    pageList.forEach(System.out::println);
}).doRead();
```

### 3. Write File

```java
EasyTxt.write(file, BeanTest.class, ",", pageNo, pageSize, (pNum, pSize) -> {
    return queryPageData(pNum, pSize);
}).doWrite();
```

> Full examples in the [`easy-txt-test`](./easy-txt-test) module.

---

## Large File Performance

**Environment:** Windows 10, 16GB RAM, mechanical HDD (4 years old).

### Simple types (30 columns per row)

| Operation | Rows | Size | Time |
|-----------|------|------|------|
| Write | 2,000,000 | 2.52 GB | **111s** |
| Read | 2,000,000 | 2.52 GB | **105s** |

### Mixed types (22 columns with conversion)

| Operation | Rows | Size | Time |
|-----------|------|------|------|
| Write | 8,000,000 | 2.21 GB | **186s** |
| Read | 8,000,000 | 2.52 GB | **223s** |

See [`BigFileDemoTest`](./easy-txt-test/src/test/java/com/small/easytxt/test/bigfile/BigFileDemoTest.java) and [`BigFileTypeDemoTest`](./easy-txt-test/src/test/java/com/small/easytxt/test/bigfile/BigFileTypeDemoTest.java).

---

## Change Log

- [v1.1.0](./change_log.md#v110) — Type converters, date/number format annotations, Java 8 time support
- [v1.0.0](./change_log.md#v100) — Initial release: batch read/write, bean mapping

