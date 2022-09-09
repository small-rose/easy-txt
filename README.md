# easy-txt
easy-txt

## easy-txt 是什么
类似 easyexecel 读取txt的行自动转成Bean

## 使用场景

有些场景使用文本文件进行业务数据交互时可以使用该工具

- 文件导出导出

## 开始

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
读取文件：
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

固定索引模式：是指使用  `@TxtFiled` 对应的Bean属性，并指明txt文本行分割后的取值索引即，从0开始。默认会使用该种模式读取。

```java
@Data
public class BeanTest {

    @TxtFiled( index = 0 )
    private String line1;

    @TxtFiled( index = 1 )
    private String line2;

    @TxtFiled( index = 2 )
    private String line3;

    @TxtFiled( index = 3 )
    private String line4;
}
```



```java
public class BeanReadDemoTest {
    @Test
    public void test_simple_bean() throws IOException {
        File file = new File("d:\\2.txt");
        EasyTxt.read(file, BeanTest.class, ",", new BeanReadListener()).doRead();
    }
    
    @Test
    public void test_page_bean() throws IOException {
        //每次读取条件
        Integer pageSize = 2;
        File file = new File("d:\\2.txt");
        //Bean 属性多少于或等于数据列
        EasyTxt.read(file, BeanTest.class, "\\|", pageSize, (pagelist) -> {
            pagelist.stream().forEach(System.out::println);
            System.out.println(pagelist.size());
        }).doRead();
    }
}
```

## 自动升序模式

固定索引模式：是指使用  `@TxtFiled` 对应的Bean属性时，不指明txt文本行分割后的取值索引，而是根据设置的索引大小自动升升序赋值。

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

调用

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