# logrolling
Logrolling: The art of making life easier

![Java CI with Maven (Server)](https://github.com/palmenros/logrolling/workflows/Java%20CI%20with%20Maven%20(Server)/badge.svg)

This repository contains project documentation, the Android Client and Java Server. There is a CI action building the Java Server on every commit affecting it. To get the latest server `.jar` file without compiling with maven, download the `.jar` from `CI Server Build` [release](https://github.com/palmenros/logrolling/releases/tag/CI).

In order to execute the server `.jar`, the folder `Server/out/artifacts/Server_war_exploded/images` must be copied inside a new folder called `tomcat.8080`, in the same directory as the executed `.jar`, resulting in the folder `tomcat.8080/images`.

For the REST API documentation, visit the website https://palmenros.github.io/logrolling/, where all possible requests are explained and documented.
