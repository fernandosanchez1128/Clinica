DROP  TABLE IF EXISTS  Areas  CASCADE;
DROP  TABLE IF EXISTS  Cama CASCADE;
DROP  TABLE IF EXISTS  Persona CASCADE;
DROP  TABLE IF EXISTS  Medicamento CASCADE;
DROP  TABLE IF EXISTS  Campanna CASCADE;
DROP  TABLE IF EXISTS  Empleado CASCADE;
DROP  TABLE IF EXISTS  Paciente CASCADE;
DROP  TABLE IF EXISTS  Medico CASCADE;
DROP  TABLE IF EXISTS  Enfermera CASCADE;
DROP  TABLE IF EXISTS  Habilidades_enfermera CASCADE;
DROP  TABLE IF EXISTS  Historia CASCADE;
DROP  TABLE IF EXISTS  Cita CASCADE;
DROP  TABLE IF EXISTS  Causa CASCADE;
DROP  TABLE IF EXISTS  Paciente_Cama CASCADE;
DROP  TABLE IF EXISTS  paciente_Campanna CASCADE;
DROP  TABLE IF EXISTS  Registro CASCADE;
DROP  TABLE IF EXISTS  Medicacion CASCADE;


--Areas(cod_area , nombre, descripcion)
DROP SEQUENCE areas_seq;
CREATE SEQUENCE areas_seq;

CREATE TABLE Areas(
cod_area VARCHAR(7) NOT NULL PRIMARY KEY, 
nombre VARCHAR(50) UNIQUE, 
descripcion TEXT );
--area999
ALTER TABLE Areas ALTER cod_area SET DEFAULT nextval('areas_seq');
-----------------------------------------------------------------------

--Cama(cod_cama, cod_area (fk­>area), descripcion, estado)
DROP SEQUENCE cama_seq;
CREATE SEQUENCE cama_seq;
CREATE TABLE cama(
cod_cama VARCHAR(8) NOT NULL PRIMARY KEY, 
cod_area VARCHAR(7) NOT NULL, 
descripcion TEXT, 
estado VARCHAR(15), 
CONSTRAINT fk_cama FOREIGN KEY (cod_area) references areas(cod_area) 
ON UPDATE NO ACTION ON DELETE NO ACTION ); 
--cama9999
ALTER TABLE cama ALTER cod_cama SET DEFAULT nextval('cama_seq');
-----------------------------------------------------------------------

--Persona(id, nombre, direccion, telefono)
CREATE TABLE persona(
id VARCHAR (15) NOT NULL PRIMARY KEY,
nombre VARCHAR  (20),
direccion VARCHAR  (20), 
telefono VARCHAR  (10) );

-----------------------------------------------------------------------

--Medicamento(cod_medicamento, nombre, descripcion, costo)
DROP SEQUENCE medicamento_seq;
CREATE SEQUENCE medicamento_seq;

CREATE TABLE Medicamento(
cod_medicamento VARCHAR (15) NOT NULL PRIMARY KEY, 
nombre VARCHAR  (20) UNIQUE , 
descripcion TEXT, 
costo INTEGER );

ALTER TABLE Medicamento ALTER cod_medicamento SET DEFAULT nextval('medicamento_seq');
--Empleado(id_empleado (fk­>persona), cargo, salario, email, id_jefe(fk­>empleado), cod_area)
-----------------------------------------------------------------------

CREATE TABLE Empleado(
id_empleado VARCHAR (15) NOT NULL PRIMARY KEY, 
cargo VARCHAR  (20), 
salario INTEGER, 
email VARCHAR  (35),
 id_jefe VARCHAR  (15),
 CONSTRAINT Empleado_fk1 FOREIGN KEY (id_empleado)
 REFERENCES Persona (id) 
ON DELETE NO ACTION,
 CONSTRAINT Empleado_fk2 FOREIGN KEY (id_jefe)
 REFERENCES Empleado (id_empleado) 
ON DELETE NO ACTION );
-----------------------------------------------------------------------

