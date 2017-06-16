DROP TABLE IF EXISTS core.summonerentity;
DROP TABLE IF EXISTS core.SpellEntity;
DROP TABLE IF EXISTS core.RuneEntity;
DROP TABLE IF EXISTS core.ResultEntity;
DROP TABLE IF EXISTS core.GameEntity;
DROP TABLE IF EXISTS core.RankedStatusEntity;
DROP TABLE IF EXISTS core.PlayerEntity;
DROP TABLE IF EXISTS core.ItemEntity;
DROP TABLE IF EXISTS core.ChampionStatsEntity;
DROP TABLE IF EXISTS core.ChampionEntity;
DROP TABLE IF EXISTS core.AggregateStatsEntity;
DROP TABLE IF EXISTS core.Bottom;
DROP TABLE IF EXISTS core.Junggle;
DROP TABLE IF EXISTS core.Top;
DROP TABLE IF EXISTS core.Mid2;

create table core.SummonerEntity(
   `id` int Not Null auto_increment primary Key,
    `summonerId` int Not Null unique,
    `name` varchar(50) Not Null,
    `profileIconId` int Not Null,
    `revisionDate` long Not Null,
    `summonerLevel` long Not Null
);

create table core.SpellEntity(
   `id` int Not Null auto_increment primary Key,
    `spellId` long Not Null,
    `name` varchar(50) Not Null,
    `ename` VARCHAR(100) Not null
);

create table core.RuneEntity(
   `id` int Not Null auto_increment primary Key,
    `runId` int Not Null,
    `runName` varchar(50) Not Null,
    `type` VARCHAR (100) Not null,
    `descript` VARCHAR (200) NOT null
);

create table core.ResultEntity(
   `id` int Not Null auto_increment primary Key,
    `summonerId` int Not Null,
    `name` varchar(50) Not Null,
    `winningRate` double Not Null,
    `tier` varchar(50) Not Null,
    `division` varchar(50) Not Null,
    `wins` int Not Null,
    `losses` int Not Null,
    `leaguePoints` int Not Null,
    foreign key(summonerId) references core.SummonerEntity(summonerId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table core.GameEntity(
   `id` int Not Null auto_increment primary Key,
    `summoner_id` long Not Null,
    `game_id` long Not Null,
    `createDate` varchar(50) Not Null,
    `game_mode` varchar(50) Not Null,
    `championId` varchar(50) Not Null,
    `mapId` int Not Null,
    `subType` varchar(50) Not Null,
    `teamId` int Not Null,
    `spell1` varchar(50) Not Null,
    `spell2` varchar(50) Not Null,
   `item0` varchar(50) ,
    `item1` varchar(50) ,
    `item2` varchar(50) ,
    `item3` varchar(50) ,
    `item4` varchar(50) ,
    `item5` varchar(50) ,
    `item6` varchar(50) ,
    `gameKill` int Not Null,
    `gameDeath` int Not Null,
    `gameAssist` int Not Null,
    `gamedate` long Not Null,
    `win` BOOLEAN Not Null,
    `cs` int NOT NULL,
    `pinkward` int Not null,
    `playtime` VARCHAR(100),
    `level` int Not NULL,
    `durationTime` VARCHAR (100) Not null,
    `recordeKill` VARCHAR (100),
    `inkda` DOUBLE not NULL
);

create table core.RankedStatusEntity(
   `id` int Not Null auto_increment primary Key,
    `modifyDate` long Not Null,
    `summonerId` long Not Null
);

create table core.PlayerEntity(
   `id` int Not Null auto_increment primary Key,
    `gameid` long Not Null,
    `teamid` long Not Null,
    `championId` int Not Null,
    `playerId` long Not Null,
    `playerName` varchar(50) Not Null
    ,`championName` VARCHAR(100) NOT NULL

);

create table core.ItemEntity(
   `id` int Not Null auto_increment primary Key,
    `itemId` int Not Null,
    `name` varchar(100)
);

create table core.ChampionStatsEntity(
   `id` int Not Null auto_increment primary Key,
    `championId` int Not Null
);

create table core.ChampionEntity(
   `id` int Not Null auto_increment primary Key,
    `name` varchar(50) Not Null,
    `championId` int Not Null,
    `ename` varchar(100) NOt null
);

create table core.AggregateStatsEntity(
   `id` int Not Null auto_increment primary Key,
    `totalSessionPlayed` int Not Null,
    `totalSessionWon` int Not Null,
    `totalSessionLost` int Not Null,
    `totalChampionKills` int Not Null,
    `totalAssists` int Not Null,
    `totalDeathPerSession` int Not Null,
    `totalMinionKills` int Not Null
);

create table core.Bottom(
   `bottom_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table core.Junggle(
   `junggle_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table core.Mid2(
   `mid_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table core.Top(
   `top_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table core.MasteryEntity(
   `id` int Not Null auto_increment primary Key,
    `masteryid` int Not Null,
    `masteryname` VARCHAR(100) Not Null

);




