/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     2011-6-18 16:50:20                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Fdapcarhisdata_') and o.name = 'FK_FDAPCARH_RELATIONS_FDAPSTAR')
alter table Fdapcarhisdata_
   drop constraint FK_FDAPCARH_RELATIONS_FDAPSTAR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Fdaphisalarm_') and o.name = 'FK_FDAPHISA_RELATIONS_FDAPAIIN')
alter table Fdaphisalarm_
   drop constraint FK_FDAPHISA_RELATIONS_FDAPAIIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Fdaprefhisdata_') and o.name = 'FK_FDAPREFH_RELATIONS_FDAPAIIN')
alter table Fdaprefhisdata_
   drop constraint FK_FDAPREFH_RELATIONS_FDAPAIIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Fdapstartup_') and o.name = 'FK_FDAPSTAR_RELATIONS_FDAPREF')
alter table Fdapstartup_
   drop constraint FK_FDAPSTAR_RELATIONS_FDAPREF
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapaiinfo') and o.name = 'FK_FDAPAIIN_RELATIONS_FDAPREF')
alter table fdapaiinfo
   drop constraint FK_FDAPAIIN_RELATIONS_FDAPREF
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapauthcode') and o.name = 'FK_FDAPAUTH_RELATIONS_FDAPORG')
alter table fdapauthcode
   drop constraint FK_FDAPAUTH_RELATIONS_FDAPORG
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapcarrealdata') and o.name = 'FK_FDAPCARR_RELATIONS_FDAPREF')
alter table fdapcarrealdata
   drop constraint FK_FDAPCARR_RELATIONS_FDAPREF
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdaplink') and o.name = 'FK_FDAPLINK_RELATIONS_FDAPLINK')
alter table fdaplink
   drop constraint FK_FDAPLINK_RELATIONS_FDAPLINK
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapproject') and o.name = 'FK_FDAPPROJ_RELATIONS_FDAPORG')
alter table fdapproject
   drop constraint FK_FDAPPROJ_RELATIONS_FDAPORG
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdaprealalarm') and o.name = 'FK_FDAPREAL_RELATIONS_FDAPAIIN')
alter table fdaprealalarm
   drop constraint FK_FDAPREAL_RELATIONS_FDAPAIIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapref') and o.name = 'FK_FDAPREF_RELATIONS_FDAPPROJ')
alter table fdapref
   drop constraint FK_FDAPREF_RELATIONS_FDAPPROJ
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapref') and o.name = 'FK_FDAPREF_RELATIONS_FDAPSTOR')
alter table fdapref
   drop constraint FK_FDAPREF_RELATIONS_FDAPSTOR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdaprefactive') and o.name = 'FK_FDAPREFA_RELATIONS_FDAPREF')
alter table fdaprefactive
   drop constraint FK_FDAPREFA_RELATIONS_FDAPREF
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdaprefrealdata') and o.name = 'FK_FDAPREFR_RELATIONS_FDAPAIIN')
alter table fdaprefrealdata
   drop constraint FK_FDAPREFR_RELATIONS_FDAPAIIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdaprole_power') and o.name = 'FK_FDAPROLE_RELATIONS_FDAPROLE')
alter table fdaprole_power
   drop constraint FK_FDAPROLE_RELATIONS_FDAPROLE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdaprole_power') and o.name = 'FK_FDAPROLE_RELATIONS_FDAPPOWE')
alter table fdaprole_power
   drop constraint FK_FDAPROLE_RELATIONS_FDAPPOWE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapuser') and o.name = 'FK_FDAPUSER_RELATIONS_FDAPROLE')
alter table fdapuser
   drop constraint FK_FDAPUSER_RELATIONS_FDAPROLE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapuser') and o.name = 'FK_FDAPUSER_RELATIONS_FDAPORG')
