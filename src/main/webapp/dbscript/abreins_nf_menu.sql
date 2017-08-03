create table NF_MENU
(
  ID               VARCHAR2(32) not null,
  NAME             VARCHAR2(64) not null,
  APP_ID           VARCHAR2(32),
  TARGET           VARCHAR2(32),
  URL              VARCHAR2(256),
  IMAGE            VARCHAR2(256),
  ORDER_NUM        NUMBER(3),
  IS_DEFAULT       CHAR(1),
  DESCRIPTION      VARCHAR2(256),
  PARENT_ID        VARCHAR2(32),
  TITLE            VARCHAR2(32),
  INSERT_OPER VARCHAR2(32),
  INSERT_TIME DATE,
  UPDATE_OPER VARCHAR2(32),
  UPDATE_TIME DATE,
  IS_ENABLED       CHAR(1) default 'T' not null
)
;
comment on table NF_MENU
  is '菜单表';
comment on column NF_MENU.ID
  is '菜单ID';
comment on column NF_MENU.NAME
  is '菜单名称';
comment on column NF_MENU.APP_ID
  is '应用ID';
comment on column NF_MENU.TARGET
  is '目标区域';
comment on column NF_MENU.URL
  is '请求URL';
comment on column NF_MENU.IMAGE
  is '图片';
comment on column NF_MENU.ORDER_NUM
  is '菜单序号';
comment on column NF_MENU.IS_DEFAULT
  is '是否默认显示';
comment on column NF_MENU.DESCRIPTION
  is '描述';
comment on column NF_MENU.PARENT_ID
  is '父菜单ID';
comment on column NF_MENU.TITLE
  is '菜单标题';
comment on column NF_MENU.INSERT_OPER
  is '创建人';
comment on column NF_MENU.INSERT_TIME
  is '创建时间';
comment on column NF_MENU.UPDATE_OPER
  is '修改人';
comment on column NF_MENU.UPDATE_TIME
  is '修改时间';
comment on column NF_MENU.IS_ENABLED
  is '是否启用';
alter table NF_MENU
  add constraint NF_MENU_PK primary key (ID);
  
  
insert into NF_MENU (ID, NAME, APP_ID, TARGET, URL, IMAGE, ORDER_NUM, IS_DEFAULT, DESCRIPTION, PARENT_ID, TITLE, INSERT_OPER, INSERT_TIME, UPDATE_OPER, UPDATE_TIME, IS_ENABLED)
values ('passwordChange', 'passwordChange', 'techcomp', null, '/techcomp/org/passwordChange_entry.action', null, 7, 'F', null, 'securityManager', '用户密码修改', null, null, null, null, 'T');
