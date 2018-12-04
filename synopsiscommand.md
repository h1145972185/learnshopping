# Github 命令列表
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
##### 远程分支合并
###### 先将分支切换到要合并的分支：git checkout dev
###### 再将远程分支文件拉取到本地：git pull origin dev
###### 再将分支切换到主分支：git checkout master
###### 然后开始合并 git merge dev
###### 再将master发布到远程仓库：git push origin master