
--mysql时区问题
set global time_zone='+8:00';
--
-- Create a database and authorize users
--
--创建名称为“xtt_bs”数据库，并设定编码集为utf8
CREATE DATABASE IF NOT EXISTS xtt_bs DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
 --创建了一个名为：xtt 密码为：123456 的用户
 create user 'xtt'@'%' identified by '123456';
 --此处的"localhost"，是指该用户只能在本地登录，不能在另外一台机器上远程登录。如果想远程登录的话，将"localhost"改为"%"，表示在任何一台电脑上都可以登录。也可以指定某台机器可以远程登录。

--授予用户test通过外网IP对数据库“xtt_bs”的全部权限
grant all privileges on *.* to 'xtt'@'%' identified by '123456';

--刷新权限
flush privileges;



-- 暂不使用
--授予用户“xtt”通过外网IP对于该数据库“xtt_bs”中表的创建、修改、删除权限,以及表数据的增删查改权限
grant create,alter,drop,select,insert,update,delete on xtt_bs.* to xtt@'%';