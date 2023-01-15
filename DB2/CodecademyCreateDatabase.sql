USE [master]
GO
/****** Object:  Database [Codecademy]    Script Date: 15-1-2023 13:35:16 ******/
CREATE DATABASE [Codecademy]
 CONTAINMENT = NONE
GO
ALTER DATABASE [Codecademy] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Codecademy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Codecademy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Codecademy] SET ARITHABORT OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Codecademy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Codecademy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Codecademy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Codecademy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Codecademy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Codecademy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Codecademy] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Codecademy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Codecademy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Codecademy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Codecademy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Codecademy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Codecademy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Codecademy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Codecademy] SET RECOVERY FULL 
GO
ALTER DATABASE [Codecademy] SET  MULTI_USER 
GO
ALTER DATABASE [Codecademy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Codecademy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Codecademy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Codecademy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Codecademy] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Codecademy] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Codecademy', N'ON'
GO
ALTER DATABASE [Codecademy] SET QUERY_STORE = OFF
GO
USE [Codecademy]
GO
/****** Object:  Table [dbo].[Aanbevolen]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Aanbevolen](
	[AanbevolenID] [int] NOT NULL,
	[AanbevolenBijId] [int] NOT NULL,
 CONSTRAINT [PK_Aanbevolen] PRIMARY KEY CLUSTERED 
(
	[AanbevolenID] ASC,
	[AanbevolenBijId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bekijkt]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bekijkt](
	[Datum] [date] NOT NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Voortgang] [int] NULL,
	[CursistID] [int] NOT NULL,
	[ContentItemID] [int] NOT NULL,
 CONSTRAINT [PK_Bekijkt_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Certificaat]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificaat](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Cijfer] [decimal](3, 1) NULL,
	[NaamMedewerker] [nvarchar](50) NULL,
	[CertificaatNummer] [int] NOT NULL,
	[InschrijvingID] [int] NOT NULL,
 CONSTRAINT [PK_Certificaat] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ContentItem]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContentItem](
	[ContentItemNummer] [int] NOT NULL,
	[PublicatieDatum] [date] NULL,
	[Status] [nvarchar](50) NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_ContentItem] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cursist]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cursist](
	[EmailAdres] [nvarchar](50) NOT NULL,
	[Naam] [nvarchar](50) NULL,
	[GeboorteDatum] [date] NULL,
	[Geslacht] [nvarchar](50) NULL,
	[Huisnummer] [nvarchar](50) NULL,
	[Postcode] [nchar](10) NULL,
	[Straat] [nvarchar](50) NULL,
	[Woonplaats] [nvarchar](50) NULL,
	[Land] [nvarchar](50) NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Cursist] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cursus]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cursus](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CursusNaam] [nvarchar](50) NOT NULL,
	[Onderwerp] [nvarchar](50) NULL,
	[IntroductieTekst] [nvarchar](200) NULL,
	[NiveauAanduiding] [nvarchar](50) NULL,
 CONSTRAINT [PK_Cursus] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inschrijving]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inschrijving](
	[CursusID] [int] NOT NULL,
	[CursistID] [int] NOT NULL,
	[Datum] [date] NOT NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Inschrijving_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[Titel] [nvarchar](50) NOT NULL,
	[Versie] [int] NOT NULL,
	[Beschrijving] [nvarchar](200) NULL,
	[NaamContactpersoon] [nvarchar](50) NULL,
	[EmailContactpersoon] [nvarchar](50) NULL,
	[Volgnummer] [int] NULL,
	[CursusID] [int] NULL,
	[ContentItemID] [int] NOT NULL,
 CONSTRAINT [PK_Module_1] PRIMARY KEY CLUSTERED 
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 15-1-2023 13:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[ContentItemID] [int] NOT NULL,
	[Titel] [nvarchar](50) NOT NULL,
	[Beschrijving] [nvarchar](200) NULL,
	[NaamSpreker] [nvarchar](50) NULL,
	[Organisatie] [nvarchar](50) NULL,
	[Tijdsduur] [time](7) NULL,
	[URL] [nvarchar](50) NULL,
 CONSTRAINT [PK_Webcast_1] PRIMARY KEY CLUSTERED 
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Index [AK_Bekijkt]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Bekijkt] ON [dbo].[Bekijkt]
(
	[Datum] ASC,
	[CursistID] ASC,
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [AK_Certificaat]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Certificaat] ON [dbo].[Certificaat]
(
	[InschrijvingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [AK_ContentItem]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_ContentItem] ON [dbo].[ContentItem]
(
	[ContentItemNummer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_Cursist]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Cursist] ON [dbo].[Cursist]
(
	[EmailAdres] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_CursusNaam]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_CursusNaam] ON [dbo].[Cursus]
(
	[CursusNaam] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [AK_Inschrijving]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Inschrijving] ON [dbo].[Inschrijving]
(
	[CursusID] ASC,
	[CursistID] ASC,
	[Datum] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_Module]    Script Date: 15-1-2023 13:35:16 ******/
