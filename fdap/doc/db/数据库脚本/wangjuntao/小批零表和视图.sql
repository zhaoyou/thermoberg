/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     2012-12-04 13:00:01                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Fdapretailhisdata_') and o.name = 'FK_FDAPRETIALH_RELATIONS_FDAPRETAILSTAR')
alter table Fdapretailhisdata_
   drop constraint FK_FDAPRETAILH_RELATIONS_FDAPRETAILSTAR
go


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Fdapretailstartup_') and o.name = 'FK_FDAPRETAILSTAR_RELATIONS_FDAPREF')
alter table Fdapretailstartup_
   drop constraint FK_FDAPRETAILSTAR_RELATIONS_FDAPREF
go



if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('fdapretailrealdata') and o.name = 'FK_FDAPRETAILR_RELATIONS_FDAPREF')
alter table fdapretailrealdata
   drop constraint FK_FDAPRETAILR_RELATIONS_FDAPREF
go


if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdapretailhisdata_')
            and   name  = 'time'
            and   indid > 0
            and   indid < 255)
   drop index Fdapretailhisdata_.time
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdapretailhisdata_')
            and   type = 'U')
   drop table Fdapretailhisdata_
go


if exists (select 1
            from  sysindexes
           where  id    = object_id('Fdapretailstartup_')
            and   name  = 'startTime'
            and   indid > 0
            and   indid < 255)
   drop index Fdapretailstartup_.startTime
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Fdapretailstartup_')
            and   type = 'U')
   drop table Fdapretailstartup_
go


if exists (select 1
            from  sysobjects
           where  id = object_id('fdapretailrealdata')
            and   type = 'U')
   drop table fdapretailrealdata
go


/*==============================================================*/
/* Table: Fdapretailhisdata_                                       */
/*==============================================================*/
create table Fdapretailhisdata_ (
   id                   bigint               identity,
   startupid            bigint               null,
   t1                   decimal(5,2)         not null,
   t2                   decimal(5,2)         null,
   t3                   decimal(5,2)         null,
   latitude             decimal(8,4)         not null,
   latitude_dir         int                  not null,
   longitude            decimal(9,4)         not null,
   longitude_dir        int                  not null,
   time                 datetime             not null,
   isalarm              int                  not null default 1,
   constraint PK_FDAPRETAILHISDATA_ primary key nonclustered (id)
)
go

/*==============================================================*/
/* Index: time                                                  */
/*==============================================================*/
create index time on Fdapretailhisdata_ (
time ASC
)
go



/*==============================================================*/
/* Table: Fdapretailstartup_                                          */
/*==============================================================*/
create table Fdapretailstartup_ (
   startupid            bigint               identity,
   refId                bigint               null,
   startTime            datetime             not null,
   endTime              datetime             null,
   carrier              varchar(30)          null,
   intervalValue        int                  not null default 0,
   constraint PK_FDAPRETAILSTARTUP_ primary key nonclustered (startupid)
)
go

/*==============================================================*/
/* Index: startTime                                             */
/*==============================================================*/
create index startTime on Fdapretailstartup_ (
startTime ASC
)
go



/*==============================================================*/
/* Table: fdapretailrealdata                                       */
/*==============================================================*/
create table fdapretailrealdata (
   realretailid            bigint               identity,
   refId                bigint               null,
   aid1                 int                  not null,
   aid2                 int                   null,
   aid3                 int                   null,
   t1                   decimal(5,2)         not null,
   t2                   decimal(5,2)          null,
   t3                   decimal(5,2)          null,
   latitude             decimal(8,4)         not null,
   latitude_dir         int                  not null,
   longitude            decimal(9,4)         not null,
   longitude_dir        int                  not null,
   time                 datetime             not null,
   isalarm              int                  not null default 1,
   constraint PK_FDAPRETAILREALDATA primary key nonclustered (realretailid)
)
go



alter table Fdapretailhisdata_
   add constraint FK_FDAPRETAILH_RELATIONS_FDAPSTAR foreign key (startupid)
      references Fdapretailstartup_ (startupid)
go



alter table Fdapretailstartup_
   add constraint FK_FDAPRETAILSTAR_RELATIONS_FDAPREF foreign key (refId)
      references fdapref (refId)
         on update cascade on delete cascade
go


alter table fdapretailrealdata
   add constraint FK_FDAPRETAILR_RELATIONS_FDAPREF foreign key (refId)
      references fdapref (refId)
         on update cascade on delete cascade
go


IF EXISTS (SELECT * FROM sysobjects WHERE /*¼ì²âÊÇ·ñ´æÔÚ*/  
                         name = 'fdap_view_retailrealdata')  
     DROP VIEW fdap_view_retailrealdata /*É¾³ıÊÓÍ¼*/  
GO  
CREATE VIEW fdap_view_retailrealdata 
        AS 
        select ref.refId AS refId,ref.name AS name,r.aid1 AS aid1,r.aid2 AS aid2,r.aid3 AS aid3,
               r.t1 AS t1,r.t2 AS t2,r.t3 AS t3,r.latitude AS latitude,r.latitude_dir AS latitude_dir,
                r.longitude AS longitude,r.longitude_dir AS longitude_dir,r.Time AS time,r.isalarm AS isalarm,
                 p.projectid AS projectid,p.projectNO AS projectNO from ((fdapref ref join fdapretailrealdata r 
                  on((ref.refId = r.refId))) join fdapproject p on((p.projectid = ref.projectid))) where (p.type = 2)
go









