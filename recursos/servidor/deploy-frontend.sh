cd /var/www/xclin.com.br

if [ `pwd` = '/var/www/xclin.com.br' ]; then
	rm -r *
	cp -r /home/xy190/xclin.com.br/* .

	systemctl restart apache2
fi
