#!/bin/bash
echo ldapsearch -h 172.16.113.129 -b ou=people,dc=example,dc=com -x uid=jlittle
ldapsearch -h 172.16.113.129 -b ou=people,dc=example,dc=com -x uid=jlittle
