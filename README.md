
<div align="center">

![](https://github.com/small-rose/easy-txt/blob/develop/logo.jpg)

[![](https://img.shields.io/badge/blog-@small.rose-ff69b4.svg)](https://zhangxiaocai.cn)
![](https://img.shields.io/badge/java-@>=1.8-blueviolet.svg)
![](https://img.shields.io/badge/maven-@1.18.24-yellow.svg)
![](https://img.shields.io/badge/hutool-@5.3.8-green.svg)
![](https://img.shields.io/badge/commons.io-@2.11.0-critical.svg)
![](https://img.shields.io/badge/lombok-@1.18.24-blue.svg)

</div>

------------------------
## easy-txt  Information
 
## easy-txt 是什么

类似 easyexecel 功能，只不过处理的不是excel，而是文本行式的文件，这里txt只是代表其中之一。

只要是按行写的文件，有规律的使用分隔符的文件，均可以处理，并不是必须txt文件，支持大文件分批读取，分批写入。


- 1、读取txt的行自动转成Bean。
- 2、将数据库查询的bean写入相应的行文件。


## 使用场景

有些场景使用文本文件进行业务数据交互时可以使用该工具

- 批处理文件导入导出


# 开始

使用基本上与easyexcel一致。
编写映射的VO类，然后编写对应的监听，然后调用基本API就可以。

## 读文件

### 默认读取行

监听类：
```java
public class DemoReadListener extends AbstractReadListener {

    @Override
    public void lineInvoke(Integer rowNo, Object data) {
        String ot = StrUtil.format(" rowNo: {} data : {} ", new Object[]{rowNo, data});
        System.out.println(ot);
    }

    @Override
    public void fileEnd(File file) {
        System.out.println("File read over : " + file.getAbsolutePath());
    }
}
```

普通的按行读取文件：

```java
public class ReadDemoTest {
    @Test
    public void test_1() throws IOException {
        File file = new File("d:\\2.txt");
        EasyTxt.read(file,  new DemoReadListener()).doRead();
    }
   
}
```

### 读取行转成自定义的Bean

(1)定义转换的Bean

- 固定索引模式。
- 升序索引模式。

固定索引模式：是指使用  `@TxtFiled` 对应的Bean属性，并指明txt文本行分割后的取值索引即，从0开始。默认会使用该种模式读取。因为这种模式下，索引必须要自然升序写，且索引值不得大于映射数目。


编写映射行数据的Bean/VO类:

```java
@Data
public class BeanTypeTest {

    @TxtFiled( index = 0 )
    private String name;

    @TxtFiled( index = 1 )
    private int age;

    @TxtFiled( index = 2 )
    private BigDecimal money;

    @TxtFiled( index = 3 )
    private Long accNo;

    @TxtFiled( index = 4 )
    @NumberFormatFiled(value = "## #.##")
    private Double height;

    @TxtFiled( index = 5 )
    @DateFormatFiled(value = "yyyy-MM-dd")
    private Date birth;
}
```


编写对应的监听处理数据的Bean/VO类:

```java
public class BeanTypeReadListener extends AbstractReadListener<BeanTypeTest> {

    public BeanTypeReadListener() {

    }

    @Override
    public void lineInvoke(Integer rowNo, BeanTypeTest data) {
        String ot = StrUtil.format(" rowNo: {} data : {} ", new Object[]{rowNo, data});
        System.out.println(ot);
    }

    @Override
    public void fileEnd(File file) {
        System.out.println("File read over : " + file.getAbsolutePath());
    }

}
```

如果不想自己造文件，可以先看后面写文件部分，使用 **easy-txt** 生成测试文件

```java
public class BeanTypeDemoTest {

    @Test
    public void test_47() throws IOException {
        //解析测试文件
        File file = new File("d:\\1_typetest_bean_write.txt");
        EasyTxt.read(file, BeanTypeTest.class, ",", new BeanTypeReadListener()).doRead();
    }
}
```

## 自动升序模式

自动升序模式:

- 是指使用  `@TxtFiled` 对应的Bean属性时，不指明txt文本行分割后的取值索引，而是根据设置的索引大小自动升升序赋值。
- 使用该模式时必须使用类注解 `@TxtPorperty(fixIndex = false)` 来注解bean，且必须 `fixIndex = false ` 方可生效，
- 一般不建议固定索引模式，自动升序模式已够用。

```java

@Data
@TxtPorperty(fixIndex = false)
public class BeanTestVO {

    @TxtFiled( index = 1 )
    private String data1;

    @TxtFiled( index = 8 )
    private String data2;

    @TxtFiled( index = 13 )
    private String data3;

    @TxtFiled( index = 14 )
    private String data4;

    @TxtFiled( index = 18 )
    private String data5;

    @TxtFiled( index = 20 )
    private String data6;

}
```

分批处理文件

```java
public class BeanReadDemoTest {
    @Test
    public void test_3() throws IOException {
        Integer pageSize = 2;
        File file = new File("d:\\2.txt");
        //Bean 属性多余数据列
        EasyTxt.read(file, BeanTestVO.class, "\\|", pageSize, pagelist -> {
            pagelist.stream().forEach(System.out::println);
            System.out.println(pagelist.size());
        }).doRead();
    }
}
```

分批处理的默认监听类是：**PageReadConsumerListener**



## 写文件

应用场景:

- 如果数据量很大，一次性查出数据加载到内存比较麻烦,可以分页/分批写入文件。

实例：

默认的分页监听处理类是 **PageWriteSupplierListener**

如果想要自己实现，可以使用内置函数式接口：**QueryPageList** 。

```java
public class BeanWriteDemoTest {


    @Test
    public void test_bean_write(){

        File file = new File("d:\\3.txt");
        if (file.exists()){ file.delete();}
        int maxPageNo =4;
        for (int i = 1; i <= maxPageNo ; i++) {
            EasyTxt.write(file, BeanTest.class, ",",i, 10, (pageNum, pageSize) -> {
                //模拟分页数据分批写入文件
                List<BeanTest> list = new ArrayList<>();
                for (int x=0; x <10 ; x++){
                    BeanTest bean = new BeanTest();
                    bean.setLine1("A-"+pageNum+x);
                    bean.setLine2("X01-"+pageNum+x);
                    bean.setLine3("测试-"+pageNum+x);
                    bean.setLine4("100-"+pageNum+x);
                    list.add(bean);
                }
                return list ;
            }).doWrite();
        }

    }
}
```


更多Demo参考 easy-txt-test 的测试用例

## Update Log

* [Update Log](/change_log.md)