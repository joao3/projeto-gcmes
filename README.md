# projeto-gcmes

## Introdução
Projeto desenvolvido para a disciplina SSC0535 - Gerência de Configuração, Manutenção e Evolução de Software.
Sistema utilizado: https://github.com/stars/dupradosantini/lists/sos-tool.

## Grupo

Grupo 6

- Eduardo Maciel de Matos
- João Otávio da Silva
- Leonardo Gonçalves Chahud
- Rafael Jun Teramae Dantas

## Para rodar o sistema
### Backend
Com java instalado, na pasta sostool-backend-master, execute:
```
./mvnw compile
./mvnw package
java -jar target/sostool-backend-0.0.1-SNAPSHOT.jar
```

### Frontend
Com node e npm instalados, na pasta sostool-frontend-master, execute:
```
npm install
npm run start
```
## Para rodar o docker
Utiliza docker e docker-buildx.
### Backend
Na pasta sostool-backend-master, execute:

```
docker buildx build -t sos-backend .
docker run -d --name sos-backend -p 8081:8081 sos-backend
```

### Frontend
Na pasta sostool-frontend-master, execute:
```
docker buildx build -t sos-frontend .
docker run -d --name sos-frontend -p 4200:4200 sos-frontend
```
