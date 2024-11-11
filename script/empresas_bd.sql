--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0 (Debian 14.0-1.pgdg110+1)
-- Dumped by pg_dump version 14.0

-- Started on 2024-11-11 17:10:17 UTC

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 32866)
-- Name: direcciones; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.direcciones (
    id bigint NOT NULL,
    calle_avenida character varying(255) NOT NULL,
    ciudad character varying(255) NOT NULL,
    numero character varying(255) NOT NULL
);


ALTER TABLE public.direcciones OWNER TO root;

--
-- TOC entry 209 (class 1259 OID 32865)
-- Name: direcciones_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.direcciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.direcciones_id_seq OWNER TO root;

--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 209
-- Name: direcciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.direcciones_id_seq OWNED BY public.direcciones.id;


--
-- TOC entry 211 (class 1259 OID 32874)
-- Name: empresas; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.empresas (
    id character varying(255) NOT NULL,
    digito_verificador character varying(255) NOT NULL,
    fecha_insercion date NOT NULL,
    numero_rut bigint NOT NULL,
    razon_social character varying(255) NOT NULL
);


ALTER TABLE public.empresas OWNER TO root;

--
-- TOC entry 213 (class 1259 OID 32882)
-- Name: trabajadores; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.trabajadores (
    id bigint NOT NULL,
    apellido_materno character varying(255),
    apellido_paterno character varying(255) NOT NULL,
    digito_verificador character varying(255) NOT NULL,
    fecha_insercion date,
    nombre character varying(255) NOT NULL,
    numero_rut bigint NOT NULL,
    direccion_id bigint,
    empresa_id character varying(255) NOT NULL
);


ALTER TABLE public.trabajadores OWNER TO root;

--
-- TOC entry 212 (class 1259 OID 32881)
-- Name: trabajadores_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.trabajadores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trabajadores_id_seq OWNER TO root;

--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 212
-- Name: trabajadores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.trabajadores_id_seq OWNED BY public.trabajadores.id;


--
-- TOC entry 3176 (class 2604 OID 32869)
-- Name: direcciones id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.direcciones ALTER COLUMN id SET DEFAULT nextval('public.direcciones_id_seq'::regclass);


--
-- TOC entry 3177 (class 2604 OID 32885)
-- Name: trabajadores id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.trabajadores ALTER COLUMN id SET DEFAULT nextval('public.trabajadores_id_seq'::regclass);


--
-- TOC entry 3335 (class 0 OID 32866)
-- Dependencies: 210
-- Data for Name: direcciones; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.direcciones (id, calle_avenida, ciudad, numero) FROM stdin;
\.


--
-- TOC entry 3336 (class 0 OID 32874)
-- Dependencies: 211
-- Data for Name: empresas; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.empresas (id, digito_verificador, fecha_insercion, numero_rut, razon_social) FROM stdin;
\.


--
-- TOC entry 3338 (class 0 OID 32882)
-- Dependencies: 213
-- Data for Name: trabajadores; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.trabajadores (id, apellido_materno, apellido_paterno, digito_verificador, fecha_insercion, nombre, numero_rut, direccion_id, empresa_id) FROM stdin;
\.


--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 209
-- Name: direcciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.direcciones_id_seq', 1, false);


--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 212
-- Name: trabajadores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.trabajadores_id_seq', 1, false);


--
-- TOC entry 3179 (class 2606 OID 32873)
-- Name: direcciones direcciones_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.direcciones
    ADD CONSTRAINT direcciones_pkey PRIMARY KEY (id);


--
-- TOC entry 3183 (class 2606 OID 32880)
-- Name: empresas empresas_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT empresas_pkey PRIMARY KEY (id);


--
-- TOC entry 3190 (class 2606 OID 32889)
-- Name: trabajadores trabajadores_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.trabajadores
    ADD CONSTRAINT trabajadores_pkey PRIMARY KEY (id);


--
-- TOC entry 3185 (class 2606 OID 32891)
-- Name: empresas uk_7tsf44ll4fi4bkavloourieim; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT uk_7tsf44ll4fi4bkavloourieim UNIQUE (numero_rut);


--
-- TOC entry 3192 (class 2606 OID 32893)
-- Name: trabajadores uk_j2bg8hr8dnyck09f2lhg606p4; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.trabajadores
    ADD CONSTRAINT uk_j2bg8hr8dnyck09f2lhg606p4 UNIQUE (numero_rut);


--
-- TOC entry 3180 (class 1259 OID 32912)
-- Name: empresas_ix1; Type: INDEX; Schema: public; Owner: root
--

CREATE INDEX empresas_ix1 ON public.empresas USING btree (id);


--
-- TOC entry 3181 (class 1259 OID 32913)
-- Name: empresas_ix2; Type: INDEX; Schema: public; Owner: root
--

CREATE INDEX empresas_ix2 ON public.empresas USING btree (numero_rut);


--
-- TOC entry 3186 (class 1259 OID 32909)
-- Name: trabajadores_ix1; Type: INDEX; Schema: public; Owner: root
--

CREATE INDEX trabajadores_ix1 ON public.trabajadores USING btree (id);


--
-- TOC entry 3187 (class 1259 OID 32910)
-- Name: trabajadores_ix2; Type: INDEX; Schema: public; Owner: root
--

CREATE INDEX trabajadores_ix2 ON public.trabajadores USING btree (numero_rut);


--
-- TOC entry 3188 (class 1259 OID 32911)
-- Name: trabajadores_ix3; Type: INDEX; Schema: public; Owner: root
--

CREATE INDEX trabajadores_ix3 ON public.trabajadores USING btree (empresa_id);


--
-- TOC entry 3193 (class 2606 OID 32894)
-- Name: trabajadores fkddnadq88g6k63nxu68g2elk3g; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.trabajadores
    ADD CONSTRAINT fkddnadq88g6k63nxu68g2elk3g FOREIGN KEY (direccion_id) REFERENCES public.direcciones(id);


--
-- TOC entry 3194 (class 2606 OID 32899)
-- Name: trabajadores fkjjjbdr4kuhrdiq5houakgb0pe; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.trabajadores
    ADD CONSTRAINT fkjjjbdr4kuhrdiq5houakgb0pe FOREIGN KEY (empresa_id) REFERENCES public.empresas(id);


-- Completed on 2024-11-11 17:10:18 UTC

--
-- PostgreSQL database dump complete
--

