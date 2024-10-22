# AI docs

## how to configure
first add a pdf file in the resource/docs folder
the pdf file should be named as ```reference-doc.pdf```

## how to run
```mvn clean install```
```mvn spring-boot:run```
when running for the first time, the pdf file will be read and the data will be stored in the pgvector database
this process will take a while to complete depending on the size of the PDF. 

After running for the first time, the data will be stored in the database and the application will be ready to use.

## how to use
```mvn spring-boot:run```
after initialization is completed shell prompt should be shown, 
type ```q``` and ask a question, the application will return the answer from the document.

```shell
 q "what is this document about"
```