alter table fdapuser
   drop constraint FK_FDAPUSER_RELATIONS_FDAPORG
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdapcarhisdata_')
            and   name  = 'time'
            and   indid > 0
            and   indid < 255)
   drop index Fdapcarhisdata_.time
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdapcarhisdata_')
            and   type = 'U')
   drop table Fdapcarhisdata_
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdaphisalarm_')
            and   name  = 'endTime'
            and   indid > 0
            and   indid < 255)
   drop index Fdaphisalarm_.endTime
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdaphisalarm_')
            and   name  = 'startTime'
            and   indid > 0
            and   indid < 255)
   drop index Fdaphisalarm_.startTime
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdaphisalarm_')
            and   type = 'U')
   drop table Fdaphisalarm_
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdapphone')
            and   type = 'U')
   drop table Fdapphone
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdaprefhisdata_')
            and   name  = 'time'
            and   indid > 0
            and   indid < 255)
   drop index Fdaprefhisdata_.time
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdaprefhisdata_')
            and   type = 'U')
   drop table Fdaprefhisdata_
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdapstartup_')
            and   name  = 'startTime'
            and   indid > 0
            and   indid < 255)
   drop index Fdapstartup_.startTime
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdapstartup_')
            and   type = 'U')
   drop table Fdapstartup_
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapaiinfo')
            and   type = 'U')
   drop table fdapaiinfo
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('fdapauthcode')
            and   name  = 'code'
            and   indid > 0
            and   indid < 255)
   drop index fdapauthcode.code
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapauthcode')
            and   type = 'U')
   drop table fdapauthcode
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapcarrealdata')
            and   type = 'U')
   drop table fdapcarrealdata
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaplink')
            and   type = 'U')
   drop table fdaplink
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaplinktype')
            and   type = 'U')
   drop table fdaplinktype
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaporg')
            and   type = 'U')
   drop table fdaporg
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdappower')
            and   type = 'U')
   drop table fdappower
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapproject')
            and   type = 'U')
   drop table fdapproject
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaprealalarm')
            and   type = 'U')
   drop table fdaprealalarm
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapref')
            and   type = 'U')
   drop table fdapref
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaprefactive')
            and   type = 'U')
   drop table fdaprefactive
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaprefrealdata')
            and   type = 'U')
   drop table fdaprefrealdata
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaprole')
            and   type = 'U')
   drop table fdaprole
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdaprole_power')
            and   type = 'U')
   drop table fdaprole_power
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapstoretype')
            and   type = 'U')
   drop table fdapstoretype
go

if exists (select 1
            from  sysobjects
           where  id = object_id('fdapuser')
            and   type = 'U')
   drop table fdapuser
go

/*==============================================================*/
/* Table: Fdapcarhisdata_                                       */
/*==============================================================*/
create table Fdapcarhisdata_ (
   id                   bigint               identity,
   startupid            bigint               null,
   t1                   decimal(5,2)         not null,
   t2                   decimal(5,2)         not null,
   t3                   decimal(5,2)         not null,
   latitude             decimal(8,4)         not null,
   latitude_dir         int                  not null,
   longitude            decimal(9,4)         not null,
   longitude_dir        int                  not null,
   time                 datetime             not null,
   isalarm              int                  not null default 1,
   constraint PK_FDAPCARHISDATA_ primary key nonclustered (id)
)
go

/*==============================================================*/
/* Index: time                                                  */
/*==============================================================*/
create index time on Fdapcarhisdata_ (
time ASC
)
go

/*==============================================================*/
/* Table: Fdaphisalarm_                                         */
/*==============================================================*/
create table Fdaphisalarm_ (
   hisalarmid           bigint               identity,
   aiguid               varchar(36)          null,
   alarmdata            decimal(5,2)         not null,
   startTime            datetime             not null,
   endTime              datetime             null,
   alarmlevel           int                  not null,
   alarmtype            int                  not null,
   alarmstandard        decimal(5,2)         null,
   flag                 int                  not null default 0,
   constraint PK_FDAPHISALARM_ primary key nonclustered (hisalarmid)
)
go

/*==============================================================*/
/* Index: startTime                                             */
/*==============================================================*/
create index startTime on Fdaphisalarm_ (
startTime ASC
)
go

/*==============================================================*/
/* Index: endTime                                               */
/*==============================================================*/
create index endTime on Fdaphisalarm_ (
endTime ASC
)
go

/*==============================================================*/
/* Table: Fdapphone                                             */
/*==============================================================*/
create table Fdapphone (
   phoneid              bigint               identity,
   phonenumber          varchar(25)          not null,
   messagenumber        varchar(25)          not null,
   callcode             varchar(50)          not null,
   phoneActive          int                  not null,
   messageActive        int                  not null,
   constraint PK_FDAPPHONE primary key nonclustered (phoneid)
)
go

