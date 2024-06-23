cd frontend

ng build

cd dist/frontend

rm -r ../../../src/main/webapp/*
cp -r * ../../../src/main/webapp/

cd ../../../
./mvnw clean package -DskipTests