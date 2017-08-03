CREATE TABLE NF_ROLE
(
    ID VARCHAR2(32) NOT NULL,
    NAME VARCHAR2(32) NOT NULL,
    DESCRIPTION VARCHAR2(255),
    ACTIVE_FLAG VARCHAR2(32),
    TIME_BEGIN DATE,
    TIME_END DATE,
    INSERT_OPER VARCHAR2(32),
    INSERT_TIME DATE,
    UPDATE_OPER VARCHAR2(32),
    UPDATE_TIME DATE
  );
comment on table NF_ROLE
  is '业务角色表';
comment on column NF_ROLE.ID
  is '角色ID';
comment on column NF_ROLE.NAME
  is '角色名称';
comment on column NF_ROLE.DESCRIPTION
  is '描述信息';
comment on column NF_ROLE.ACTIVE_FLAG
  is '使用状态';
comment on column NF_ROLE.TIME_BEGIN
  is '生效时间';
comment on column NF_ROLE.TIME_END
  is '失效时间';
comment on column NF_ROLE.INSERT_TIME
  is '创建人';
comment on column NF_ROLE.INSERT_TIME
  is '创建时间';
comment on column NF_ROLE.UPDATE_OPER
  is '修改人';
comment on column NF_ROLE.UPDATE_TIME
  is '修改时间';
alter table NF_ROLE
  add constraint NF_ROLE_PK primary key (ID);