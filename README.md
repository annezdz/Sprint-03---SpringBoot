# Sprint 03 SpringBoot

## Desafio proposto

![img.png](img.png)
![img_1.png](img_1.png)

### Execução 

A API utiliza o banco H2, utilizei a configuração padrão do mesmo no arquivo 
.properties, conforme imagem.

![img_2.png](img_2.png)

### POST Body
```json
{ 
"nome": "Paraná", 
"regiao": "Sul", 
"populacao": 100990.000, 
"capital": "Curitiba", 
"area": 528880.797 
}

```
### Validações

A API não permite registros duplicados (por nome), não registra em caso de erro ortográfico ou
em caso de estar faltando algum atributo no json entre outras, conforme abaixo.

![img_6.png](img_6.png)
![img_7.png](img_7.png)


### Filtrando por estado

![img_8.png](img_8.png)