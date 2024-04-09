DROP DATABASE invManagement;
-- Create a new database
CREATE DATABASE invManagement;
-- Use the newly created database
USE invManagement;

-- Create Magic table
CREATE TABLE Magic (
  MagicID INT PRIMARY KEY,
  MagicName VARCHAR(255)
);

-- Create Item table
CREATE TABLE Item (
  ItemID INT PRIMARY KEY,
  Name VARCHAR(255) UNIQUE,
  Quantity INT,
  Description TEXT,
  MagicID INT,
  DangerLevel INT,
  PurchasePrice DECIMAL(10, 2),
  SellingPrice DECIMAL(10, 2),
  FOREIGN KEY (MagicID) REFERENCES Magic(MagicID)
);

-- Create Customer table
CREATE TABLE Customer (
  CustomerID INT PRIMARY KEY,
  Name VARCHAR(255),
  Age INT
);

-- Create Sales table
CREATE TABLE Sales (
  SaleID INT PRIMARY KEY,
  ItemID INT,
  CustomerID INT,
  SaleDate DATE,
  SaleAmount DECIMAL(10, 2),
  FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
  FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- Insert sample data into Magic table
INSERT INTO Magic (MagicID, MagicName) VALUES
(1, 'Fire'),
(2, 'Wind'),
(3, 'Earth'),
(4, 'Ice'),
(5, 'Wizardry'),
(6, 'Sorcery'),
(7, 'Rune');

-- Insert sample data into Item table
INSERT INTO Item (ItemID, Name, Quantity, Description, MagicID, DangerLevel, PurchasePrice, SellingPrice) VALUES
(1, 'Magic Wand', 10, 'A powerful wand capable of casting spells', 5, 3, 50.00, 100.00),
(2, 'Fireball Scroll', 5, 'A scroll containing the Fireball spell', 1, 4, 20.00, 40.00),
(3, 'Ice Ring', 3, 'A ring that grants the wearer control over ice', 4, 2, 80.00, 150.00);

-- Insert sample data into Customer table
INSERT INTO Customer (CustomerID, Name, Age) VALUES
(1, 'Alice', 25),
(2, 'Bob', 30),
(3, 'Charlie', 40);

-- Insert sample data into Sales table
INSERT INTO Sales (SaleID, ItemID, CustomerID, SaleDate, SaleAmount) VALUES
(1, 1, 1, '2024-04-01', 100.00),
(2, 2, 2, '2024-04-03', 40.00),
(3, 3, 3, '2024-04-05', 150.00);
-- Insert additional sample data into Item table
INSERT INTO Item (ItemID, Name, Quantity, Description, MagicID, DangerLevel, PurchasePrice, SellingPrice) VALUES
(4, 'Wind Staff', 8, 'A staff imbued with the power of wind', 2, 3, 60.00, 120.00),
(5, 'Earth Shield', 12, 'A shield enchanted with earth magic', 3, 4, 90.00, 180.00),
(6, 'Frost Sword', 5, 'A sword with a blade of pure ice', 4, 5, 120.00, 240.00),
(7, 'Wizard Hat', 15, 'A hat worn by wizards for extra spellcasting power', 5, 2, 40.00, 80.00),
(8, 'Sorcerer Robe', 7, 'Robes worn by sorcerers for protection and style', 6, 3, 80.00, 160.00),
(9, 'Rune Amulet', 3, 'An amulet inscribed with ancient runes for magical protection', 7, 4, 100.00, 200.00),
(10, 'Flame Dagger', 10, 'A dagger with a blade that burns with flames', 1, 3, 70.00, 140.00),
(11, 'Gust Pendant', 6, 'A pendant that harnesses the power of wind for its wearer', 2, 2, 50.00, 100.00),
(12, 'Terra Boots', 8, 'Boots that grant the wearer the stability of earth', 3, 3, 60.00, 120.00),
(13, 'Icicle Staff', 4, 'A staff that conjures icicles to impale enemies', 4, 4, 100.00, 200.00),
(14, 'Wizard Staff', 7, 'A staff favored by wizards for its magical properties', 5, 3, 70.00, 140.00),
(15, 'Sorcerer Wand', 5, 'A wand used by sorcerers to channel their spells', 6, 4, 90.00, 180.00),
(16, 'Runic Tome', 2, 'A tome filled with powerful runes for casting spells', 7, 5, 120.00, 240.00),
(17, 'Inferno Scroll', 3, 'A scroll containing powerful fire spells', 1, 4, 80.00, 160.00),
(18, 'Tornado Orb', 1, 'An orb that summons powerful tornadoes', 2, 5, 150.00, 300.00),
(19, 'Quake Hammer', 6, 'A hammer that causes earthquakes with each strike', 3, 5, 130.00, 260.00),
(20, 'Blizzard Cloak', 4, 'A cloak that envelops the wearer in a chilling blizzard', 4, 5, 140.00, 280.00),
(21, 'Mage Robes', 9, 'Robes worn by mages for protection and agility', 5, 3, 80.00, 160.00),
(22, 'Necromancer Staff', 2, 'A staff used by necromancers to control the undead', 6, 4, 110.00, 220.00),
(23, 'Enchanted Pendant', 5, 'A pendant with mysterious enchantments', 7, 2, 60.00, 120.00),
(24, 'Phoenix Feather', 3, 'A feather imbued with the essence of a phoenix', 1, 3, 70.00, 140.00),
(25, 'Gale Boots', 8, 'Boots that grant the wearer incredible speed in the wind', 2, 3, 80.00, 160.00),
(26, 'Gaia Gauntlets', 6, 'Gauntlets that harness the power of the earth for protection', 3, 4, 100.00, 200.00),
(27, 'Frozen Heart', 1, 'A heart frozen in ice, granting the bearer icy powers', 4, 5, 150.00, 300.00),
(28, 'Arcane Circlet', 4, 'A circlet that enhances the wearer\'s magical abilities', 5, 3, 70.00, 140.00),
(29, 'Sorceress Tiara', 3, 'A tiara worn by sorceresses for elegance and power', 6, 2, 50.00, 100.00),
(30, 'Runic Pendant', 7, 'A pendant inscribed with ancient runes for protection', 7, 4, 90.00, 180.00),
(31, 'Flame Tome', 5, 'A tome filled with powerful fire spells', 1, 4, 80.00, 160.00),
(32, 'Tempest Staff', 2, 'A staff that controls the raging tempests of the sky', 2, 5, 120.00, 240.00),
(33, 'Stone Golem Figurine', 3, 'A figurine that summons a stone golem to aid the bearer', 3, 5, 140.00, 280.00),
(34, 'Frostbite Blade', 4, 'A blade that inflicts frostbite upon its victims', 4, 4, 110.00, 220.00),
(35, 'Mage\'s Mantle', 6, 'A mantle worn by mages for protection against magic', 5, 3, 80.00, 160.00),
(36, 'Necrotic Scepter', 1, 'A scepter used by necromancers to drain life energy', 6, 5, 130.00, 260.00),
(37, 'Rune of Protection', 5, 'A rune that creates a protective barrier around the bearer', 7, 2, 60.00, 120.00),
(38, 'Phoenix Ashes', 2, 'Ashes from a phoenix, said to grant rebirth', 1, 3, 70.00, 140.00),
(39, 'Zephyr Boots', 7, 'Boots that allow the wearer to walk on air currents', 2, 3, 80.00, 160.00),
(40, 'Terra Blade', 4, 'A blade imbued with the power of the earth', 3, 4, 100.00, 200.00),
(41, 'Frozen Crown', 2, 'A crown made of ice that enhances the bearer\'s powers', 4, 5, 150.00, 300.00),
(42, 'Arcane Orb', 3, 'An orb containing pure arcane energy', 5, 4, 110.00, 220.00),
(43, 'Sorcerer\'s Cloak', 6, 'A cloak worn by sorcerers for protection and style', 6, 3, 80.00, 160.00),
(44, 'Runic Gauntlets', 2, 'Gauntlets inscribed with powerful runes for protection', 7, 4, 90.00, 180.00),
(45, 'Firestorm Scroll', 5, 'A scroll containing a devastating firestorm spell', 1, 5, 140.00, 280.00),
(46, 'Tornado Scepter', 1, 'A scepter that commands the power of tornadoes', 2, 5, 130.00, 260.00),
(47, 'Stone Armor', 4, 'Armor made of stone for maximum protection', 3, 5, 150.00, 300.00),
(48, 'Frostbite Pendant', 3, 'A pendant that inflicts frostbite upon enemies', 4, 4, 110.00, 220.00),
(49, 'Mage\'s Robe', 6, 'Robes worn by mages for protection and comfort', 5, 3, 80.00, 160.00),
(50, 'Necromancer Cloak', 2, 'A cloak worn by necromancers for protection and intimidation', 6, 4, 90.00, 180.00);


-- Insert additional sample data into Customer table
INSERT INTO Customer (CustomerID, Name, Age) VALUES
(4, 'David', 35),
(5, 'Eve', 28),
(6, 'Frank', 45),
(7, 'Grace', 32),
(8, 'Henry', 50),
(9, 'Isabel', 27),
(10, 'Jack', 40),
(11, 'Katherine', 33),
(12, 'Liam', 29),
(13, 'Mia', 42),
(14, 'Nathan', 31),
(15, 'Olivia', 36);

-- Insert additional sample data into Sales table
INSERT INTO Sales (SaleID, ItemID, CustomerID, SaleDate, SaleAmount) VALUES
(4, 4, 4, '2025-04-10', 200.00),
(5, 5, 5, '2025-04-15', 250.00),
(6, 6, 6, '2025-05-02', 180.00),
(7, 7, 7, '2025-05-20', 120.00),
(8, 8, 8, '2025-06-05', 150.00),
(9, 9, 9, '2025-06-15', 220.00),
(10, 10, 10, '2025-07-01', 190.00),
(11, 11, 11, '2025-07-10', 210.00),
(12, 12, 12, '2025-08-03', 170.00),
(13, 13, 13, '2025-08-18', 240.00),
(14, 14, 14, '2025-09-02', 200.00),
(15, 15, 15, '2025-09-25', 260.00);
