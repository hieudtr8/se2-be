-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2023 at 05:29 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `se2`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_auth`
--

CREATE TABLE `admin_auth` (
  `id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
INSERT INTO `admin_auth` (`id`, `password`) VALUES
('admin', 'admin'),
-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_auth`
--

CREATE TABLE `customer_auth` (
  `id` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_id` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_auth`
--

INSERT INTO `customer_auth` (`id`, `email`, `password`, `profile_id`) VALUES
('2c71816b-30ab-4f7c-8e28-64db0741cc19', 'hieudtr8@gmail.com', 'MTIzNDU2ODk=', 'f77f8a24-1c1f-416d-8607-86ae34ed9c89'),

-- --------------------------------------------------------

--
-- Table structure for table `customer_profile`
--

CREATE TABLE `customer_profile` (
  `id` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_profile`
--

INSERT INTO `customer_profile` (`id`, `address`, `age`, `avatar`, `name`, `phone`) VALUES
('f77f8a24-1c1f-416d-8607-86ae34ed9c89', '', 0, '', '', ''),

-- --------------------------------------------------------

--
-- Table structure for table `customer_voucher`
--

CREATE TABLE `customer_voucher` (
  `code` varchar(100) NOT NULL,
  `customer_id` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` varchar(100) NOT NULL,
  `discount_amount` double NOT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `product_id` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `products` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `voucher_value` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `category_id` varchar(100) NOT NULL,
  `product_id` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `voucher`
--

CREATE TABLE `voucher` (
  `code` varchar(100) NOT NULL,
  `expired_at` datetime DEFAULT NULL,
  `minimum_applicable_price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `value` double NOT NULL,
  `visibility` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_auth`
--
ALTER TABLE `admin_auth`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_auth`
--
ALTER TABLE `customer_auth`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKe9cakac8coxnuupq62r7j59s0` (`email`),
  ADD KEY `FK2hh1xt1ug3pc6apl3kmoj6j3s` (`profile_id`);

--
-- Indexes for table `customer_profile`
--
ALTER TABLE `customer_profile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_voucher`
--
ALTER TABLE `customer_voucher`
  ADD PRIMARY KEY (`code`,`customer_id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`category_id`,`product_id`);

--
-- Indexes for table `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
