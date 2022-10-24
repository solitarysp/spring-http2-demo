#!/bin/bash
/startpulse.sh &
apk add curl &
/usr/bin/openbox-session > /dev/null 2>&1
