create table Detenu(
n_ecrou varchar(10),
prenom varchar(30),
nom varchar(30),
date_naissance Date,
lieu_naissance varchar(30),
constraint Detenu_key primary key(n_ecrou));

create table Affaire(
n_affaire varchar(10),
nom_juridiction varchar(30),
date_faits Date,
constraint Affaire_key primary key(n_affaire,nom_juridiction));

create table Detenu_Affaire(
n_ecrou varchar(10),
n_affaire varchar(10),
nom_juridiction varchar(30),
constraint Detenu_Affaire_key primary key(n_ecrou,n_affaire,nom_juridiction),
constraint Detenu_Affaire_foreign_key foreign key(n_ecrou) references Detenu(n_ecrou),
constraint Detenu_Affaire_foreign_key2 foreign key(n_affaire,nom_juridiction) references Affaire(n_affaire,nom_juridiction));

create table Motif(
n_motif varchar(10),
libelle_motif varchar(50) not null,
constraint Motif_key primary key(n_motif),
constraint Motif_unique unique(libelle_motif));

create table Incarceration(
n_ecrou varchar(10),
n_affaire varchar(10) not null,
nom_juridiction varchar(30) not null,
date_incarceration Date,
n_motif varchar(10) not null,
constraint Incarceration_key primary key(n_ecrou),
constraint Incarceration_foreign_key foreign key(n_ecrou,n_affaire,nom_juridiction) references Detenu_Affaire(n_ecrou,n_affaire,nom_juridiction),
constraint Incarceration_foreign_key2 foreign key(n_motif) references Motif(n_motif));

create table Decision(
n_type_decision varchar(1),
n_ecrou varchar(10),
date_decision Date,
constraint Decision_key primary key(n_type_decision,n_ecrou,date_decision),
constraint Decision_fk foreign key(n_ecrou) references Detenu(n_ecrou));

create table Condamnation(
n_type_decision varchar(1),
n_ecrou varchar(10),
date_decision Date,
duree Integer,
constraint Condamnation_key primary key(n_type_decision,n_ecrou,date_decision),
constraint Condamnation_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade);

create table Reduction_peine(
n_type_decision varchar(1),
n_ecrou varchar(10),
date_decision Date,
duree Integer,
constraint Reduction_peine_key primary key(n_type_decision,n_ecrou,date_decision),
constraint Reduction_peine_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade);

create table Liberation_definitive(
n_type_decision varchar(1),
n_ecrou varchar(10),
date_decision Date,
date_liberation Date,
constraint Liberation_definitive_key primary key(n_type_decision,n_ecrou,date_decision),
constraint Liberation_definitive_fk foreign key(n_type_decision,n_ecrou,date_decision) references Decision(n_type_decision,n_ecrou,date_decision) on delete cascade);

INSERT INTO Detenu VALUES('1963','Franck','Barbier',DATE('1963-01-11'),'Montbeliard');
INSERT INTO Detenu VALUES('1964','Sophie','Darnal',DATE(1964-07-28),'Besancon');

INSERT INTO Affaire VALUES('44','Nantes',DATE('1991-10-01'));

INSERT INTO Motif VALUES('01','vols et délits assimilés');

INSERT INTO Detenu_Affaire VALUES('1963','44','Nantes');
INSERT INTO Incarceration VALUES('1963','44','Nantes',DATE('2008-04-16'),'01');

INSERT INTO Decision VALUES('1','1963',DATE('2006-11-22'));
INSERT INTO Condamnation VALUES('1','1963',DATE('2006-11-22'),10);
INSERT INTO Decision VALUES('3','1964',DATE('2006-11-23'));
INSERT INTO Liberation_definitive VALUES('3','1964',DATE('2006-11-23'),DATE('2007-01-01'));
