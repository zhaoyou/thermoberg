/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2011-6-18 16:49:48                           */
/*==============================================================*/


/*drop index time on Fdapcarhisdata_;*/

drop table if exists Fdapcarhisdata_;

/*drop index endTime on Fdaphisalarm_;*/

/*drop index startTime on Fdaphisalarm_;*/

drop table if exists Fdaphisalarm_;

drop table if exists Fdapphone;

/*drop index time on Fdaprefhisdata_;*/

drop table if exists Fdaprefhisdata_;

/*drop index startTime on Fdapstartup_;*/

drop table if exists Fdapstartup_;

drop table if exists fdapaiinfo;

/*drop index code on fdapauthcode;*/

drop table if exists fdapauthcode;

drop table if exists fdapcarrealdata;

drop table if exists fdaplink;

drop table if exists fdaplinktype;

drop table if exists fdaporg;

drop table if exists fdappower;

drop table if exists fdapproject;

drop table if exists fdaprealalarm;

drop table if exists fdapref;

drop table if exists fdaprefactive;

drop table if exists fdaprefrealdata;

drop table if exists fdaprole;

drop table if exists fdaprole_power;

drop table if exists fdapstoretype;

drop table if exists fdapuser;

/*==============================================================*/
/* Table: Fdapcarhisdata_                                       */
/*==============================================================*/
create table Fdapcarhisdata_
(
   id                   bigint not null auto_increment,
   startupid            bigint,
   t1                   decimal(5,2) not null,
   t2                   decimal(5,2) not null,
   t3                   decimal(5,2) not null,
   latitude             decimal(8,4) not null,
   latitude_dir         int not null,
   longitude            decimal(9,4) not null,
   longitude_dir        int not null,
   time                 datetime not null,
   isalarm              int not null default 1,
   primary key (id)
);

/*==============================================================*/
/* Index: time                                                  */
/*==============================================================*/
create index time on Fdapcarhisdata_
(
   time
);

/*==============================================================*/
/* Table: Fdaphisalarm_                                         */
/*==============================================================*/
create table Fdaphisalarm_
(
   hisalarmid           bigint not null auto_increment,
   aiguid               varchar(36),
   alarmdata            decimal(5,2) not null,
   startTime            datetime not null,
   endTime              datetime,
   alarmlevel           int not null,
   alarmtype            int not null,
   alarmstandard        decimal(5,2),
   flag                 int not null default 0,
   primary key (hisalarmid)
);

/*==============================================================*/
/* Index: startTime                                             */
/*==============================================================*/
create index startTime on Fdaphisalarm_
(
   startTime
);

/*==============================================================*/
/* Index: endTime                                               */
/*==============================================================*/
create index endTime on Fdaphisalarm_
(
   endTime
);

/*==============================================================*/
/* Table: Fdapphone                                             */
/*==============================================================*/
create table Fdapphone
(
   phoneid              bigint not null auto_increment,
   phonenumber          varchar(25) not null,
   messagenumber        varchar(25) not null,
   callcode             varchar(50) not null,
   phoneActive          int not null,
   messageActive        int not null,
   primary key (phoneid)
);

/*==============================================================*/
/* Table: Fdaprefhisdata_                                       */
/*==============================================================*/
create table Fdaprefhisdata_
(
   refhisId             bigint not null auto_increment,
   aiguid               varchar(36),
   data                 decimal(5,2) not null,
   time                 datetime not null,
   isalarm              int not null default 1,
   primary key (refhisId)
);

/*==============================================================*/
/* Index: time                                                  */
/*==============================================================*/
create index time on Fdaprefhisdata_
(
   time
);

/*==============================================================*/
/* Table: Fdapstartup_                                          */
/*==============================================================*/
create table Fdapstartup_
(
   startupid            bigint not null auto_increment,
   refId                bigint,
   startTime            datetime not null,
   endTime              datetime,
   carrier              varchar(30),
   intervalValue        int not null default 0,
   primary key (startupid)
);

/*==============================================================*/
/* Index: startTime                                             */
/*==============================================================*/
create index startTime on Fdapstartup_
(
   startTime
);

