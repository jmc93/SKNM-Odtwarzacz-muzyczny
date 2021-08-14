-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Cze 2017, 01:24
-- Wersja serwera: 10.1.21-MariaDB
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `playlista2`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `artysta`
--

CREATE TABLE `artysta` (
  `Nazwa_Artysty` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `artysta`
--

INSERT INTO `artysta` (`Nazwa_Artysty`) VALUES
('Amaranthe'),
('Behemoth'),
('BoBo'),
('Dadad'),
('Epica'),
('Helmut'),
('John Williams'),
('Oonagh'),
('The Offspring'),
('Wwa');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `odtwarzacz_baza`
--

CREATE TABLE `odtwarzacz_baza` (
  `id_Na_Odtwarzaczu` int(11) NOT NULL,
  `Artysta` varchar(50) NOT NULL,
  `Utwor` varchar(100) NOT NULL,
  `Lokalizacja` varchar(200) NOT NULL,
  `Poczatek` varchar(10) NOT NULL,
  `Koniec` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Zrzut danych tabeli `odtwarzacz_baza`
--

INSERT INTO `odtwarzacz_baza` (`id_Na_Odtwarzaczu`, `Artysta`, `Utwor`, `Lokalizacja`, `Poczatek`, `Koniec`) VALUES
(5, 'Amaranthe', 'Maximize', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\Maximize.mp3', '10', '30');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `playlista`
--

CREATE TABLE `playlista` (
  `Id_Na_Playliscie` int(11) NOT NULL,
  `Nazwa_Artysty` varchar(50) NOT NULL,
  `Nazwa_Utworu` varchar(100) NOT NULL,
  `Lokalizacja_pliku` varchar(200) NOT NULL,
  `Dlugosc_Utworu` varchar(20) DEFAULT NULL,
  `Tempo` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `playlista`
--

INSERT INTO `playlista` (`Id_Na_Playliscie`, `Nazwa_Artysty`, `Nazwa_Utworu`, `Lokalizacja_pliku`, `Dlugosc_Utworu`, `Tempo`) VALUES
(1, 'Epica', 'sssss', 'C:\\Users\\Jacek Cierpka\\Music\\EPICA - The Essence Of Silence.mp3', '01:01:01', 'szybkie'),
(2, 'Amaranthe', 'Boomerang', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\Boomerang.mp3', '00:03:24', 'szybkie'),
(3, 'Amaranthe', 'That Song', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\That Song.mp3', '00:03:12', 'srednie'),
(4, 'Amaranthe', 'Maximize', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\Maximize.mp3', '00:03:12', 'szybkie'),
(5, 'Oonagh', 'Eccaia', 'C:\\\\Users\\\\Jacek Cierpka\\\\Music\\\\03  Eccaia - Von der Flut.mp3', '00:2:20', 'asas'),
(6, 'Oonagh', 'Eccaia', 'C:\\\\Users\\\\Jacek Cierpka\\\\Music\\\\03  Eccaia - Von der Flut.mp3', '00:20:20', 'asas'),
(9, 'Wwa', 'eerr', 'C:\\Users\\Jacek Cierpka\\Music\\03  Eccaia - Von der Flut.mp3', '00:03:10', 'srednie'),
(11, 'Amaranthe', 'Boomerang', 'C:\\Users\\Jacek Cierpka\\Music\\Van Canto - Speed of light.mp3', '00:01:11', 'szybkie'),
(12, 'Behemoth', 'Your Body', 'C:\\Users\\Jacek Cierpka\\Music\\Christina Aguilera - Your Body (Audio).mp3', '00:09:12', 'wolne'),
(13, 'BoBo', 'BBoo', 'C:\\Users\\Jacek Cierpka\\Music\\Two Steps From Hell - Infinite legends (Choir).mp3', '00:14:14', 'wolne'),
(14, 'John Williams', 'Main Title', 'E:\\Track01.cda', '00:03:01', 'srednie');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tempo`
--

CREATE TABLE `tempo` (
  `Tempo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `tempo`
--

INSERT INTO `tempo` (`Tempo`) VALUES
('asas'),
('sas'),
('srednie'),
('szybkie'),
('wolne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `utwor`
--

CREATE TABLE `utwor` (
  `Nazwa_Utworu` varchar(100) NOT NULL,
  `Dlugosc_Utworu` varchar(20) NOT NULL,
  `Lokalizacja_pliku` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `utwor`
--

INSERT INTO `utwor` (`Nazwa_Utworu`, `Dlugosc_Utworu`, `Lokalizacja_pliku`) VALUES
('wol', '1', 'C:\\Users\\Jacek Cierpka\\Music\\03  Eccaia - Von der Flut.mp3'),
('wwwww', '00:12:00', 'C:\\Users\\Jacek Cierpka\\Music\\05. Zack Hemsey - Redemption.mp3'),
('eerr', '00:12:00', 'C:\\Users\\Jacek Cierpka\\Music\\Amaranthe-A_Splinter_In_My_Soul_Bonus_Track(www.mp3vip.org).mp3'),
('Your Body', '00:09:12', 'C:\\Users\\Jacek Cierpka\\Music\\Christina Aguilera - Your Body (Audio).mp3'),
('sssss', '01:01:01', 'C:\\Users\\Jacek Cierpka\\Music\\EPICA - The Essence Of Silence.mp3'),
('da', '1', 'C:\\Users\\Jacek Cierpka\\Music\\goya - tylko mnie kochaj.mp3'),
('Hopsa', '00:01:11', 'C:\\Users\\Jacek Cierpka\\Music\\Let your heart decide.mp3'),
('BBoo', '00:14:14', 'C:\\Users\\Jacek Cierpka\\Music\\Two Steps From Hell - Infinite legends (Choir).mp3'),
('Hopsa', '00:01:11', 'C:\\Users\\Jacek Cierpka\\Music\\Van Canto - Speed of light.mp3'),
('Eccaia', '00:2:20', 'C:\\\\Users\\\\Jacek Cierpka\\\\Music\\\\03  Eccaia - Von der Flut.mp3'),
('da', '1', 'C:\\\\Users\\\\Jacek Cierpka\\\\Music\\\\[Mike Curb Congregation] - Burning Bridges(Kellys Heroes).mp3'),
('Main Title', '00:03:01', 'E:\\Track01.cda'),
('Boomerang', '00:03:22', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\Boomerang.mp3'),
('Maximize', '00:03:10', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\Maximize.mp3'),
('That Song', '00:03:12', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\That Song.mp3');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `artysta`
--
ALTER TABLE `artysta`
  ADD PRIMARY KEY (`Nazwa_Artysty`),
  ADD UNIQUE KEY `Nazwa_Artysty_3` (`Nazwa_Artysty`),
  ADD KEY `Nazwa_Artysty` (`Nazwa_Artysty`),
  ADD KEY `Nazwa_Artysty_2` (`Nazwa_Artysty`);

--
-- Indexes for table `odtwarzacz_baza`
--
ALTER TABLE `odtwarzacz_baza`
  ADD PRIMARY KEY (`id_Na_Odtwarzaczu`);

--
-- Indexes for table `playlista`
--
ALTER TABLE `playlista`
  ADD PRIMARY KEY (`Id_Na_Playliscie`),
  ADD KEY `Nazwa_Artysty` (`Nazwa_Artysty`),
  ADD KEY `Nazwa_Utworu` (`Nazwa_Utworu`),
  ADD KEY `Lokalizacja_pliku` (`Lokalizacja_pliku`),
  ADD KEY `Tempo` (`Tempo`),
  ADD KEY `Dlugosc` (`Dlugosc_Utworu`);

--
-- Indexes for table `tempo`
--
ALTER TABLE `tempo`
  ADD PRIMARY KEY (`Tempo`);

--
-- Indexes for table `utwor`
--
ALTER TABLE `utwor`
  ADD PRIMARY KEY (`Lokalizacja_pliku`),
  ADD UNIQUE KEY `Lokalizacja_pliku` (`Lokalizacja_pliku`),
  ADD KEY `Dlugosc_Utworu` (`Dlugosc_Utworu`) USING BTREE,
  ADD KEY `Nazwa_Utworu` (`Nazwa_Utworu`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `odtwarzacz_baza`
--
ALTER TABLE `odtwarzacz_baza`
  MODIFY `id_Na_Odtwarzaczu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `playlista`
--
ALTER TABLE `playlista`
  MODIFY `Id_Na_Playliscie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `playlista`
--
ALTER TABLE `playlista`
  ADD CONSTRAINT `playlista_ibfk_1` FOREIGN KEY (`Nazwa_Artysty`) REFERENCES `artysta` (`Nazwa_Artysty`),
  ADD CONSTRAINT `playlista_ibfk_2` FOREIGN KEY (`Lokalizacja_pliku`) REFERENCES `utwor` (`Lokalizacja_pliku`),
  ADD CONSTRAINT `playlista_ibfk_3` FOREIGN KEY (`Tempo`) REFERENCES `tempo` (`Tempo`),
  ADD CONSTRAINT `playlista_ibfk_5` FOREIGN KEY (`Nazwa_Utworu`) REFERENCES `utwor` (`Nazwa_Utworu`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
