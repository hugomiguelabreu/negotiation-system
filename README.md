# negotiation-system
Trabalho prático de Paradigmas de Sistemas Distribuídos | Sistema de Negociação
# Enunciado do trabalho prático
[Consultar aqui o enunciado do trabalho prático.](./enunciado.pdf)
# Execução
### Erlang
Para executar o front-end server:
```
rebar3 shell
1> frontServer_app:start().
```
### Servidor REST
Compilar o servidor:
```
mvn package
```
Para executar o servidor:
```
java -jar ./target/REST-server-1.0-SNAPSHOT.jar server
```
O servidor encontra-se a responder em localhost:8080.
### Exchange
A exchange é dinâmica e, por isso recebe como argumento o mercado a executar:
```
args: PSI20, NASDAQ
```
### Cliente
O cliente funciona sem qualquer tipo de condiguração.
