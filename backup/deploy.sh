#!/bin/bash

USER_HOME=/home/xy206
PROJ_HOME=$USER_HOME/xclin

TARGET_HOME=$PROJ_HOME/target
ANGULAR_BUILD_HOME=$PROJ_HOME/frontend/dist/frontend

WEBAPP_HOME=/opt/tomcat/webapps
WEB_HOME=/var/www/xclin.com.br

REPOSITORY=https://italoherbert:$GIT_TOKEN@github.com/italoherbert/xclin

if [ -d "$PROJ_HOME" ]; then
	rm -rf $PROJ_HOME
fi

git clone $REPOSITORY

if [ $? -ne 0 ]; then
	echo "Não foi possível executar o clone com sucesso!"
	exit 1;
fi

cd $PROJ_HOME

mvn clean package

if [ $? -ne 0 ]; then
	echo "Não foi possível fazer o empacotamento do backend"
	exit 1
fi

cd $WEBAPPS_HOME
rm -r ROOT

unzip -q $PROJ_HOME/target/xclin.war -d ROOT

if [ $? -ne 0 ]; then
        echo "Não foi possível desempacotar o arquivo .war"
	exit 1
fi

cd $WEB_HOME

cp -r $ANGULAR_BUILD_HOME/* .

echo "Deploy feito com sucesso."
