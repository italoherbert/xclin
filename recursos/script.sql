
insert into usuario ( username, senha, perfil ) values 
( 'raiz', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'RAIZ' ),
( 'admin', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'ADMIN' ),
( 'diretor', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'DIRETOR' ),
( 'medico', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'MEDICO' ),
( 'recepcionista', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'RECEPCIONISTA' );

insert into usuario_grupo( nome ) values ('RAIZ'), ('ADMIN'), ('DIRETOR'), ('MEDICO'), ('RECEPCIONISTA');

insert into recurso ( nome ) values 
('usuario'),
('usuarioGrupo'),
('recurso');

insert into usuario_grupo_map ( usuario_id, grupo_id ) values 
( (select id from usuario where username='raiz'), (select id from usuario_grupo where nome='RAIZ') ),
( (select id from usuario where username='admin'), (select id from usuario_grupo where nome='ADMIN') ),
( (select id from usuario where username='diretor'), (select id from usuario_grupo where nome='DIRETOR') ),
( (select id from usuario where username='medico'), (select id from usuario_grupo where nome='MEDICO') ),
( (select id from usuario where username='recepcionista'), (select id from usuario_grupo where nome='RECEPCIONISTA') );

insert into acesso ( grupo_id, recurso_id, leitura, escrita, remocao ) values 
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='usuario'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='usuarioGrupo'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='recurso'), true, true, true ),

( (select id from usuario_grupo where nome='ADMIN'), (select id from recurso where nome='usuario'), true, true, false ),
( (select id from usuario_grupo where nome='ADMIN'), (select id from recurso where nome='usuarioGrupo'), true, false, false ),
( (select id from usuario_grupo where nome='ADMIN'), (select id from recurso where nome='recurso'), true, false, false );

