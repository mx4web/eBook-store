-- create ebookstore database
DROP DATABASE IF EXISTS ebookstore;
CREATE DATABASE ebookstore;

Use ebookstore;

-- create user and grant permission
GRANT all ON ebookstore.* TO 'fish'@"localhost" IDENTIFIED BY "fish";

-- create clients table
DROP TABLE IF EXISTS clients;

CREATE TABLE `clients` (
  `ClientID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `CompanyName` varchar(255) DEFAULT NULL,
  `Address1` varchar(255) DEFAULT NULL,
  `Address2` varchar(255) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `Province` varchar(45) DEFAULT NULL,
  `Country` varchar(45) DEFAULT NULL,
  `PostalCode` varchar(45) DEFAULT NULL,
  `HomePhone` varchar(45) DEFAULT NULL,
  `CellPhone` varchar(45) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `PrivilegeID` int(11) NOT NULL COMMENT '0, administrator\n1, customer',
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `ebookstore`.`clients` (`Title`, `LastName`, `FirstName`, `CompanyName`, `Address1`, `City`, `Province`, `Country`, `PostalCode`, `HomePhone`, `CellPhone`, `Email`, `password`,  `Username`,  `PrivilegeID`) VALUES ('Sir', 'Manager', 'Manager', 'Concordia', '88 St Catherine', 'Montreal', 'QC', 'Canada', 'H1H2A2', '514-111-2222', '514-111-2222', 'manager@cejv539.com', 'concordia', 'Manager', 0);
INSERT INTO `ebookstore`.`clients` (`Title`, `LastName`, `FirstName`, `CompanyName`, `Address1`, `City`, `Province`, `Country`, `PostalCode`, `HomePhone`, `CellPhone`, `Email`, `Password`,  `Username`,  `PrivilegeID`) VALUES ('Mr', 'Tom', 'Tomee', 'hybris', '100 Sherbrook', 'Montreal', 'QC', 'Canada', 'H5H9A9', '514-888-8888', '514-999-9999', 'tomee@cejv539.com', 'concordia', 'tomee', 1);
INSERT INTO `ebookstore`.`clients` (`Title`, `LastName`, `FirstName`, `CompanyName`, `Address1`, `City`, `Province`, `Country`, `PostalCode`, `HomePhone`, `CellPhone`, `Email`, `password`,  `Username`,  `PrivilegeID`) VALUES ('Sir', 'Consumer', 'Consumer', 'Concordia', '99 Maisonneuve', 'Montreal', 'QC', 'Canada', 'H2H1H1', '514-555-6666', '514-555-6666', 'consumer@cejv539.com', 'concordia', 'Consumer', 1);


-- create genre table
DROP TABLE IF EXISTS genre;
CREATE TABLE `genre` (
  `GenreID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`GenreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `ebookstore`.`genre` (Name) VALUES('Science');
INSERT INTO `ebookstore`.`genre` (Name) VALUES('Cooking');
INSERT INTO `ebookstore`.`genre` (Name) VALUES('Computer');
INSERT INTO `ebookstore`.`genre` (Name) VALUES('Outdoor');

-- create inventory table
DROP TABLE IF EXISTS inventory;

CREATE TABLE `inventory` (
  `BookID` int(11) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(45) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Author` varchar(255) DEFAULT NULL,
  `Publisher` varchar(255) DEFAULT NULL,
  `NumberOfPages` int(11) DEFAULT NULL,
  `GenreID` int(11) NOT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `WholesalePrice` decimal(13,2) DEFAULT NULL,
  `ListPrice` decimal(13,2) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT NULL,
  `Status` tinyint(4) DEFAULT NULL COMMENT '0, pending\n1, approved',
  `Description` text DEFAULT NULL,
  `SoldQty` int(11) DEFAULT 0,
  PRIMARY KEY (`BookID`),
  KEY `inventory.genreid_idx` (`GenreID`),
  CONSTRAINT `inventory.genreid` FOREIGN KEY (`GenreID`) REFERENCES `genre` (`GenreID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0471503819', 'Janice VanCleave\'s Biology For Every Kid: 101 Easy Experiments That Really Work', 'Janice VanCleave', 'Wiley', '240', '1', 'JaniceVanCleavesBiologyForEveryKid101EasyExperimentsThatReallyWork', '6.89', '15.99', '2014/3/1', '1', 'What\'s the effect of osmosis on a raisin?

Now you can discover answers to these and other fascinating questions about biologythe study of living organisms. In Biology for Every Kid, you\'ll learn how to talk with fireflies, watch bacteria wage war in a glass of milk, discover how to tell the temperature by counting cricket chirps, and find out how an apple and an onion can taste the same.

Each of the 101 experiments is broken down into its purpose, a list of materials, step-by-step instructions, expected results, and an easy to understand explanation. Every activity has been pretested and can be performed safely and inexpensively in the classroom or at home.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0691102953', 'Wildlife of the Galápagos', 'Julian Fitter', 'Princeton University Press', '256', '1', 'WildlifeoftheGalapagos', '6.99', '15.12', '2014/1/26', '1', 'The Galápagos is a truly special place. Unlike the rest of the world''s archipelagoes, it still has 95 percent of its prehuman quota of species. Wildlife of the Galápagos is the most superbly illustrated and comprehensive identification guide ever to the natural splendor of these incomparable islands--islands today threatened by alien species and diseases that have diminished but not destroyed what so enchanted Darwin on his arrival there in 1835. Covering over 200 commonly seen birds, mammals, reptiles, invertebrates, and plants, it reveals the archipelago''s striking beauty through more than 400 color photographs, maps, and drawings and well-written, informative text.

While the Galápagos Giant Tortoise, the Galápagos Sea Lion, and the Flightless Cormorant are recognized the world over, these thirty-three islands--in the Pacific over 600 miles from mainland Ecuador--are home to many more unique but less famous species. Here, reptiles well outnumber mammals, for they were much better at drifting far from a continent the archipelago was never connected with; the largest native land mammals are rice rats. The islands'' sixty resident bird species include the only penguin to breed entirely in the tropics and to inhabit the Northern Hemisphere.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1038553082', 'The Future of the Mind: The Scientific Quest to Understand, Enhance, and Empower the Mind', 'Michio Kaku', 'Doubleday', '400', '1', 'TheFutureoftheMind', '6.79', '20.99', '2014/1/27', '1', 'The New York Times best-selling author of Physics of the Impossible, Physics of the Future and Hyperspace tackles the most fascinating and complex object in the known universe: the human brain. 
        
For the first time in history, the secrets of the living brain are being revealed by a battery of high tech brain scans devised by physicists. Now what was once solely the province of science fiction has become a startling reality. Recording memories, telepathy, videotaping our dreams, mind control, avatars, and telekinesis are not only possible; they already exist. ');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0198390025', 'IB Chemistry: Study Guide', 'Geoffrey Neuss', 'Oxford University Press', '192', '1', 'IBChemistryStudyGuide', '7.89', '19.99', '2014/1/28', '1', 'Build unrivalled assessment potential. Fully comprehensive coverage of the 2007 syllabus at SL and HL, this user-friendly guide effectively reinforces all the key concepts and supports the highest achievement in assessment. With in-built support for the internal assessment, it will build confident and cement understanding.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0963277642', 'Keepers Of The Garden', 'Dolores Cannon', 'Ozark Mountain Publishing, Inc.', '288', '1', 'KeepersOfTheGarden', '7.99', '13', '2014/1/29', '1', 'A young man wanting to explore past-life regression discovers this is his first lifetime on Earth. All his other existences were on alien worlds and in other dimensions.

As this unique case is explored further, he finds out that his association with extraterrestrials did not cease with these other lives. The interaction with UFOs and aliens has continued during all his present life, although the information was protectively hidden by his subconscious.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0393240053', 'Charcuterie', 'Michael Ruhlman', 'WW Norton', '320', '2', 'Charcuterie', '8.99', '23.2', '2014/3/2', '1', 'Charcuterie exploded onto the scene in 2005 and encouraged an army of home cooks and professional chefs to start curing their own foods. This love song to animal fat and salt has blossomed into a bona fide culinary movement, throughout America and beyond, of curing meats and making sausage, pates, and confits. Charcuterie: Revised and Updated will remain the ultimate and authoritative guide to that movement, spreading the revival of this ancient culinary craft. Early in his career, food writer Michael Ruhlman had his first taste of duck confit. The experience "became a fascination that transformed into a quest" to understand the larger world of food preservation, called charcuterie, once a critical factor in human survival. He wondered why its methods and preparations, which used to keep communities alive and allowed for long-distance exploration, had been almost forgotten. Along the way he met Brian Polcyn, who had been surrounded with traditional and modern charcuterie since childhood.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0143187228', 'The Oh She Glows Cookbook: Vegan Recipes to Glow from the Inside Out', 'Angela Liddon', 'Penguin Canada', '336', '2', 'TheOhSheGlowsCookbook', '9.99', '14.5', '2014/1/31', '1','After a decade of struggling with an eating disorder and subsisting on diet, low-calorie processed foods, Angela Liddon vowed to get healthy once and for all. Done with feeling sick and tired, she threw out her fat-free butter spray and frozen dinners. Instead, Angela embraced whole foods that made her glow from the inside out. But first, she had to learn to cook—and eat—right.
Five years ago, Angela started a blog, ohsheglows.com, to spread the word about her journey to health and the powerful transformation that food can make in our lives. Almost overnight, her energy and authenticity attracted readers eager to create their own positive life changes. Today, Oh She Glows attracts millions of visitors every month, making it one of the most popular vegan recipe blogs on the Internet.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1936608138', 'The 21-Day Sugar Detox Cookbook: Over 100 Recipes for any Program Level', 'Diane Sanfilippo BS NC', 'Victory Belt Publishing', '240', '2', 'The21DaySugarDetoxCookbook', '5.99', '25.01', '2014/2/1', '1','The 21-Day Sugar Detox Cookbook, a companion to The 21-Day Sugar Detox program guidebook, bursts with more than a hundred grain-, gluten-, legume-, dairy-, and sugar-free recipes to keep you inspired as you blow your cravings for sugar and carbs to smithereens.

Taking on a detox plan can seem daunting, but these sumptuous recipes and life-altering eating concepts will make you wish you''d started sooner. Your 21 days will be over before you know it, your carb and sugar cravings a distant memory.

With palate-pleasing, and soul-satisfying, recipes for breakfasts, lunches, dinners, snacks, and even some "sweet" treats, The 21-Day Sugar Detox Cookbook is your guarantee for delicious detox success!');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0143186906', 'Grain Power: Over 100 Delicious Gluten-Free Ancient Grain and Superblend Recipes', 'Patricia Green', 'Penguin Canada', '240', '2', 'GrainPower', '6.98', '20.06', '2014/2/2', '1','Grain Power makes it simple to include a variety of delicious gluten-free ancient grains in your everyday meals. Ancient grains are great tasting and not only ideal for people with food allergies, gluten intolerance and health issues, but also those looking for delicious, nutrient-rich grains for a healthy lifestyle.

Packed with lots of variety and unique, natural flavors, recipes feature the most popular and versatile gluten-free ancient grains available today. It’s easy to super-charge all your meals with these health-boosting, nutrient-dense superfoods');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0143187074', 'Thrive Energy Cookbook: 150 Functional, Plant-Based Whole Food Recipes', 'Brendan Brazier', 'Penguin Canada', '320', '2', 'ThriveEnergyCookbook', '6.29', '20.06', '2014/2/3', '1','Brendan Brazier, author of the international bestseller The Thrive Diet, changed the way millions of people eat.

Packed with 150 plant-based, nutrient-dense, whole food recipes developed within the Thrive nutritional philosophy, the Thrive Energy Cookbook brings concepts that started the functional, plant-based nutrition revolution to life. Recipes are all allergen-free (or with gluten-free options) to eliminate wheat, yeast, gluten, soy, refined sugar, and dairy from your diet.

Easy-to-make and performance-enhancing, these chef-created recipes merge purpose-driven functionality with mouth-watering appeal. From the alkaline-forming, plant protein–packed Vanilla-Almond-Mocha Motivator Smoothie, Roasted Red Pepper and Sweet Potato Soup, Thai Green Curry Bowl, and desserts such as Raspberry Chocolate Pomegranate Tart, the Thrive Energy Cookbook will have you quickly preparing nutrient-packed and delicious dishes.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1430247649', 'Oracle Certified Professional Java SE 7 Programmer Exams 1Z0-804 and 1Z0-805: A Comprehensive Ocpjp 7 Certification Guid: A Comprehensive Ocpjp 7 Certification Guide', 'S G Ganesh, Tushar Sharma', 'Springer/Sci-Tech/Trade', '656', '3', 'OracleCertifiedProfessional', '10.99', '45.06', '2014/3/3', '1','Oracle Certified Professional Java SE 7 Programmer Exams 1Z0-804 and 1Z0-805 is a concise, comprehensive, step-by-step, and one-stop guide for the Oracle Certified Professional Java SE 7 Programmer Exam. The first two chapters set the stage for exam preparation and let the reader get started quickly. The first chapter answers frequently asked questions about the OCPJP exam.   This book assumes that the reader is already familiar with Java fundamentals which is in line with the prerequisite of having a OCAJP certification.   The book sports considerable supportive material to help the reader in effective exam preparation in the form of appendices:   2 mock tests to give the reader a sense of a real-exam. An instant refresher summarizing the most important concepts with tips on answering questions to revise just before the exam. This book will be a delectable read for any OCPJP aspirant because of its simple language, example driven approach, and easy-to-read style. Further, given its 100% focus on the exam and helpful supportive material, this book is clearly an attractive buy to OCPJP aspirants worldwide. What you''ll learn In-depth coverage of all 13 exam topics for the certification. The book covers programming concepts succinctly with numerous illustrative programming and real-world examples. These examples will help the reader quickly internalize the discussed concepts.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0071772006', 'OCA/OCP Java SE 7 Programmer I & II Study Guide (Exams 1Z0-803 & 1Z0-804)', 'Kathy Sierra and Bert Bates', 'McGraw-Hill Osborne Media', '888', '3', 'OCAOCPJavaSE7Programmer', '11.99', '34.45', '2014/2/5', '1','From Oracle Press—the definitive, bestselling guide to the #1 certification for Java programmers, written and revised by the co-developers of the original SCJP exam

OCP Java SE 7 Programmer Study Guide offers complete coverage of the latest Java Standard Edition programmer exam release, which brings the popular SCJP exam under the Oracle Certified Professional certification program and covers Java Platform, Standard Edition 7. This complete study guide provides in-depth, up-to-date coverage of all the exam objectives, and goes a step beyond to cover the Java Developer exam (now an Oracle Certified Expert level credential).

This book provides an integrated study system based on proven pedagogy--step-by-step exercises, special Exam Watch, Inside-the-Exam, and On-the-Job notes, and chapter self tests help reinforce and teach practical skills while preparing you for the exam. The CD-ROM includes MasterExam practice exam software featuring more than 100 questions that appear only on the CD, and a searchable e-book.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0789751690', 'My MacBook (covers OS X Mavericks on MacBook, MacBook Pro, and MacBook Air) (4th Edition)', 'John Ray', 'Que Publishing', '528', '3', 'MyMacBook', '8.99', '16.29', '2014/2/6', '1','Covers MacBook, MacBook Pro, and MacBook Air

Step-by-step instructions with callouts to MacBook photos that show you exactly what to do.
Help when you run into hardware or operating system problems or limitations.
Tips and Notes to help you get the most from your MacBook.

Full-color, step-by-step tasks walk you through getting and keeping
your MacBook working just the way you want.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('0596007124', 'Head First Design Patterns', 'Eric Freeman, Elisabeth Robson, Bert Bates and Kathy Sierra', 'O''Reilly Media', '678', '3', 'HeadFirstDesignPatterns', '9.99', '32.92', '2014/2/7', '1','At any given moment, somewhere in the world someone struggles with the same software design problems you have. You know you don''t want to reinvent the wheel (or worse, a flat tire), so you look to Design Patterns--the lessons learned by those who''ve faced the same problems. With Design Patterns, you get to take advantage of the best practices and experience of others, so that you can spend your time on...something else. Something more challenging. Something more complex. Something more fun.

You want to learn about the patterns that matter--why to use them, when to use them, how to use them (and when NOT to use them). But you don''t just want to see how patterns look in a book, you want to know how they look "in the wild". In their native environment. In other words, in real world applications. You also want to learn how patterns are used in the Java API, and how to exploit Java''s built-in pattern support in your own code.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1118063333 ', 'Operating System Concepts', 'Abraham Silberschatz, Peter B. Galvin and Greg Gagne', 'Wiley', '944', '3', 'OperatingSystemConcepts', '11.99', '130.46', '2014/2/8', '1','Operating System Concepts, now in its ninth edition, continues to provide a solid theoretical foundation for understanding operating systems. The ninth edition has been thoroughly updated to include contemporary examples of how operating systems function. The text includes content to bridge the gap between concepts and actual implementations. End-of-chapter problems, exercises, review questions, and programming exercises help to further reinforce important concepts.  A new Virtual Machine provides interactive exercises to help engage students with the material.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1616284870 ', 'The Total Fishing Manual (Field & Stream): 317 Essential Fishing Skills', 'Joe Cermele', 'Weldon Owen', '256', '4', 'TheTotalFishingManual', '12.99', '18.8', '2014/3/4', '1','GEAR UP How to pick the best lures, baits, flies, and tackle for every situation and every style of water you plan to fish. Customize your rod and reel to suit your every need. Learn how to customize your gear, get the most out of your boat, and more.

HIT THE WATER From small streams to major rivers, ponds to big lakes, and bays to the open ocean, hundreds of field-tested strategies will help you catch more fish with or without a boat.

FIND THE FISHProfessional fishing guides from across the country tell you how and where to find lunker bass, trophy walleyes, huge trout, and much more right in your home waters.

SET THE HOOK Whether you''re bobber fishing for bluegills with the kids or heading out after the muskie of a lifetime, the techniques and tactics in this book will make your trip a success.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1571885153 ', 'Float-Fishing for Salmon & Steelhead', 'Terry J. Wiest', 'Weldon Owen', '256', '4', 'FloatFishingforSalmonSteelhead', '10.89', '17.69', '2014/2/10', '1','This title has not yet been released.
You may pre-order it now and we will deliver it to you when it arrives.');

INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1592288189 ', 'The Orvis Fly-Fishing Guide, Completely Revised and Updated with Over 400 New Color Photos and Illustrations', 'Tom Rosenbauer', 'Lyons Press', '288', '4', 'TheOrvisFlyFishingGuide', '12.99', '17.25', '2014/2/11', '1','Now for the first time in full color, The Orvis Fly-Fishing Guide appears in a completely revised and updated edition that solidifies its place as the flagship title of the Orvis books. A best-selling, fully illustrated, and comprehensive book, this large-format volume has been required reading for every angler for the past two decades.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1896980724 ', 'Ice Fishing: The Ultimate Guide', 'Tim Allard', 'Lyons Press', '216', '4', 'IceFishingTheUltimateGuide', '10.99', '18.8', '2014/2/12', '1','Whether you''re a beginner or an expert, a lone wolf angler or a parent with fish-fanatic youngsters, fishing on ice has lots to offer everyone.  But fishing on ice isn''t without it''s challenges, and that''s where this book comes in.
Ice Fishing: The Ultimate Guide covers everything you need to know to make your hard water adventures as comfortable, safe, enjoyable and productive as possible. The first part of the book looks at the equipment that''s involved with ice fishing. The second part of the book takes an in depth look at winter''s best sport fish, including walleye, perch, crappie, pike, trout, whitefish, sunfish, catfish, bass and more.  For each species, you''ll find detailed information about them, where you can expect to find them, and the different strategies for catching them. You''ll learn about: Staying warm and safe on the ice Rod, reel and line selection Lure selection and techniques Specialty gear selection Ice fishing strategies Where to find the fish Jigging and set line secrets Includes tips from many of North America''s finest pro ice anglers and guides.');
	
INSERT INTO `ebookstore`.`inventory` (`ISBN`, `Title`, `Author`, `Publisher`, `NumberOfPages`, `GenreID`, `Image`, `WholesalePrice`, `ListPrice`, `CreatedDate`, `Status`, `Description`) 
	VALUES ('1896980724 ', 'A Guide to Catching Trout - Grandfather''s Secrets for Reeling in Some Big Fish! (Sport Fishing, Fishing, Trout Fishing, Lake Fishing, Trout Fishing Books, ... in America, Catching Fish, Trout, Fish)', 'David Wright', 'Amazon', '24', '4', 'AGuidetoCatchingTrout', '1.99', '5.8', '2014/2/13', '1','This book contains proven techniques and tricks to catching some world class trout.
Today only, get this Amazing Amazon book for just $2.99. Regularly priced at $4.99. Read on your PC, Mac, Smart Phone, Tablet or Kindle Device.

I can still remember those trips that my Grandfather and I used to take. We knew the right spot, we had the right tackle, the right equipment, we knew the right strategies, and best of all we caught some seriously impressive trout!');

-- create invoice table
DROP TABLE IF EXISTS invoice;
CREATE TABLE `invoice` (
  `InvoiceID` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceDate` timestamp NULL DEFAULT NULL,
  `ClientID` int(11) DEFAULT NULL,
  `SubTotal` decimal(13,2) DEFAULT NULL,
  `GST` decimal(13,2) DEFAULT NULL,
  `Discount` decimal(13,2) DEFAULT NULL,
  `InvoiceTotal` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`InvoiceID`),
  KEY `invoice.clientid_idx` (`ClientID`),
  CONSTRAINT `invoice.clientid` FOREIGN KEY (`ClientID`) REFERENCES `clients` (`ClientID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- create invoicedetails table
DROP TABLE IF EXISTS invoicedetails;
CREATE TABLE `invoicedetails` (
  `InvoiceDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  `Price` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`InvoiceDetailID`),
  KEY `invoicedetails.invoiceid_idx` (`InvoiceID`),
  KEY `invoicedetails.bookid_idx` (`BookID`),
  CONSTRAINT `invoicedetails.invoiceid` FOREIGN KEY (`InvoiceID`) REFERENCES `invoice` (`InvoiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `invoicedetails.bookid` FOREIGN KEY (`BookID`) REFERENCES `inventory` (`BookID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- create reviews table
DROP TABLE IF EXISTS reviews;
CREATE TABLE `reviews` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `BookID` int(11) NOT NULL,
  `ReviewDate` timestamp NULL DEFAULT NULL,
  `ClientID` int(11) NOT NULL,
  `Rating` tinyint(4) DEFAULT NULL,
  `ReviewText` text,
  `Status` tinyint(4) DEFAULT NULL COMMENT '0, pending\n1, approved',
  PRIMARY KEY (`ReviewID`),
  KEY `reviews.isbn_idx` (`BookID`),
  KEY `reviews.clientid_idx` (`ClientID`),
  CONSTRAINT `reviews.clientid` FOREIGN KEY (`ClientID`) REFERENCES `clients` (`ClientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reviews.bookid` FOREIGN KEY (`BookID`) REFERENCES `inventory` (`BookID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
