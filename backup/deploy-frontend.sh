WEB_DIR=$HOME/temp/web

if [ -e "$WEB_DIR" ]; then
	rm -rf /var/www/xclin.com.br/*
	cp -r $WEB_DIR/* /var/www/xclin.com.br/ 
else
	echo "Diretório temp/web não encontrado."
fi
