# easy-txt
easy-txt

## easy-txt 是什么
类似 easyexecel 读取txt的行自动转成Bean

## 使用场景

有些场景使用文本文件进行业务数据交互时可以使用该工具

- 文件导出导出

# 开始

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
        //按批条数处理
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

自动升序模式:

- 是指使用  `@TxtFiled` 对应的Bean属性时，不指明txt文本行分割后的取值索引，而是根据设置的索引大小自动升升序赋值。
- 使用该模式时必须使用类注解 `@TxtPorperty(fixIndex = false)` 来注解bean，且必须 `fixIndex = false ` 方可生效，
- 一般不建议固定索引模式够用。

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

## 写文件

应用场景:

- 如果数据量很大，一次性查出数据加载到内存比较麻烦,可以分页/分批写入文件。

实例：

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