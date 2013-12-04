eclipse:
	mvn eclipse:eclipse -Dwtpversion=2.0 -DdownloadJavadocs=true -DdownloadSources=true

clean:
	mvn clean
	mvn eclipse:clean
