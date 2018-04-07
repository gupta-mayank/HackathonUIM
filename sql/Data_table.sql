USE [AIOPS_UIM]
GO

/****** Object:  Table [dbo].[Data_table_2]    Script Date: 4/7/2018 6:17:33 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Data_table_2](
	[Thread_name] [nvarchar](50) NOT NULL,
	[recordsinserted] [int] NOT NULL,
	[timetakenforinsertion] [float] NOT NULL,
	[commit_date_time] [numeric](18, 0) NOT NULL,
	[rn_table_name] [nvarchar](50) NOT NULL
) ON [PRIMARY]

GO
