Template de aplicatie web
===========================
Template de aplicatie web de la care sa incepeti cand lucrati la proiect.

## Ce face
Are un web server incorporat ( tomcat ) in care este pornita o aplicatie web. Aplicatia web raspunde la 3 requesturi. Poate salva si extrage informatii din DynamoDB.

## Cum creez binarul si cum o rulez

### Aveti nevoie de

* Java 7
* [Maven 3](http://maven.apache.org/download.html)


### Inainte de toate

In primul rand trebuie sa aveti confugurate credentialele de amazon. Pentru a le configura creati un fisier in `~/.aws/credentials` care arata cam asa:

    [default]
    aws_access_key_id = XXX
    aws_secret_access_key = XXX
    region = us-east-1


Inlocuiti `XXX` cu credentialele pe care le-ati primit la curs.

### Crearea binarului

In directorul care contine `pom.xml` rulati comanda: `mvn clean package`

    [INFO] Scanning for projects...
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] Building webapp-template 0.1.0
    [INFO] ------------------------------------------------------------------------
    [INFO]
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ webapp-template ---
    [INFO] Deleting /Users/rhasna/repos/curs-aws/laborator/webapp-template/target
    [INFO]
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ webapp-template ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] Copying 0 resource
    [INFO] Copying 1 resource
    [INFO]
    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ webapp-template ---
    [INFO] Changes detected - recompiling the module!
    [INFO] Compiling 5 source files to /Users/rhasna/repos/curs-aws/laborator/webapp-template/target/classes
    [INFO]
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ webapp-template ---
    [INFO] Using 'UTF-8' encoding to copy filtered resources.
    [INFO] skip non existing resourceDirectory /Users/rhasna/repos/curs-aws/laborator/webapp-template/src/test/resources
    [INFO]
    [INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ webapp-template ---
    [INFO] No sources to compile
    [INFO]
    [INFO] --- maven-surefire-plugin:2.15:test (default-test) @ webapp-template ---
    [INFO] No tests to run.
    [INFO]
    [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ webapp-template ---
    [INFO] Building jar: /Users/rhasna/repos/curs-aws/laborator/webapp-template/target/webapp-template-0.1.0.jar
    [INFO]
    [INFO] --- spring-boot-maven-plugin:1.1.8.RELEASE:repackage (default) @ webapp-template ---
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 1.620 s
    [INFO] Finished at: 2015-12-03T23:52:03+02:00
    [INFO] Final Memory: 27M/270M
    [INFO] ------------------------------------------------------------------------



### Rularea aplicatiei

Dupa ce ati creat binarul, a aparut un folder nou numit target.
Pentru a rula aplicatie executati comanda: `java -jar target/webapp-template-0.1.0.jar`

      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v1.1.8.RELEASE)
    
    2015-12-03 23:53:33.320  INFO 10980 --- [           main] cursaws.proiect.webapp.Application       : Starting Application on alucard with PID 10980 (/Users/rhasna/repos/curs-aws/laborator/webapp-template/target/webapp-template-0.1.0.jar started by rhasna in /Users/rhasna/repos/curs-aws/laborator/webapp-template)
    2015-12-03 23:53:33.359  INFO 10980 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@3df72af3: startup date [Thu Dec 03 23:53:33 EET 2015]; root of context hierarchy
    2015-12-03 23:53:33.987  INFO 10980 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Overriding bean definition for bean 'beanNameViewResolver': replacing [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter.class]]
    2015-12-03 23:53:34.757  INFO 10980 --- [           main] .t.TomcatEmbeddedServletContainerFactory : Server initialized with port: 8080
    2015-12-03 23:53:34.941  INFO 10980 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
    2015-12-03 23:53:34.942  INFO 10980 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/7.0.55
    2015-12-03 23:53:35.027  INFO 10980 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2015-12-03 23:53:35.027  INFO 10980 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1670 ms
    2015-12-03 23:53:35.523  INFO 10980 --- [ost-startStop-1] o.s.b.c.e.ServletRegistrationBean        : Mapping servlet: 'dispatcherServlet' to [/]
    2015-12-03 23:53:35.526  INFO 10980 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
    2015-12-03 23:53:36.004  INFO 10980 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
    2015-12-03 23:53:36.086  INFO 10980 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/person/{personName}/age/{age}],methods=[POST],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public void cursaws.proiect.webapp.ApplicationController.savePerson(java.lang.String,java.lang.String)
    2015-12-03 23:53:36.086  INFO 10980 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/person/{personName}],methods=[],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public cursaws.proiect.model.Person cursaws.proiect.webapp.ApplicationController.person(java.lang.String)
    2015-12-03 23:53:36.086  INFO 10980 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/greeting],methods=[],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public java.lang.String cursaws.proiect.webapp.ApplicationController.greeting(java.lang.String,org.springframework.ui.Model)
    2015-12-03 23:53:36.088  INFO 10980 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],methods=[],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
    2015-12-03 23:53:36.088  INFO 10980 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],methods=[],params=[],headers=[],consumes=[],produces=[text/html],custom=[]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest)
    2015-12-03 23:53:36.107  INFO 10980 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
    2015-12-03 23:53:36.107  INFO 10980 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
    2015-12-03 23:53:36.409  INFO 10980 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
    2015-12-03 23:53:36.473  INFO 10980 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080/http
    2015-12-03 23:53:36.474  INFO 10980 --- [           main] cursaws.proiect.webapp.Application       : Started Application in 4.012 seconds (JVM running for 4.458)

## Exemplu de interactiune cu aplicatia:

* Greeting cu numele generic `curl "http://localhost:8080/greeting"`
* Greeting cu un nume setat de noi: `curl "http://localhost:8080/greeting?name=Ionescu"`
* Stocarea unei persoane in baza de date: `curl -X POST "http://localhost:8080/person/ionescu/age/71"`
* Extragerea unei persoane din baza de date: `curl "http://localhost:8080/person/ionescu"`
* Servirea unui fisier static: `curl "http://localhost:8080/my-static-file.txt"`
