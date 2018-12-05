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
##### 2、添加配置文件
##### 1）spring配置文件
##### 2）spring MVC配置文件
##### 3）mybatis配置文件
##### 4）web.xml
#####  3、使用框架

