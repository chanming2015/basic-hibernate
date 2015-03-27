--通过数据字典查看参数(动态性能视图)
SELECT  * FROM v$parameter;

--修改SGA内存大小
alter system set sga_max_size=1G scope=spfile;  --修改SGA内存大小

--查看所有用户信息
select * from Dba_Users;

--查看控制文件，数据文件，重做日志文件的位置
select * from v$controlfile;
select * from v$datafile;
select * from v$logfile;

--查看进程和后台进程
SELECT * FROM v$process;
SELECT * FROM v$bgprocess;

--查看当前的用户会话
SELECT SID,serial#,username FROM v$session WHERE username IS NOT NULL;

--alter system kill session ‘SID,SERIAL#’ 来强制关闭用户的会话
ALTER SYSTEM KILL SESSION '214,10';

SELECT * FROM V$tablespace;
----------------------------------------------------------------------------
--这是一个可以自增长的表空间，而且大小不限
CREATE TABLESPACE myspace1
DATAFILE 'F:\oracle\files\myspace.dbf'
SIZE 1m REUSE
AUTOEXTEND ON NEXT 1m
MAXSIZE UNLIMITED;

--使表空间离线
ALTER TABLESPACE myspace1 OFFLINE;

--删除表空间，连同内容一起
DROP TABLESPACE myspace1 INCLUDING CONTENTS;

--修改数据文件的位置(只是改变逻辑结构)
ALTER TABLESPACE myspace1
RENAME DATAFILE 'F:\oracle\files\myspace.dbf' TO 'E:\oracle\files\myspace.dbf';

--使表空间上线
ALTER TABLESPACE myspace1 ONLINE;
------------------------------------------------------------------------
CREATE USER zs IDENTIFIED BY "123";
CREATE USER ww IDENTIFIED BY "123";
CREATE USER tq IDENTIFIED BY "123";

--修改用户的默认表空间和限额
ALTER USER zs
DEFAULT TABLESPACE myspace1
QUOTA UNLIMITED ON myspace1;

--授予的是系统权限
--授权可以创建会话
GRANT CREATE SESSION TO zs;
--授权可以创建表
GRANT CREATE TABLE TO zs;

CREATE TABLE zs.TEXT1
(
       str   VARCHAR2(100) NOT NULL
)
TABLESPACE myspace1;

INSERT INTO zs.TEXT1 VALUES('sdfsdfsdfdsdfsdfsdfsdf');
INSERT INTO zs.TEXT1 SELECT * FROM zs.TEXT1;
COMMIT;
SELECT COUNT(1) FROM zs.TEXT1;
DROP TABLE zs.TEXT1;

GRANT CREATE SESSION TO ww;
--授予对象权限
GRANT SELECT ON zs.text1 TO ww;
--收回权限
REVOKE SELECT ON zs.text1 FROM ww;

--授予角色
GRANT DBA TO tq WITH ADMIN OPTION;
REVOKE DBA FROM tq;

SELECT * FROM Dba_Roles;
SELECT * FROM Dba_Role_Privs WHERE grantee = 'DBA';
-----------------------------------------------------------------
CREATE ROLE myrole1;

GRANT CONNECT,RESOURCE TO myrole1;
GRANT SELECT ON zs.text1 TO tq;

GRANT myrole1 TO tq;
SELECT * FROM user_tablespaces;
----------------------------------------------------------------------
ALTER SESSION SET nls_date_format='YYYY"年"MM"月"DD"日"';
SELECT * FROM emp;
SELECT to_char(hiredate,'YYYY-MM-DD') 入职时间 FROM emp;
--连接字符串
SELECT CONCAT(empno,ename)信息 FROM emp;
SELECT * FROM emp ORDER BY empno ASC;
SELECT * FROM emp ORDER BY deptno ASC,empno DESC;
--四舍五入
SELECT ROUND(3.14) FROM emp;
--截断字符串
SELECT TRUNC(3.14,1) FROM emp;
--取余数
SELECT MOD(52,3) FROM emp;
--大写
SELECT UPPER('aa') FROM emp;
--小写
SELECT LOWER('AA') FROM emp;

CREATE TABLE NEW AS SELECT * FROM emp;
TRUNCATE TABLE NEW;
DROP TABLE NEW;
SELECT * FROM NEW,emp;
SELECT * FROM NEW JOIN emp USING(empno);
-----------------------------------------------------------
--练习
SELECT * FROM dept;
SELECT * FROM emp ORDER BY sal DESC;
--1 
SELECT * FROM dept WHERE deptno IN
(
SELECT DISTINCT deptno FROM emp 
);
--2
SELECT * FROM emp WHERE sal>(SELECT sal FROM emp WHERE ename='SMITH');
--3
SELECT e1.ename,e2.ename FROM emp e1 LEFT OUTER JOIN emp e2 ON e1.mgr=e2.empno;
--4
SELECT * FROM emp e1 LEFT OUTER JOIN emp e2 ON e1.mgr=e2.empno WHERE e1.hiredate<e2.hiredate;
--5
SELECT d.dname,e.* FROM dept d LEFT OUTER JOIN emp e ON d.deptno=e.deptno;
--6
SELECT e.ename,d.dname FROM dept d JOIN emp e ON d.deptno=e.deptno WHERE e.job='CLERK';
--7

