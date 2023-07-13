@echo off

mkdir ordermng-api

cd ordermng-api

java -jar /home/arbori/swagger-editor/swagger-codegen-cli.jar generate \
-i ../ordermng-api.yaml \
--api-package com.ordermng.api \
--model-package com.ordermng.api.model \
--invoker-package com.ordermng.api \
--group-id com.ordermng.api \
--artifact-id ordermng-api \
--artifact-version 0.0.1-SNAPSHOT \
-l spring \
#--library resttemplate \
-o ./

cd ..
