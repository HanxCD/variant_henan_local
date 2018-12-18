-- Create USERS tablespace
  CREATE TABLESPACE "USERS" DATAFILE 
  '+DATA/soap/datafile/users.dbf' SIZE 8242880
  AUTOEXTEND ON NEXT 1310720 MAXSIZE 32767M
  LOGGING ONLINE PERMANENT BLOCKSIZE 8192
  EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT 
 NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO



create table CHECK_TASK_DT_bak1 as select * from CHECK_TASK_DT;

drop table CHECK_TASK_DT;

-- Create table
create table CHECK_TASK_DT
(
  taskid              NVARCHAR2(32),
  nename              NVARCHAR2(32),
  scriptid            NUMBER,
  dictid              NVARCHAR2(32),
  instructionid       NVARCHAR2(32),
  scripttype          NUMBER,
  groupid             NVARCHAR2(32),
  checktype           NVARCHAR2(32),
  operatetype         NVARCHAR2(32),
  scriptstr           CLOB,
  "COMMENT"           NVARCHAR2(512),
  codestr             NVARCHAR2(200),
  dictidquery         VARCHAR2(100),
  dictpriority        NUMBER,
  instructionpriority NUMBER,
  templetepriority    NUMBER,
  subtaskid           VARCHAR2(50),
  createdate          DATE,
  subtaskorder        NUMBER,
  datakey             VARCHAR2(500),
  instrlevel          NVARCHAR2(200),
  instrtype           NVARCHAR2(200),
  dictdelpriority     INTEGER,
  c_confirm_status    INTEGER,
  c_active_status     INTEGER,
  c_active_report     VARCHAR2(4000),
  isrollback          INTEGER
)
 PARTITION BY RANGE(createdate)  
INTERVAL ( NUMTODSINTERVAL (7, 'day') )
(PARTITION CHECK_TASK_DTINST_P1
       VALUES LESS THAN (TO_DATE ('2016-09-01', 'YYYY-MM-DD')),
 PARTITION CHECK_TASK_DTINST_P2
       VALUES LESS THAN (TO_DATE ('2016-09-08', 'YYYY-MM-DD'))
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 952
    next 8
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 

create index CHECK_DT_DICTID_INDEX on CHECK_TASK_DT (DICTID);

create index CHECK_DT_NENAME_INDEX on CHECK_TASK_DT (NENAME);

create index CHECK_DT_SCRIPTID_INDEX on CHECK_TASK_DT (SCRIPTID);

create index CHECK_DT_TASKID_INDEX on CHECK_TASK_DT (TASKID);

create index CHECK_DT_SUBTASKID_INDEX on CHECK_TASK_DT (SUBTASKID);


insert into CHECK_TASK_DT  select * from CHECK_TASK_DT_bak1;

alter table CHECK_SUB_TASK modify c_status default 0;
alter table CHECK_SUB_TASK modify c_total_cmds default 0;
alter table CHECK_SUB_TASK modify c_success_cmds default 0;
alter table CHECK_SUB_TASK modify c_failed_cmds default 0;

alter table CHECK_TASK_DT modify c_confirm_status default 1;
alter table CHECK_TASK_DT modify c_active_status default 0;
alter table CHECK_TASK_DT modify c_active_report default 'null';

alter table CHECK_TASK_ROLLBACK_DT modify c_confirm_status default 1;
alter table CHECK_TASK_ROLLBACK_DT modify c_active_status default 0;
alter table CHECK_TASK_ROLLBACK_DT modify c_active_report default 'null';


create table CHECK_TASK_DT_NEW as select * from check_task_dt where 0=1;
alter table CHECK_TASK_DT_NEW nologging;
alter session enable parallel dml; 
insert /*+ append parallel(p,10) */ into CHECK_TASK_DT_NEW p select /*+ parallel(n,10) */ * from CHECK_TASK_DT n;

---查询表上有多少分区
SELECT * FROM useR_TAB_PARTITIONS WHERE TABLE_NAME='CHECK_TASK_DT'