@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  test-api startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and TEST_API_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\test-api.jar;%APP_HOME%\lib\test-core.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.25.jar;%APP_HOME%\lib\spring-boot-starter-web-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-actuator-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-cache-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-redis-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-json-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-autoconfigure-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-configuration-processor-2.1.2.RELEASE.jar;%APP_HOME%\lib\springfox-swagger-ui-2.7.0.jar;%APP_HOME%\lib\springfox-swagger2-2.7.0.jar;%APP_HOME%\lib\jedis-2.9.1.jar;%APP_HOME%\lib\springfox-swagger-common-2.7.0.jar;%APP_HOME%\lib\springfox-spring-web-2.7.0.jar;%APP_HOME%\lib\swagger-models-1.5.13.jar;%APP_HOME%\lib\springfox-schema-2.7.0.jar;%APP_HOME%\lib\springfox-spi-2.7.0.jar;%APP_HOME%\lib\springfox-core-2.7.0.jar;%APP_HOME%\lib\spring-plugin-metadata-1.2.0.RELEASE.jar;%APP_HOME%\lib\spring-plugin-core-1.2.0.RELEASE.jar;%APP_HOME%\lib\spring-data-redis-2.1.4.RELEASE.jar;%APP_HOME%\lib\spring-data-keyvalue-2.1.4.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.1.2.RELEASE.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.11.1.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\spring-data-commons-2.1.4.RELEASE.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\spring-boot-actuator-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.1.2.RELEASE.jar;%APP_HOME%\lib\swagger-annotations-1.5.13.jar;%APP_HOME%\lib\reflections-0.9.11.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\hibernate-validator-6.0.14.Final.jar;%APP_HOME%\lib\classmate-1.4.0.jar;%APP_HOME%\lib\mapstruct-1.1.0.Final.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-web-5.1.4.RELEASE.jar;%APP_HOME%\lib\micrometer-core-1.1.2.jar;%APP_HOME%\lib\spring-context-support-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-context-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-oxm-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-core-5.1.4.RELEASE.jar;%APP_HOME%\lib\commons-pool2-2.6.0.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.8.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.8.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.8.jar;%APP_HOME%\lib\jackson-databind-2.9.8.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\snakeyaml-1.23.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.14.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.14.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.14.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\HdrHistogram-2.1.9.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\spring-jcl-5.1.4.RELEASE.jar;%APP_HOME%\lib\javassist-3.21.0-GA.jar;%APP_HOME%\lib\byte-buddy-1.9.7.jar;%APP_HOME%\lib\jackson-core-2.9.8.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\log4j-api-2.11.1.jar

@rem Execute test-api
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %TEST_API_OPTS%  -classpath "%CLASSPATH%" test.ml.ApiStarter %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable TEST_API_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%TEST_API_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
