DROP TABLE IF EXISTS GANG.SummonerEntity;

create table GANG.SummonerEntity(
	`id` int Not Null auto_increment primary Key,
    `summonerId` long Not Null,
    `name` varchar(50) Not Null,
    `profileIconId` int Not Null,
    `revisionDate` long Not Null,
    `summonerLevel` long Not Null
);