cd /opt/tomcat/webapps

rm -r ROOT

unzip /home/xy190/xclin.war -d ROOT

systemctl restart tomcat
