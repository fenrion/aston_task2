create table actors(
                       id_act int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name_act varchar(100) not null UNIQUE
);

create table movies(
                       id_mov int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name_mov varchar(100) not null unique
);
create table actors_movies(
                              actor_id int references actors(id),
                              movies_id int references movies(id),
                              primary key (actor_id,movies_id)
);

create table phone_numbers(
                              id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              actor_id int references actors(id),
                              number varchar(15) not null unique,
);


insert into actors(name) values ('Tom');
insert into actors(name) values ('Anna');
insert into actors(name) values ('Sam');
insert into actors(name) values ('Kate');
insert into actors(name) values ('Leo');


insert  into movies(name) values ('Film1');
insert  into movies(name) values ('Film2');
insert  into movies(name) values ('Film3');

insert into actors_movies values (1,1);
insert into actors_movies values (1,2);
insert into actors_movies values (1,3);
insert into actors_movies values (2,1);
insert into actors_movies values (2,2);
insert into actors_movies values (3,1);
insert into actors_movies values (4,2);
insert into actors_movies values (4,3);
insert into actors_movies values (5,3);

insert into phone_numbers (number, actor_id) values ('1777',1);
insert into phone_numbers (number, actor_id) values ('177527',1);
insert into phone_numbers (number, actor_id) values ('1777321',1);
insert into phone_numbers (number, actor_id) values ('276327',2);
insert into phone_numbers (number, actor_id) values ('15215',2);
insert into phone_numbers (number, actor_id) values ('3775217',3);
insert into phone_numbers (number, actor_id) values ('4776127',4);
insert into phone_numbers (number, actor_id) values ('5777521',5);


