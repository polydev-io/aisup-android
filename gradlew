#!/bin/sh

# Gradle wrapper script
# Downloads gradle if needed and runs it

APP_NAME="Gradle"
APP_BASE_NAME=${0##*/}
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

die() {
    echo "$*" 1>&2
    exit 1
}

GRADLE_HOME="${GRADLE_USER_HOME:-${HOME}/.gradle}"

exec gradle "$@"
