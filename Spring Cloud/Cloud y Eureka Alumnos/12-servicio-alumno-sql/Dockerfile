FROM openjdk:11
VOLUME /tmp
ADD ./target/12-servicio-alumno-sql-V1.jar servicio-alumno-sql.jar
ENTRYPOINT ["java","-jar","/servicio-alumno.jar"]

##FROM openjdk:17.0.2
##WORKDIR /app
##COPY ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar .
##EXPOSE 8001
##ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]
##CONSTRUIR IMAGEN CON 
#docker build . 
#probar crear imagen con 
##docker build . -t alumnos-sql
##para ver si la crea con ese nombre