# Github 命令列表
## 2018年12月04日
## git简介
#### Git是一款免费、开源的分布式版本控制系统
## 特点
#### Git是一个开源的分布式版本控制系统，可以有效，高速的从很小到很大的项目版本管理
## git基础配置
#### 1、配置用户名（提交时会引用）
##### git config --global user.name "你的用户名"
#### 2、配置邮箱
##### git config --global user.email "你的邮箱"
#### 3、编码配置
##### 避免git gui中的中文乱码
##### git config --global gui.encoding utf-8
##### 避免git status显示的中文文件乱码
##### git config --global core.quotepath off
#### 4、其他
##### git config --global core.ignorecase false
## git ssh key pair配置
#### 1、在git bash命令行窗口中输入：
##### ssh-keygen -t rsa -C "你的邮箱"
#### 2、然后一路回车，不要输入任何密码之类，生成ssh key pair
#### 3、在用户目录下生成.ssh文件夹，找到公钥和私钥
##### id_rsa id_rsa.pub
#### 4、将公钥的内容复制
#### 5、进入github网站，将公钥添加进去
## git验证
#### 执行git --version，出现版本信息，安装成功。
## git常用命令
#### git init 创建本地仓库
#### git add 添加到暂存区
#### git commit -m "描述"提交到本地仓库
#### git status 检查工作区文件状态
#### git log 查看提交committed
#### git reset --hard committid版本回退
#### git branch 查看分支
#### git checkout -b dev创建并切换到dev分支
#### 切换分支:git checkout 分支名
#### 拉取:git pull
#### 提交:git push -u origin master
#### 分支合并：git merge branchname
#### get fetch (远程仓库,本地查看)
### 关联
#### git remote add origin "远程仓库地址
### 第一次向远程仓库推送
#### git push -u -f origin master
### 以后提交到远程
#### git push origin master
## 企业项目开发模式
#### 项目采用分支开发,主干发布
#### 创建分支:git checkout -b v1.0 origin/master
#### 将分支推送到远程 git push origin HEAD -u
#### 检查远程,发现多了v1.0分支
## 项目提交=到github
#### .gitignore文件:告诉git那些文件不需要添加到版本管理中
#### 忽略规则:
##### https://www.cnblogs.com/kevingrace/p/5690241.html
##### "#"为注释 - 将被git忽略
##### *.a 忽略所有.a结尾的文件
##### !lib.a   但是lib.a除外
##### /todo 仅仅忽略项目根目录下的todo文件,不包括subdir/todo
##### build/   忽略build/目录下所有文件
##### doc/*.txt  会忽略doc/notes.txt 但不包括 doc/server/arch.txt
##### git add .   //提交所有
##### 克隆远程仓库:git clone 加上远程仓库地址
##### git config --list#查看配置的信息
##### git rm -f * #强制删除所有文件
##### git add *#跟踪新文件
##### git checkout -- file#取消对文件的修改（从暂存区去除file）
##### git push origin :server#删除远端分支（推送空分支，目前等同于删除）
## 2018年12月04日
##### 远程分支合并
###### 先将分支切换到要合并的分支：git checkout dev
###### 再将远程分支文件拉取到本地：git pull origin dev
###### 再将分支切换到主分支：git checkout master
###### 然后开始合并 git merge dev
###### 再将master发布到远程仓库：git push origin master

### 项目架构--四层架构

