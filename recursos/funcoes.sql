create or replace function lower_unaccent( varchar ) returns varchar as $$
begin
    return translate( lower($1), 'âãáàêéèíìôõóòúùüç', 'aaaaeeeiioooouuuc' );
end;
$$ language plpgsql;