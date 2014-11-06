# Elastic MapReduce

## Crearea unui cluster

In consola AWS mergeti la sectiunea pentru Elastic MapReduce si creati un cluster.

## Rularea unui job MapReduce

In folder-ul `laborator/laborator5/map-reduce` aveti un exemplu word count implementat cu map reduce. Generati jar-ul ( cu dependinte ) si urcati-l pe S3.

Pentru a rula un Job, trebuie sa creati un `Step` din interfata web. Specificati ca vreti sa rulati un `Custom jar`. Completati restul campurilor.

Observatii:

1. Jar-ul primeste 2 argumente cand este rulat. 
1. Primul argument este calea catre folderul de intrare din s3 ( `s3://bucket-name/cale/catre-fisiere/` )
1. Al doilea argument este calea unde vrem sa salvam rezultatul. Este tot o cale de S3. Nu trebuie sa existe, altfel va arunca exceptie.

## Exercitii

Implementati un map reduce care:

1. Determina cuvintele unice.
2. Determina numarul de cuvinte unice.
3. Determina numarul de cuvinte care nu sunt unice.
4. Exemplele cu URL-uri din curs.