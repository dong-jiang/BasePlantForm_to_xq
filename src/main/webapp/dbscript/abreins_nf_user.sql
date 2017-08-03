-- Create table
CREATE TABLE NF_USER
(
  ID                    VARCHAR2(32) not null,
  ACCOUNT               VARCHAR2(32),
  NAME                  VARCHAR2(100) not null,
  TYPE                  VARCHAR2(32),
  PASSWORD              VARCHAR2(32),
  SALT			VARCHAR2(4) default '',
  DESCRIPTION           VARCHAR2(255),
  ORGCODE               VARCHAR2(32),
  PASSWORD_CHANGED_DATE DATE,
  ACCOUNT_ENABLED       CHAR(1) not null,
  ACCOUNT_LOCKED        CHAR(1) not null,
  ACCOUNT_LOCKED_REASON CHAR(1),
  SEX                   CHAR(1),
  BIRTHDATE             DATE,
  NATIONALITY           VARCHAR2(2),
  CREDENTIALS_TYPE      VARCHAR2(32),
  CREDENTIALS_NUMBER    VARCHAR2(32),
  EMAIL                 VARCHAR2(100),
  MOBILE_TELEPHONE      VARCHAR2(16),
  HOME_TELEPHONE        VARCHAR2(16),
  OFFICE_TELEPHONE      VARCHAR2(16),
  FAX                   VARCHAR2(16),
  HOME_ADDRESS          VARCHAR2(256),
  INSERT_OPER VARCHAR2(32),
  INSERT_TIME DATE,
  UPDATE_OPER VARCHAR2(32),
  UPDATE_TIME DATE,
  ACTIVE_FLAG           CHAR(1),
  DELETION_DATE         DATE
);
-- Add comments to the table 
comment on table NF_USER
  is '用户表';
-- Add comments to the columns 
comment on column NF_USER.ID
  is '用户ID';
comment on column NF_USER.ACCOUNT
  is '帐号';
comment on column NF_USER.NAME
  is '姓名';
comment on column NF_USER.TYPE
  is '用户类型';
comment on column NF_USER.PASSWORD
  is '密码';
comment on column NF_USER.SALT
  is '盐';
comment on column NF_USER.DESCRIPTION
  is '描述信息';
comment on column NF_USER.ORGCODE
  is '机构代码';
comment on column NF_USER.PASSWORD_CHANGED_DATE
  is '密码最后一次修改时间';
comment on column NF_USER.ACCOUNT_ENABLED
  is '帐号是否启用';
comment on column NF_USER.ACCOUNT_LOCKED
  is '帐号是否锁定';
comment on column NF_USER.ACCOUNT_LOCKED_REASON
  is '帐号锁定原因';
comment on column NF_USER.SEX
  is '性别';
comment on column NF_USER.BIRTHDATE
  is '生日';
comment on column NF_USER.NATIONALITY
  is '国籍';
comment on column NF_USER.CREDENTIALS_TYPE
  is '证件类型';
comment on column NF_USER.CREDENTIALS_NUMBER
  is '证件号码';
comment on column NF_USER.EMAIL
  is '邮件地址';
comment on column NF_USER.MOBILE_TELEPHONE
  is '移动电话';
comment on column NF_USER.HOME_TELEPHONE
  is '家庭电话';
comment on column NF_USER.OFFICE_TELEPHONE
  is '办公电话';
comment on column NF_USER.FAX
  is '传真';
comment on column NF_USER.HOME_ADDRESS
  is '家庭住址';
comment on column NF_USER.INSERT_OPER
  is '创建人';
comment on column NF_USER.INSERT_TIME
  is '创建时间';
comment on column NF_USER.UPDATE_OPER
  is '修改人';
comment on column NF_USER.UPDATE_TIME
  is '修改时间';
comment on column NF_USER.ACTIVE_FLAG
  is '使用状态';
comment on column NF_USER.DELETION_DATE
  is '删除时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table NF_USER add constraint NF_USER_PK primary key (ID);
-- Create/Recreate indexes 
create index NF_USER_INX_1 on NF_USER (ACCOUNT);
create index NF_USER_INX_2 on NF_USER (NAME);


insert into NF_USER (ID, ACCOUNT, NAME, TYPE, PASSWORD, DESCRIPTION, PASSWORD_CHANGED_DATE, ACCOUNT_ENABLED, ACCOUNT_LOCKED, ACCOUNT_LOCKED_REASON, SEX, BIRTHDATE, NATIONALITY, CREDENTIALS_TYPE, CREDENTIALS_NUMBER, EMAIL, MOBILE_TELEPHONE, HOME_TELEPHONE, OFFICE_TELEPHONE, FAX, HOME_ADDRESS, INSERT_OPER, INSERT_TIME, UPDATE_OPER, UPDATE_TIME, ACTIVE_FLAG, DELETION_DATE)
values ('admin', 'admin', 'admin', null, 'xMpCOKC5I4INzFCab3WEmw==', '管理员', null, 'T', 'F', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'T', null);
