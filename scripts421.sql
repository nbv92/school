alter table student add constraint age_check check (age >= 16);

alter table student add constraint unique_name unique (name);
alter table faculty add constraint unique_name_color unique (name, color);

alter table student alter colomn name set not null;

alter table student alter colomn age set default = 20;


