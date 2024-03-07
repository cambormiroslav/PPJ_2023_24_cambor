# Cvičení 1 – features java 21

Dokumentace featury:
[JEP 448: Vector API (Sixth Incubator)](https://openjdk.org/jeps/448)

Videodokumentace:
[Learn how to write fast Java code with the Vector API - JEP Café #18](https://www.youtube.com/watch?v=42My8Yfzwbg)

Odkaz na kód:
[Main.java](https://github.com/cambormiroslav/PPJ_2023_24_cambor/blob/main/task1_features_java/src/Main.java)


## Obsah

Obsahem je Vector API, který zrychluje výpočty s polem. Přečemž v souboru Main.java jsou zpracovány operace sečtení dvou polí, normalizace, průměrná hodnota v poli, jaké hodnoty obsahují pole větší než limit a s průměrem.

U hodnot větších než limit a u průměru pro tyto hodnoty nelze zadat při požití pole naplněnýho hodnotami 1-9 zadat hodnoty 1-6 pro limit. První metoda pro součet čísel v poli pro hodnoty 9 v obou případech, neboli při provedení operace 9+9 provede špatně výpočet a výsledek je 0. Druhá metoda podává správné výsledky.

## Spuštění skriptu

V této době není podporován tento balík s VECTOR API pomocí Jetbrains IntelliJ IDEA, proto je potřeba použít příkazovou řádku.

Příkaz pro spuštení skriptu z kořenového adresáře: java --add-modules jdk.incubator.vector ./src/Main.java