##### 视图层(看到的/表单)
##### 控制层 controller(接受视图层传递的数据/同时还负责调用业务逻辑层,处理业务逻辑,将返回的值通过控制层返回视图层)
##### 业务逻辑层service
###### 接口和实现类(方便拓展)
##### Dao层(跟数据库做交互,数据库的增删改查)
```
项目架构--四层架构（上层依赖下层）
视图层
表单进行数据的提交，提交到servlet或者是springMVC的controller层
控制层controller
接收视图层传的数据，同时负责调用业务逻辑层，将业务逻辑层返回的值通过控制层返回到视图层
业务逻辑层service
业务逻辑层是负责具体的业务逻辑的，业务逻辑层调用DAO层进行数据的处理
接口和实现类
DAO层
主要是和数据层进行交互的，对数据库的增删改查
common放常量和枚举之类的
工具包，读写之类的，时间等，封装的一些方法
Mybatis-generator插件
它可以一键生成dao接口，实体类，映射文件，sql文件
properties文件中的这些值，不加前缀也可以，但是username默认加载操作系统的用户名，防止插件默认记载系统名，要加前缀
搭建ssm框架
引入依赖包
springMVC框架其实就是管理controller，所以扫描注解的时候只扫描controller包中的就可以了
```
### Mybatis-generator插件
##### mybatis-generator是一款在使用mybatis框架时，自动生成model，dao和mapper的工具，很大程度上减少了业务开发人员的手动编码时间
##### 本人使用的是maven构建，首先需要在pom.xml文件添加mybatis-generator依赖包以及插件
##### dependencies中添加
```
<dependency>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-core</artifactId>
    <version>1.3.2</version>
</dependency>
```
##### 在build的plugins中添加：
```
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.2</version>
    <configuration>
　　　　　　　<!-- mybatis用于生成代码的配置文件 -->
    　　<configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
        <verbose>true</verbose>
        <overwrite>true</overwrite>
    </configuration>
</plugin>
```
##### generatorConfig.xml文件介绍
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
 PUBLIC " -//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
 "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <classPathEntry
            location="/Users/yehaixiao/Maven/mysql/mysql-connector-java/5.1.30/mysql-connector-java.jar"/>
    <context id="my" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="false"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
　　　　 <!-- mysql数据库连接 -->　
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://127.0.0.1:3306/test" userId="root"
                        password="******"/>

　　　　　<!-- 生成model实体类文件位置 -->
        <javaModelGenerator targetPackage="com.ssmgen.demo.model"
                            targetProject="/Users/yehaixiao/asiainfo/ssm-demo/src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

　　　　　<!-- 生成mapper.xml配置文件位置 -->
        <sqlMapGenerator targetPackage="com.ssmgen.demo.mapper"
                         targetProject="/Users/yehaixiao/asiainfo/ssm-demo/src/main/java">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <!-- 生成mapper接口文件位置 -->
        <javaClientGenerator targetPackage="com.ssmgen.demo.mapper"
                             targetProject="/Users/yehaixiao/asiainfo/ssm-demo/src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
　　　　 <!-- 需要生成的实体类对应的表名，多个实体类复制多份该配置即可 -->
        <table tableName="TEST1" domainObjectName="Test"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false"
               selectByExampleQueryId="false">
        </table>

    </context>
</generatorConfiguration>
```
#### @RestController和@Controller的区别
```
@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用

1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
   如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解
```
## 搭建ssm框架
#### 引入依赖包
#### springMVC框架其实就是管理controller，所以扫描注解的时候只扫描controller包中的就可以了
### mybatis-generator
##### 自动生成数据库交互代码
##### 1、pom中配置
##### 2、配置generotorConfig.xml
##### 搭建ssm框架
##### 1、pom.xml
```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
    <spring.version>4.2.0.RELEASE</spring.version>
  </properties>
  <dependencies>
    <!--单元测试-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <!-- mysql驱动包 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.47</version>
    </dependency>
    <!--mybatis依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.5</version>
    </dependency>
    <!--mybatis-generator依赖-->
    <dependency>
      <groupId>org.mybatis.generator</groupId>
      <artifactId>mybatis-generator-core</artifactId>
      <version>1.3.5</version>
    </dependency>
    <!--mybatis集成spring依赖包-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.0</version>
    </dependency>
    <!--数据库连接池-->
    <dependency>
      <groupId>com.mchange</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.5</version>
    </dependency>
    <!--spring核心依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--spring web项目的依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--事务管理-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--servlet依赖-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>RELEASE</version>
    </dependency>
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    <!--日志框架Logback的依赖-->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.1.2</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-core</artifactId>
      <version>1.1.2</version>
      <scope>compile</scope>
    </dependency>
    <!--json解析依赖-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.5.4</version>
    </dependency>
    <!--guava缓存-->
    <dependency>
      <groupId>com.google.guava</groupId>
      <artifactId>guava</artifactId>
      <version>22.0</version>
    </dependency>
