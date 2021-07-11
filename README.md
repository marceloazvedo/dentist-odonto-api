# Microserviço: dentist-odonto-api
Microserviço que faz parte de um ecosistema de microserviços, em que esse é responsável pelo cadastro de dentistas.

# Requisitos:
- Java 14+ (você pode usar o [Jabba](https://github.com/shyiko/jabba) para gerenciar versões do Java);
- Dynamo DB (leia a documentação a respeito [aqui](https://aws.amazon.com/pt/dynamodb/));
- Intellij Community Edition (IDE sugerida, pode ser baixada [aqui](https://www.jetbrains.com/pt-br/idea/download/#section=linux).

# Configurando o projeto
Antes de configurar o projeto na sua IDE, é importante configurar o Dynamo DB para que o projeto se conecte a ela, para isso siga as instruções abaixo.

## Inicializando o Dynamo DB
Para o projeto rodar ele precisa se conectar ao Dynamo DB, então precisamos iniciar uma instancia do Dynamo DB em nossa máquina antes de inicializar o projeto.

Temos duas formas de startar o Dynamo DB, via docker e via "jar". Vamos a cada uma delas:

### Docker e docker-compose
> Atenção: Para incializar o dynamo db via docker você precisa ter o docker e docker-compose instalado. Você pode [seguir esse tutorial para instalar o docker](https://docs.docker.com/engine/install/ubuntu/) e [esse tutorial para instalar o docker-compose](https://docs.docker.com/compose/install/).

Navegue pelo terminal até a paster docker do projeto e dentro dela execute o seguinte comando:
```
docker-compose up --build
```
Isso irá inicializar umas instancia do Dynamo DB na porta 8000 e você já vai poder inicializar o projeto.

### Jar
Para inicialiar o Dynamo DB através do Jar, vá até [esse link](https://docs.aws.amazon.com/pt_br/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html) e selecione a região de sua escolha (sugiro a US West (Oregon) Region) e baixe o zip com o Jar.

Depois disso, extraia o zip e com o terminal aberto na pasta que foi extraída, execute o seguinte comando:
```
java -Djava.libray.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```
Isso irá inicializar umas instancia do Dynamo DB na porta 8000 e você já vai poder inicializar o projeto.

## Inicializando o projeto
Ao fazer o clone do projeto na sua máquina, com o Intellij aberto, no menu superior vá em **file** -> **open** e selecione a pasta do **dentist-odontoa-api**.

Certifique-se de que o projeto foi construído na sua máquina antes de iniciar os próximos passos.
  - No barra de menu superior vá em **run** -> **Edit Configurations...** (você também pode user o atalho que aparece na barra superior para isso):
    - Na janela que abriu clique no ícone "+" e nas opções de seleção escolha "Kotlin";
    - Agora em **Name** insira `dentist-odonto-api` ou o que você preferir;
    - Na **aba Configuration**, na opção de **Main class:** selecione **DentistOdontoApiKt**
    - Ainda na **aba Configuration**, em **VM options** insira o valor `-Dspring.profiles.active=local` para rodar o projeto em ambiente local;
    - Ainda na **aba Configuration**, em **Use classpath of module** selecione o valor **dentist-odonto-api.main**;
    - Ainda na **aba Configuration**, em **JRE** selecione a versão do Java 11 ou outra maior de sua escolha;
    - Clique em **Apply** e depois em **OK** para salvar as configurações.
  - Agora você pode inicializar o projeto indo na barra de menu superior e em **run** e fdepois selecionando **Run...** (ou pode usar a parte visual que fica na barra superior e clicar no "botão de play").

Pronto, seu projeto está inicializado e já pode receber requisições :blush:.
Para verificar se o projeto tá up use o endpoint do actuator: http://localhost:8080/actuator/health.

# Testes
Infelizmente o Kotlin tem alguns problemas ao executar o Dynamo DB localmente e inicializa-lo para executar executar os testes.
Então para rodar os testes é necessário inicializar o Dynamo DB para que os testes funcionem.
