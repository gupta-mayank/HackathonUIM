USE [AIOPS_UIM]
GO

/****** Object:  Table [dbo].[prediction_tbl_3]    Script Date: 4/7/2018 6:13:27 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[prediction_tbl_4](
	[prediction_id] [int] NOT NULL,
	[prediction_title] [nvarchar](150) NOT NULL,
	[prediction_description] [nvarchar](520) NULL,
	[remediation_status] [nvarchar](50) NULL,
	[prediction_time] [numeric](18, 0) NOT NULL,
 CONSTRAINT [PK_prediction_tbl_4] PRIMARY KEY CLUSTERED 
(
	[prediction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO