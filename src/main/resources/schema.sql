CREATE DATABASE IF NOT EXISTS nominas;
USE nominas;

CREATE TABLE Empleados (
                           dni       CHAR(9)     PRIMARY KEY,
                           nombre    VARCHAR(20) NOT NULL,
                           sexo      CHAR(1)     NOT NULL CHECK (sexo IN ('f','m')),
                           categoria TINYINT     NOT NULL CHECK (categoria BETWEEN 1 AND 10),
                           anyos     INT         NOT NULL CHECK (anyos >= 0)
);

CREATE TABLE Nominas (
                         dni    CHAR(9) PRIMARY KEY,
                         sueldo INT     NOT NULL,
                         FOREIGN KEY (dni) REFERENCES Empleados(dni)
                             ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Empleados (dni, nombre, sexo, categoria, anyos) VALUES
                                                                ('32000032G','James Cosling','m',9,4),
                                                                ('32000031R','Ada Lovelace','f',1,3);