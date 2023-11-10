# Carbon Footprint Reduction

## Dépendances

- Linux ou MacOS
- Node (v18)
- NPM (v8)
- Java (v17)
- Maven (v3.6)
- Docker (optionnel)
- Docker-compose (optionnel)

## Démarrage simple de l'application

### Démarrage avec Docker

Pour lancer l'application avec Docker, suivez ces étapes :

1. Assurez-vous que Docker et Docker compose sont installés sur votre système.

2. Exécutez le script `run.sh` pour lancer l'application (CTRL+C pour arrêter) :

```bash 
./run.sh
```

L'application sera disponible via un navigateur sur le port 3000 : [http://localhost:3000]


### Démarrage sans Docker

Si vous préférez démarrer l'application sans Docker, suivez ces étapes :

1. Créez une base de données MySQL sur votre système.

2. Modifier le fichier `src/server/src/main/resources/application.properties` pour qu'il corresponde à votre base de données.

3. Exécutez le script `run.sh` pour lancer l'application (CTRL+C pour arrêter) :

```bash 
./run.sh
```

L'application sera disponible via un navigateur sur le port 3000 : [http://localhost:3000]


## Démarrage séparé

1. Dans un premier terminal, lancer la base de données avec la commande (Si vous voulez utiliser Docker pour la base de données, sinon vous pouvez créer une base de données Mysql sur votre système et modifier le fichier `src/server/src/main/resources/application.properties` pour qu'il corresponde à votre base de données) : 

```bash 
./run_database.sh
```
2. Dans un deuxième terminal, lancer le serveur avec la commande : 

```bash 
./run_server.sh
```

3. Dans un troisième terminal, lancer le serveur avec la commande : 

```bash
./run/run_client.sh
```

L'application sera disponible via un navigateur sur le port 3000 : [http://localhost:3000]
