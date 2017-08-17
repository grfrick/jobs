Monitoramento de diertorio

Programinha: "BATCH"
Fica "escutando" um diretorio, casa apareça um arquivo ".dat", 
consome e gera um relatório (.proc) em outro diretorio.

Ide:  Intellig
Lombok error:
In order to solve the problem set:

Preferences (Ctrl + Alt + S)
Build, Execution, Deployment
Compiler
Annotation Processors
Enable annotation processing
Make sure you have the Lombok plugin for IntelliJ installed!

Preferences -> Plugins
Search for "Lombok Plugin"
Click Browse repositories...
Choose Lombok Plugin
Install
Restart IntelliJ

...............
gradlew build && java -jar build/libs/batch-0.1.0.jar
./gradlew build && java -jar build/libs/batch-0.1.0.jar