CREATE UNIQUE NONCLUSTERED INDEX [AK_Module] ON [dbo].[Module]
(
	[Titel] ASC,
	[Versie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Aanbevolen]  WITH CHECK ADD  CONSTRAINT [FK_Aanbevolen_Cursus] FOREIGN KEY([AanbevolenID])
REFERENCES [dbo].[Cursus] ([ID])
GO
ALTER TABLE [dbo].[Aanbevolen] CHECK CONSTRAINT [FK_Aanbevolen_Cursus]
GO
ALTER TABLE [dbo].[Aanbevolen]  WITH CHECK ADD  CONSTRAINT [FK_Aanbevolen_Cursus1] FOREIGN KEY([AanbevolenBijId])
REFERENCES [dbo].[Cursus] ([ID])
GO
ALTER TABLE [dbo].[Aanbevolen] CHECK CONSTRAINT [FK_Aanbevolen_Cursus1]
GO
ALTER TABLE [dbo].[Bekijkt]  WITH CHECK ADD  CONSTRAINT [FK_Bekijkt_ContentItem] FOREIGN KEY([ContentItemID])
REFERENCES [dbo].[ContentItem] ([ID])
GO
ALTER TABLE [dbo].[Bekijkt] CHECK CONSTRAINT [FK_Bekijkt_ContentItem]
GO
ALTER TABLE [dbo].[Bekijkt]  WITH CHECK ADD  CONSTRAINT [FK_Bekijkt_Cursist] FOREIGN KEY([CursistID])
REFERENCES [dbo].[Cursist] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Bekijkt] CHECK CONSTRAINT [FK_Bekijkt_Cursist]
GO
ALTER TABLE [dbo].[Certificaat]  WITH CHECK ADD  CONSTRAINT [FK_Certificaat_Inschrijving] FOREIGN KEY([InschrijvingID])
REFERENCES [dbo].[Inschrijving] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Certificaat] CHECK CONSTRAINT [FK_Certificaat_Inschrijving]
GO
ALTER TABLE [dbo].[Inschrijving]  WITH CHECK ADD  CONSTRAINT [FK_Inschrijving_Cursist] FOREIGN KEY([CursistID])
REFERENCES [dbo].[Cursist] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Inschrijving] CHECK CONSTRAINT [FK_Inschrijving_Cursist]
GO
ALTER TABLE [dbo].[Inschrijving]  WITH CHECK ADD  CONSTRAINT [FK_Inschrijving_Cursus] FOREIGN KEY([CursusID])
REFERENCES [dbo].[Cursus] ([ID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Inschrijving] CHECK CONSTRAINT [FK_Inschrijving_Cursus]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_ContentItem] FOREIGN KEY([ContentItemID])
REFERENCES [dbo].[ContentItem] ([ID])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_ContentItem]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Cursus] FOREIGN KEY([CursusID])
REFERENCES [dbo].[Cursus] ([ID])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Cursus]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_ContentItem] FOREIGN KEY([ContentItemID])
REFERENCES [dbo].[ContentItem] ([ID])
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_ContentItem]
GO
USE [master]
GO
ALTER DATABASE [Codecademy] SET  READ_WRITE 
GO