/*==============================================================*/
/* Table: Fdaprefhisdata_                                       */
/*==============================================================*/
create table Fdaprefhisdata_ (
   refhisId             bigint               identity,
   aiguid               varchar(36)          null,
   data                 decimal(5,2)         not null,
   time                 datetime             not null,
   isalarm              int                  not null default 1,
   constraint PK_FDAPREFHISDATA_ primary key nonclustered (refhisId)
)
go

/*==============================================================*/
/* Index: time                                                  */
/*==============================================================*/
create index time on Fdaprefhisdata_ (
time ASC
)
go

/*==============================================================*/
/* Table: Fdapstartup_                                          */
/*==============================================================*/
create table Fdapstartup_ (
   startupid            bigint               identity,
   refId                bigint               null,
   startTime            datetime             not null,
   endTime              datetime             null,
   carrier              varchar(30)          null,
   intervalValue        int                  not null default 0,
   constraint PK_FDAPSTARTUP_ primary key nonclustered (startupid)
)
go

/*==============================================================*/
/* Index: startTime                                             */
/*==============================================================*/
create index startTime on Fdapstartup_ (
startTime ASC
)
go

/*==============================================================*/
/* Table: fdapaiinfo                                            */
/*==============================================================*/
create table fdapaiinfo (
   aiguid               varchar(36)          not null,
   refId                bigint               null,
   reid                 int                  not null,
   name                 varchar(50)          not null,
   selftype             int                  not null default 0,
   location             int                  null,
   lowerlimit           decimal(5,2)         not null,
   lowerdelay           int                  not null,
   minlowerlimit        decimal(5,2)         null,
   minlowerdelay        int                  null,
   highlimit            decimal(5,2)         not null,
   highdelay            int                  not null,
   maxhighlimit         decimal(5,2)         null,
   maxhighdelay         int                  null,
   constraint PK_FDAPAIINFO primary key nonclustered (aiguid)
)
go

/*==============================================================*/
/* Table: fdapauthcode                                          */
/*==============================================================*/
create table fdapauthcode (
   authid               int                  identity,
   oid                  bigint               null,
   code                 varchar(40)          not null,
   remark               varchar(30)          null,
   constraint PK_FDAPAUTHCODE primary key nonclustered (authid)
)
go

/*==============================================================*/
/* Index: code                                                  */
/*==============================================================*/
create index code on fdapauthcode (
code ASC
)
go

/*==============================================================*/
/* Table: fdapcarrealdata                                       */
/*==============================================================*/
create table fdapcarrealdata (
   realcarid            bigint               identity,
   refId                bigint               null,
   aid1                 int                  not null,
   aid2                 int                  not null,
   aid3                 int                  not null,
   t1                   decimal(5,2)         not null,
   t2                   decimal(5,2)         not null,
   t3                   decimal(5,2)         not null,
   latitude             decimal(8,4)         not null,
   latitude_dir         int                  not null,
   longitude            decimal(9,4)         not null,
   longitude_dir        int                  not null,
   time                 datetime             not null,
   isalarm              int                  not null default 1,
   constraint PK_FDAPCARREALDATA primary key nonclustered (realcarid)
)
go

/*==============================================================*/
/* Table: fdaplink                                              */
/*==============================================================*/
create table fdaplink (
   lid                  bigint               identity,
   ltid                 bigint               null,
   name                 varchar(50)          not null,
   url                  varchar(50)          null,
   constraint PK_FDAPLINK primary key nonclustered (lid)
)
go

/*==============================================================*/
/* Table: fdaplinktype                                          */
/*==============================================================*/
create table fdaplinktype (
   ltid                 bigint               identity,
   ltname               varchar(25)          not null,
   constraint PK_FDAPLINKTYPE primary key nonclustered (ltid)
)
go

