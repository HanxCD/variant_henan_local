--方案有关
select * from ur_busi_info; --业务数据（树的第一层） 
select * from tat_inas_product;  
select * from sys_product2flow;
select * from ur_busi_case_info; --方案数据(业务数据下的节点) 
select * from ur_case_rule_info; --规则数据(一竖溜到第四层) 
select * from ur_rule_instruction_info; --指令和规则关系表 
select * from ur_rule_instruction_param;--指令和规则关系和参数表 
select * from sys_product2table;  --产品关联表设计 dictid和指令字典表一致


--指令字典有关
select * from ur_operation_map_group; --映射操作组表 dictid和指令字典表一致
select * from ur_operation_map_info; --映射操作表   dictid对应ur_operation_map_group的id
select * from ur_param_variant;--变量表 
select * from ur_instruction_dict;--指令字典表 
select * from ur_instruction_dict_item;--指令字典参数表 dictid和指令字典表一致
select * from ur_instruction_item_value;--指令字典参数值表 dictid和指令字典表一致

--所有菜单项
select * from taw_app_tree;--菜单

--任务工单
select * from tai_inas_order

--厂家
select * from TAT_NA_VENDOR

--网元管理
select * from RES_NETWORK_ELEMENT_INFO;--网元版本管理
SELECT * FROM TAI_NA_NE;--网元别名、名称、OMCID



select * from sys_busi_table_def; --表定义
select * from sys_busi_column_def; --列定义
select * from ur_collect_cmdgroup ;--采集命令组
select * from UR_COLLECT_CMD ;--采集命令
select * from sys_file_parse ;--解析配置


select * from ur_busi_info; --业务数据（树的第一层） 
select * from ur_busi_case_info; --方案数据(业务数据下的节点) 
select * from ur_case_rule_info; --规则数据(一竖溜到第四层) 
select * from ur_rule_instruction_info; --指令和规则关系表 
select * from ur_rule_instruction_param;--指令和规则关系和参数表 
select * from ur_case_font;--方案与用户对应的指令字体大小 
select * from sys_dict_info; --系统字典表

select * from sys_busi_table_def; --表定义
select * from sys_busi_column_def; --列定义
select * from sys_product2table; --产品关联表设计 dictid和指令字典表一致
select * from sys_dict_item_info; --网络类型网元类型厂家表 dictid和指令字典表一致
select * from ur_operation_map--映射操作组表 dictid和指令字典表一致
select * from ur_operation_map_info; --映射操作表 dictid对应ur_operation_map_group的id
select * from ur_param_variant;--变量表 
select * from ur_instruction_dict;--指令字典表 
select * from ur_instruction_dict_item;--指令字典参数表 dictid和指令字典表一致
select * from ur_instruction_item_value;--指令字典参数值表 dictid和指令字典表一致

select * from sys_product2flow; --产品关联流程表
select * from inas121.dbo.tat_inas_product; --产品信息表


