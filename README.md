# springBoot-study

## mybatis中jdbcType和java类型的对应关系（有些可能要根据数据库的不同进行调整）  

|jdbcType| java类型|
|:-:|:-:|
|CHAR|String|
|VARCHAR|String|
|LONGVARCHAR|String|
|NUMERIC|java.math.BigDecimal|
|DECIMAL|java.math.BigDecimal|
|BIT|boolean|
|BOOLEAN|boolean|
|TINYINT|byte|
|SMALLINT|short|
|INTEGER|INTEGER|
|BIGINT|long|
|REAL | float|
|FLOAT|double|
|DOUBLE|double|
|BINARY|byte[]|
|VARBINARY|byte[]|  

## demo编写中一些主要的问题  
1. 只是用hibernate进行数据库操作时，插入正常，后加入mybatis后，出现主键重复的报错。原本采用的注释是  
    ```java
    @GeneratedValue(strategy = GenerationType.AUTO)
    ```
    改为  
    ```java
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ```
    就能正常混合使用了。  
    ```text
    前者是由hibernate来进行主键的维护，和数据库无关，而后者hibernate不维护主键，和数据有关。
    increment：代理主键，适合于所有数据库，由hibernate维护主键自增，和底层数据库无关，但是不适合于2个或以上hibernate进程。
    identity：代理主键，适合于Mysql或ms sql server等支持自增的dbms，主键值不由hibernate维护。
    sequence：代理主键，适合于oracle等支持序列的dbms，主键值不由hibernate维护，由序列产生。
    native：代理主键，根据底层数据库的具体特性选择适合的主键生成策略，如果是mysql或sqlserver，选择identity，如果是oracle，选择sequence。
    hilo：代理主键，hibernate把特定表的字段作为hign值，生成主键值
    uuid.hex：代理主键，hibernate采用uuid 128位算法生成基于字符串的主键值
    assign：适合于应用程序维护的自然主键。
    ```
