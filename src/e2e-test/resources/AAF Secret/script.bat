@echo off

setlocal


rmdir /s /q "output"

mkdir output

java -jar lib/cadi-core-1.4.2.jar keygen output/keyfile

set /p pass="Enter mech id password :"

cls

java -jar lib/cadi-core-1.4.2.jar digest %pass% output/keyfile >> output/aaf_password.txt

echo Keyfile and hashed password generated and placed under output folder.

pause