# spring-config-server
ejemplo de spring cloud config 


Now we’re able to start our server. The Git-backed configuration API provided by our server can be queried using the following paths:

/{application}/{profile}[/{label}]

/{application}-{profile}.yml

/{label}/{application}-{profile}.yml

/{application}-{profile}.properties

/{label}/{application}-{profile}.properties


http://127.0.0.1:8888/config-server-client/production