--Paciente(id_paciente (fk ­>persona), num_seguridad_social, fecha_nac, actividad_economica)
CREATE TABLE Paciente(
id_paciente VARCHAR (15) NOT NULL PRIMARY KEY, 
num_seguridad_social VARCHAR  (20), 
fecha_nac date, 
actividad_economica VARCHAR  (20),
 CONSTRAINT Paciente_fk FOREIGN KEY (id_paciente)
 REFERENCES Persona (id) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Medico (id_medico (fk­>persona) , especialidad, num_licencia, universidad)
CREATE TABLE Medico(
id_medico VARCHAR (15) NOT NULL PRIMARY KEY, 
especialidad VARCHAR  (20), 
num_licencia  VARCHAR (20), 
universidad VARCHAR  (20),
CONSTRAINT Medico_fk FOREIGN KEY (id_medico)
REFERENCES Persona (id) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Campanna(cod_campanna, nombre, objetivo, fecha_realizacion, id_medico (fk­>medico))
DROP SEQUENCE campanna_seq;
CREATE SEQUENCE campanna_seq;

CREATE TABLE Campanna(
cod_campanna VARCHAR (15) NOT NULL PRIMARY KEY, 
nombre VARCHAR (20) UNIQUE, 
objetivo TEXT, 
fecha_realizacion DATE,
 id_medico VARCHAR  (20) NOT NULL, 
 CONSTRAINT Campanna_fk FOREIGN KEY (id_medico)
 REFERENCES Medico (id_medico) 
ON DELETE NO ACTION );

ALTER TABLE Campanna ALTER cod_campanna SET DEFAULT nextval('campanna_seq');

-----------------------------------------------------------------------

--Enfermera (id_enfermera (fk­>persona) , experiencia, cod_area (fk­>area))
CREATE TABLE Enfermera(
id_enfermera VARCHAR (15) NOT NULL PRIMARY KEY, 
experiencia VARCHAR  (20), 
cod_area  VARCHAR (20) NOT NULL,
 CONSTRAINT Enfermera_fk FOREIGN KEY (cod_area)
 REFERENCES Areas (cod_area) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Habilidades_enfermera( id_enfermera (fk­> persona), habilidad)
CREATE TABLE Habilidades_enfermera(
id_enfermera VARCHAR (15) NOT NULL, 
habilidad VARCHAR  (20), 
CONSTRAINT  habilidades_enfermera_pk PRIMARY KEY (id_enfermera, habilidad), 
CONSTRAINT Habilidades_enfermera_fk FOREIGN KEY (id_enfermera)
REFERENCES Enfermera(id_enfermera) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Historia ( cod_historia, id_paciente (fk­>paciente), fecha_apertura)
DROP SEQUENCE historia_seq;
CREATE SEQUENCE historia_seq;

CREATE TABLE Historia(
cod_historia VARCHAR (20) NOT NULL PRIMARY KEY, 
id_paciente VARCHAR  (20) NOT NULL, 
fecha_apertura DATE,
 CONSTRAINT Historia_fk FOREIGN KEY (id_paciente)
 REFERENCES Paciente(id_paciente) 
ON DELETE NO ACTION );

ALTER TABLE Historia ALTER cod_historia SET DEFAULT nextval('historia_seq');

-----------------------------------------------------------------------

--Cita ( id_paciente (fk­>persona), id_medico (fk­>persona) ,hora, fecha, tipo,costo)
CREATE TABLE Cita(
id_paciente VARCHAR (20) NOT NULL, 
id_medico VARCHAR  (20) NOT NULL, 
hora TIME NOT NULL,
fecha DATE NOT NULL,
tipo VARCHAR (30),
costo INTEGER,
 CONSTRAINT cita_pk PRIMARY KEY (id_paciente, id_medico, hora, fecha),
 CONSTRAINT cita_fk1 FOREIGN KEY (id_paciente)
 REFERENCES Paciente(id_paciente) 
 ON DELETE NO ACTION,
 CONSTRAINT cita_fk2 FOREIGN KEY (id_medico)
 REFERENCES Medico(id_medico) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Causa( codigo_causa, nombre, descripcion)
DROP SEQUENCE causa_seq;
CREATE SEQUENCE causa_seq;

CREATE TABLE Causa(
codigo_causa VARCHAR (10) NOT NULL PRIMARY KEY, 
nombre VARCHAR  (50), 
descripcion TEXT );

ALTER TABLE Causa ALTER codigo_causa SET DEFAULT nextval('causa_seq');

-----------------------------------------------------------------------
--Paciente_Cama(id_paciente (fk­>persona), cod_cama (fk­>cama), fecha)
CREATE TABLE Paciente_Cama(
id_paciente VARCHAR (20) NOT NULL, 
cod_cama VARCHAR  (8) NOT NULL, 
fecha TIMESTAMP,
 CONSTRAINT Paciente_Cama_pk PRIMARY KEY (id_paciente, cod_cama, fecha),
 CONSTRAINT Paciente_Cama_fk1 FOREIGN KEY (id_paciente)
 REFERENCES Paciente(id_paciente) 
 ON DELETE NO ACTION,
 CONSTRAINT Paciente_Cama_fk2 FOREIGN KEY (cod_cama)
 REFERENCES Cama(cod_cama) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Paciente_Campanna( id_paciente (fk­>persona), cod_campanna (fk­> campanna))
CREATE TABLE Paciente_Campanna(
id_paciente VARCHAR (20) NOT NULL, 
cod_campanna VARCHAR  (15) NOT NULL, 
fecha DATE,
 CONSTRAINT Paciente_Campanna_pk PRIMARY KEY (id_paciente, cod_campanna),
 CONSTRAINT Paciente_Campanna_fk1 FOREIGN KEY (id_paciente)
 REFERENCES Paciente(id_paciente) 
 ON DELETE NO ACTION,
 CONSTRAINT Paciente_Campanna_fk2 FOREIGN KEY (cod_campanna)
 REFERENCES Campanna(cod_campanna) 
ON DELETE NO ACTION );

-----------------------------------------------------------------------

--Registro (id_medico (fk­>persona), cod_historia (fk­>historia), cod_causa
--(fk­>causa))

CREATE TABLE Registro(
id_medico VARCHAR (20) NOT NULL,
cod_historia  VARCHAR (20) NOT NULL,
codigo_causa VARCHAR  (10) NOT NULL,
CONSTRAINT Registro_pk PRIMARY KEY (id_medico, cod_historia,codigo_causa),
CONSTRAINT Registro_fk1 FOREIGN KEY (id_medico)
REFERENCES Medico(id_medico) 
ON DELETE NO ACTION,
CONSTRAINT Registro_fk2 FOREIGN KEY (cod_historia)
REFERENCES Historia(cod_historia) 
ON DELETE NO ACTION,
CONSTRAINT Registro_fk3 FOREIGN KEY (codigo_causa)
REFERENCES Causa(codigo_causa) 
ON DELETE NO ACTION);

-----------------------------------------------------------------------

--Medicacion (id_medico (fk­->persona), cod_historia (fk­->historia), cod_medicamento
--(fk­->medicamento), cantidad, fecha, precio)

CREATE TABLE Medicacion (
id_medico VARCHAR (20) NOT NULL, 
cod_historia VARCHAR  (10) NOT NULL,
cod_medicamento  VARCHAR (15) NOT NULL,
cantidad INTEGER,
fecha DATE,
precio double precision,
CONSTRAINT Medicacion_pk PRIMARY KEY (id_medico, cod_medicamento, cod_historia),
CONSTRAINT Medicacion_fk1 FOREIGN KEY (id_medico)
REFERENCES Medico(id_medico) 
ON DELETE NO ACTION,

CONSTRAINT Medicacion_fk2 FOREIGN KEY (cod_historia)
REFERENCES Historia(cod_historia) 
ON DELETE NO ACTION,

CONSTRAINT Registro_fk3 FOREIGN KEY (cod_medicamento)
REFERENCES Medicamento(cod_medicamento) 
ON DELETE NO ACTION);

-----------------------------------------------------------------------

