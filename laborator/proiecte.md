# Proiect: Aplicatii distribuite in cloud

## Observatii

* Proiectul se implementeaza in grupe de cate 3.
* Nota pe proiect va fi aceeasi pentru toti membri unei grupe.
* Nota din proiect este 70% din nota finala.
* Nu se poate promova fara proiect! inclusiv la restanta.
* Codul sursa al proiectele se va stoca intr-un repository de git pe github.
* Puteti folosi orice fel de limbaj sau tehnologie doriti.
* Toate proiectele sunt compuse din 2 parti:
	1. Un client cu care interactioneaza utilizatorul. De preferat o interfata web.
	2. Un server care se va implementa folosind componente AWS.
* Accentul la punctare se pune pe arhitectura, scalabilitatea si folosirea componentelor AWS, nu pe cum arata interfata ( e ok si un script de BASH pe post de interfata ).
* Un proiect este implementat de catre o singura echipa.

## Punctarea proiectelor

La punctarea proiectelor se va tine cont de:

* Capacitatea sistemul de scalare: se vor genera mii de requesturi pe secunda pentru a vedea cum variaza timpul de raspuns.
* Viteza de scalare: se va testa cat de repede se acomodeaza sistemul cu un spike de utilizare.
* Capacitatea sistemul de a autoscala.
* Arhitectura infrastructurii.
* Lipsa unui single point of failure.
* Raportarea progresului in aplicatia client.
* Distribuirea muncii in echipa.

## Proiecte propuse

Proiectele propuse sunt pe post de exemplu. Puteti si sunteti incurajati sa alegeti un proiect care nu este pe lista.

### 1. Serviciu de conversie video

Implementati un serviciu scalabil de conversie video care poate executa urmatoarele operatii:

1. Modificarea rezolutiei.
2. Modificarea codecului video si/sau audio.
3. Transformarea clipului in grayscale.

### 2. Index de loguri

Implementati un serviciu scalabil de indexare a logurilor care are urmatoarele functionalitati:

1. Indexeaza logurile dintr-un fisier.
2. Logurile au un format prestabilit.
3. Logurile sunt grupate dupa numele proiectului si numele masinii gazda.
4. Se poate face o cautare in loguri.
5. Cautarea poate fi filtrata dupa una din conditiile de grupare.
6. Lista de loguri returnata de o cautare este actualizata in timp real.

### 3. Motor de cautare in poze

Implementati un serviciu care indexeaza poze si poate sa caute poze similare. Nu se va pune accent pe calitatea rezultatelor returnate.

### 4. Analizator de TCP dump

Implementati un serviciu care indexeaza TCP dump si poate raspunde la urmatoarele intrebari:

1. Care este cantitatea de date transmisa intre o masina gazda si una remote intr-un interval de timp.
2. Cat timp a fost folosit un serviciu intr-un interval de timp.
3. Cu care masina remote s-a comunicat cel mai mult intr-un interval de timp.

### 5. Tweeter

Implementati un serviciu care are urmatoarele functionalitati:

1. Utilizator poate trimite un mesaj in sistem.
2. Un mesaj poate avea atasate taguri.
3. Utilizator se poate abona la mesajele trimise de alt utilizator si la mesajele care au anume tag.
4. Utilizatorul receptioneaza mesajele trimise pa canelele la care este abonat in timp real.