/*==============================================================*/
/* Table: fdaporg                                               */
/*==============================================================*/
create table fdaporg (
   oid                  bigint               identity,
   name                 varchar(50)          not null,
   account              varchar(30)          not null,
   telephone            varchar(15)          null,
   email                varchar(30)          null,
   parentId             bigint               not null,
   flag                 int                  not null default 0,
   nodetype             int                  not null,
   isshowmap            int                  not null,
   remark               varchar(30)          null,
   constraint PK_FDAPORG primary key nonclustered (oid)
)
go

/*==============================================================*/
/* Table: fdappower                                             */
/*==============================================================*/
create table fdappower (
   pid                  bigint               identity,
   name                 varchar(50)          not null,
   remark               varchar(30)          null,
   constraint PK_FDAPPOWER primary key nonclustered (pid)
)
go

/*==============================================================*/
/* Table: fdapproject                                           */
/*==============================================================*/
create table fdapproject (
   projectid            bigint               identity,
   oid                  bigint               null,
   name                 varchar(50)          not null,
   type                 int                  not null default 1,
   remark               varchar(30)          null,
   projectNO            varchar(20)          null,
   constraint PK_FDAPPROJECT primary key nonclustered (projectid)
)
go

/*==============================================================*/
/* Table: fdaprealalarm                                         */
/*==============================================================*/
create table fdaprealalarm (
   realalarmid          bigint               identity,
   aiguid               varchar(36)          null,
   orgId                bigint               not null,
   alarmdata            decimal(5,2)         not null,
   time                 datetime             not null,
   isrealalarm          int                  not null,
   alarmlevel           int                  not null,
   alarmtype            int                  not null,
   alarmstandard        decimal(5,2)         null,
   constraint PK_FDAPREALALARM primary key nonclustered (realalarmid)
)
go

/*==============================================================*/
/* Table: fdapref                                               */
/*==============================================================*/
create table fdapref (
   refId                bigint               identity,
   projectid            bigint               null,
   storeid              int                  null,
   name                 varchar(50)          not null,
   floorType            int                  null,
   floorNo              int                  null,
   isactive             int                  not null default 0,
   remark               varchar(30)          null,
   constraint PK_FDAPREF primary key nonclustered (refId)
)
go

/*==============================================================*/
/* Table: fdaprefactive                                         */
/*==============================================================*/
create table fdaprefactive (
   refactiveid          int                  identity,
   refId                bigint               null,
   refactivestate       int                  not null,
   refactivetime        datetime             not null,
   constraint PK_FDAPREFACTIVE primary key nonclustered (refactiveid)
)
go

/*==============================================================*/
/* Table: fdaprefrealdata                                       */
/*==============================================================*/
create table fdaprefrealdata (
   realrefid            bigint               identity,
   aiguid               varchar(36)          null,
   data                 decimal(5,2)         not null,
   time                 datetime             not null,
   isalarm              int                  not null default 1,
   constraint PK_FDAPREFREALDATA primary key nonclustered (realrefid)
)
go

/*==============================================================*/
/* Table: fdaprole                                              */
/*==============================================================*/
create table fdaprole (
   rid                  bigint               identity,
   name                 varchar(50)          not null,
   remark               varchar(30)          null,
   constraint PK_FDAPROLE primary key nonclustered (rid)
)
go

/*==============================================================*/
/* Table: fdaprole_power                                        */
/*==============================================================*/
create table fdaprole_power (
   rpid                 bigint               identity,
   pid                  bigint               null,
   rid                  bigint               null,
   constraint PK_FDAPROLE_POWER primary key nonclustered (rpid)
)
go

/*==============================================================*/
/* Table: fdapstoretype                                         */
/*==============================================================*/
create table fdapstoretype (
   storeid              int                  identity,
   name                 varchar(50)          not null,
   templowerlimit       decimal(5,2)         not null,
   templowerdelay       int                  not null,
   tempminlowerlimit    decimal(5,2)         null,
   tempminlowerdelay    int                  null,
   temphighlimit        decimal(5,2)         not null,
   temphighdelay        int                  not null,
   tempmaxhighlimit     decimal(5,2)         null,
   tempmaxhighdelay     int                  null,
   humlowerlimit        decimal(5,2)         not null,
   humlowerdelay        int                  not null,
   humhighlimit         decimal(5,2)         not null,
   humhighdelay         int                  not null,
   remark               varchar(30)          null,
   constraint PK_FDAPSTORETYPE primary key nonclustered (storeid)
)
go

