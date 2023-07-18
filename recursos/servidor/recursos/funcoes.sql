create or replace function lower_unaccent( varchar ) returns varchar as $$
begin
    return lower(unaccent($1));
end;
$$ language plpgsql;