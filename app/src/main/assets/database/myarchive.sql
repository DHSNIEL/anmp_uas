-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2024 at 12:25 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myarchive`
--

-- --------------------------------------------------------

--
-- Table structure for table `planetdetails`
--

CREATE TABLE `planetdetails` (
  `id` int(11) NOT NULL,
  `detail` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `planet_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `planetdetails`
--

INSERT INTO `planetdetails` (`id`, `detail`, `planet_id`) VALUES
(1, 'Not content with the laws of the common world, the Genius Society\'s Herta set her sights on the unexplained existences of the galaxy, expecting those distant mysteries to satisfy her curiosity. To this end, Herta led the establishment of a museum-like starship and launched it towards the Star Rail, starting the research work to seal all strange existences among the stars. And the famous Herta Space Station was born.', 1),
(2, 'Nowadays, the space station possesses innumerable Curios and is a veritable space museum. A great number of researchers also live on the station. They continuously collect relics and Curios all over the cosmos, record their appearances and functions, establish systematic methods to observe and study them... ultimately finding even more confounding enigmas from their studies. That in fact, was Herta\'s initial goal.', 1),
(3, 'This large \"collection\" often attracts jealous eyes. Various forces watch from the shadows. Due to Herta\'s power, the space station had rarely experienced a real crisis, and the collection and investigation work continues to operate in an orderly manner.', 1),
(4, 'Historians often start the planetary history of Jarilo-VI from the War of the Gods thousands of years ago. The war between the eleven Perun States and the Veles Union Army scorched the land for years until an early spring completely changed the fortunes of war, and the Veles had a total victory. They praised the spring war god Jarilo for helping them eliminate their arch enemy, and praised the land with poems of the same name. The current name of the planet in the cosmos\'s records — Jarilo-VI — may be a mistranslation that contains a poetry stanza number.', 2),
(5, 'When the mythological war was over, Jarilo-VI welcomed a long period of peace and developed from a hunter-gatherer society to one conducting space explorations. Unfortunately, Jarilo-VI was a planet with few resources. Intensive development brought top-tier technological advances to Jarilo-VI, but also brought an end to their history. Exhausted resources, the arrival of a Stellaron, and the appearance of the Eternal Freeze... These various disasters completely annihilated Jarilo-VI\'s glorious but short-lived civilization.', 2),
(6, 'The survivors of the Eternal Freeze managed to stay alive in a corner of the planet. They forgot the old name of Jarilo and created Belobog, a city made to shelter against the Eternal Freeze, and continued their civilization. Due to its isolated and dangerous natural environment, Jarilo-VI lost contact with other worlds after the appearance of the Cancer of All Worlds.', 2),
(7, 'Xianzhou Luofu is one of the six flagships of the Xianzhou Alliance. It sails in the endless ocean of stars like a one-way arrow, shooting toward the destined enemy of the Lord of The Hunt.', 3),
(8, 'After the war with the Denizens of Abundance, the Luofu traveled between densely populated interstellar regions, trading and conducting cultural exchange between different worlds. They signed agreements with the Interastral Peace Corporation, and helped many worlds ravaged by the Denizens of Abundances to rebuild their ecosystem. People arrived at the Luofu to seek medical treatment, study, and trade, with throngs of visitors filling the docks.', 3),
(9, 'Though warfare has receded, the Luofu has not relaxed its watch against the Denizens of Abundance. The starskiffs of the Cloud Knights would head to planets that went out of contact to seek information, while the Xianzhou Ten-Lords Commission remains on guard for any Elixir Seeker pursuing everlasting life — The desire for immortality is the beginning of all tragedies. The Xianzhou will not allow any younger civilizations to walk its old path.', 3),
(10, 'The Planet of Festivities in the Asdana star system, also known as Land of the Dreams. A luxurious hotel positioned above the fathomless sky, Penacony is also a vacation spot where interstellar celebrities party the days away and the affluent from diverse words revel in lavish ventures. People flock here in search of dreams they\'ve longed for or have already buried, and through Dreampools in the hotel are transported to a realm of dreams where everything can come true.', 4),
(11, 'Rarely do guests pay attention to Penacony\'s heavy past. A millennium ago, it was a frontier prison established by the IPC. Exiled prisoners must survive an arduous journey across vast distances to reach this location, and are then forced to take up a duty: To mend the leak through which memoria seeps into the galaxy. As they labor while being exposed to memoria, the prisoners gradually discovered themselves entering a captivating shared dream world, where they obtained a single shared desire: freedom.', 4),
(12, 'After the eruption of the Cancer of All Worlds, the ownership of the Frontier Prison changed hands. Historical records related to this struggle are scarce and often questionable. However, some accounts clearly state that with the assistance of various factions - including the Mourning Actors, the Masked Fools, the Nameless, and the Omen Vanguards - the prisoners emerged as the new rulers of this nascent realm. Sounds of discord could then periodically be heard during the subsequent times when it became a land of exiles. It was only when The Family descended and took control of the Dreamscape that Penacony experienced unparalleled and explosive prosperity, transforming into a renowned cosmic resort', 4);

