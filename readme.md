用java实现的聊天室程序
实现的功能清单：
1、一对一聊天
2、多用户聊天（可在服务器端指定私聊）
3、建立在文件本机路径基础的文件传输
4、其他功能:基于本机图片路径的图片传输（服务器根据图片文件路径存储转发，客户端根据新路径显示图片），好友信息标识，数据存储用户名以及密码供登录验证等功能
运行顺序：server.java运行后按照ui界面操作即可，若换机运行需要对login.java下的数据库配置进行更改，换成本地的数据库环境方可运行，否则会报错。



需要修改成本地的mysql数据库连接
运行server.java按照GUI操作即可。
一些可能错误的原因：
数据库不是mysql或未正确配置
系统盘符不是C或D或路径表示错误被当做是普通信息
运行中服务端不应关闭