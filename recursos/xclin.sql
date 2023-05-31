--
-- xclinQL database dump
--

-- Dumped from database version 12.15 (Ubuntu 12.15-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.15 (Ubuntu 12.15-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: lower_unaccent(character varying); Type: FUNCTION; Schema: public; Owner: xclin
--

CREATE FUNCTION public.lower_unaccent(character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
begin
    return translate( lower($1), 'Ã¢Ã£Ã¡Ã ÃªÃ©Ã¨Ã­Ã¬Ã´ÃµÃ³Ã²ÃºÃ¹Ã¼Ã§', 'aaaaeeeiioooouuuc' );
end;
$_$;


ALTER FUNCTION public.lower_unaccent(character varying) OWNER TO xclin;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: acesso; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.acesso (
    id bigint NOT NULL,
    escrita boolean NOT NULL,
    leitura boolean NOT NULL,
    remocao boolean NOT NULL,
    grupo_id bigint,
    recurso_id bigint
);


ALTER TABLE public.acesso OWNER TO xclin;

--
-- Name: acesso_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.acesso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.acesso_id_seq OWNER TO xclin;

--
-- Name: acesso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.acesso_id_seq OWNED BY public.acesso.id;


--
-- Name: clinica; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.clinica (
    id bigint NOT NULL,
    email character varying(255),
    nome character varying(255),
    telefone character varying(255),
    usuario_id bigint,
    endereco_id bigint
);


ALTER TABLE public.clinica OWNER TO xclin;

--
-- Name: clinica_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.clinica_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clinica_id_seq OWNER TO xclin;

--
-- Name: clinica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.clinica_id_seq OWNED BY public.clinica.id;


--
-- Name: consulta; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.consulta (
    id bigint NOT NULL,
    paga boolean NOT NULL,
    retorno boolean NOT NULL,
    status character varying(255),
    valor double precision NOT NULL,
    paciente_id bigint,
    profissional_id bigint,
    turno character varying(255),
    clinica_id bigint,
    observacoes character varying(255),
    data_agendamento timestamp without time zone,
    data_atendimento date,
    data_finalizacao timestamp(6) without time zone,
    especialidade_id bigint
);


ALTER TABLE public.consulta OWNER TO xclin;

--
-- Name: consulta_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consulta_id_seq OWNER TO xclin;

--
-- Name: consulta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.consulta_id_seq OWNED BY public.consulta.id;


--
-- Name: diretor; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.diretor (
    id bigint NOT NULL,
    nome character varying(255),
    usuario_id bigint
);


ALTER TABLE public.diretor OWNER TO xclin;

--
-- Name: diretor_clinica_vinculo; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.diretor_clinica_vinculo (
    id bigint NOT NULL,
    clinica_id bigint,
    diretor_id bigint
);


ALTER TABLE public.diretor_clinica_vinculo OWNER TO xclin;

--
-- Name: diretor_clinica_vinculo_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.diretor_clinica_vinculo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.diretor_clinica_vinculo_id_seq OWNER TO xclin;

--
-- Name: diretor_clinica_vinculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.diretor_clinica_vinculo_id_seq OWNED BY public.diretor_clinica_vinculo.id;


--
-- Name: diretor_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.diretor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.diretor_id_seq OWNER TO xclin;

--
-- Name: diretor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.diretor_id_seq OWNED BY public.diretor.id;


--
-- Name: endereco; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    bairro character varying(255),
    logradouro character varying(255),
    numero character varying(255),
    codigo_municipio integer,
    codigo_uf integer
);


ALTER TABLE public.endereco OWNER TO xclin;

--
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_seq OWNER TO xclin;

--
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.id;


--
-- Name: especialidade; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.especialidade (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.especialidade OWNER TO xclin;

--
-- Name: especialidade_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.especialidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.especialidade_id_seq OWNER TO xclin;

--
-- Name: especialidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.especialidade_id_seq OWNED BY public.especialidade.id;


--
-- Name: paciente; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.paciente (
    id bigint NOT NULL,
    cpf character varying(255),
    data_nascimento date,
    data_registro timestamp(6) without time zone,
    email character varying(255),
    estado_civil character varying(255),
    nacionalidade character varying(255),
    nome character varying(255),
    observacoes character varying(255),
    ocupacao character varying(255),
    rg character varying(255),
    sexo character varying(255),
    telefone character varying(255),
    clinica_id bigint,
    endereco_id bigint
);


ALTER TABLE public.paciente OWNER TO xclin;

--
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.paciente_id_seq OWNER TO xclin;

--
-- Name: paciente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.paciente_id_seq OWNED BY public.paciente.id;


--
-- Name: profissional; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.profissional (
    id bigint NOT NULL,
    funcao smallint,
    nome character varying(255),
    valor_consulta double precision NOT NULL,
    usuario_id bigint
);


ALTER TABLE public.profissional OWNER TO xclin;

--
-- Name: profissional_clinica_vinculo; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.profissional_clinica_vinculo (
    id bigint NOT NULL,
    clinica_id bigint,
    profissional_id bigint
);


ALTER TABLE public.profissional_clinica_vinculo OWNER TO xclin;

--
-- Name: profissional_clinica_vinculo_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.profissional_clinica_vinculo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profissional_clinica_vinculo_id_seq OWNER TO xclin;

--
-- Name: profissional_clinica_vinculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.profissional_clinica_vinculo_id_seq OWNED BY public.profissional_clinica_vinculo.id;


--
-- Name: profissional_especialidade_vinculo; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.profissional_especialidade_vinculo (
    id bigint NOT NULL,
    consulta_valor double precision NOT NULL,
    especialidade_id bigint,
    profissional_id bigint
);


ALTER TABLE public.profissional_especialidade_vinculo OWNER TO xclin;

--
-- Name: profissional_especialidade_vinculo_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.profissional_especialidade_vinculo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profissional_especialidade_vinculo_id_seq OWNER TO xclin;

--
-- Name: profissional_especialidade_vinculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.profissional_especialidade_vinculo_id_seq OWNED BY public.profissional_especialidade_vinculo.id;


--
-- Name: profissional_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.profissional_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profissional_id_seq OWNER TO xclin;

--
-- Name: profissional_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.profissional_id_seq OWNED BY public.profissional.id;


--
-- Name: recepcionista; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.recepcionista (
    id bigint NOT NULL,
    nome character varying(255),
    clinica_id bigint,
    usuario_id bigint
);


ALTER TABLE public.recepcionista OWNER TO xclin;

--
-- Name: recepcionista_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.recepcionista_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recepcionista_id_seq OWNER TO xclin;

--
-- Name: recepcionista_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.recepcionista_id_seq OWNED BY public.recepcionista.id;


--
-- Name: recurso; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.recurso (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.recurso OWNER TO xclin;

--
-- Name: recurso_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.recurso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recurso_id_seq OWNER TO xclin;

--
-- Name: recurso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.recurso_id_seq OWNED BY public.recurso.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    perfil character varying(255),
    senha character varying(255),
    username character varying(255),
    criador_id bigint
);


ALTER TABLE public.usuario OWNER TO xclin;

--
-- Name: usuario_grupo; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.usuario_grupo (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.usuario_grupo OWNER TO xclin;

--
-- Name: usuario_grupo_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.usuario_grupo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_grupo_id_seq OWNER TO xclin;

--
-- Name: usuario_grupo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.usuario_grupo_id_seq OWNED BY public.usuario_grupo.id;


--
-- Name: usuario_grupo_vinculo; Type: TABLE; Schema: public; Owner: xclin
--

CREATE TABLE public.usuario_grupo_vinculo (
    id bigint NOT NULL,
    grupo_id bigint,
    usuario_id bigint
);


ALTER TABLE public.usuario_grupo_vinculo OWNER TO xclin;

--
-- Name: usuario_grupo_vinculo_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.usuario_grupo_vinculo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_grupo_vinculo_id_seq OWNER TO xclin;

--
-- Name: usuario_grupo_vinculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.usuario_grupo_vinculo_id_seq OWNED BY public.usuario_grupo_vinculo.id;


--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: xclin
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO xclin;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: xclin
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: acesso id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.acesso ALTER COLUMN id SET DEFAULT nextval('public.acesso_id_seq'::regclass);


--
-- Name: clinica id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.clinica ALTER COLUMN id SET DEFAULT nextval('public.clinica_id_seq'::regclass);


--
-- Name: consulta id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.consulta ALTER COLUMN id SET DEFAULT nextval('public.consulta_id_seq'::regclass);


--
-- Name: diretor id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor ALTER COLUMN id SET DEFAULT nextval('public.diretor_id_seq'::regclass);


--
-- Name: diretor_clinica_vinculo id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor_clinica_vinculo ALTER COLUMN id SET DEFAULT nextval('public.diretor_clinica_vinculo_id_seq'::regclass);


--
-- Name: endereco id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);


--
-- Name: especialidade id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.especialidade ALTER COLUMN id SET DEFAULT nextval('public.especialidade_id_seq'::regclass);


--
-- Name: paciente id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.paciente ALTER COLUMN id SET DEFAULT nextval('public.paciente_id_seq'::regclass);


--
-- Name: profissional id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional ALTER COLUMN id SET DEFAULT nextval('public.profissional_id_seq'::regclass);


--
-- Name: profissional_clinica_vinculo id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_clinica_vinculo ALTER COLUMN id SET DEFAULT nextval('public.profissional_clinica_vinculo_id_seq'::regclass);


--
-- Name: profissional_especialidade_vinculo id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_especialidade_vinculo ALTER COLUMN id SET DEFAULT nextval('public.profissional_especialidade_vinculo_id_seq'::regclass);


--
-- Name: recepcionista id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.recepcionista ALTER COLUMN id SET DEFAULT nextval('public.recepcionista_id_seq'::regclass);


--
-- Name: recurso id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.recurso ALTER COLUMN id SET DEFAULT nextval('public.recurso_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Name: usuario_grupo id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario_grupo ALTER COLUMN id SET DEFAULT nextval('public.usuario_grupo_id_seq'::regclass);


--
-- Name: usuario_grupo_vinculo id; Type: DEFAULT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario_grupo_vinculo ALTER COLUMN id SET DEFAULT nextval('public.usuario_grupo_vinculo_id_seq'::regclass);


--
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.acesso (id, escrita, leitura, remocao, grupo_id, recurso_id) FROM stdin;
1	t	t	t	1	1
2	t	t	t	1	2
3	t	t	t	1	3
4	t	t	f	2	1
5	f	t	f	2	2
6	f	t	f	2	3
7	t	t	t	1	4
8	t	t	t	1	5
9	t	t	t	1	6
10	t	t	t	1	7
11	f	f	f	5	1
12	f	f	f	5	2
13	f	f	f	5	3
18	t	t	t	5	8
19	f	f	f	4	1
20	f	f	f	4	2
21	f	f	f	4	3
26	t	t	t	4	8
27	t	t	t	5	9
28	f	f	f	3	1
29	f	f	f	3	2
30	f	f	f	3	3
35	t	t	t	3	8
37	t	t	t	4	9
36	t	t	t	3	9
41	t	t	t	3	13
42	t	t	t	3	12
43	t	t	t	3	11
44	t	t	t	5	13
45	t	t	t	5	12
46	t	t	t	5	11
47	t	t	t	4	13
48	t	t	t	4	12
49	t	t	t	4	11
50	t	t	t	3	14
51	t	t	t	5	14
52	t	t	t	4	14
53	t	t	t	1	15
54	f	f	f	1	14
55	f	f	f	1	13
56	f	f	f	1	12
57	f	f	f	1	11
58	f	f	f	1	9
59	f	f	f	1	8
60	t	t	t	2	15
61	f	f	f	2	14
62	f	f	f	2	13
63	f	f	f	2	12
64	f	f	f	2	11
65	f	f	f	2	9
66	f	f	f	2	8
67	f	f	f	2	7
68	f	f	f	2	6
69	f	f	f	2	5
70	f	f	f	2	4
33	f	t	f	3	6
32	f	t	f	3	5
31	f	t	f	3	4
17	f	t	f	5	7
16	f	t	f	5	6
15	f	t	f	5	5
14	f	t	f	5	4
25	f	t	f	4	7
24	f	t	f	4	6
23	f	t	f	4	5
22	f	t	f	4	4
34	f	t	f	3	7
\.


--
-- Data for Name: clinica; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.clinica (id, email, nome, telefone, usuario_id, endereco_id) FROM stdin;
2	italoherbert@outlook.com	Clinica Nordeste	(87) 99905-2371	1	1
3		Clínica Amazonas	(87) 99050-9687	1	2
4		Clínica João Mariano	(81) 99906-8955	1	3
\.


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.consulta (id, paga, retorno, status, valor, paciente_id, profissional_id, turno, clinica_id, observacoes, data_agendamento, data_atendimento, data_finalizacao, especialidade_id) FROM stdin;
17	f	f	REGISTRADA	450	2	5	MANHA	2	3000abc	2023-05-27 16:09:00.324	2023-05-27	\N	1
18	f	f	REGISTRADA	450	3	5	MANHA	2	Xxxxx	2023-05-28 18:02:48.921	2023-05-27	\N	1
\.


--
-- Data for Name: diretor; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.diretor (id, nome, usuario_id) FROM stdin;
\.


--
-- Data for Name: diretor_clinica_vinculo; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.diretor_clinica_vinculo (id, clinica_id, diretor_id) FROM stdin;
\.


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.endereco (id, bairro, logradouro, numero, codigo_municipio, codigo_uf) FROM stdin;
1	Centro	Rua vereador raimundo eufrásio	170	2613602	26
2				1302603	13
3				0	15
5				0	0
6				0	0
7				0	0
8				0	0
\.


--
-- Data for Name: especialidade; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.especialidade (id, nome) FROM stdin;
1	Cardiologista
\.


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.paciente (id, cpf, data_nascimento, data_registro, email, estado_civil, nacionalidade, nome, observacoes, ocupacao, rg, sexo, telefone, clinica_id, endereco_id) FROM stdin;
2		1992-07-15	2023-05-25 20:25:32.886		\N	\N	carlos josé	\N			\N		2	5
3		1992-07-15	2023-05-25 20:25:54.316		\N	\N	maria carla	\N			\N		2	6
4		2023-05-01	2023-05-25 20:26:23.427		\N	\N	beto camargo	\N			\N		2	7
5		2023-05-02	2023-05-25 20:26:43.251		\N	\N	Joana maria	\N			\N		2	8
\.


--
-- Data for Name: profissional; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.profissional (id, funcao, nome, valor_consulta, usuario_id) FROM stdin;
5	0	Carlos Alberto Teixeira	0	12
\.


--
-- Data for Name: profissional_clinica_vinculo; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.profissional_clinica_vinculo (id, clinica_id, profissional_id) FROM stdin;
2	2	5
\.


--
-- Data for Name: profissional_especialidade_vinculo; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.profissional_especialidade_vinculo (id, consulta_valor, especialidade_id, profissional_id) FROM stdin;
8	450	1	5
\.


--
-- Data for Name: recepcionista; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.recepcionista (id, nome, clinica_id, usuario_id) FROM stdin;
2	Maria Carla de Brito	2	13
\.


--
-- Data for Name: recurso; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.recurso (id, nome) FROM stdin;
1	usuario
2	usuarioGrupo
3	recurso
4	clinica
5	diretor
6	profissional
7	recepcionista
8	paciente
9	consulta
11	naoAdminDiretor
12	naoAdminProfissional
13	naoAdminRecepcionista
14	naoAdminClinica
15	especialidade
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.usuario (id, perfil, senha, username, criador_id) FROM stdin;
12	PROFISSIONAL	c56132f2aa181259dfbd2f1acab8157dd926466227758f6ee6c430631a0692b6	profissional	1
13	RECEPCIONISTA	0f514e517827dd16a54c85c3ffbaa1cec8dee097558d2b6750a6c986ed4baa00	maria	1
1	RAIZ	e0c4dddd77efedd2cdd0c3e2b55b79f6410586bdfc1ca539c2a36686b10b3c32	raiz	\N
\.


--
-- Data for Name: usuario_grupo; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.usuario_grupo (id, nome) FROM stdin;
1	RAIZ
2	ADMIN
3	DIRETOR
5	RECEPCIONISTA
4	PROFISSIONAL
\.


--
-- Data for Name: usuario_grupo_vinculo; Type: TABLE DATA; Schema: public; Owner: xclin
--

COPY public.usuario_grupo_vinculo (id, grupo_id, usuario_id) FROM stdin;
1	1	1
3	3	1
4	4	1
5	5	13
6	4	1
7	4	12
\.


--
-- Name: acesso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.acesso_id_seq', 70, true);


--
-- Name: clinica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.clinica_id_seq', 4, true);


--
-- Name: consulta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.consulta_id_seq', 18, true);


--
-- Name: diretor_clinica_vinculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.diretor_clinica_vinculo_id_seq', 5, true);


--
-- Name: diretor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.diretor_id_seq', 4, true);


--
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.endereco_id_seq', 8, true);


--
-- Name: especialidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.especialidade_id_seq', 1, true);


--
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.paciente_id_seq', 5, true);


--
-- Name: profissional_clinica_vinculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.profissional_clinica_vinculo_id_seq', 3, true);


--
-- Name: profissional_especialidade_vinculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.profissional_especialidade_vinculo_id_seq', 8, true);


--
-- Name: profissional_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.profissional_id_seq', 7, true);


--
-- Name: recepcionista_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.recepcionista_id_seq', 2, true);


--
-- Name: recurso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.recurso_id_seq', 15, true);


--
-- Name: usuario_grupo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.usuario_grupo_id_seq', 5, true);


--
-- Name: usuario_grupo_vinculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.usuario_grupo_vinculo_id_seq', 7, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: xclin
--

SELECT pg_catalog.setval('public.usuario_id_seq', 15, true);


--
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- Name: clinica clinica_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.clinica
    ADD CONSTRAINT clinica_pkey PRIMARY KEY (id);


--
-- Name: consulta consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (id);


--
-- Name: diretor_clinica_vinculo diretor_clinica_vinculo_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor_clinica_vinculo
    ADD CONSTRAINT diretor_clinica_vinculo_pkey PRIMARY KEY (id);


--
-- Name: diretor diretor_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor
    ADD CONSTRAINT diretor_pkey PRIMARY KEY (id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: especialidade especialidade_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.especialidade
    ADD CONSTRAINT especialidade_pkey PRIMARY KEY (id);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- Name: profissional_clinica_vinculo profissional_clinica_vinculo_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_clinica_vinculo
    ADD CONSTRAINT profissional_clinica_vinculo_pkey PRIMARY KEY (id);


--
-- Name: profissional_especialidade_vinculo profissional_especialidade_vinculo_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_especialidade_vinculo
    ADD CONSTRAINT profissional_especialidade_vinculo_pkey PRIMARY KEY (id);


--
-- Name: profissional profissional_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional
    ADD CONSTRAINT profissional_pkey PRIMARY KEY (id);


--
-- Name: recepcionista recepcionista_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.recepcionista
    ADD CONSTRAINT recepcionista_pkey PRIMARY KEY (id);


--
-- Name: recurso recurso_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.recurso
    ADD CONSTRAINT recurso_pkey PRIMARY KEY (id);


--
-- Name: usuario_grupo usuario_grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario_grupo
    ADD CONSTRAINT usuario_grupo_pkey PRIMARY KEY (id);


--
-- Name: usuario_grupo_vinculo usuario_grupo_vinculo_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario_grupo_vinculo
    ADD CONSTRAINT usuario_grupo_vinculo_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: consulta fk1oq22nlbbvpijm5n2t6vqi1is; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk1oq22nlbbvpijm5n2t6vqi1is FOREIGN KEY (paciente_id) REFERENCES public.paciente(id);


--
-- Name: profissional_clinica_vinculo fk3658kgyowcxws5aa792cuu1jm; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_clinica_vinculo
    ADD CONSTRAINT fk3658kgyowcxws5aa792cuu1jm FOREIGN KEY (profissional_id) REFERENCES public.profissional(id);


--
-- Name: clinica fk3ardy4cugio8i2oigmenojsss; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.clinica
    ADD CONSTRAINT fk3ardy4cugio8i2oigmenojsss FOREIGN KEY (endereco_id) REFERENCES public.endereco(id);


--
-- Name: profissional fk509yihu28yuuinro8jxectk7q; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional
    ADD CONSTRAINT fk509yihu28yuuinro8jxectk7q FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: profissional_clinica_vinculo fk55mefxfq1yxs3mh7smr9h0ubk; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_clinica_vinculo
    ADD CONSTRAINT fk55mefxfq1yxs3mh7smr9h0ubk FOREIGN KEY (clinica_id) REFERENCES public.clinica(id);


--
-- Name: consulta fk5cf4njjq0kgjvhb1077rdc4d6; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk5cf4njjq0kgjvhb1077rdc4d6 FOREIGN KEY (profissional_id) REFERENCES public.profissional(id);


--
-- Name: consulta fk6b2r1cqh222pojyxebb15l09x; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk6b2r1cqh222pojyxebb15l09x FOREIGN KEY (especialidade_id) REFERENCES public.especialidade(id);


--
-- Name: usuario fk7n8ptg8x9n6p7x0bs11a8rkw9; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk7n8ptg8x9n6p7x0bs11a8rkw9 FOREIGN KEY (criador_id) REFERENCES public.usuario(id);


--
-- Name: profissional_especialidade_vinculo fk8a5eremo0d8a2wk103ugyyb45; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_especialidade_vinculo
    ADD CONSTRAINT fk8a5eremo0d8a2wk103ugyyb45 FOREIGN KEY (especialidade_id) REFERENCES public.especialidade(id);


--
-- Name: clinica fkanqpqlfw3fhgf8es22vqab5of; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.clinica
    ADD CONSTRAINT fkanqpqlfw3fhgf8es22vqab5of FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: consulta fkeku8hqbtg77sov7q67nt81074; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fkeku8hqbtg77sov7q67nt81074 FOREIGN KEY (clinica_id) REFERENCES public.clinica(id);


--
-- Name: usuario_grupo_vinculo fkg771d6fl2whmboerscrs2sgq0; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario_grupo_vinculo
    ADD CONSTRAINT fkg771d6fl2whmboerscrs2sgq0 FOREIGN KEY (grupo_id) REFERENCES public.usuario_grupo(id);


--
-- Name: acesso fkh6g3pl8m5532hpiwchblfa8n3; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT fkh6g3pl8m5532hpiwchblfa8n3 FOREIGN KEY (recurso_id) REFERENCES public.recurso(id);


--
-- Name: usuario_grupo_vinculo fkinb0jr0jn2h73e480r3x7bjfd; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.usuario_grupo_vinculo
    ADD CONSTRAINT fkinb0jr0jn2h73e480r3x7bjfd FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: paciente fkkot1yx16akjbjpmbxp47advjb; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT fkkot1yx16akjbjpmbxp47advjb FOREIGN KEY (clinica_id) REFERENCES public.clinica(id);


--
-- Name: acesso fkn0twqkqdabt0m5g5q9oop6hd2; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT fkn0twqkqdabt0m5g5q9oop6hd2 FOREIGN KEY (grupo_id) REFERENCES public.usuario_grupo(id);


--
-- Name: recepcionista fko3gbobbj43dw6r37p4f49ebjm; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.recepcionista
    ADD CONSTRAINT fko3gbobbj43dw6r37p4f49ebjm FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: profissional_especialidade_vinculo fkom6xcr6evevbvfvtnuvmc4au2; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.profissional_especialidade_vinculo
    ADD CONSTRAINT fkom6xcr6evevbvfvtnuvmc4au2 FOREIGN KEY (profissional_id) REFERENCES public.profissional(id);


--
-- Name: diretor fkpju2hubicpb0l2vq8vr9fq34s; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor
    ADD CONSTRAINT fkpju2hubicpb0l2vq8vr9fq34s FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: recepcionista fkppebap8451v2nydf63yoxxb5a; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.recepcionista
    ADD CONSTRAINT fkppebap8451v2nydf63yoxxb5a FOREIGN KEY (clinica_id) REFERENCES public.clinica(id);


--
-- Name: paciente fkpus64vtl67yxnw2kimogd7abx; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT fkpus64vtl67yxnw2kimogd7abx FOREIGN KEY (endereco_id) REFERENCES public.endereco(id);


--
-- Name: diretor_clinica_vinculo fks3hq3jsvkmah9cxnw7dw2rwnc; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor_clinica_vinculo
    ADD CONSTRAINT fks3hq3jsvkmah9cxnw7dw2rwnc FOREIGN KEY (clinica_id) REFERENCES public.clinica(id);


--
-- Name: diretor_clinica_vinculo fktlbxxqr90j3mgm9enpsqj5nw2; Type: FK CONSTRAINT; Schema: public; Owner: xclin
--

ALTER TABLE ONLY public.diretor_clinica_vinculo
    ADD CONSTRAINT fktlbxxqr90j3mgm9enpsqj5nw2 FOREIGN KEY (diretor_id) REFERENCES public.diretor(id);


--
-- Name: TABLE acesso; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.acesso TO xclin;


--
-- Name: SEQUENCE acesso_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.acesso_id_seq TO xclin;


--
-- Name: TABLE clinica; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.clinica TO xclin;


--
-- Name: SEQUENCE clinica_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.clinica_id_seq TO xclin;


--
-- Name: TABLE consulta; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.consulta TO xclin;


--
-- Name: SEQUENCE consulta_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.consulta_id_seq TO xclin;


--
-- Name: TABLE diretor; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.diretor TO xclin;


--
-- Name: TABLE diretor_clinica_vinculo; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.diretor_clinica_vinculo TO xclin;


--
-- Name: SEQUENCE diretor_clinica_vinculo_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.diretor_clinica_vinculo_id_seq TO xclin;


--
-- Name: SEQUENCE diretor_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.diretor_id_seq TO xclin;


--
-- Name: TABLE endereco; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.endereco TO xclin;


--
-- Name: SEQUENCE endereco_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.endereco_id_seq TO xclin;


--
-- Name: TABLE especialidade; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.especialidade TO xclin;


--
-- Name: SEQUENCE especialidade_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.especialidade_id_seq TO xclin;


--
-- Name: TABLE paciente; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.paciente TO xclin;


--
-- Name: SEQUENCE paciente_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.paciente_id_seq TO xclin;


--
-- Name: TABLE profissional; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.profissional TO xclin;


--
-- Name: TABLE profissional_clinica_vinculo; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.profissional_clinica_vinculo TO xclin;


--
-- Name: SEQUENCE profissional_clinica_vinculo_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.profissional_clinica_vinculo_id_seq TO xclin;


--
-- Name: TABLE profissional_especialidade_vinculo; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.profissional_especialidade_vinculo TO xclin;


--
-- Name: SEQUENCE profissional_especialidade_vinculo_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.profissional_especialidade_vinculo_id_seq TO xclin;


--
-- Name: SEQUENCE profissional_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.profissional_id_seq TO xclin;


--
-- Name: TABLE recepcionista; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.recepcionista TO xclin;


--
-- Name: SEQUENCE recepcionista_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.recepcionista_id_seq TO xclin;


--
-- Name: TABLE recurso; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.recurso TO xclin;


--
-- Name: SEQUENCE recurso_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.recurso_id_seq TO xclin;


--
-- Name: TABLE usuario; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.usuario TO xclin;


--
-- Name: TABLE usuario_grupo; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.usuario_grupo TO xclin;


--
-- Name: SEQUENCE usuario_grupo_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.usuario_grupo_id_seq TO xclin;


--
-- Name: TABLE usuario_grupo_vinculo; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON TABLE public.usuario_grupo_vinculo TO xclin;


--
-- Name: SEQUENCE usuario_grupo_vinculo_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.usuario_grupo_vinculo_id_seq TO xclin;


--
-- Name: SEQUENCE usuario_id_seq; Type: ACL; Schema: public; Owner: xclin
--

GRANT ALL ON SEQUENCE public.usuario_id_seq TO xclin;


--
-- xclinQL database dump complete
--

