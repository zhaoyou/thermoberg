SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmRefRelation]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmRefRelation](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[erpRefId] [bigint] NOT NULL,
	[erpRefName] [varchar](50) NOT NULL,
	[ccRefId] [bigint] NOT NULL,
	[ccRefName] [varchar](50) NOT NULL,
	[isDelete] [tinyint] NOT NULL,
 CONSTRAINT [PK_pbmRefRelation] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmCarRelation]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmCarRelation](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[miCarId] [bigint] NOT NULL,
	[miCarName] [varchar](50) NOT NULL,
	[ccCarId] [bigint] NOT NULL,
	[ccCarName] [varchar](50) NOT NULL,
	[isDelete] [tinyint] NOT NULL,
 CONSTRAINT [PK_pbmCarRelation] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmBarcodeDetailTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmBarcodeDetailTrack](
	[BcdId] [bigint] NOT NULL,
	[BcId] [bigint] NOT NULL,
	[GoodsFullId] [bigint] NOT NULL,
	[TotalNum] [int] NOT NULL,
	[Kid] [bigint] NOT NULL,
	[UploadStatus] [tinyint] NOT NULL CONSTRAINT [DF_pbmBarcodeDetailTrack_UploadStatus]  DEFAULT ((0)),
	[orderId] [bigint] NULL,
	[isDelete] [tinyint] NULL,
	[oid] [bigint] NULL,
 CONSTRAINT [PK_pbmBarcodeDetailTrack] PRIMARY KEY CLUSTERED 
(
	[BcdId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmBarcodeTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmBarcodeTrack](
	[BcId] [bigint] NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[Barcode] [varchar](50) NOT NULL,
	[PacketType] [int] NOT NULL,
	[UploadStatus] [tinyint] NOT NULL CONSTRAINT [DF_pbmBarcodeTrack_UploadStatus]  DEFAULT ((0)),
	[packetNum] [int] NULL,
	[isDelete] [tinyint] NULL,
	[oid] [bigint] NULL,
 CONSTRAINT [PK_pbmBarcodeTrack] PRIMARY KEY CLUSTERED 
(
	[BcId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmCarInOutTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmCarInOutTrack](
	[cdId] [bigint] NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[pid] [bigint] NOT NULL,
	[InTime] [datetime] NOT NULL,
	[OutTime] [datetime] NOT NULL,
	[demandTime] [datetime] NOT NULL,
	[inPdaId] [bigint] NOT NULL,
	[outPdaId] [bigint] NOT NULL,
	[MiCarId] [bigint] NOT NULL,
	[subOrderMid] [bigint] NOT NULL,
	[isDelete] [tinyint] NOT NULL,
	[inOutStatus] [tinyint] NOT NULL,
	[UploadStatus] [tinyint] NOT NULL CONSTRAINT [DF_pbmCarInOutTrack_UploadStatus]  DEFAULT ((0)),
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_pbmCarInOutTrack] PRIMARY KEY CLUSTERED 
(
	[cdId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmMiReceiver]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmMiReceiver](
	[rid] [bigint] NOT NULL,
	[fullName] [varchar](20) NULL,
	[shortName] [varchar](4) NULL,
	[isDelete] [tinyint] NULL,
	[uploadStatus] [tinyint] NULL,
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_pbmMiReceiver] PRIMARY KEY CLUSTERED 
(
	[rid] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmOrderTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmOrderTrack](
	[OrderId] [bigint] NOT NULL,
	[OrderNo] [bigint] NOT NULL,
	[Receiverid] [bigint] NOT NULL,
	[OrderTime] [datetime] NOT NULL,
	[OrderStatus] [int] NOT NULL CONSTRAINT [DF_pbmOrderTrack_OrderStatus]  DEFAULT ((1)),
	[UploadStatus] [tinyint] NOT NULL CONSTRAINT [DF_pbmOrderTrack_UploadStatus]  DEFAULT ((0)),
	[oid] [bigint] NULL,
	[isDelete] [tinyint] NULL,
 CONSTRAINT [PK_pbmOrderTrack] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PbmSubOrderDetailTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[PbmSubOrderDetailTrack](
	[subOrderDetailId] [bigint] NOT NULL,
	[subOrderMid] [bigint] NULL,
	[orderId] [bigint] NULL,
	[bcdId] [bigint] NULL,
	[totalNum] [int] NULL,
	[isDelete] [tinyint] NULL,
	[uploadStatus] [tinyint] NULL,
	[oid] [bigint] NULL,
 CONSTRAINT [PK_PbmSubOrderDetailTrack] PRIMARY KEY CLUSTERED 
(
	[subOrderDetailId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PbmSubOrderMainTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[PbmSubOrderMainTrack](
	[subOrderMid] [bigint] NOT NULL,
	[subOrderParentMid] [bigint] NOT NULL,
	[orderId] [bigint] NOT NULL,
	[subOrderName] [varchar](50) NOT NULL,
	[inoutType] [tinyint] NOT NULL,
	[inWholeOrder] [tinyint] NOT NULL,
	[loosePacketNum] [int] NULL,
	[wholePacketNum] [int] NULL,
	[orderStatus] [tinyint] NOT NULL,
	[planType] [tinyint] NULL,
	[planStatus] [tinyint] NULL,
	[uploadStatus] [tinyint] NULL,
	[isDelete] [tinyint] NULL,
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_PbmSubOrderMainTrack] PRIMARY KEY CLUSTERED 
(
	[subOrderMid] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmGoodsBasicInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmGoodsBasicInfo](
	[GoodId] [bigint] NOT NULL,
	[Goodsname] [varchar](100) NOT NULL,
	[GoodsType] [varchar](40) NOT NULL,
	[Goodsunit] [varchar](50) NOT NULL,
	[typename] [varchar](100) NOT NULL,
	[prodarea] [varchar](40) NOT NULL,
	[StorageEnv] [varchar](50) NOT NULL,
	[UploadStatus] [tinyint] NULL CONSTRAINT [DF_pbmGoodsInfo_UploadStatus]  DEFAULT ((0)),
	[isDelete] [tinyint] NOT NULL,
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_pbmGoodsBasicInfo] PRIMARY KEY CLUSTERED 
(
	[GoodId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmGoodsFullInfo]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmGoodsFullInfo](
	[GoodFullId] [bigint] NOT NULL,
	[GoodId] [bigint] NOT NULL,
	[lotno] [varchar](20) NOT NULL,
	[invalidate] [datetime] NOT NULL,
	[UploadStatus] [tinyint] NULL CONSTRAINT [DF_pbmGoodsFullInfo_UploadStatus]  DEFAULT ((0)),
	[isDelete] [tinyint] NULL,
	[oid] [bigint] NULL
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmMiCar]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmMiCar](
	[micarid] [bigint] NOT NULL,
	[micarname] [varchar](50) NOT NULL,
	[isdelete] [tinyint] NOT NULL,
	[uploadStatus] [tinyint] NULL,
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_pbmMiCar] PRIMARY KEY CLUSTERED 
(
	[micarid] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmMiRef]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmMiRef](
	[mirefid] [bigint] NOT NULL,
	[mirefname] [varchar](50) NOT NULL,
	[isDelete] [tinyint] NOT NULL,
	[uploadStatus] [tinyint] NULL,
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_pbmMiRef] PRIMARY KEY CLUSTERED 
(
	[mirefid] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmRefInOutTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmRefInOutTrack](
	[kdId] [bigint] NOT NULL,
	[orderId] [bigint] NOT NULL,
	[pid] [bigint] NOT NULL,
	[InTime] [datetime] NOT NULL,
	[OutTime] [datetime] NOT NULL,
	[demandTime] [datetime] NOT NULL,
	[inPdaId] [bigint] NOT NULL,
	[outPdaId] [bigint] NOT NULL,
	[ErpRefId] [varchar](50) NOT NULL,
	[ErpPosId] [varchar](50) NOT NULL,
	[subOrderMid] [bigint] NOT NULL,
	[inOutStatus] [tinyint] NOT NULL,
	[TotalNum] [int] NOT NULL,
	[UploadStatus] [tinyint] NULL CONSTRAINT [DF_pbmRefInOutTrack_UploadStatus]  DEFAULT ((0)),
	[oid] [bigint] NOT NULL,
	[isDelete] [tinyint] NULL,
 CONSTRAINT [PK_pbmRefInOutTrack] PRIMARY KEY CLUSTERED 
(
	[kdId] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmErpRefInOutTrack]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmErpRefInOutTrack](
	[kdid] [bigint] NOT NULL,
	[kid] [bigint] NOT NULL,
	[orderId] [bigint] NOT NULL,
	[inTime] [datetime] NOT NULL,
	[outTime] [datetime] NOT NULL,
	[goodFullId] [bigint] NOT NULL,
	[totalNum] [int] NOT NULL,
	[erpRefId] [bigint] NOT NULL,
	[erpPosId] [bigint] NOT NULL,
	[isPacketRec] [tinyint] NOT NULL,
	[inOutType] [tinyint] NOT NULL,
	[subOrderMid] [bigint] NOT NULL,
	[inOutStatus] [tinyint] NOT NULL,
	[isDelete] [tinyint] NOT NULL,
	[uploadStatus] [tinyint] NULL,
	[oid] [bigint] NOT NULL,
 CONSTRAINT [PK_pbmErpRefInOutTrack] PRIMARY KEY CLUSTERED 
(
	[kdid] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pbmmipacketType]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pbmmipacketType](
	[Pid2] [bigint] IDENTITY(1,1) NOT NULL,
	[PacketTypeName] [varbinary](50) NOT NULL,
	[Oid] [bigint] NOT NULL
) ON [PRIMARY]
END
