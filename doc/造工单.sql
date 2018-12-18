SELECT * FROM TAI_INAS_SUBTASK where 
ORDER_ID = '12e3f3b2239549729080463455f10d6a'  order by START_EXEC_TIME for  update

SELECT * FROM tai_inas_sheet where sheet_id=(select  SHEET_ID from  TAI_INAS_ORDER where order_id='09064bd906c64b6eb41b48817b12f90c') for update


update TAI_INAS_ORDER m set ORDER_TITLE=(SELECT n.sheet_title FROM tai_inas_sheet n,TAI_INAS_ORDER m where m.sheet_id=n.sheet_id and n.sheet_title != m.ORDER_TITLE)

update TAI_INAS_ORDER m set  m.order_title=ORDER_CODE

SELECT 'update check_task_dt set c_confirm_status=1,c_active_status=3,c_active_report=scriptstr,c_report=scriptstr where subtaskid='''||SUBTASKID||''';' FROM check_sub_task   where TASKID=(SELECT TASK_ID FROM tai_inas_order_soap c where c.comn_order_id = '6c938f580a374c6eac71c8c553f6edde')
update check_sub_task set c_status=3,c_success_cmds=c_total_cmds where taskid = (SELECT TASK_ID FROM tai_inas_order_soap c where c.comn_order_id = '6c938f580a374c6eac71c8c553f6edde')


update TAI_INAS_SUBTASK set END_EXEC_TIME=END_EXEC_TIME+((23-to_char(END_EXEC_TIME,'hh24'))/24) WHERE  SUBTASK_NAME in ('试点网元指令下发','网元指令下发') and (to_char( END_EXEC_TIME,'hh')<23 or to_char( END_EXEC_TIME,'hh')>3 ) 


SELECT END_EXEC_TIME,to_char(END_EXEC_TIME,'hh24'),23-to_char(END_EXEC_TIME,'hh24'),(23-to_char(END_EXEC_TIME,'hh24'))/24,END_EXEC_TIME+((23-to_char(END_EXEC_TIME,'hh24'))/24) FROM TAI_INAS_SUBTASK WHERE  SUBTASK_NAME in ('试点网元指令下发','网元指令下发') and (to_char( END_EXEC_TIME,'hh')<23 or to_char( END_EXEC_TIME,'hh')>3 )



update TAI_INAS_SUBTASK set END_EXEC_TIME=END_EXEC_TIME+1 WHERE  SUBTASK_NAME in ('试点网元指令下发','网元指令下发') and START_EXEC_TIME>END_EXEC_TIME
SELECT * FROM TAI_INAS_SUBTASK WHERE  SUBTASK_NAME in ('试点网元指令下发','网元指令下发') and START_EXEC_TIME>END_EXEC_TIME
update TAI_INAS_SUBTASK set DISPATCH_TYPE=1 where DISPATCH_TYPE  is null and SUBTASK_NAME='指令批量审核'
SELECT * FROM TAI_INAS_SUBTASK WHERE  DISPATCH_TYPE  is null and SUBTASK_NAME='指令批量审核'


update TAI_INAS_SUBTASK set END_EXEC_TIME=END_EXEC_TIME-23/24 where to_char(END_EXEC_TIME,'dd')- to_char(START_EXEC_TIME,'dd')=1 and SUBTASK_NAME in ('试点网元指令下发','网元指令下发')



SELECT * FROM TAI_INAS_SUBTASK where to_char(END_EXEC_TIME,'dd')- to_char(START_EXEC_TIME,'dd')=1 and SUBTASK_NAME in ('试点网元指令下发','网元指令下发')