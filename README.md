# Carbon Footprint Reduction 

## Dépendances

- Linux ou MacOS
- Node (v18)
- NPM (v8)
- Java (v17)
- Maven (v3.6)
- Docker 
- Docker-compose


## Démarrage simple de l'application

Pour simplement lancer l'application, il faut lancer le script suivant (CTRL+C pour stopper) :

```bash
./start_all.sh
```

L'application sera disponible via un navigateur sur le port 3000 : [http://localhost:3000]

## Démarrage séparé

Dans un premier terminal, lancer le serveur avec la commande (il faut lancer la docker compose avant):

```bash
./run/run_server.sh
```

Dans un deuxième terminal, lancer le serveur avec la commande :

```bash
./run/run_client.sh
```