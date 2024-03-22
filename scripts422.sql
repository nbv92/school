create table human (
    id serial primary key,
    name varchar(50),
    age integer,
    has_license boolean,
    car_id integer references car(id)
);

create table car (
    id serial primary key,
    brand varchar(50),
    model varchar(50),
    cost integer)