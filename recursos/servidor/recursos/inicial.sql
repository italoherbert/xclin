create database xclin;

create user xclin with password '3950Vm08';

alter database xclin owner to xclin;

grant all on database xclin to xclin;
grant all on all tables in schema public to xclin;
grant all on all sequences in schema public to xclin;
