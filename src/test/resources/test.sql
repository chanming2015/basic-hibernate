--ͨ�������ֵ�鿴����(��̬������ͼ)
SELECT  * FROM v$parameter;

--�޸�SGA�ڴ��С
alter system set sga_max_size=1G scope=spfile;  --�޸�SGA�ڴ��С

--�鿴�����û���Ϣ
select * from Dba_Users;

--�鿴�����ļ��������ļ���������־�ļ���λ��
select * from v$controlfile;
select * from v$datafile;
select * from v$logfile;

--�鿴���̺ͺ�̨����
SELECT * FROM v$process;
SELECT * FROM v$bgprocess;

--�鿴��ǰ���û��Ự
SELECT SID,serial#,username FROM v$session WHERE username IS NOT NULL;

--alter system kill session ��SID,SERIAL#�� ��ǿ�ƹر��û��ĻỰ
ALTER SYSTEM KILL SESSION '214,10';

SELECT * FROM V$tablespace;
----------------------------------------------------------------------------
--����һ�������������ı�ռ䣬���Ҵ�С����
CREATE TABLESPACE myspace1
DATAFILE 'F:\oracle\files\myspace.dbf'
SIZE 1m REUSE
AUTOEXTEND ON NEXT 1m
MAXSIZE UNLIMITED;

--ʹ��ռ�����
ALTER TABLESPACE myspace1 OFFLINE;

--ɾ����ռ䣬��ͬ����һ��
DROP TABLESPACE myspace1 INCLUDING CONTENTS;

--�޸������ļ���λ��(ֻ�Ǹı��߼��ṹ)
ALTER TABLESPACE myspace1
RENAME DATAFILE 'F:\oracle\files\myspace.dbf' TO 'E:\oracle\files\myspace.dbf';

--ʹ��ռ�����
ALTER TABLESPACE myspace1 ONLINE;
------------------------------------------------------------------------
CREATE USER zs IDENTIFIED BY "123";
CREATE USER ww IDENTIFIED BY "123";
CREATE USER tq IDENTIFIED BY "123";

--�޸��û���Ĭ�ϱ�ռ���޶�
ALTER USER zs
DEFAULT TABLESPACE myspace1
QUOTA UNLIMITED ON myspace1;

--�������ϵͳȨ��
--��Ȩ���Դ����Ự
GRANT CREATE SESSION TO zs;
--��Ȩ���Դ�����
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
--�������Ȩ��
GRANT SELECT ON zs.text1 TO ww;
--�ջ�Ȩ��
REVOKE SELECT ON zs.text1 FROM ww;

--�����ɫ
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
ALTER SESSION SET nls_date_format='YYYY"��"MM"��"DD"��"';
SELECT * FROM emp;
SELECT to_char(hiredate,'YYYY-MM-DD') ��ְʱ�� FROM emp;
--�����ַ���
SELECT CONCAT(empno,ename)��Ϣ FROM emp;
SELECT * FROM emp ORDER BY empno ASC;
SELECT * FROM emp ORDER BY deptno ASC,empno DESC;
--��������
SELECT ROUND(3.14) FROM emp;
--�ض��ַ���
SELECT TRUNC(3.14,1) FROM emp;
--ȡ����
SELECT MOD(52,3) FROM emp;
--��д
SELECT UPPER('aa') FROM emp;
--Сд
SELECT LOWER('AA') FROM emp;

CREATE TABLE NEW AS SELECT * FROM emp;
TRUNCATE TABLE NEW;
DROP TABLE NEW;
SELECT * FROM NEW,emp;
SELECT * FROM NEW JOIN emp USING(empno);
-----------------------------------------------------------
--��ϰ
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
--��������
CREATE SEQUENCE seq1
START WITH 10
INCREMENT BY 10
MAXVALUE 100;
SELECT seq1.nextval FROM dual;

DROP SEQUENCE seq1;
--������ͼ
CREATE OR REPLACE VIEW empView AS 
SELECT e.*,d.dname FROM emp e LEFT OUTER JOIN dept d ON e.deptno=d.deptno
WITH READ ONLY;

SELECT * FROM empView;
DROP VIEW empView;
--��������
CREATE INDEX inx_mgr ON emp(mgr);
DROP INDEX inx_mgr;
--������
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
         v_num2 INT :=&����;
         BEGIN
           dbms_output.put_line(v_num1/v_num2);
           EXCEPTION 
                WHEN zero_divide THEN
                dbms_output.put_line('��������Ϊ��');
         END;
END;

--������䡪��IF
DECLARE
 v_num INT :=&NUM;
BEGIN
   IF v_num<0 THEN
     dbms_output.put_line('����');
   ELSIF v_num=0 THEN
     dbms_output.put_line('0');
   ELSE
     dbms_output.put_line('����');
   END IF;
END;

--������䡪��CASE
DECLARE
  v_num INT;
BEGIN
  v_num :=&num;
  CASE 
       WHEN MOD(v_num,2)=0 THEN
            dbms_output.put_line(v_num || '��ż����');
       WHEN MOD(v_num,3)=0 THEN
            dbms_output.put_line(v_num || '��3�ı�����');
       ELSE
            dbms_output.put_line(v_num || '����ż����Ҳ����3�ı�����');
  END CASE;
END;
 
--1��LOOPѭ��
DECLARE
  v_num INT := 1;
BEGIN
  LOOP 
    dbms_output.put_line(v_num);
    v_num:=v_num+1;
    IF v_num>10 THEN
      EXIT;--����LOOPѭ��
    END IF;
  END LOOP;
END;

--2��WHILE-LOOPѭ��
DECLARE
  v_num INT := 1;
BEGIN
  WHILE v_num<=100 LOOP
    dbms_output.put_line(v_num);
    v_num := v_num+3;
  END LOOP;
END;

--2��FOR-LOOPѭ��
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

--1����¼ record
DECLARE
  --�Զ���һ����¼����
  TYPE emp_record_type IS RECORD (
             eno emp.empno%TYPE,
             ename emp.ename%TYPE,
             ejob emp.job%TYPE
       );
  --������¼���͵ı���
  emp_record emp_record_type;
BEGIN
     --���ʼ�¼�ĳ�Ա
     emp_record.eno := 9999;
     emp_record.ename := 'ZHANGSAN';
     emp_record.ejob := 'KILLER';
     
     --�������
     dbms_output.put_line( emp_record.eno || ',' || emp_record.ename || ','
                           || emp_record.ejob );
END;

--Ƕ�ױ���÷�
DECLARE
  --������һ��Ƕ�ױ�����
  TYPE eno_table_type IS TABLE OF emp.empno%TYPE;
  --����Ƕ�ױ����
  eno_table eno_table_type;
BEGIN
  --��Ƕ�ױ����һ���ʼֵ
  eno_table := eno_table_type(1234,1235,1236,1237);
  
  --�±��1��ʼ
  --dbms_output.put_line(eno_table(1));
  
  --Ƕ�ױ���һ��COUNT���ԣ���ʾ����
  FOR v_i IN 1..eno_table.COUNT LOOP
      dbms_output.put_line(eno_table(v_i));
  END LOOP;
  
END;




