@echo off
setlocal enableExtensions
::echo.
::echo The browsers installed on this machine are:
::echo.

rem for 64 bit systems
START /W REGEDIT /E "%Temp%\BROW3.reg" HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Clients\StartMenuInternet
set "bits=64"

rem for 32 bit systems
if not exist "%Temp%\BROW3.reg" (
	START /W REGEDIT /E "%Temp%\BROW3.reg" HKEY_LOCAL_MACHINE\SOFTWARE\Clients\StartMenuInternet
	set "bits=32"
)

if "%bits%" equ "64" (
	for /f "tokens=6 delims=\" %%B in ('type "%Temp%\BROW3.reg" ^| findstr /E "DefaultIcon]"') do (
		echo %%B
	)
)

if "%bits%" equ "32" (
	for /f "tokens=5 delims=\" %%B in ('type "%Temp%\BROW3.reg" ^| findstr /E "DefaultIcon]"') do (
		echo %%B
	)
)

del /Q /F "%Temp%\BROW3.reg"
exit /b 0