----------------------------------------------------------------------
--创建序列
CREATE SEQUENCE seq1
START WITH 10
INCREMENT BY 10
MAXVALUE 100;
SELECT seq1.nextval FROM dual;

DROP SEQUENCE seq1;
--创建视图
CREATE OR REPLACE VIEW empView AS 
SELECT e.*,d.dname FROM emp e LEFT OUTER JOIN dept d ON e.deptno=d.deptno
WITH READ ONLY;

SELECT * FROM empView;
DROP VIEW empView;
--创建索引
CREATE INDEX inx_mgr ON emp(mgr);
DROP INDEX inx_mgr;
--锁机制
SELECT * FROM scott.emp FOR UPDATE;
--
SELECT * FROM user_tables;

SELECT * FROM tb_user;

DROP TABLE tb_user;

-------------------------------------
--PL/SQL
DECLARE 
  v_name VARCHAR2(10) :='hello';
BEGIN
  dbms_output.put_line(v_name);
  DECLARE 
         v_num1 INT :=100;
         v_num2 INT :=&除数;
         BEGIN
           dbms_output.put_line(v_num1/v_num2);
           EXCEPTION 
                WHEN zero_divide THEN
                dbms_output.put_line('除数不能为零');
         END;
END;

--条件语句——IF
DECLARE
 v_num INT :=&NUM;
BEGIN
   IF v_num<0 THEN
     dbms_output.put_line('负数');
   ELSIF v_num=0 THEN
     dbms_output.put_line('0');
   ELSE
     dbms_output.put_line('正数');
   END IF;
END;

--条件语句——CASE
DECLARE
  v_num INT;
BEGIN
  v_num :=&num;
  CASE 
       WHEN MOD(v_num,2)=0 THEN
            dbms_output.put_line(v_num || '是偶数。');
       WHEN MOD(v_num,3)=0 THEN
            dbms_output.put_line(v_num || '是3的倍数。');
       ELSE
            dbms_output.put_line(v_num || '不是偶数，也不是3的倍数。');
  END CASE;
END;
 
--1、LOOP循环
DECLARE
  v_num INT := 1;
BEGIN
  LOOP 
    dbms_output.put_line(v_num);
    v_num:=v_num+1;
    IF v_num>10 THEN
      EXIT;--跳出LOOP循环
    END IF;
  END LOOP;
END;

--2、WHILE-LOOP循环
DECLARE
  v_num INT := 1;
BEGIN
  WHILE v_num<=100 LOOP
    dbms_output.put_line(v_num);
    v_num := v_num+3;
  END LOOP;
END;

--2、FOR-LOOP循环
DECLARE
BEGIN
  FOR v_num IN REVERSE 1..30 LOOP
    dbms_output.put_line(v_num);
  END LOOP;
END;

DECLARE
  v_num tb_user.id%TYPE := 1;
BEGIN
     dbms_output.put('v_num=' || v_num);
     dbms_output.put_line('');
END;

--1、记录 record
DECLARE
  --自定义一个记录类型
  TYPE emp_record_type IS RECORD (
             eno emp.empno%TYPE,
             ename emp.ename%TYPE,
             ejob emp.job%TYPE
       );
  --声明记录类型的变量
  emp_record emp_record_type;
BEGIN
     --访问记录的成员
     emp_record.eno := 9999;
     emp_record.ename := 'ZHANGSAN';
     emp_record.ejob := 'KILLER';
     
     --测试输出
     dbms_output.put_line( emp_record.eno || ',' || emp_record.ename || ','
                           || emp_record.ejob );
END;

--嵌套表的用法
DECLARE
  --定义了一个嵌套表类型
  TYPE eno_table_type IS TABLE OF emp.empno%TYPE;
  --声明嵌套表变量
  eno_table eno_table_type;
BEGIN
  --给嵌套表变量一组初始值
  eno_table := eno_table_type(1234,1235,1236,1237);
  
  --下标从1开始
  --dbms_output.put_line(eno_table(1));
  
  --嵌套表有一个COUNT属性，表示长度
  FOR v_i IN 1..eno_table.COUNT LOOP
      dbms_output.put_line(eno_table(v_i));
  END LOOP;
  
END;




