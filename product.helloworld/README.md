# Web based Hello World application

## Assumptions/Pre-requisites

### Hardware
Laptop with at least 8 Gb memory (recommended 16 Gb, ideally 32 Gb)

### Software
1. Maven (v 3.6.2, or higher)
* Instructions to install here: https://maven.apache.org/download.cgi
* Check installation with the command `mvn -version`



2. VirtualBox(v 6.0, or higher)
* Instructions to install here: https://www.virtualbox.org/wiki/Downloads 


3. Vagrant (v 2.2.5, or higher) 
* Instructions to install here: https://www.vagrantup.com/downloads.html
* (only if using Windows 10 or Windows 8 Pro) Disable Hyper-V, see instructions to disable here: https://www.poweronplatforms.com/enable-disable-hyper-v-windows-10-8/
* Check installation with the command `vagrant -v`'


4. Postman (V 7.19.1 (7.19.1) or higher)
* Instructions to install here: https://www.postman.com/




## Set local working environment

1- Import the given project into your favourite IDE

2- Build the project using Maven. Either you do it from within the IDE, or from a terminal. From the terminal you must:

2.1-  Get to the directory

```
cd ~/<git_root_folder>/helloworld/product.helloworld
```

2.2- Run the comand.

```
mvn clean install
```

Running this command should shown (almost at the end of the output):

```
[INFO] BUILD SUCCESS
```


**Note**

* The project is ready to be imported on the Eclipse IDE as an existen Maven project.




## Set local testing environment
In order to run the application some tools have to be installed. The idea is to use the same versions and configurations of the tools
as expected to be in the production environment. In this manner, testing the application in our local environment (to some extend) 
it's similar that running the application on the production environment. 

For this purpose, a local production-like testing environment is going to be setup. 


1-  Get to the directory

```
cd ~/<git_root_folder>/helloworld/product.helloworld`
```


2-  Run the command

```
vagrant up
```

Running this command should shown at the end of the output:

```
 default: Done.
```



## Check local testing environment setup


1-  Get to the directory

```
cd ~/<git_root_folder>/helloworld/product.helloworld
```

2- Jump into the virtual environment (refered to also as *guest*) : 
```
 vagrant ssh
```


3-  Check mysql installation

```
mysql -u root -p
```

password: 12345678

You should have been able to connect to the database engine. To quit, type:

```
quit;
```


4-  Check Tomcat installation and configuration

Open a browser, and try to access to this url

http://192.168.33.14:8080


Then, try to access to the Tomcat's management are, via this url

http://192.168.33.14:8080/manager/html

user and password: admin




## Run the application

In order to try out the application, it frst has to be deployed in the virtual environment setup for such pourpose.
This is done by executing the following steps:


1-  Get to the directory

```
cd ~/<git_root_folder>/helloworld/product.helloworld
```

2- Jump into the virtual environment (refered to also as *guest*) : 
```
 vagrant ssh
```

3- Create the dabase schema and tables 

```
mysql -u root -p  < /vagrant_scripts/db-helloworld.sql
```

password: 12345678


4- Deploy the application

Go to folder
```
cd /vagrant_scripts/
 ```

Execute script
```
sudo ./deploy-snapshot.sh
 ```


**Note**

* Only step 4 has to be executed every time you want to re-deploy the application, assuming no modifications to the 
database were made. 



## Test the application


1. Landing page: http://192.168.33.14:8080/helloworld
2. Static html page: http://192.168.33.14:8080/helloworld/helloworld.html
3. Web service 1 => Timestamp service: http://192.168.33.14:8080/helloworld/FirstServlet
4. Web service 2 => TEST database table's content: http://192.168.33.14:8080/helloworld2/select

5. Web service 3 => TEST database table's insert: use postman application and check if you can properly post
data into the database and get proper response.

The url to use is: 

```
http://192.168.33.14:8080/helloworld/insert
```

Fill the parameters as you wish.

The expected answer should be:

```
<html><body><b>Successfully Inserted</b></body></html>
```


## Final remarks

This guidelines explain how to come up with a development environment such that:

- the build of the application is automatically done using maven,
- the setup of a local testing environment is automatically made using Vagrant, and
- the (re)deployment of the application for testing purposes is automtically done.