/*==============================================================*/
/* Table: fdapuser                                              */
/*==============================================================*/
create table fdapuser (
   userid               bigint               identity,
   oid                  bigint               null,
   rid                  bigint               null,
   name                 varchar(50)          not null,
   password             varchar(20)          not null,
   remark               varchar(30)          null,
   constraint PK_FDAPUSER primary key nonclustered (userid)
)
go

alter table Fdapcarhisdata_
   add constraint FK_FDAPCARH_RELATIONS_FDAPSTAR foreign key (startupid)
      references Fdapstartup_ (startupid)
go

alter table Fdaphisalarm_
   add constraint FK_FDAPHISA_RELATIONS_FDAPAIIN foreign key (aiguid)
      references fdapaiinfo (aiguid)
         on update cascade on delete cascade
go

alter table Fdaprefhisdata_
   add constraint FK_FDAPREFH_RELATIONS_FDAPAIIN foreign key (aiguid)
      references fdapaiinfo (aiguid)
         on update cascade on delete cascade
go

alter table Fdapstartup_
   add constraint FK_FDAPSTAR_RELATIONS_FDAPREF foreign key (refId)
      references fdapref (refId)
         on update cascade on delete cascade
go

alter table fdapaiinfo
   add constraint FK_FDAPAIIN_RELATIONS_FDAPREF foreign key (refId)
      references fdapref (refId)
go

alter table fdapauthcode
   add constraint FK_FDAPAUTH_RELATIONS_FDAPORG foreign key (oid)
      references fdaporg (oid)
go

alter table fdapcarrealdata
   add constraint FK_FDAPCARR_RELATIONS_FDAPREF foreign key (refId)
      references fdapref (refId)
         on update cascade on delete cascade
go

alter table fdaplink
   add constraint FK_FDAPLINK_RELATIONS_FDAPLINK foreign key (ltid)
      references fdaplinktype (ltid)
go

alter table fdapproject
   add constraint FK_FDAPPROJ_RELATIONS_FDAPORG foreign key (oid)
      references fdaporg (oid)
go

alter table fdaprealalarm
   add constraint FK_FDAPREAL_RELATIONS_FDAPAIIN foreign key (aiguid)
      references fdapaiinfo (aiguid)
         on update cascade on delete cascade
go

alter table fdapref
   add constraint FK_FDAPREF_RELATIONS_FDAPPROJ foreign key (projectid)
      references fdapproject (projectid)
go

alter table fdapref
   add constraint FK_FDAPREF_RELATIONS_FDAPSTOR foreign key (storeid)
      references fdapstoretype (storeid)
go

alter table fdaprefactive
   add constraint FK_FDAPREFA_RELATIONS_FDAPREF foreign key (refId)
      references fdapref (refId)
go

alter table fdaprefrealdata
   add constraint FK_FDAPREFR_RELATIONS_FDAPAIIN foreign key (aiguid)
      references fdapaiinfo (aiguid)
         on update cascade on delete cascade
go

alter table fdaprole_power
   add constraint FK_FDAPROLE_RELATIONS_FDAPROLE foreign key (rid)
      references fdaprole (rid)
go

alter table fdaprole_power
   add constraint FK_FDAPROLE_RELATIONS_FDAPPOWE foreign key (pid)
      references fdappower (pid)
go

alter table fdapuser
   add constraint FK_FDAPUSER_RELATIONS_FDAPROLE foreign key (rid)
      references fdaprole (rid)
go

alter table fdapuser
   add constraint FK_FDAPUSER_RELATIONS_FDAPORG foreign key (oid)
      references fdaporg (oid)
         on update cascade on delete cascade
go





IF EXISTS (SELECT * FROM sysobjects WHERE /*检测是否存在*/  
                         name = 'fdap_view_carrealdata')  
     DROP VIEW fdap_view_carrealdata /*删除视图*/  