/*==============================================================*/
/* Table: fdapaiinfo                                            */
/*==============================================================*/
create table fdapaiinfo
(
   aiguid               varchar(36) not null,
   refId                bigint,
   reid                 int not null,
   name                 varchar(50) not null,
   selftype             int not null default 0,
   location             int,
   lowerlimit           decimal(5,2) not null,
   lowerdelay           int not null,
   minlowerlimit        decimal(5,2),
   minlowerdelay        int,
   highlimit            decimal(5,2) not null,
   highdelay            int not null,
   maxhighlimit         decimal(5,2),
   maxhighdelay         int,
   primary key (aiguid)
);

/*==============================================================*/
/* Table: fdapauthcode                                          */
/*==============================================================*/
create table fdapauthcode
(
   authid               int not null auto_increment,
   oid                  bigint,
   code                 varchar(40) not null,
   remark               varchar(30),
   primary key (authid)
);

/*==============================================================*/
/* Index: code                                                  */
/*==============================================================*/
create index code on fdapauthcode
(
   code
);

/*==============================================================*/
/* Table: fdapcarrealdata                                       */
/*==============================================================*/
create table fdapcarrealdata
(
   realcarid            bigint not null auto_increment,
   refId                bigint,
   aid1                 int not null,
   aid2                 int not null,
   aid3                 int not null,
   t1                   decimal(5,2) not null,
   t2                   decimal(5,2) not null,
   t3                   decimal(5,2) not null,
   latitude             decimal(8,4) not null,
   latitude_dir         int not null,
   longitude            decimal(9,4) not null,
   longitude_dir        int not null,
   time                 datetime not null,
   isalarm              int not null default 1,
   primary key (realcarid)
);

/*==============================================================*/
/* Table: fdaplink                                              */
/*==============================================================*/
create table fdaplink
(
   lid                  bigint not null auto_increment,
   ltid                 bigint,
   name                 varchar(50) not null,
   url                  varchar(50),
   primary key (lid)
);

/*==============================================================*/
/* Table: fdaplinktype                                          */
/*==============================================================*/
create table fdaplinktype
(
   ltid                 bigint not null auto_increment,
   ltname               varchar(25) not null,
   primary key (ltid)
);

/*==============================================================*/
/* Table: fdaporg                                               */
/*==============================================================*/
create table fdaporg
(
   oid                  bigint not null auto_increment,
   name                 varchar(50) not null,
   account              varchar(30) not null,
   telephone            varchar(15),
   email                varchar(30),
   parentId             bigint not null,
   flag                 int not null default 0,
   nodetype             int not null,
   isshowmap            int not null,
   remark               varchar(30),
   primary key (oid)
);

/*==============================================================*/
/* Table: fdappower                                             */
/*==============================================================*/
create table fdappower
(
   pid                  bigint not null auto_increment,
   name                 varchar(50) not null,
   remark               varchar(30),
   primary key (pid)
);

/*==============================================================*/
/* Table: fdapproject                                           */
/*==============================================================*/
create table fdapproject
(
   projectid            bigint not null auto_increment,
   oid                  bigint,
   name                 varchar(50) not null,
   type                 int not null default 1,
   remark               varchar(30),
   projectNO            varchar(20),
   primary key (projectid)
);

/*==============================================================*/
/* Table: fdaprealalarm                                         */
/*==============================================================*/
create table fdaprealalarm
(
   realalarmid          bigint not null auto_increment,
   aiguid               varchar(36),
   orgId                bigint not null,
   alarmdata            decimal(5,2) not null,
   time                 datetime not null,
   isrealalarm          int not null,
   alarmlevel           int not null,
   alarmtype            int not null,
   alarmstandard        decimal(5,2),
   primary key (realalarmid)
);

/*==============================================================*/
/* Table: fdapref                                               */
/*==============================================================*/
create table fdapref
(
   refId                bigint not null auto_increment,
   projectid            bigint,
   storeid              int,
   name                 varchar(50) not null,
   floorType            int,
   floorNo              int,
   isactive             int not null default 0,
   remark               varchar(30),
   primary key (refId)
);

