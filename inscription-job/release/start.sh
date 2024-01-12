#!/bin/bash
PROFILE=$1
./stop.sh "$PROFILE"
nohup /usr/local/jdk8/bin/java -jar inscription-job*.jar --spring.profiles.active="$PROFILE" > /dev/null 2>&1 &
sleep 5
pid=$(ps aux | grep inscription-job | grep active="$PROFILE" | grep -v grep | awk '{print $2}')
echo "$pid"
if [ -n "$pid" ]; then
 echo "start success"
else
  echo "retry start ..."
  nohup /usr/local/jdk8/bin/java -jar inscription-job*.jar --spring.profiles.active="$PROFILE" > /dev/null 2>&1 &
fi
echo 'inscription-job Process List:'
ps -elf|grep inscription-job | grep active="$PROFILE"
