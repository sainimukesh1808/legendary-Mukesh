@if not DEFINED _SIMQATOOLS_DEBUG_ @echo off

set SIMQATOOLS_LOCALDIR=%LOCALAPPDATA%\simqatools
set MACHINE=%COMPUTERNAME%

setlocal ENABLEEXTENSIONS
setlocal ENABLEDELAYEDEXPANSION

set SITE=johnston
set LOCATION=%LOGONSERVER:~5,3%
IF %LOCATION%==PUN SET SITE=pune

echo --
echo %MACHINE% is from %SITE%.

set SETUP_SRC=\\filer2sim\qa\qa\svc_simindus\do_not_delete\simqatools
if %SITE%==pune (
    set SETUP_SRC=\\Punpmwinsim02\d\do_not_delete\SIMQATOOLS
)

echo Updating %MACHINE% with the latest qa tools.
ROBOCOPY %SETUP_SRC% %SIMQATOOLS_LOCALDIR% /MIR >nul

endlocal
endlocal

set SIMQATOOLS_EXE=%SIMQATOOLS_LOCALDIR%\bin
set SIMQATOOLS_BATCH=%SIMQATOOLS_LOCALDIR%\install_batch
set PATH=%PATH%;%SIMQATOOLS_LOCALDIR%;%SIMQATOOLS_BATCH%;%SIMQATOOLS_EXE%
GOTO END

:END
title simqatools
echo Successfully completed.
echo --
exit /B 0