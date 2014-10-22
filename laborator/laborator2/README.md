# Amazon SQS: Simple Queue Service

## Crearea unei cozi

1. Mergeti la sectiunea pentru SQS ![](./sqs-workflow-1.png)
1. Creati o noua coada: ![](./sqs-workflow-2.png)
1. Configurati proprietatiile: ![](./sqs-workflow-3.png)
1. Coada a fost creata: ![](./sqs-workflow-4.png)

## Trimiterea unui mesaj in coada din consola web

1. Porniti actiunea de trimitere: ![](./sqs-workflow-5.png)
1. Scrieti mesajul care vreti sa fie adaugat in coada: ![](./sqs-workflow-6.png)
1. Dupa trimitere vi se dau informatiile necesare pentru a identfica mesajul si integritatea sa: ![](./sqs-workflow-7.png)
1. Mesajul a fost adaugat. Vedem asta prin numarul de mesaje din coada care acum este 1. Numarul de mesaje afisat este o aproximare si nu numarul exact. ![](./sqs-workflow-8.png)

## Citirea unui mesaj din coada din consola web

1. Porniti actiunea de citire/stergere a mesajelor: ![](./sqs-workflow-9.png)
1. Incepeti interogarea cozii: ![](./sqs-workflow-10.png)
1. Mesajul trimis este recuperat si afisat: ![](./sqs-workflow-11.png)

## Trimiterea, citirea si stergerea unui mesaj folosind SDK-ul.

### Proiect exemplu

Clonati repository-ul curent de git pe masina locala: `git clone git@github.com:hasnarobert/curs-aws.git`.

In folder-ul `curs-aws/laborator/laborator2/producator-consumator` aveti un exemplu de cod care trimite un mesaj intr-o coada SQS, il citeste si se asigura ca mesajul este corect apoi il sterge.

Proiectul `producator-consumator` foloseste `maven` pentru a gestiona librariile de care depinde.

### Comenzi utile pentru maven:

1. Compilarea proiectului: `mvn compile`
1. Impachetarea proiectului intr-un jar cu dependinte: `mvn package`. Binarele le gasiti in folderul `target`.
1. Stergerea fisierelor generate: `mvn clean`.
1. Pot fi combinate: `mvn clean package` care sterge binarele, apoi compileaza si impacheteaza aplicatia.

### Rularea proiectului exemplu

Veti avea nevoie de:

1. Cheile de access ale user-ului vostru ( ACCESS_KEY, SECRET_KEY )
1. URL-ul cozii create mai devreme ( QUEUE_URL )

In fisierul `src/main/java/cursaws/laborator2/producatorconsumator/Main.java` inlocuiti `xxx` cu valorile obtinute anterior:

```java
    public static final String ACCESS_KEY = "xxx";
    public static final String SECRET_KEY = "xxx";
    public static final String QUEUE_URL = "xxx";
```

Compilati si impachetati proiectul intr-un jar executabil: `mvn clean package`.

Rulati jar-ul creat: `java -jar target/producator-consumator-1.0-SNAPSHOT-jar-with-dependencies.jar`. Urmariti output-ul.

## Producator separat de consumator 

Folositiva de proiectul exemplu ( inclusiv maven, `pom.xml` ) pentru a crea doua aplicatii:

1. O aplicatie producator care citeste de la tastatura mesaje si le trimite in coada SQS.
2. O aplicatie consumator care citeste mesajele din coada si le afiseaza pe ecran intr-o bucla infinita. Obs: Sa folositi un `Thread.sleep` de cel putin o 200ms intre apelurile de citire din coada.

Impachetati aplicatiile si rulati-le in paralel. In terminalul producator scrieti mesaje si verificati ca apar si in terminalul consumator aceleasi mesaje.

#### Exercitiu

Folositi-va de proiectul exemplu sa faceti o aplicatie care are un producator care citeste mesaje de la tastatura si un consumator care afiseaza mesajele intr-o bucla infinita.

Porniti doua instante ale acestei aplicatii si trimiteti mesaje de pe una din ele. Urmariti ce se intampla.


## Rularea consumatorului pe o masina EC2

1. Creati o masina EC2 ( Laborator 1 )
2. Copiati jar-ul executabil al aplicatiei consumator pe masina remote: `scp -i <KeyPair> producator-consumator-1.0-SNAPSHOT-jar-with-dependencies.jar ec2-user@<IP_MASINA_EC2>:~/consumator.jar`
3. Conectati-va prin ssh la masina remote si porniti aplicatia consumator.
4. Rulati aplicatia producator local si trimiteti mesaje. Mesajele sunt citite de o masina EC2 care ruleaza in amazon.


## Autoscalare

### Crearea unei imagini de baza

