# 数据库





# /user	用户模块

1. 登录接口 /login

   提交post

   ```json
   {
       userName:xxx,
       userPwd:xxx
   }
   ```

   返回

   ```json
   {
   	status: success/fail,
   	msg: ''//错误类型：密码错误/账号不存在
       result:{
       	userName:xxx,
   	}
   }
   ```

2. 修改密码接口 /editpwd

   提交post

   ```json
   {
       userName:xxx,
       userOldPwd:xxx,
       userNewPwd:xxx
   }
   ```

   返回

   ```json
   {
   	status: success/fail,
   	msg: ''//错误类型：原密码错误
       result:{
       	userName:xxx,
   	}
   }
   ```
3. 公告信息接口（之一） 
	提交post
	   ```json
   {
       date:xxx
   }
   返回
      ```json
   {
   	status: success/fail,
   	msg: ''//错误类型：获取失败
       result:{
	   message[]{
		title：xxx         //公告标题
       	content:xxx,     //公告信息
		date：xxx         //发布时间
		type:xxx          //公告类型
		}
   	}
   }
4.推荐信息(登陆情况下)
	提交post
	   ```json
   {
       userName:xxx
   }
      返回

   ```json
   {
   	status: success/fail,
   	msg: ''//错误类型：获取失败
       result:{
       	message[]{
			title:xxx             //帖子标题
			content:xxx			  //发帖内容
			date:xxx			  //发帖时间
			addresser:xxx		  //发帖人
		}
   	}
   }
   ```
5.4.推荐信息(非登陆情况下)                       //按热度获取
	提交post
		   ```json
   {
   }
         返回

   ```json
   {
   	status: success/fail,
   	msg: ''//错误类型：原密码错误
       result:{
       	message[]{
			title:xxx             //帖子标题
			content:xxx			  //发帖内容
			date:xxx			  //发帖时间
			addresser:xxx		  //发帖人
		}
   	}
   }