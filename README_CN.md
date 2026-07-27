<div align="center">

<a href="README.md">English</a>

![](logo.jpg)

# easy-txt：Java 文本行文件读写库

**类 EasyExcel 的文本文件处理工具。注解驱动 Bean 映射，支持 CSV/管道符/任意分隔符，2G+ 大文件分批读写。**

[![Maven Central](https://img.shields.io/maven-central/v/com.small/easy-txt?color=brightgreen)](https://search.maven.org/artifact/com.small/easy-txt)
[![Java CI](https://img.shields.io/github/actions/workflow/status/sm-rose/easy-txt/maven.yml?logo=github&label=build)](https://github.com/sm-rose/easy-txt/actions)
[![Java](https://img.shields.io/badge/Java-1.8+-blue)](https://java.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-green)](LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/sm-rose/easy-txt)](https://github.com/sm-rose/easy-txt/releases)
[![GitHub Stars](https://img.shields.io/github/stars/sm-rose/easy-txt?logo=github)](https://github.com/sm-rose/easy-txt)
[![博客](https://img.shields.io/badge/blog-@small.rose-ff69b4)](https://zhangxiaocai.cn)

</div>

```java
// 读文件：3 行
EasyTxt.read(file, UserBean.class, ",", new BeanReadListener()).doRead();

// 写文件：3 行
EasyTxt.write(file, UserBean.class, ",", pageNo, pageSize, this::queryPage).doWrite();
```

---

## 特性

- **注解驱动 Bean 映射** — `@TxtFiled`、`@DateFormatFiled`、`@NumberFormatFiled`
- **双索引模式** — 固定索引 & 自动升序，灵活应对不同文本结构
- **分批读写** — 分页回调，避免大文件内存溢出
- **2G+ 大文件支持** — 实测 2.52GB 写入 111s，读取 105s（见下方性能表）
- **丰富类型转换** — String、int、long、BigDecimal、Date、LocalDate、LocalDateTime 等 15+ 类型
- **自定义分隔符** — CSV、管道符 `|`、Tab 或任意字符
- **零重量框架** — 无 Spring 依赖，纯 Java + Hutool + Commons IO

### 同类对比

| 能力 | easy-txt | OpenCSV | Apache Commons CSV |
|------|----------|---------|-------------------|
| 注解 Bean 映射 | ✅ | ❌ (需 MappingStrategy) | ❌ |
| 分批回调读写 | ✅ | ❌ | ❌ |
| 2G+ 大文件 | ✅ | ✅ | ✅ |
| 自定义分隔符 | ✅ | ✅ | ✅ |
| 内置类型转换器 | ✅ (15+ 种) | ⚠️ (基础) | ❌ |
| 日期/数字格式注解 | ✅ | ❌ | ❌ |

---

## 安装

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

## 快速开始

### 1. 定义 Bean

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

### 2. 读文件

```java
// 通过监听器逐行处理
EasyTxt.read(file, UserBean.class, ",", new BeanReadListener()).doRead();

// 通过 Consumer 分批消费
EasyTxt.read(file, BeanTestVO.class, "\\|", pageSize, pageList -> {
    pageList.forEach(System.out::println);
}).doRead();
```

### 3. 写文件

```java
EasyTxt.write(file, BeanTest.class, ",", pageNo, pageSize, (pNum, pSize) -> {
    return queryPageData(pNum, pSize);
}).doWrite();
```

> 完整示例见 [`easy-txt-test`](./easy-txt-test) 模块。

---

## 大文件性能

**测试环境：** Windows 10, 16G 内存, 机械硬盘（已使用 4 年）。

### 简单类型（30 列/行）

| 操作 | 数据行 | 文件大小 | 耗时 |
|------|--------|---------|------|
| 写入 | 2,000,000 | 2.52 GB | **111s** |
| 读取 | 2,000,000 | 2.52 GB | **105s** |

### 混合类型（22 列，含类型转换）

| 操作 | 数据行 | 文件大小 | 耗时 |
|------|--------|---------|------|
| 写入 | 8,000,000 | 2.21 GB | **186s** |
| 读取 | 8,000,000 | 2.52 GB | **223s** |

详见 [`BigFileDemoTest`](./easy-txt-test/src/test/java/com/small/easytxt/test/bigfile/BigFileDemoTest.java) 和 [`BigFileTypeDemoTest`](./easy-txt-test/src/test/java/com/small/easytxt/test/bigfile/BigFileTypeDemoTest.java)。

---

## 更新日志

- [v1.1.0](./change_log.md#v110) — 增加类型转换器、日期/数字格式化注解、Java 8 时间支持
- [v1.0.0](./change_log.md#v100) — 首个版本：基本分批读写、Bean 映射