GO  
CREATE VIEW fdap_view_carrealdata AS select ref.refId AS refId,ref.name AS name,r.aid1 AS aid1,r.aid2 AS aid2,r.aid3 AS aid3,r.t1 AS t1,r.t2 AS t2,r.t3 AS t3,r.latitude AS latitude,r.latitude_dir AS latitude_dir,r.longitude AS longitude,r.longitude_dir AS longitude_dir,r.Time AS time,r.isalarm AS isalarm,p.projectid AS projectid,p.projectNO AS projectNO from ((fdapref ref join fdapcarrealdata r on((ref.refId = r.refId))) join fdapproject p on((p.projectid = ref.projectid))) where (p.type = 2)
go


IF EXISTS (SELECT * FROM sysobjects WHERE /*检测是否存在*/  
                         name = 'fdap_view_configinfo')
     DROP VIEW fdap_view_configinfo /*删除视图*/  
GO 
CREATE VIEW fdap_view_configinfo
AS
SELECT     dbo.fdapaiinfo.reid, dbo.fdapaiinfo.name AS ainame, dbo.fdapproject.oid, dbo.fdapproject.type, dbo.fdapproject.projectNO, 
                      dbo.fdapref.name AS refname
FROM         dbo.fdapaiinfo INNER JOIN
                      dbo.fdapref ON dbo.fdapaiinfo.refId = dbo.fdapref.refId INNER JOIN
                      dbo.fdapproject ON dbo.fdapref.projectid = dbo.fdapproject.projectid

GO



INSERT INTO fdaprole(name,remark) VALUES ('工程角色', '系统配置、系统参数设置') 
go
INSERT INTO fdaprole(name,remark) VALUES ( '系统管理员', '为系统创建用户') 
go
INSERT INTO fdaprole(name,remark) VALUES ('查看用户', '为各级机构创建登录用户') 
go

INSERT INTO fdaporg(name,account,telephone,email,parentid,flag,nodetype,isshowmap,remark) VALUES( '河南爱生医药', 'hnas', '4564544544', 'as@google.cn', '-1', '0', '0', '0','top') 
go

INSERT INTO fdapuser(oid,rid,name,password,remark) VALUES ( '1', '1', 'project', 'project', null) 
go
INSERT INTO fdapuser(oid,rid,name,password,remark) VALUES ( '1', '2', 'admin', 'admin', null) 
go


INSERT INTO fdapstoretype(name,templowerlimit,templowerdelay,tempminlowerlimit,tempminlowerdelay,temphighlimit,temphighdelay,tempmaxhighlimit,tempmaxhighdelay,humlowerlimit,humlowerdelay,humhighlimit,humhighdelay,remark) 
VALUES ('常温库', '2.00', '15', '0.00', '15', '30.00', '15', '35.00', '15', '45.00', '15', '75.00', '15', null) 
go
INSERT INTO fdapstoretype(name,templowerlimit,templowerdelay,tempminlowerlimit,tempminlowerdelay,temphighlimit,temphighdelay,tempmaxhighlimit,tempmaxhighdelay,humlowerlimit,humlowerdelay,humhighlimit,humhighdelay,remark) 
VALUES ('阴凉间', '2.00', '15', '0.00', '15', '20.00', '15', '35.00', '15', '45.00', '15', '75.00', '15', null) 
go
INSERT INTO fdapstoretype(name,templowerlimit,templowerdelay,tempminlowerlimit,tempminlowerdelay,temphighlimit,temphighdelay,tempmaxhighlimit,tempmaxhighdelay,humlowerlimit,humlowerdelay,humhighlimit,humhighdelay,remark) 
VALUES ('中温库', '2.00', '15', '0.00', '15', '8.00', '15', '10.00', '15', '45.00', '15', '75.00', '15', null) 
go
INSERT INTO fdapstoretype(name,templowerlimit,templowerdelay,tempminlowerlimit,tempminlowerdelay,temphighlimit,temphighdelay,tempmaxhighlimit,tempmaxhighdelay,humlowerlimit,humlowerdelay,humhighlimit,humhighdelay,remark) 
VALUES ('冷冻库', '-20.00', '15', '0.00', '15', '-12.00', '15', '10.00', '15', '0.00', '60', '100.00', '60', null) 
go

INSERT INTO Fdapphone(phonenumber,messagenumber,callcode,phoneActive,messageActive) values('18721397550','18721397550','Thermoberg',0,0)
go