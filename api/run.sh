#!/bin/sh

gunicorn -w 2 -b 127.0.0.1:4000 --max-requests 100 --access-logfile - --preload --error-logfile - app:app
