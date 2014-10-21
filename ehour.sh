#!/bin/sh

####
#
# Startup script for Digimap Services.
#
####

case "$1" in
  build)
    # Build EHour service.

    ;;
  deploy)
    # Deploy EHour Service to Heroku.
    ;;
  test)
    # Test EHour Service locally.
    ;;
  *)
    echo "Usage: $0 build|deploy|test" >&2
    exit 3
    ;;
esac


function build() {
	mvn clean package -Pprod
}

function prepare() {
  cd eHour-war/target/

  unzip ehour-1.4.2-SNAPSHOT-war.zip

  cd ..
}

function clean-heroku() {
  heroku apps:destroy --app edina-geostaff-activity --confirm edina-geostaff-activity
}

function create-heroku() {
  heroku apps:create edina-geostaff-activity
}

function deploy-heroku() {
   git push heroku edina:master
heroku logs --tail
}

function test() {
  # Running webrunner
  java -Ddb.url=jdbc:postgresql://localhost:5433/postgres -Ddb.username=postgres -Ddb.password=Bookchin1789 -DEHOUR_HOME=ehour-packages/ehour-1.4.2-  SNAPSHOT -jar target/dependency/webapp-runner.jar  --path / ehour-packages/ehour-1.4.2-SNAPSHOT/app/ehour-1.4.2-SNAPSHOT.war
}

exit $?