/*==============================================================*/
/* Table: fdaprefactive                                         */
/*==============================================================*/
create table fdaprefactive
(
   refactiveid          int not null auto_increment,
   refId                bigint,
   refactivestate       int not null,
   refactivetime        datetime not null,
   primary key (refactiveid)
);

/*==============================================================*/
/* Table: fdaprefrealdata                                       */
/*==============================================================*/
create table fdaprefrealdata
(
   realrefid            bigint not null auto_increment,
   aiguid               varchar(36),
   data                 decimal(5,2) not null,
   time                 datetime not null,
   isalarm              int not null default 1,
   primary key (realrefid)
);

/*==============================================================*/
/* Table: fdaprole                                              */
/*==============================================================*/
create table fdaprole
(
   rid                  bigint not null auto_increment,
   name                 varchar(50) not null,
   remark               varchar(30),
   primary key (rid)
);

/*==============================================================*/
/* Table: fdaprole_power                                        */
/*==============================================================*/
create table fdaprole_power
(
   rpid                 bigint not null auto_increment,
   pid                  bigint,
   rid                  bigint,
   primary key (rpid)
);

/*==============================================================*/
/* Table: fdapstoretype                                         */
/*==============================================================*/
create table fdapstoretype
(
   storeid              int not null auto_increment,
   name                 varchar(50) not null,
   templowerlimit       decimal(5,2) not null,
   templowerdelay       int not null,
   tempminlowerlimit    decimal(5,2),
   tempminlowerdelay    int,
   temphighlimit        decimal(5,2) not null,
   temphighdelay        int not null,
   tempmaxhighlimit     decimal(5,2),
   tempmaxhighdelay     int,
   humlowerlimit        decimal(5,2) not null,
   humlowerdelay        int not null,
   humhighlimit         decimal(5,2) not null,
   humhighdelay         int not null,
   remark               varchar(30),
   primary key (storeid)
);

/*==============================================================*/
/* Table: fdapuser                                              */
/*==============================================================*/
create table fdapuser
(
   userid               bigint not null auto_increment,
   oid                  bigint,
   rid                  bigint,
   name                 varchar(50) not null,
   password             varchar(20) not null,
   remark               varchar(30),
   primary key (userid)
);

alter table Fdapcarhisdata_ add constraint FK_Relationship_17 foreign key (startupid)
      references Fdapstartup_ (startupid) on delete restrict on update restrict;

alter table Fdaphisalarm_ add constraint FK_Relationship_14 foreign key (aiguid)
      references fdapaiinfo (aiguid) on delete cascade on update cascade;

alter table Fdaprefhisdata_ add constraint FK_Relationship_15 foreign key (aiguid)
      references fdapaiinfo (aiguid) on delete cascade on update cascade;

alter table Fdapstartup_ add constraint FK_Relationship_16 foreign key (refId)
      references fdapref (refId) on delete cascade on update cascade;

alter table fdapaiinfo add constraint FK_Relationship_9 foreign key (refId)
      references fdapref (refId) on delete restrict on update restrict;

alter table fdapauthcode add constraint FK_Relationship_5 foreign key (oid)
      references fdaporg (oid) on delete restrict on update restrict;

alter table fdapcarrealdata add constraint FK_Relationship_11 foreign key (refId)
      references fdapref (refId) on delete cascade on update cascade;

alter table fdaplink add constraint FK_Relationship_18 foreign key (ltid)
      references fdaplinktype (ltid) on delete restrict on update restrict;

alter table fdapproject add constraint FK_Relationship_6 foreign key (oid)
      references fdaporg (oid) on delete restrict on update restrict;

alter table fdaprealalarm add constraint FK_Relationship_12 foreign key (aiguid)
      references fdapaiinfo (aiguid) on delete cascade on update cascade;

alter table fdapref add constraint FK_Relationship_7 foreign key (projectid)
      references fdapproject (projectid) on delete restrict on update restrict;

alter table fdapref add constraint FK_Relationship_8 foreign key (storeid)
      references fdapstoretype (storeid) on delete restrict on update restrict;

