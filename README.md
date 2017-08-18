# Monitoramento de diertorio

Programinha: "BATCH"
Fica "escutando" um diretorio, casa apareça um arquivo ".dat", 
consome e gera um relatório (.proc) em outro diretorio.

** para teste de leitura de arquivos .dat, deve ser colocado no diretório "in" localizado no projeto em: "src/main/resources/dados/"

Ide:  Intellig

# **Lombok error:

-In order to solve the problem set:

# 1

- Preferences (Ctrl + Alt + S)

- Build, Execution, Deployment

- Compiler

- Annotation Processors

- Enable annotation processing

- Make sure you have the Lombok plugin for IntelliJ installed!


# 2

- Preferences -> Plugins

- Search for "Lombok Plugin"

- Click Browse repositories...

- Choose Lombok Plugin

- Install

- Restart IntelliJ


# * extras:

gradlew build && java -jar build/libs/batch-0.1.0.jar

./gradlew build && java -jar build/libs/batch-0.1.0.jar

# future:

* usar "Spring Batch"
