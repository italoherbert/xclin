cd /opt/tomcat/webapps
sudo -u tomcat rm -rf ROOT
sudo -u tomcat unzip -q $HOME/temp/xclin.war -d ROOT
sudo -u tomcat chown -R tomcat:tomcat ROOT
