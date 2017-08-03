CREATE TABLE NF_AUTHORITY_REL
(
    ID VARCHAR2(32) NOT NULL,
    DESCRIPTION VARCHAR2(255),
    ROLE_ID VARCHAR2(32) NOT NULL,
    ROLE_TYPE VARCHAR2(32) NOT NULL,
    RESOURCE_ID VARCHAR2(128) NOT NULL,
    RESOURCE_TYPE VARCHAR2(32) NOT NULL,
    AUTHORITY_TYPE VARCHAR2(32) NOT NULL,
    DIMENSION_CONSTRAINT VARCHAR2(32),
    CIRCUMSTANCE_ID VARCHAR2(256),
    CIRCUMSTANCE_TYPE VARCHAR2(32),
    INSERT_OPER VARCHAR2(32),
    INSERT_TIME DATE,
    UPDATE_OPER VARCHAR2(32),
    UPDATE_TIME DATE
);
comment on table NF_AUTHORITY_REL
 is '菜单权限表';
comment on column NF_AUTHORITY_REL.ID
 is '授权ID';
comment on column NF_AUTHORITY_REL.DESCRIPTION
 is '描述信息';
comment on column NF_AUTHORITY_REL.ROLE_ID
 is '角色ID';
comment on column NF_AUTHORITY_REL.ROLE_TYPE
 is '角色类型';  
 -- 角色类型目前有角色role,用户user
comment on column NF_AUTHORITY_REL.RESOURCE_ID
 is '资源ID';
comment on column NF_AUTHORITY_REL.RESOURCE_TYPE
 is '资源类型';  
 -- 资源类型目前有菜单menu
comment on column NF_AUTHORITY_REL.AUTHORITY_TYPE
 is '授权类型';
comment on column NF_AUTHORITY_REL.DIMENSION_CONSTRAINT
 is '维度约束';
comment on column NF_AUTHORITY_REL.CIRCUMSTANCE_ID
 is '场景ID';
comment on column NF_AUTHORITY_REL.CIRCUMSTANCE_TYPE
 is '场景类型';
comment on column NF_AUTHORITY_REL.INSERT_OPER
 is '创建人';
comment on column NF_AUTHORITY_REL.INSERT_TIME
 is '创建时间';
comment on column NF_AUTHORITY_REL.UPDATE_OPER
 is '修改人';
comment on column NF_AUTHORITY_REL.UPDATE_TIME
 is '修改时间';
alter table NF_AUTHORITY_REL add constraint NF_AUTHORITY_REL_PK primary key (ID);