<!--分页-->
    <!--mybatis-pagehelper -->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>4.1.0</version>
    </dependency>
    <dependency>
      <groupId>com.github.miemiedev</groupId>
      <artifactId>mybatis-paginator</artifactId>
      <version>1.2.17</version>
    </dependency>
    <dependency>
      <groupId>com.github.jsqlparser</groupId>
      <artifactId>jsqlparser</artifactId>
      <version>0.9.4</version>
    </dependency>
    <!--joda-time-->
    <dependency>
      <groupId>joda-time</groupId>
      <artifactId>joda-time</artifactId>
      <version>2.3</version>
    </dependency>
    <!--图片上传-->
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3</version>
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.2</version>
    </dependency>
    <!--ftpclient-->
    <dependency>
      <groupId>commons-net</groupId>
      <artifactId>commons-net</artifactId>
      <version>3.1</version>
    </dependency>
<!--集成支付宝-->
    <!-- alipay -->
    <dependency>
      <groupId>commons-codec</groupId>
      <artifactId>commons-codec</artifactId>
      <version>1.10</version>
    </dependency>
    <dependency>
      <groupId>commons-configuration</groupId>
      <artifactId>commons-configuration</artifactId>
      <version>1.10</version>
    </dependency>
    <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
    </dependency>
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.1.1</version>
    </dependency>
    <dependency>
      <groupId>com.google.zxing</groupId>
      <artifactId>core</artifactId>
      <version>2.1</version>
    </dependency>
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.3.1</version>
    </dependency>
    <dependency>
      <groupId>org.hamcrest</groupId>
      <artifactId>hamcrest-core</artifactId>
      <version>1.3</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>2.8.0</version>
    </dependency>
  </dependencies>
  <build>
    <finalName>Ilearnshopping</finalName>
    <plugins>
      <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.6</version>
        <configuration>
          <verbose>true</verbose>
          <overwrite>true</overwrite>
        </configuration>
      </plugin>
    </plugins>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.7.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.20.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

```
##### 2、添加配置文件
##### 1）spring配置文件
##### 2）spring MVC配置文件
##### 3）mybatis配置文件
##### 4）web.xml
#####  3、使用框架

## 2018年12月05日

```
/*
* service层调用dao层
* */
//将UserServiceImpl类交给容器管理
//标识为业务逻辑层的一个类
@Service
public class UserServiceImpl  implements IUserService {
    //自动封装
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public int register(UserInfo userInfo) {
      //返回int类型，表示影响了数据库的行数，返回值大于0说明成功，返回值为零表示插入不成功
        return userInfoMapper.insert(userInfo);
    }
}
```
#### 在接收视图层数据时 注解 @RequestParam中有三个值 
#### 其中value与页面中字段相同，required为布尔型  true时则表示value中字段的值不能为空
#### false时表示可写可不写  defaultValue 值为字符串 当required值为false时，可以给其一个默认值


### 用户模块
 #### 项目中的问题
     横向越权，纵向越权安全漏洞
     横向越权：攻击者尝试访问与他拥有相同权限的用户的资源
     纵向越权：低级别攻击者尝试访问高级别用户的资源
     MD5明文加密
     guava缓存的使用
     高服用服务对象的设计思想及抽象封装
     
 #### 封装返回前端的高服用对象
      class ServerResponse<T>{
        int status;//接口返回状态码
        T data;//封装了接口的返回数据
        String msg;//封装错误信息
      }
       服务端响应前端的高复用对象
       json返回类型{（int）status,data,msg}
       成功返回{status,data} 失败返回{status,msg}
       data的返回类型可以是字符串，数组，对象，数字，所以他的返回类型不确定，就用泛型
       
       @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
       在转json字符串的时候把空字段过滤掉
       当对这个对象ServerResponse进行转成一个字符的时候，那些非空字段是不会进行转化的
       
       @JsonIgnore
        ServerResponse转json的时候把success这个字段忽略掉
  ### 业务逻辑      
  #### 登录
```  
       1：参数的非空校验
         <dependency>
             <groupId>commons-lang</groupId>
             <artifactId>commons-lang</artifactId>
             <version>2.6</version>
           </dependency>
           这个依赖提供了一些常用的校验方法
           StringUtils.isblank();
           public static boolean isBlank(String str) {
                   int strLen;
                   if (str != null && (strLen = str.length()) != 0) {
                       for(int i = 0; i < strLen; ++i) {
                           if (!Character.isWhitespace(str.charAt(i))) {
                               return false;
                           }
                       }
           
                       return true;
                   } else {
                       return true;
                   }
               }
               StringUtils.isEmpty();
               public static boolean isEmpty(String str) {
                       return str == null || str.length() == 0;
                   }
                   当判断如果是一个“ ”空的字符串带空格的时候，StringUtils.isEmpty();这个方法不能判断
       2：检查username是否存在
       
       3：根据用户名和密码查询
            多个参数的时候参数类型parameterType为map
       4：处理结果并返回  
