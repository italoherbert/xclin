
insert into usuario ( username, senha, perfil, clinica_id ) values 
( 'raiz', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'RAIZ', NULL ),
( 'admin', '59f62a0320ea304cbec2498764c5c6742bfcabf1b591e26a3bea6bfcca3e358e', 'ADMIN', NULL );

insert into usuario_grupo( nome ) values ('RAIZ'), ('ADMIN'), ('DIRETOR'), ('MEDICO'), ('RECEPCIONISTA');

insert into recurso ( nome ) values 
('usuario'),
('usuarioGrupo'),
('recurso'),
('clinica'),
('diretor'),
('profissional'),
('recepcionista'),
('paciente'),
('consulta');

insert into usuario_grupo_vinculo ( usuario_id, grupo_id ) values 
( (select id from usuario where username='raiz'), (select id from usuario_grupo where nome='RAIZ') ),
( (select id from usuario where username='admin'), (select id from usuario_grupo where nome='ADMIN') );

insert into acesso ( grupo_id, recurso_id, leitura, escrita, remocao ) values 
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='usuario'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='usuarioGrupo'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='recurso'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='clinica'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='diretor'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='profissional'), true, true, true ),
( (select id from usuario_grupo where nome='RAIZ'), (select id from recurso where nome='recepcionista'), true, true, true ),

( (select id from usuario_grupo where nome='ADMIN'), (select id from recurso where nome='usuario'), true, true, false ),
( (select id from usuario_grupo where nome='ADMIN'), (select id from recurso where nome='usuarioGrupo'), true, false, false ),
( (select id from usuario_grupo where nome='ADMIN'), (select id from recurso where nome='recurso'), true, false, false ),

( (select id from usuario_grupo where nome='RECEPCIONISTA'), (select id from recurso where nome='paciente'), true, true, true ),
( (select id from usuario_grupo where nome='RECEPCIONISTA'), (select id from recurso where nome='consulta'), true, true, true ),

( (select id from usuario_grupo where nome='PROFISSIONAL'), (select id from recurso where nome='paciente'), true, true, true ),
( (select id from usuario_grupo where nome='PROFISSIONAL'), (select id from recurso where nome='consulta'), true, true, true );
