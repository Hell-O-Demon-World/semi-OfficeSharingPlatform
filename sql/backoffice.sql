CREATE TABLE `PLACE` (
                         `ID`	BIGINT UNSIGNED	primary key NOT NULL auto_increment,
                         `CO_ID`	BIGINT UNSIGNED	NOT NULL,
                         `PL_NAME`	VARCHAR(30)	NOT NULL,
                         `PL_DESCRIPTION`	VARCHAR(50)	NOT NULL,
                         `PL_OPENDAYS`	JSON	NOT NULL	COMMENT '{"Mon" : true, "Wed" : true, "Tue" : false, "Thu" : true, "Fri" : true, "Sat" : true, "Sun" : true}',
                         `PL_START`	TIME	NOT NULL	COMMENT 'HH:MM:SS',
                         `PL_END`	TIME	NOT NULL	COMMENT 'HH:MM:SS',
                         `PL_ADDINFO`	JSON	NOT NULL	COMMENT '{"와이파이" : false, "주차장" : false, "모니터" : false, "커피머신" : false}',
                         `ADDRESS_ID`	BIGINT UNSIGNED	NOT NULL
);