```   
   
 #### 注册
``` 
    时间直接用mysql里面的方法now(),前端在进行注册的时间直接用时间函数就好,不用再传时间了
    枚举：一个变量它的值时有限的，就可以定义为枚举，开关，姓名
    密码密文应该写到注册接口，因为注册接口是往数据库里写数据
    
    数据库中拿到的是经过注册后得密文密码，而我们要用自己注册的明文密码进行登录，e而这时登陆的明文密码和对应的数据库中的密文密码肯定不一样，所以要对登录时的明文密码进行加密
       购物车只有用户登录时候才能用，当要用购物车的时候就需要判断用户是否进行了登录，登录就可以直接用，没登录对他进行提醒，登录后才能使用
       登录后信息保存在session当中
       
       检查用户名是否有效
       在注册时，页面会立刻有个反馈做时时的提示防止有恶意的调用接口，用ajax 异步加载调用接口返回数据
``` 
 #### 忘记密码之修改密码
``` 
    修改密码的时候要考虑到一个越权问题
      横向越权：权限都是一样的，a用户去修改其他用户  
      纵向越权：低级别用户修改高级别用户的权限
      解决：提交答案成功的时候，服务端会给客户端返回一个值（数据），这个数据在客户端服务端都分别保存，当用户去重置密码的时候，用户端必须带上这个数据，只有拿到数据服务端校验合法了才能修改
           所以服务端要给客户端传一个token,服务端客户端都分别保存，然后两个进行校验
           
    @JsonSerialize:json是个键值对往页面传对象的时候是通过，扫描类下的get方法来取值的
           
    UUID生成的是一个唯一的随机生成一个字符串，每次生成都是唯一的
```    
  #### 根据用户名查询密保问题
```  
      1：参数非空校验
      2：校验username是否存在
      3：根据username查询密保问题
      4：返回结果
```      
  
  ##### 提交问题答案  
```  
  
         服务端生成一个token保存并将token返回给客户端
         String user_Token=UUID.randomUUID().toString();
         UUID每次生成的字符串是唯一的，不会重复的
         guava cache
         TokenCache.put(username,user_Token);
         缓存里用key或取，key要保证他的唯一性，key就是用户，key直接用value就可以了
         这样就把token放到服务端的缓存里面了，同时要将token返回到客户端
   
```      
  ##### 修改密码
```  
      1：参数的非空校验
      2：校验token
      3：更新密码
      4：返回结果
 ```     
  ### 登录状态下修改密码
```  
      1：参数的非空校验
      2：校验旧密码是否正确,根据用户名和旧密码查询这个用户
      3：修改密码
      4：返回结果
      在控制层中要先判断是否登录
```      
      
  ### 类别模块    
  #### 1：功能介绍
       获取节点
       增加节点
       修改名称
       获取分类
       递归子节点
   #### 2：学习目标
 ```
        如何设计界封装无限层级的树状数据结构
        递归算法的设计思想
          递归一定要有一个结束条件，否则就成了一个死循环
        如何处理复杂对象重排
        重写hashcode和equals的注意事项  
 ```
   #### 获取品类子节点（平级）
  ```
      1：非空校验
      2：根据categoryId查询类别
      3：查询子类别
      4：返回结果
   ```
   #### 增加节点
   ```
   非空校验
   添加节点
   返回结果
   ```
   #### 修改节点
   ```
      非空校验
      根据categoryId查询类别
      修改类别
      返回结果
   ```    
   #### 获取当前分类id及递归子节点categoryId

```
       先定义一个递归的方法
         先查找本节点
             set里面的集合不可重复，通过类别id判断是不是重复，要重写类别对象的equals方法，在重写equals方法前先重写hashcode方法
                 两个类相等这两个类的hashcode一定相等，如果两个类的hashcode相等两个类不一定相等
         查找categoryId下的子节点（平级）
             遍历这个集合拿到这个每个子节点
             对集合的每个节点调用当前这个递归方法
         递归的结束语句就是 categoryList==null&&categoryList.size()<=0
       1参数的非空校验
       2查询
   ```