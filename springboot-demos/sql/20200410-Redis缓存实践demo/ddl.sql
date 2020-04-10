DROP TABLE IF EXISTS AgreeProtocolInfo;

CREATE TABLE AgreeProtocolInfo (
   agree_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
   cust_no VARCHAR(32) COMMENT '客户号',
   created_date DATETIME COMMENT '插入时间'
) COMMENT='记录是否勾选过协议表';


-- Study -------------------------------------------------------------------------------------------------------------------------------
-- 查看表注释
select
    TABLE_NAME,
    TABLE_COMMENT
from
    INFORMATION_SCHEMA.Tables
where
    table_schema = 'noodles';

-- 查看表字段注释
SELECT
    COLUMN_NAME,
    COLUMN_COMMENT
FROM
    INFORMATION_SCHEMA.Columns
WHERE
    table_name = 'AgreeProtocolInfo'
    AND table_schema='noodles';