alter table fdaprefactive add constraint FK_Relationship_13 foreign key (refId)
      references fdapref (refId) on delete restrict on update restrict;

alter table fdaprefrealdata add constraint FK_Relationship_10 foreign key (aiguid)
      references fdapaiinfo (aiguid) on delete cascade on update cascade;

alter table fdaprole_power add constraint FK_Relationship_1 foreign key (rid)
      references fdaprole (rid) on delete restrict on update restrict;

alter table fdaprole_power add constraint FK_Relationship_2 foreign key (pid)
      references fdappower (pid) on delete restrict on update restrict;

alter table fdapuser add constraint FK_Relationship_3 foreign key (rid)
      references fdaprole (rid) on delete restrict on update restrict;

alter table fdapuser add constraint FK_Relationship_4 foreign key (oid)
      references fdaporg (oid) on delete cascade on update cascade;


	  
DROP VIEW IF EXISTS `fdap_view_carrealdata`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `fdap_view_carrealdata` AS select `ref`.`refId` AS `refId`,`ref`.`name` AS `name`,`r`.`aid1` AS `aid1`,`r`.`aid2` AS `aid2`,`r`.`aid3` AS `aid3`,`r`.`t1` AS `t1`,`r`.`t2` AS `t2`,`r`.`t3` AS `t3`,`r`.`latitude` AS `latitude`,`r`.`latitude_dir` AS `latitude_dir`,`r`.`longitude` AS `longitude`,`r`.`longitude_dir` AS `longitude_dir`,`r`.`Time` AS `time`,`r`.`isalarm` AS `isalarm`,`p`.`projectid` AS `projectid`,`p`.`projectNO` AS `projectNO` from ((`fdapref` `ref` join `fdapcarrealdata` `r` on((`ref`.`refId` = `r`.`refId`))) join `fdapproject` `p` on((`p`.`projectid` = `ref`.`projectid`))) where (`p`.`type` = 2);


DROP VIEW IF EXISTS `fdap_view_configinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW fdap_view_configinfo
AS
SELECT     `ai`.`reid` AS `reid`, `ai`.`name` AS `ainame`, `pro`.`oid` AS `oid`, `pro`.`type` AS `type`,`pro`.`projectNO` AS `projectNO`, 
                      `ref`.`name` AS `refname`
FROM         `fdapaiinfo` `ai` INNER JOIN
                      `fdapref` `ref` ON `ai`.`refId` = `ref`.`refId` INNER JOIN
                      `fdapproject` `pro` ON `ref`.`projectid` = `pro`.`projectid`;
					  
					  

INSERT INTO fdaprole VALUES ('1', '工程角色', '系统配置、系统参数设置');
INSERT INTO fdaprole VALUES ('2', '系统管理员', '为系统创建用户');
INSERT INTO fdaprole VALUES ('3', '查看用户', '为各级机构创建登录用户');

INSERT INTO fdaporg VALUES ('1', '河南爱生医药', 'ashn', '4564564645', 'as@google.cn', '-1', '0', '0', '0','top');

INSERT INTO fdapuser VALUES ('1', '1', '1', 'project', 'project', null);
INSERT INTO fdapuser VALUES ('2', '1', '2', 'admin', 'admin', null);

										 
INSERT INTO `fdapstoretype` VALUES ('1', '常温库', '2.00', '15', '0.00', '15', '30.00', '15', '35.00', '15', '45.00', '15', '75.00', '15', null);
INSERT INTO `fdapstoretype` VALUES ('2', '阴凉间', '2.00', '15', '0.00', '15', '20.00', '15', '35.00', '15', '45.00', '15', '75.00', '15', null);
INSERT INTO `fdapstoretype` VALUES ('3', '中温库', '2.00', '15', '0.00', '15', '8.00', '15', '10.00', '15', '45.00', '15', '75.00', '15', null);
INSERT INTO `fdapstoretype` VALUES ('4', '冷冻库', '-20.00', '15', '0.00', '15', '-12.00', '15', '10.00', '15', '0.00', '60', '100.00', '60', null);
	  
INSERT INTO `fdapphone` VALUES ('1','18721397550','18721397550','thermoberg',0,0);