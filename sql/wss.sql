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
   userid               int not null comment '�û�id  ��Ӧ�û���',
   name                 varchar(50) comment 'ҽ������',
   birthday             date comment '��������',
   sex                  tinyint comment '�Ա�  1 �У�2 Ů',
   hospital_id          int comment '������λid',
   card_type            int,
   card_code            varchar(50),
   isdoctor             tinyint comment '�Ƿ�ҽ�� 0:���ǣ�1����ҽ��',
   isdeliver            tinyint comment '�Ƿ������ 0:���ǣ�1���� ',
   office_code          varchar(50) comment '���ڿ���',
   offer_title          varchar(50) comment 'ְ��',
   phone                varchar(20) comment '��ϵ�绰',
   email                varchar(50) comment '����',
   ca_name              varchar(50) comment 'ca֤����',
   ca_expire            datetime comment 'ca֤����Ч��',
   ca_code              varchar(50) comment 'ca֤�����',
   readcard_type        tinyint comment '����������',
   readcard_code        varchar(50) comment '���������',
   status               tinyint default 0 comment '״̬  0:��Ч��1����Ч Ĭ����Ч',
   remark               varchar(100),
   primary key (id)
);

alter table hos_doctor comment 'ҽ����';

/*==============================================================*/
/* Table: hos_hospital                                          */
/*==============================================================*/
create table hos_hospital
(
   id                   int unsigned not null auto_increment,
   name                 varchar(50) comment '��λ����',
   hos_code             varchar(20) comment '��λ����',
   org_code             varchar(20) comment '��������',
   region_code          varchar(20) comment '���ڵ���',
   ext_address          varchar(50) comment '�����ַ',
   sort                 int comment '���',
   isbjy                tinyint comment '�Ƿ��Ǹ��ױ���Ժ 0:���ǣ�1 ����',
   contacts             varchar(50) comment '��ϵ��',
   phone                varchar(20) comment '��ϵ�绰',
   zipcode              varchar(10) comment '��������',
   hos_property         tinyint comment '��λ���� 1:���� 2��˽��',
   level                tinyint comment 'ҽԺ�ȼ�',
   hos_type             int comment '��λ����',
   sms_account          varchar(50),
   sm_password          varchar(50),
   status               tinyint default 0 comment '״̬  0:��Ч��1����Ч Ĭ����Ч',
   ��ע                   varchar(50),
   primary key (id)
);

alter table hos_hospital comment 'ҽԺ��λ��';

/*==============================================================*/
/* Table: sys_module                                            */
/*==============================================================*/
create table sys_module
(
   id                   int unsigned not null auto_increment,
   modname              varchar(30) not null comment 'ģ������',
   isleaf               bit comment '�Ƿ�Ҷ�ӽڵ� 1:�ǣ�0������',
   pid                  int not null comment '��id',
   sen                  varchar(50) not null comment 'Ӣ����',
   status               tinyint not null default 0 comment '״̬:0:�����ã�1������ Ĭ�Ͽ���',
   sort                 int comment '���',
   primary key (id)
);

alter table sys_module comment 'ϵͳģ���';

/*==============================================================*/
/* Table: sys_region                                            */
/*==============================================================*/
create table sys_region
(
   id                   int unsigned not null auto_increment,
   code                 varchar(20) not null comment '�������',
   level                tinyint not null comment '�㼶',
   name                 varchar(30) not null comment '����',
   pcode                varchar(20) not null comment '�����',
   status               tinyint not null default 0 comment '״̬ 0�������ã�1������ Ĭ�Ͽ���',
   primary key (id)
);

alter table sys_region comment '������';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int unsigned not null auto_increment,
   rolename             varchar(30) not null comment '��ɫ����',
   primary key (id)
);

alter table sys_role comment '��ɫ��';

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

alter table sys_role_module comment '��ɫģ���';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int unsigned not null auto_increment,
   username             varchar(30) not null,
   realname             varchar(30) not null,
   password             varchar(60) not null,
   usertype             tinyint comment '�û�����  1������Ա 2��ҽ��',
   reg_time             datetime comment 'ע��ʱ��',
   lastlogin_time       datetime comment '����¼ʱ��',
   ip                   varchar(30),
   random               varchar(30),
   status               tinyint default 1 comment '�û�״̬  0����Ч��1����Ч Ĭ����Ч',
   primary key (id)
);

alter table sys_user comment '�û���';

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

alter table sys_user_role comment '�û���ɫ��';

