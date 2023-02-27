-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-02-2023 a las 17:48:24
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ejercicio_examenes`
--
CREATE DATABASE IF NOT EXISTS `ejercicio_examenes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ejercicio_examenes`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
CREATE TABLE IF NOT EXISTS `alumnos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `foto` longblob DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `alumnos`:
--

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id`, `apellido`, `create_at`, `email`, `foto`, `nombre`) VALUES
(1, 'Apellido0', '2023-02-22 06:45:48.000000', 'Email0@mail.com', NULL, 'Nombre0'),
(2, 'Apellido1', '2023-02-22 06:46:06.000000', 'Email0@mail.com', NULL, 'Nombre1'),
(3, 'Apellido2', '2023-02-22 06:46:57.000000', 'Email0@mail2.com', NULL, 'Nombre2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignaturas`
--

DROP TABLE IF EXISTS `asignaturas`;
CREATE TABLE IF NOT EXISTS `asignaturas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `padre_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1a657vrlox5uthk8wbwrxh6e8` (`padre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `asignaturas`:
--   `padre_id`
--       `asignaturas` -> `id`
--

--
-- Volcado de datos para la tabla `asignaturas`
--

INSERT INTO `asignaturas` (`id`, `nombre`, `padre_id`) VALUES
(1, 'Matemáticas', NULL),
(2, 'Lenguaje', NULL),
(3, 'Inglés', NULL),
(4, 'Ciencias Naturales', NULL),
(5, 'Ciencias Sociales y Historia', NULL),
(6, 'Música', NULL),
(7, 'Artes', NULL),
(8, 'Algebra', 1),
(9, 'Aritmética', 1),
(10, 'Trigonometría', 1),
(11, 'Lectura y comprensión', 2),
(12, 'Verbos', 2),
(13, 'Gramática', 2),
(14, 'Inglés', 3),
(15, 'Gramática', 3),
(16, 'Verbos', 3),
(17, 'Ciencias Naturales', 4),
(18, 'Biología', 4),
(19, 'Física', 4),
(20, 'Quimica', 4),
(21, 'Historia', 5),
(22, 'Ciencias Sociales', 5),
(23, 'Filosofía', 5),
(24, 'Música', 6),
(25, 'Artes', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `cursos`:
--

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id`, `create_at`, `nombre`) VALUES
(1, '2023-02-22 06:46:30.000000', 'NombreCurso0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos_alumnos`
--

DROP TABLE IF EXISTS `cursos_alumnos`;
CREATE TABLE IF NOT EXISTS `cursos_alumnos` (
  `Curso_id` bigint(20) NOT NULL,
  `alumnos_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_pgxvghn8cixqplaxq8dny0gnu` (`alumnos_id`),
  KEY `FKbiq3ch1xy5rj1i6v3ewnwa0nm` (`Curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `cursos_alumnos`:
--   `alumnos_id`
--       `alumnos` -> `id`
--   `Curso_id`
--       `cursos` -> `id`
--

--
-- Volcado de datos para la tabla `cursos_alumnos`
--

INSERT INTO `cursos_alumnos` (`Curso_id`, `alumnos_id`) VALUES
(1, 1),
(1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos_examenes`
--

DROP TABLE IF EXISTS `cursos_examenes`;
CREATE TABLE IF NOT EXISTS `cursos_examenes` (
  `Curso_id` bigint(20) NOT NULL,
  `examenes_id` bigint(20) NOT NULL,
  KEY `FK6ags9h8g0q074pch8ckfy8nw5` (`examenes_id`),
  KEY `FKiitvj2kfff8c7ipao49fmpw1r` (`Curso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `cursos_examenes`:
--   `examenes_id`
--       `examenes` -> `id`
--   `Curso_id`
--       `cursos` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `examenes`
--

DROP TABLE IF EXISTS `examenes`;
CREATE TABLE IF NOT EXISTS `examenes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `asignatura_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ti4mhut3mays6044rt8syqd8` (`asignatura_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `examenes`:
--   `asignatura_id`
--       `asignaturas` -> `id`
--

--
-- Volcado de datos para la tabla `examenes`
--

INSERT INTO `examenes` (`id`, `create_at`, `nombre`, `asignatura_id`) VALUES
(1, '2023-02-22 07:27:58.000000', 'Examen1', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
CREATE TABLE IF NOT EXISTS `preguntas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `texto` varchar(255) DEFAULT NULL,
  `examen_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9hlw51x7hfqs1tv3sviwqycqi` (`examen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `preguntas`:
--   `examen_id`
--       `examenes` -> `id`
--

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`id`, `texto`, `examen_id`) VALUES
(1, 'texto del examen1 pregunta1', 1),
(2, 'texto del examen1 pregunta2', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
CREATE TABLE IF NOT EXISTS `respuestas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `texto` varchar(255) DEFAULT NULL,
  `alumno_id` bigint(20) DEFAULT NULL,
  `pregunta_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmcisxf21kv5io47mvg6b1l7nm` (`alumno_id`),
  KEY `FKptndav2td8pqi7e51ihgq95gp` (`pregunta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `respuestas`:
--   `alumno_id`
--       `alumnos` -> `id`
--   `pregunta_id`
--       `preguntas` -> `id`
--

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD CONSTRAINT `FK1a657vrlox5uthk8wbwrxh6e8` FOREIGN KEY (`padre_id`) REFERENCES `asignaturas` (`id`);

--
-- Filtros para la tabla `cursos_alumnos`
--
ALTER TABLE `cursos_alumnos`
  ADD CONSTRAINT `FK99m7xtdwkc4l2hr8ie8urri1o` FOREIGN KEY (`alumnos_id`) REFERENCES `alumnos` (`id`),
  ADD CONSTRAINT `FKbiq3ch1xy5rj1i6v3ewnwa0nm` FOREIGN KEY (`Curso_id`) REFERENCES `cursos` (`id`);

--
-- Filtros para la tabla `cursos_examenes`
--
ALTER TABLE `cursos_examenes`
  ADD CONSTRAINT `FK6ags9h8g0q074pch8ckfy8nw5` FOREIGN KEY (`examenes_id`) REFERENCES `examenes` (`id`),
  ADD CONSTRAINT `FKiitvj2kfff8c7ipao49fmpw1r` FOREIGN KEY (`Curso_id`) REFERENCES `cursos` (`id`);

--
-- Filtros para la tabla `examenes`
--
ALTER TABLE `examenes`
  ADD CONSTRAINT `FK6ti4mhut3mays6044rt8syqd8` FOREIGN KEY (`asignatura_id`) REFERENCES `asignaturas` (`id`);

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `FK9hlw51x7hfqs1tv3sviwqycqi` FOREIGN KEY (`examen_id`) REFERENCES `examenes` (`id`);

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `FKmcisxf21kv5io47mvg6b1l7nm` FOREIGN KEY (`alumno_id`) REFERENCES `alumnos` (`id`),
  ADD CONSTRAINT `FKptndav2td8pqi7e51ihgq95gp` FOREIGN KEY (`pregunta_id`) REFERENCES `preguntas` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