1. Identificati masina pe care ati copiat jar-ul executabil cu consumatorul. Dati click dreapta pe ea si creati o imagine: ![](./creare-imagine-1.png)
1. Completati detalii despre imagine: ![](./creare-imagine-2.png)
1. Imagine este in curs de a fi creata: ![](./creare-imagine-3.png)
1. Imaginea a fost creata: ![](./creare-imagine-4.png)

### Crearea unui "Launch Configuration"

1. Mergeti in sectiunea pentru Launch Confugiration: ![](./auto-scaling-1.png)
2. Creati un Launch Configuration nou: ![](./auto-scaling-2.png)
3. Selectati ca imagine de baza imaginea pe care tocmai ati creat-o: ![](./auto-scaling-3.png)
4. Selectati tipul masinilor care vreti sa fie in noul grup: ![](./auto-scaling-4.png)
5. Introduceti detalii: ![](./auto-scaling-5.png)
6. Confugurati stocarea pentru masinile din noul grup: ![](./auto-scaling-6.png)
7. Selectati un Security Group existent: ![](./auto-scaling-7.png)
8. Folositi unul care este in VPC: ![](./auto-scaling-8.png)
9. Sumar cu configuratia: ![](./auto-scaling-9.png)
10. Alegeti cheia de ssh care sa fie folosita la crearea masinilor din noul grup: ![](./auto-scaling-10.png)
11. Launch Configuration a fost creat.

### Crearea unui "Autoscaling Group"

1. Mergeti la sectiunea pentru Autoscaling Group. Sub Launch Configuration.
2. Creati un nou Autoscaling Group: ![](./auto-scaling-11.png)
3. Dati un nume grupului si specificati sa fie creat in VPC: ![](./auto-scaling-12.png)
4. Configurati numarul de masini minim si maxim din grup: ![](./auto-scaling-13.png)
5. Ignorati setarile de notificari: ![](./auto-scaling-14.png)
6. Configurati tag-urile care sunt setate pe masinile create in grup. Cu ajutorul lor putem identifica fiecare masina din ce grup face parte: ![](./auto-scaling-15.png)
7. Pagina de review: ![](./auto-scaling-16.png)
8. Autoscaling group-ul a fost creat: ![](./auto-scaling-17.png)
9. Mergeti la sectiunea EC2 la masinile care ruleaza si identificati masina creata din Autoscaling group-ul creat: ![](./auto-scaling-18.png)

#### Exercitiu

Cand am creat grupul am specificat minim o masina, maxim 4, si ca dorim ca momentan sa fie o singura masina. Masina pe care o vedeti in console EC2 este creata conform specificatiilor noastre.

Mergeti inapoi la sectiunea Autoscaling group si configurati grupul nou creat sa aiba 2 masini in loc de una singura. Observati cum inca o masina identica a fost creata. Intrati in una din ele prin ssh si verificati ca aplicatia consumator este prezenta.

Setati din nou numarul de masini la 1 si observati ca una din masini este distrusa.

### Crearea unei alarme pentru numarul de mesaje din coada

1. Mergeti la console Cloud Watch: ![](./alarme-1.png)
2. Mergeti la lista cu metrici: ![](./alarme-2.png)
3. Mergeti la metricile pentru SQS: ![](./alarme-3.png)
4. Cautati metrica metru numarul de mesaje vizibile asociata cozii pe care ati creat-o mai devreme: ![](./alarme-4.png)
5. Creati o alarma pe metrica selectata: ![](./alarme-5.png)
6. Configurati metrica in felul urmator ( cu nume unic ): ![](./alarme-6.png)
7. Mergeti si editati Autoscaling group-ul creat anterior: ![](./alarme-7.png)
8. Editati sectiunea Scaling Policies, si adaugati una: ![](./alarme-8.png)
9. Adaugati o masina cand alarma nou creata se activeaza: ![](./alarme-9.png)
10. Adaugati multe mesaje in coada si opriti toti consumatorii. Ii puteti lasa sa mearga daca puneti un delay mare intre citirea mesajelor. Cand conditiile din alarma sunt indeplinite ( numarul de mesaje e mai mare decat X timp de T minute ) alarma se va activa: ![](./alarme-10.png)
11. Politica de scalare este activata si este adaugata o masina noua in grup: ![](./alarme-11.png)

#### Exercitiu

Creati o noua alarma pentru un numar mic de mesaje in coada. Creati o noua plitica de scalare in care sa scadeti numarul de masini din grup cand noua alarma este activata.

Testati ca ambele politici de scalare se activeaza utilizand aplicatiile producator consumator:

1. Rulati consumatorul pe fiecare masina din grup cu delay mare intre citirea de mesaje astfel incat sa scrieti mai multe decat se pot consuma.
2. Rulati producatorul local si trimiteti mesaje in coada pana cand numarul de masini din grup creste.
3. Dupa ce a crescut asteptati sa termine consumatorii mesajele din coada si observati cum numarul de masini din grup scade pe parcurs.

## Cleanup

Stergeti toate resursele create la sfarsitul laboratorului.
