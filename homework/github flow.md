# GitHub多人协作
## 概述
 &ensp;&ensp;&ensp;&ensp;**GitHub Flow** —— 以部署为中心的开发模式,通过简单的功能和规则，持续且高速 安全地进行部署。在实际开发中往往一天之内会实施几十次部署，而支撑这一切的，就是足够简单的开发流程以及完全的自动化。 
      
 **GitHub Flow 特点**：    
1、令master 分支时常保持可以部署的状态；         
2、进行新的作业时要从master 分支创建新的分支，新分支名称要具有描述性；   
3、在2新建的本地仓库分支中进行提交    
4、在Github 端仓库创建同名分支，定期push    
需要帮助、反馈，或者branch已经准备merging时，创建Pull Request，以Pull Request 进行交流   
5、让其他开发者进行审查，确认作业完成后与master分支进行合并（合并的代码一定要测试   
6、与master分支合并后，立刻部署。  


## 步骤
#### **一、创建者工作**   
1、创建仓库：创建者在*github*上创建一个公共的team_work_demo仓库；    
2、上传文件：选择项目文件，使用git初始化，使用git add和git commit命令上传文件，并使用push命令将文件push到team_work_demo仓库中；       
3、邀请同组组员：在仓库的**Settings**中选择“*Collaborators*”，然后选择下方的“*Add people*"，在弹窗中输入组员的邮箱，即可邀请组员加入协作，如下图。     

![邀请协作人员](https://img-blog.csdnimg.cn/239f48c0340d4a9b93c061a46a5bc934.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)       

                
          
#### **二、组员工作**
1、fork仓库：通过链接等方式接受创建者邀请并进入仓库，点击右侧栏的“*Fork*”，如下：       

![点击fork](https://img-blog.csdnimg.cn/53231f2babbd492cb9839e7ef3957fe1.png#pic_center)    

稍等片刻后，自己的账号下就会克隆一个相同的仓库；     
2、pull仓库到本地：在自己账号下的team_work_demo仓库中复制HTTPS链接，在git上使用*clone*命令将文件下载到本地，如图；      

![clone到本地](https://img-blog.csdnimg.cn/3513b673a8d641c58dc90881edf64e39.png#pic_center)       

3、创建分支：使用*git checkout -b deveop*命令创建并切换到develop分支；    
4、编写代码并测试：在该文件下新建一个名为Demo的java文件，并进行单元测试，直至代码无问题；   
5、合并分支：切换到master分支下，使用*git merge develop*将分支合并；      
6、提交并上传到自己的仓库：使用*git add -A*（提交全部修改的文件）和*git commit*命令将新建的文件上传，并使用*git push*命令将文件推送到远程仓库，如图：      

![push到仓库](https://img-blog.csdnimg.cn/3b3a0f4bda484d1588b42fbe7cb13930.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)           

7、提交Pull requests：点击仓库的“*Pull requests*”，新建一个“Pull requests”，输入内容，点击“*Create pull request*”即可，如下图：      

![创建pull request](https://img-blog.csdnimg.cn/38d3553310b743789b97e0764c16b569.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center) 

     
#### **三、创建者查看**
1、查看Pull request并审查代码：创建者可以收到并查看组员发送的“*Pull request*”，点击查看组员上传的具体文件，并审查代码，如图：     

![查看pull request](https://img-blog.csdnimg.cn/3db6f99440f64f9db696bd6dde93ce02.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)         

![审查代码](https://img-blog.csdnimg.cn/9cfb5f80552644728c50eccc0595f812.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)       
      
      

2、合并文件：创建者在审核完成后，可以选择“*Merge pull request*”，即可在创建者的仓库中看到文件的更新，如图：    

![merge pull request](https://img-blog.csdnimg.cn/6621fd7bf6b74651b3599a2d611fac29.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)      
    
![查看更新后的文件](https://img-blog.csdnimg.cn/a48ce88514f441419cf0bf8eed88b790.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)     




## 遇见的问题及其解决方法
#### **问题**
mk0-0使用命令git push -u origin master向本地及远程仓库提交代码时，连接超时：
```
fatal: unable to access 'https://github.com/mk0-0/team_work_demo.git/': Failed to connect to github.com port 443: Timed out
```
如图：      

![问题截图](https://img-blog.csdnimg.cn/c88a200e2ba14ea89eaea3053541585a.png#pic_center)       

#### **解决**
1、在github网站中的个人主页里（Settings->Developer settings->Private Access Token）生成并复制Private Access Token至剪切板；    
2、使用命令git push https://github.com/mk0-0/team_work_demo.git 向本地及远程仓库提交代码，并在弹出的对话框中填入PAT令牌，完成提交。        

![解决方案](https://img-blog.csdnimg.cn/9967ad34eb6a4f0b9c7e2c365b5471e9.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LuZ5bqm55Ge5ouJ5b-r6LeR,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)    
