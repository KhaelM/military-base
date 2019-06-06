DROP TABLE MISSION;
CREATE TABLE MISSION (
    id_mission SERIAL PRIMARY KEY,
    objectif VARCHAR(255),
    etat INTEGER
);
-- ETAT: 0 disponible, 1 en cours, 2 validé, 3 échoué

INSERT INTO Mission (objectif, etat) VALUES ('Infiltration en territoire ennemi', 0);
INSERT INTO Mission (objectif, etat) VALUES ('Recupération de données de la banque mondiale',0);
INSERT INTO Mission (objectif, etat) VALUES ('Escorte de marchandise',0);
INSERT INTO Mission (objectif, etat) VALUES ('Recherche enfant nocturne', 0);
INSERT INTO Mission (objectif, etat) VALUES ('Extermination village terroriste', 0);


DROP TABLE SOLDAT;
CREATE TABLE SOLDAT (
    id_soldat SERIAL PRIMARY KEY,
    nom_utilisateur VARCHAR(50),
    mot_de_passe VARCHAR(50),
    categorie INTEGER
);
-- Categorie: 0 soldat normal , 1 pour commandant
INSERT INTO SOLDAT (nom_utilisateur, mot_de_passe, categorie) VALUES ('Tiger9999', 'Tiger9999', 1);

DROP TABLE POSTE;
CREATE TABLE POSTE (
    id_poste SERIAL PRIMARY KEY,
    nom VARCHAR(50)
);

INSERT INTO POSTE (nom) VALUES ('infirmier');
INSERT INTO POSTE (nom) VALUES ('espion');
INSERT INTO POSTE (nom) VALUES ('pilote');
INSERT INTO POSTE (nom) VALUES ('mécanicien');
INSERT INTO POSTE (nom) VALUES ('informaticien');

DROP TABLE XP_SOLDAT_PAR_POSTE;
CREATE TABLE XP_SOLDAT_PAR_POSTE (
    id_xp_soldat_par_poste SERIAL PRIMARY KEY,
    id_poste INTEGER,
    id_soldat INTEGER,
    xp INTEGER,
    FOREIGN KEY (id_poste) REFERENCES POSTE(id_poste),
    FOREIGN KEY (id_soldat) REFERENCES SOLDAT(id_soldat)
);

DROP TABLE XP_MISSION_PAR_POSTE;
CREATE TABLE XP_MISSION_PAR_POSTE (
    id_xp_mission_par_poste SERIAL PRIMARY KEY,
    id_mission INTEGER,
    id_poste INTEGER,
    xp_min INTEGER,
    xp_gain INTEGER,
    FOREIGN KEY (id_poste) REFERENCES POSTE(id_poste),
    FOREIGN KEY (id_mission) REFERENCES MISSION(id_mission)
);

-- Mission1
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (1, 1, 100, 50);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (1, 2, 750, 400);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (1, 3, 100, 50);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (1, 4, 250, 150);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (1, 5, 750, 400);

-- Mission2
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (2, 1, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (2, 2, 500, 200);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (2, 3, 100, 50);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (2, 4, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (2, 5, 1000, 500);

-- Mission3
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (3, 1, 50, 25);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (3, 2, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (3, 3, 750, 450);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (3, 4, 500, 200);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (3, 5, 100, 50);

-- Mission4
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (4, 1, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (4, 2, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (4, 3, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (4, 4, 0, 5);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (4, 5, 0, 5);

-- Mission5
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (5, 1, 2000, 1500);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (5, 2, 2000, 1500);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (5, 3, 2000, 1500);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (5, 4, 2000, 1500);
INSERT INTO XP_MISSION_PAR_POSTE (id_mission, id_poste, xp_min, xp_gain) VALUES (5, 5, 2000, 1500);

DROP TABLE POSTE_SOLDAT_SUR_MISSION;
CREATE TABLE POSTE_SOLDAT_SUR_MISSION (
    id_poste_soldat_sur_mission SERIAL PRIMARY KEY,
    id_mission INTEGER,
    id_soldat INTEGER,
    id_poste INTEGER,
    FOREIGN KEY (id_mission) REFERENCES MISSION(id_mission),
    FOREIGN KEY (id_soldat) REFERENCES SOLDAT(id_soldat),   
    FOREIGN KEY (id_poste) REFERENCES POSTE(id_poste)
);