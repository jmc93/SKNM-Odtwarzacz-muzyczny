-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 21 Kwi 2017, 21:48
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
('Oonagh'),
('The Offspring');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `playlista`
--

CREATE TABLE `playlista` (
  `Id_Na_Playliscie` int(11) NOT NULL,
  `Nazwa_Artysty` varchar(50) NOT NULL,
  `Nazwa_Utworu` varchar(100) NOT NULL,
  `Lokalizacja_pliku` varchar(200) NOT NULL,
  `Dlugosc` time NOT NULL,
  `Tempo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `playlista`
--

INSERT INTO `playlista` (`Id_Na_Playliscie`, `Nazwa_Artysty`, `Nazwa_Utworu`, `Lokalizacja_pliku`, `Dlugosc`, `Tempo`) VALUES
(1, 'Oonagh', 'Eccaia- Von der Flut', 'C:\\Users\\Jacek Cierpka\\Music\\03  Eccaia - Von der Flut.mp3', '00:03:30', 'Srednie'),
(2, 'Amaranthe', 'Maximize', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\01 Maximize.mp3', '00:03:10', 'Szybkie');

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
('Srednie'),
('Szybkie'),
('Wolne');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `utwor`
--

CREATE TABLE `utwor` (
  `Nazwa_Utworu` varchar(100) NOT NULL,
  `Dlugosc_Utworu` time NOT NULL,
  `Lokalizacja_pliku` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `utwor`
--

INSERT INTO `utwor` (`Nazwa_Utworu`, `Dlugosc_Utworu`, `Lokalizacja_pliku`) VALUES
('Eccaia- Von der Flut', '00:03:30', 'C:\\Users\\Jacek Cierpka\\Music\\03  Eccaia - Von der Flut.mp3'),
('Maximize', '00:03:10', 'H:\\Downloads\\Amaranthe - Maximalism (2016)\\01 Maximize.mp3');

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
-- Indexes for table `playlista`
--
ALTER TABLE `playlista`
  ADD PRIMARY KEY (`Id_Na_Playliscie`),
  ADD KEY `Nazwa_Artysty` (`Nazwa_Artysty`),
  ADD KEY `Nazwa_Utworu` (`Nazwa_Utworu`),
  ADD KEY `Lokalizacja_pliku` (`Lokalizacja_pliku`),
  ADD KEY `Dlugosc` (`Dlugosc`),
  ADD KEY `Tempo` (`Tempo`);

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
  ADD UNIQUE KEY `Dlugosc_Utworu` (`Dlugosc_Utworu`),
  ADD UNIQUE KEY `Nazwa_Utworu` (`Nazwa_Utworu`),
  ADD UNIQUE KEY `Lokalizacja_pliku` (`Lokalizacja_pliku`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `playlista`
--
ALTER TABLE `playlista`
  MODIFY `Id_Na_Playliscie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
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
  ADD CONSTRAINT `playlista_ibfk_4` FOREIGN KEY (`Dlugosc`) REFERENCES `utwor` (`Dlugosc_Utworu`),
  ADD CONSTRAINT `playlista_ibfk_5` FOREIGN KEY (`Nazwa_Utworu`) REFERENCES `utwor` (`Nazwa_Utworu`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