-- --------------------------------------------------------

--
-- Table structure for table `planets`
--

CREATE TABLE `planets` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `summary` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `img_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `planets`
--

INSERT INTO `planets` (`id`, `name`, `summary`, `img_url`) VALUES
(1, 'Herta Space Station', 'Herta Space Station is a space station founded by Herta, member #83 of the Genius Society. Researchers from different planets comes to work here as a researcher and most of them are faithful followers of Herta. Qualifying for a researcher role in the Herta Space Station is a hard process and is a cherished goal of the scientific community.', 'https://static.wikia.nocookie.net/houkai-star-rail/images/4/4e/World_Herta_Space_Station.png/revision/latest?cb=20230723033604'),
(2, 'Jarilo-VI', 'Jarilo-VI is a planet that is frosted over due to the Eternal Freeze as a product of the Stellaron\'s effect on the planet. Its name may possibly be a mistranslation that has a poetry stanza number. Belobog currently stands as its only bastion where humanity thrives amidst the harsh climate.', 'https://static.wikia.nocookie.net/honkai-star-rail/images/4/4b/World_Jarilo-VI.png/revision/latest/scale-to-width-down/1000?cb=20220505114451&path-prefix=th'),
(3, 'Xianzhou Luofu', 'The Xianzhou Luofu is one of the six Flagships owned by the Hexafleet of the Xianzhou Alliance. It sails like a one-way arrow through the galaxy, with the goal of eradicating the Abominations of Abundance.', 'https://static.wikia.nocookie.net/houkai-star-rail/images/a/a3/World_The_Xianzhou_Luofu.png/revision/latest?cb=20230514190651'),
(4, 'Penacony', 'Penacony, also known as the \"Planet of Festivities\", is a planet currently administered by The Family in the Asdana star system.', 'https://static.wikia.nocookie.net/houkai-star-rail/images/e/e0/World_Penacony.png/revision/latest/scale-to-width-down/1000?cb=20240210042535');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `img_url` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `firstname`, `lastname`, `email`, `password`, `img_url`) VALUES
(1, 'ArchiveAdmin', 'Dan', 'Heng', 'danheng@gmail.com', 'D4nH3ng', 'https://rerollcdn.com/STARRAIL/Characters/Full/1002.png'),
(2, 'weltyang', 'Welt', 'Yang', 'welt.yang@gmail.com', 'welt', 'https://rerollcdn.com/STARRAIL/Characters/Full/1004.png'),
(3, 'CoffeeLovers', 'Himeko', 'Murata', 'himeko@gmail.com', 'h1meko', 'https://rerollcdn.com/STARRAIL/Characters/Full/1003.png'),
(4, 'ClickCheese', 'March', '7th', 'march7th@gmail.com', '7thMarch', 'https://rerollcdn.com/STARRAIL/Characters/Full/1001.png'),
(7, 'General', 'Jing', 'Yuan', 'mimi@gmail.com', 'JYuan', 'https://rerollcdn.com/STARRAIL/Characters/Full/1204.png'),
(8, 'NotaFraud', 'Sampo', 'Koski', 'sampo@gmail.com', 'K0ski', 'https://rerollcdn.com/STARRAIL/Characters/Full/1108.png'),
(9, 'Bellboy', 'Misha', 'Reverie', 'misha@gmail.com', 'M1sh4', 'https://rerollcdn.com/STARRAIL/Characters/Full/3015.png'),
(10, 'HackerGirl', 'Silver', 'Wolf', 'silverwolf@gmail.com', '51Lv312', 'https://rerollcdn.com/STARRAIL/Characters/Full/1006.png'),
(11, 'new', 'newn', 'newn', 'new@gmail.', 'q', 'wd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `planetdetails`
--
ALTER TABLE `planetdetails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `planets`
--
ALTER TABLE `planets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `planetdetails`
--
ALTER TABLE `planetdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `planets`
--
ALTER TABLE `planets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
