# Carbon Footprint Reduction 

## Dépendances

- Linux ou MacOS
- Node (v18)
- NPM (v8)
- Java (v17)
- Maven (v3.6)
- Docker 
- Docker-compose

## Configuration de l'application

Pour configurer la base de données, il faut modifier le fichier `./server/src/main/resources/application.properties` en remplaçant les valeurs par défaut par les valeurs souhaitées.

Pour utilisé l'image de docker de la base de données, il faut modifier le fichier `./server/src/main/resources/application.properties` en remplaçant les valeurs par défaut par les valeurs suivantes :

```properties
spring.datasource.url=jdbc:mysql://localhost:3308/db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```
Puis lancer la commande suivante :

```bash
    docker-compose up 
```

## Démarrage simple de l'application

Pour simplement lancer l'application, il faut lancer le script suivant (CTRL+C pour stopper) :

```bash
./start_all.sh
```

L'application sera disponible via un navigateur sur le port 3000 : [http://localhost:3000]

## Démarrage séparé

Dans un premier terminal, lancer le serveur avec la commande :

```bash
./run/run_server.sh
```

Dans un deuxième terminal, lancer le serveur avec la commande :

```bash
./run/run_client.sh
```