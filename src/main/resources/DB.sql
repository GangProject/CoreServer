DROP TABLE IF EXISTS CORE.summonerentity;
DROP TABLE IF EXISTS CORE.SpellEntity;
DROP TABLE IF EXISTS CORE.RuneEntity;
DROP TABLE IF EXISTS CORE.ResultEntity;
DROP TABLE IF EXISTS CORE.GameEntity;
DROP TABLE IF EXISTS CORE.RankedStatusEntity;
DROP TABLE IF EXISTS CORE.PlayerEntity;
DROP TABLE IF EXISTS CORE.ItemEntity;
DROP TABLE IF EXISTS CORE.ChampionStatsEntity;
DROP TABLE IF EXISTS CORE.ChampionEntity;
DROP TABLE IF EXISTS CORE.AggregateStatsEntity;
DROP TABLE IF EXISTS CORE.Bottom;
DROP TABLE IF EXISTS CORE.Junggle;
DROP TABLE IF EXISTS CORE.Top;
DROP TABLE IF EXISTS CORE.Mid;

create table CORE.SummonerEntity(
	`id` int Not Null auto_increment primary Key,
    `summonerId` int Not Null unique,
    `name` varchar(50) Not Null,
    `profileIconId` int Not Null,
    `revisionDate` long Not Null,
    `summonerLevel` long Not Null
);

create table CORE.SpellEntity(
	`id` int Not Null auto_increment primary Key,
    `spellId` long Not Null,
    `name` varchar(50) Not Null
);

create table CORE.RuneEntity(
	`id` int Not Null auto_increment primary Key,
    `runId` int Not Null,
    `runName` varchar(50) Not Null
);

create table CORE.ResultEntity(
	`id` int Not Null auto_increment primary Key,
    `summonerId` int Not Null,
    `name` varchar(50) Not Null,
    `winningRate` double Not Null,
    `tier` varchar(50) Not Null,
    `division` varchar(50) Not Null,
    `wins` int Not Null,
    `losses` int Not Null,
    `leaguePoints` int Not Null,
    foreign key(summonerId) references CORE.SummonerEntity(summonerId) ON UPDATE CASCADE ON DELETE CASCADE
);

create table CORE.GameEntity(
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
    `gamedate` long Not Null
);

create table CORE.RankedStatusEntity(
	`id` int Not Null auto_increment primary Key,
    `modifyDate` long Not Null,
    `summonerId` long Not Null
);

create table CORE.PlayerEntity(
	`id` int Not Null auto_increment primary Key,
    `gameid` long Not Null,
    `teamid` long Not Null,
    `championId` int Not Null,
    `playerId` long Not Null,
    `playerName` varchar(50) Not Null

);

create table CORE.ItemEntity(
	`id` int Not Null auto_increment primary Key,
    `itemId` int Not Null,
    `name` varchar(100)
);

create table CORE.ChampionStatsEntity(
	`id` int Not Null auto_increment primary Key,
    `championId` int Not Null
);

create table CORE.ChampionEntity(
	`id` int Not Null auto_increment primary Key,
    `name` varchar(50) Not Null,
    `championId` int Not Null
);

create table CORE.AggregateStatsEntity(
	`id` int Not Null auto_increment primary Key,
    `totalSessionPlayed` int Not Null,
    `totalSessionWon` int Not Null,
    `totalSessionLost` int Not Null,
    `totalChampionKills` int Not Null,
    `totalAssists` int Not Null,
    `totalDeathPerSession` int Not Null,
    `totalMinionKills` int Not Null
);

create table CORE.Bottom(
	`bottom_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table CORE.Junggle(
	`junggle_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table CORE.Mid(
	`mid_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);

create table CORE.Top(
	`top_id` int Not Null auto_increment primary Key,
    `total_game` int Not Null,
    `win` int Not Null,
    `rose` int Not Null,
    `playerid` long Not Null
);



