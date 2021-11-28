drop database if exists disney;
create database disney;
use disney;

create table personaje(
	id_personaje	integer not null auto_increment primary key,
	imagen			varchar(250) not null,
    nombre			varchar(250) not null,
    edad			integer not null,
    peso			float not null,
    historia		varchar(250) not null
);

create table genero(
	id_genero		integer not null auto_increment primary key,
    nombre			varchar(250) not null,
    imagen			varchar(250) not null
);

create table pelicula(
	id_pelicula		integer not null auto_increment primary key,
    imagen			varchar(250) not null,
    titulo			varchar(250) not null,
    fecha			date not null,
    calificacion	integer not null,
    id_genero		integer not null,
    foreign key(id_genero) references genero(id_genero),
    CONSTRAINT calificacion_no_valida CHECK(calificacion >= 1 and calificacion <= 5)
);

create table personaje_peliculas(
	id_personaje 		integer not null,
    id_pelicula 		integer not null,
    primary key(id_personaje, id_pelicula),
    foreign key(id_personaje) references personaje(id_personaje),
    foreign key(id_pelicula) references pelicula(id_pelicula)
);

create table usuario(
	id_usuario		integer not null auto_increment primary key,
	email			varchar(250) not null unique,
    pass			varchar(15) not null,
    constraint error_email check(email LIKE '%@%.%')
);

insert into genero(nombre, imagen) values("Terror","Imagen terror");
insert into genero(nombre, imagen) values("Accion","Imagen accion");
insert into genero(nombre, imagen) values("Romance","Imagen romance");
insert into genero(nombre, imagen) values("Suspenso","Imagen suspenso");
insert into genero(nombre, imagen) values("Animada","Imagen animada");

insert into personaje(imagen, nombre, edad, peso, historia) values("imagen 1", "nombre 1", 25, 43.54, "historia personaje 1");
insert into personaje(imagen, nombre, edad, peso, historia) values("imagen 2", "nombre 2", 53, 54.2, "historia personaje 2");
insert into personaje(imagen, nombre, edad, peso, historia) values("imagen 3", "nombre 3", 32, 88.2, "historia personaje 3");
insert into personaje(imagen, nombre, edad, peso, historia) values("imagen 4", "nombre 4", 19, 69.72, "historia personaje 4");
insert into personaje(imagen, nombre, edad, peso, historia) values("imagen 5", "nombre 5", 44, 76.4, "historia personaje 5");

insert into pelicula(imagen, titulo, fecha, calificacion, id_genero) values("imagen 1", "titulo 1", "1989/12/23", 4, 1);
insert into pelicula(imagen, titulo, fecha, calificacion, id_genero) values("imagen 2", "titulo 2", "2006/12/23", 3, 2);
insert into pelicula(imagen, titulo, fecha, calificacion, id_genero) values("imagen 3", "Arcane", "2021/12/23", 5, 2);
insert into pelicula(imagen, titulo, fecha, calificacion, id_genero) values("imagen 4", "titulo 4", "1999/12/23", 2, 4);
insert into pelicula(imagen, titulo, fecha, calificacion, id_genero) values("imagen 5", "titulo 5", "1977/12/23", 5, 5);

insert into personaje_peliculas(id_personaje, id_pelicula) values(1,1);
insert into personaje_peliculas(id_personaje, id_pelicula) values(2,1);
insert into personaje_peliculas(id_personaje, id_pelicula) values(1,2);
insert into personaje_peliculas(id_personaje, id_pelicula) values(2,2);
insert into personaje_peliculas(id_personaje, id_pelicula) values(3,2);
insert into personaje_peliculas(id_personaje, id_pelicula) values(3,3);
insert into personaje_peliculas(id_personaje, id_pelicula) values(4,3);
insert into personaje_peliculas(id_personaje, id_pelicula) values(5,3);
insert into personaje_peliculas(id_personaje, id_pelicula) values(4,4);
insert into personaje_peliculas(id_personaje, id_pelicula) values(3,4);
insert into personaje_peliculas(id_personaje, id_pelicula) values(2,4);
insert into personaje_peliculas(id_personaje, id_pelicula) values(5,5);
insert into personaje_peliculas(id_personaje, id_pelicula) values(1,5);
insert into personaje_peliculas(id_personaje, id_pelicula) values(2,5);

select * from personaje;
select * from pelicula;
select * from personaje_peliculas;
use disney;
select * from usuario;