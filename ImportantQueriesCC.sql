1.  
SELECT
	1.00 * (SELECT COUNT(*)
	FROM Cursist JOIN Inschrijving ON Cursist.ID = Inschrijving.CursistID
	JOIN Certificaat ON Inschrijving.ID = Certificaat.InschrijvingID
	WHERE Geslacht = '?')
	/
	(SELECT COUNT(*)
	FROM Cursist JOIN Inschrijving ON Cursist.ID = Inschrijving.CursistID
	WHERE Geslacht = '?')
	* 100 AS Percentage
--
2.
SELECT AVG(Bekijkt.Voortgang) as GemVoortgang
FROM Cursus JOIN Module ON Module.CursusID = Cursus.ID
JOIN Bekijkt ON Bekijkt.ContentItemID = Module.ContentItemID
WHERE Cursus.ID=?
--
3.
SELECT CursusNaam, Module.ContentItemID, Module.Titel, Module.Versie, Voortgang
FROM Cursist JOIN Bekijkt ON Cursist.ID = Bekijkt.CursistID
JOIN Module ON Bekijkt.ContentItemID = Module.ContentItemID
JOIN Cursus ON Module.CursusID = Cursus.ID
WHERE Module.CursusID = ? AND Cursist.ID = ?;
--
4.
SELECT Cursus.ID, Cursus.CursusNaam, Cursus.IntroductieTekst, Cursus.NiveauAanduiding, Cursus.Onderwerp
FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID
JOIN Cursus on Inschrijving.CursusID = Cursus.ID
WHERE CursistID = ?;
--
5.
SELECT TOP 3 Webcast.Beschrijving, Webcast.Titel, Webcast.ContentItemID, COUNT(*) as AmtViewed
FROM Bekijkt JOIN Webcast ON Bekijkt.ContentItemID = Webcast.ContentItemID
GROUP BY Webcast.ContentItemID, Webcast.Beschrijving, Webcast.Titel;
--
6.
SELECT TOP 3 Cursus.ID, Cursus.CursusNaam, COUNT(*) as AmtCert
FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID
JOIN Cursus ON Cursus.ID = Inschrijving.CursusID
GROUP BY Cursus.ID, Cursus.CursusNaam;
--
7.
SELECT *
FROM Aanbevolen
WHERE AanbevolenBijId = ?;
--
8.
SELECT Cursus.ID AS CursusID, COUNT(*) AS AmtCert
FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID
JOIN Cursus on Inschrijving.CursusID = Cursus.ID
WHERE Cursus.ID = ?
GROUP BY Cursus.ID;
