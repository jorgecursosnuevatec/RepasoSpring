package com.jgr.commons.modelo.datos;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


//es un proyecto de libreria,no se va a ejecutar
//deshabilitamos la autoconfiguracion de la conexion a la bbdd
@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class CommonsModeloDatosApplication {

	

}
