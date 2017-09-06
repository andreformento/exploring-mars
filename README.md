[![Build Status](https://travis-ci.org/andreformento/exploring-mars.svg?branch=master)](https://travis-ci.org/andreformento/exploring-mars) [![codecov](https://codecov.io/gh/andreformento/exploring-mars/branch/master/graph/badge.svg)](https://codecov.io/gh/andreformento/exploring-mars) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b205e4b7bc0f440db0b53dbddd563dba)](https://www.codacy.com/app/andreformento/exploring-mars?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=andreformento/exploring-mars&amp;utm_campaign=Badge_Grade)

# Explorando Marte

## Objetivo

É necessário desenvolver um sistema que consiga gerenciar sondas para explorar Marte.

## Como funciona?

O contexto da aplicação se dá no momento em que sondas enviadas da Terra chegam em Marte.

Essas *sondas* podem se mover para frente e girar para os lados. Elas têm o objetivo de *explorar Marte*.

Há uma limitação em que as sondas podem se mover dentro do planeta que é determinado por uma *área retangular*.

Existe uma *estação espacial* que fica próximo de Marte para gerenciar a movimentação das sondas e garantir que elas se movimentem de forma adequada. Desta forma, os sinais enviados da Terra chegam até esta estação espacial e de lá que são enviados os *comandos* para as sondas.

## Documentação da API

## Implantar sonda
Enviar uma sonda para Marte informando em que localização ela vai ser implantanda.
```
POST /spacials-stations/ground-probes
```
Exemplo
```
curl -X POST 'https://exploringmars.herokuapp.com/spacials-stations/ground-probes' \
-H 'Content-Type: application/json' \
-d '
{
    "x": 1,
    "y": 2,
    "navigationSense": "N",
    "driveCommands": ["L","M","L","M","L","M","L","M","M"]
}
'
```

## Listar sondas implantadas
Listar todas as sondas já implantadas em Marte.
```
GET /spacials-stations/ground-probes
```
Exemplo
```
curl -X GET 'https://exploringmars.herokuapp.com/spacials-stations/ground-probes'
```

## Recuperar sonda por ID

```
GET /spacials-stations/ground-probes/{id}
```
Exemplo
```
curl -X GET 'https://exploringmars.herokuapp.com/spacials-stations/ground-probes/some-uuid'
```

## Controlar sonda
Enviar comandos para que a sonda se movimente.
```
PUT /spacials-stations/ground-probes/{x}/{y}/explore-planet-by-position
```
Exemplo
```
curl -X PUT 'https://exploringmars.herokuapp.com/spacials-stations/ground-probes/1/2/explore-planet-by-position' \
-H 'Content-Type: application/json' \
-d '["M","L","L","M","R"]'
```

## Jornada
Implantar uma nova sonda com comandos para que ela faça uma jornada por Marte.
```
POST /spacials-stations/ground-probes/explore-planet-by-jorney
```
Exemplo
```
curl -X POST 'https://exploringmars.herokuapp.com/spacial-station/explore-planet-by-jorney' \
-H 'Content-Type: application/json' \
-d '
{
    "x": 3,
    "y": 3,
    "navigationSense": "E",
    "driveCommands": ["M","M","R","M","M","R","M","R","R","M"]
}
'
```

## Como rodar a aplicação

### Requisitos
- Java 8
- Gradle
- Docker *(opcional)*

### Rodar local
```
gradle bootRun
```

### Rodar com docker (local)
```
gradle buildDocker
docker run -d -p 8080:8080 andreformento/exploring-mars
```

### Rodar com docker (remoto)
Neste caso, não é necessário fazer nenhum build local e é necessário apenas o Docker instalado *(não precisa nem de Java)*.

Você pode irá usar a imagem pronta do [dockerhub](https://hub.docker.com/r/andreformento/exploring-mars):
```
docker run -d -p 8080:8080 andreformento/exploring-mars
```
