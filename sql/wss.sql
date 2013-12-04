/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/12/2 12:14:36                           */
/*==============================================================*/


drop table if exists hos_doctor;

drop table if exists hos_hospital;

drop table if exists sys_module;

drop table if exists sys_region;

drop table if exists sys_role;

drop table if exists sys_role_module;

drop table if exists sys_user;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: hos_doctor                                            */
/*==============================================================*/
create table hos_doctor
(
   id                   int unsigned not null auto_increment,
   userid               int not null comment '用户id  对应用户表',
   name                 varchar(50) comment '医生姓名',
   birthday             date comment '出生日期',
   sex                  tinyint comment '性别  1 男；2 女',
   hospital_id          int comment '工作单位id',
   card_type            int,
   card_code            varchar(50),
   isdoctor             tinyint comment '是否医生 0:不是；1：是医生',
   isdeliver            tinyint comment '是否接生人 0:不是；1：是 ',
   office_code          varchar(50) comment '所在科室',
   offer_title          varchar(50) comment '职称',
   phone                varchar(20) comment '联系电话',
   email                varchar(50) comment '邮箱',
   ca_name              varchar(50) comment 'ca证书名',
   ca_expire            datetime comment 'ca证书有效期',
   ca_code              varchar(50) comment 'ca证书编码',
   readcard_type        tinyint comment '读卡器类型',
   readcard_code        varchar(50) comment '读卡器序号',
   status               tinyint default 0 comment '状态  0:无效；1：有效 默认有效',
   remark               varchar(100),
   primary key (id)
);

alter table hos_doctor comment '医生表';

/*==============================================================*/
/* Table: hos_hospital                                          */
/*==============================================================*/
create table hos_hospital
(
   id                   int unsigned not null auto_increment,
   name                 varchar(50) comment '单位名称',
   hos_code             varchar(20) comment '单位编码',
   org_code             varchar(20) comment '机构编码',
   region_code          varchar(20) comment '所在地区',
   ext_address          varchar(50) comment '补充地址',
   sort                 int comment '序号',
   isbjy                tinyint comment '是否是妇幼保健院 0:不是；1 ：是',
   contacts             varchar(50) comment '联系人',
   phone                varchar(20) comment '联系电话',
   zipcode              varchar(10) comment '邮政编码',
   hos_property         tinyint comment '单位性质 1:国企 2：私企',
   level                tinyint comment '医院等级',
   hos_type             int comment '单位分类',
   sms_account          varchar(50),
   sm_password          varchar(50),
   status               tinyint default 0 comment '状态  0:无效；1：有效 默认有效',
   备注                   varchar(50),
   primary key (id)
);

alter table hos_hospital comment '医院单位表';

/*==============================================================*/
/* Table: sys_module                                            */
/*==============================================================*/
create table sys_module
(
   id                   int unsigned not null auto_increment,
   modname              varchar(30) not null comment '模块名称',
   isleaf               bit comment '是否叶子节点 1:是；0：不是',
   pid                  int not null comment '父id',
   sen                  varchar(50) not null comment '英文名',
   status               tinyint not null default 0 comment '状态:0:不可用；1：可用 默认可用',
   sort                 int comment '序号',
   primary key (id)
);

alter table sys_module comment '系统模块表';

/*==============================================================*/
/* Table: sys_region                                            */
/*==============================================================*/
create table sys_region
(
   id                   int unsigned not null auto_increment,
   code                 varchar(20) not null comment '地区编号',
   level                tinyint not null comment '层级',
   name                 varchar(30) not null comment '名称',
   pcode                varchar(20) not null comment '父编号',
   status               tinyint not null default 0 comment '状态 0：不可用；1：可用 默认可用',
   primary key (id)
);

alter table sys_region comment '地区表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int unsigned not null auto_increment,
   rolename             varchar(30) not null comment '角色名称',
   primary key (id)
);

alter table sys_role comment '角色表';

/*==============================================================*/
/* Table: sys_role_module                                       */
/*==============================================================*/
create table sys_role_module
(
   id                   int unsigned not null auto_increment,
   roleid               int not null,
   moduleid             int not null,
   type                 int,
   primary key (id)
);

alter table sys_role_module comment '角色模块表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int unsigned not null auto_increment,
   username             varchar(30) not null,
   realname             varchar(30) not null,
   password             varchar(60) not null,
   usertype             tinyint comment '用户类型  1：管理员 2：医生',
   reg_time             datetime comment '注册时间',
   lastlogin_time       datetime comment '最后登录时间',
   ip                   varchar(30),
   random               varchar(30),
   status               tinyint default 1 comment '用户状态  0：无效；1：有效 默认有效',
   primary key (id)
);

alter table sys_user comment '用户表';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   int unsigned not null auto_increment,
   userid               int not null,
   roleid               int not null,
   primary key (id)
);

alter table sys_user_role comment '用户角色表';

