---oracle数据闪回
--1
select * from t_user_test as of timestamp to_timestamp('2016-10-31 11:27:30', 'yyyy-mm-dd hh24:mi:ss');
alter table t_user_test enable row movement;
flashback table t_user_test to timestamp TO_TIMESTAMP('2016-10-31 11:27:30','yyyy-mm-dd hh24:mi:ss');

--2
select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') time, to_char(dbms_flashback.get_system_change_number) scn from dual;
select * from t_user_test as of scn 13891603334352;



--SOAP调整核查任务工单的名称、制作时间    修改其中的SHEET_CODE列--------名称CREATE_TIME列------------------工单时间
select * from tai_inas_sheet WHERE sheet_CODE LIKE 'HA-044-160427-00002%'
SOAP--调整核查任务的核查制作人     修改其中的列HANDLE_TIME------操作人
select * from TAI_INAS_ORDER where order_code like 'HA-044-160516-00002%' for update 
---修改为归档状态
orderstate_id =4


 
---oracle解锁的SQL....
SELECT s.sid, s.serial#, s.username, s.schemaname, s.osuser, s.process, s.machine,
s.terminal, s.logon_time, l.type
FROM v$session s, v$lock l
WHERE s.sid = l.sid
AND s.username IS NOT NULL
ORDER BY sid;

--这个语句将查找到数据库中所有的DML语句产生的锁，还可以发现，
--任何DML语句其实产生了两个锁，一个是表锁，一个是行锁。

--杀掉进程 sid,serial#
alter system kill session'210,11562';


--查某session 正在执行的sql语句，从而可以快速定位到哪些操作或者代码导致事务一直进行没有结束等.
SELECT /*+ ORDERED */ 
 sql_text
  FROM v$sqltext a
 WHERE (a.hash_value, a.address) IN
       (SELECT DECODE(sql_hash_value, 0, prev_hash_value, sql_hash_value),
               DECODE(sql_hash_value, 0, prev_sql_addr, sql_address)
          FROM v$session b
         WHERE b.sid = '67')  /* 此处67 为SID*/
 ORDER BY piece ASC;
 
 
 
 create public database link TO_SOAP68 connect to soap identified by "nino_08!w" USING '(DESCRIPTION =
    (ADDRESS_LIST =
      (ADDRESS = (PROTOCOL = TCP)(HOST = 10.217.3.68)(PORT = 1521))
    )
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = SOAP)
    ))'


drop public database link TO_SOAP68