-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:7733
-- Generation Time: Jan 01, 2021 at 10:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `admin_licence`
--

-- --------------------------------------------------------

--
-- Table structure for table `licence`
--

CREATE TABLE `licence` (
  `id_licence` int(11) NOT NULL,
  `client_code` varchar(100) NOT NULL,
  `licence` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `licence`
--

INSERT INTO `licence` (`id_licence`, `client_code`, `licence`) VALUES
(13, 'cod_cliente_UNKNOWN', 'dkLR/FSCwEtgbn4bbfmWllHZyfPe0SpLzTn0cyqfZiNVUQbuitXIPgHSQVxr7v+dXJCHJCpTeN9CUoMT5kJ2cU873xZXRd4hctUd0QFIsKy8A7TV5t0FQCni1R8bHMxAOKEt8OjNSf6o46CfCFuDaUFhTR/abnOMvAHOIIWLE/v3vH8JR1pGZ4SnVqcJnH38212ogzurV8/EyGuHTGjVC46msmMYQG+HrC3zB3N5E3o=');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `licence`
--
ALTER TABLE `licence`
  ADD PRIMARY KEY (`id_licence`),
  ADD UNIQUE KEY `client_code` (`client_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `licence`
--
ALTER TABLE `licence`
  MODIFY `id_licence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
