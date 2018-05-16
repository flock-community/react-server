# This file reads the Makefile and tries to convert it to sensible Windows commands

param (
    [parameter(Position=0)]
    [ValidateSet('fe-build', 'be-build', 'stack-build', 'fe-run', 'be-run', 'stack-run', 'help')]
    [String]$argument=$(throw "Full stack (stack), Backend (be), or Frontend (fe)? Build (-build) or Run (-run)? Example: .\make.ps1 stack-build")
)

$unixFeDir = '/src/main/frontend'
$unixMavenDir = '~/.m2'
$unixCurrentDir = '$(shell pwd)'

function slash([string]$path) {
    return $path.replace('/', '\')
}

$winFeDir = slash($unixFeDir)
$winMavenDir = slash($unixMavenDir)
$winCurrentDir = Split-Path $MyInvocation.MyCommand.Path

$makeFile = Get-Content -Path ./Makefile | Where-Object { $_ -match 'docker' }

function transformUnixToWin ([string]$command) {
    return $command.replace($unixCurrentDir, '' + $winCurrentDir + '').replace($unixFeDir, $winFeDir).replace($unixMavenDir, $winMavenDir).Trim()
}

$buildStack    = transformUnixToWin($makeFile | Where-Object { $_ -match 'build.*?-stack' })
$buildFrontend = transformUnixToWin($makeFile | Where-Object { $_ -match 'build.*?-fe' })
$buildBackend  = transformUnixToWin($makeFile | Where-Object { $_ -match 'build.*?-be' })
$runStack      = transformUnixToWin($makeFile | Where-Object { $_ -match 'run.*?-stack' })
$runFrontend   = transformUnixToWin($makeFile | Where-Object { $_ -match 'run.*?-fe' })
$runBackend    = transformUnixToWin($makeFile | Where-Object { $_ -match 'run.*?-be' })

if ($argument -eq 'stack-build') {
    Write-Output $buildStack
    Invoke-Expression $buildStack
} elseif ($argument -eq 'fe-build') {
    Write-Output $buildFrontend
    Invoke-Expression $buildFrontend
} elseif ($argument -eq 'be-build') {
    Write-Output $buildBackend
    Invoke-Expression $buildBackend
} elseif ($argument -eq 'stack-run') {
    Write-Output $runStack
    Invoke-Expression $runStack
} elseif ($argument -eq 'fe-run') {
    Write-Output $runFrontend
    Invoke-Expression $runFrontend
} elseif ($argument -eq 'be-run') {
    Write-Output $runBackend
    Invoke-Expression $runBackend
} else {
    Write-Output $buildStack
    Write-Output $buildFrontend
    Write-Output $buildBackend
    Write-Output $runStack
    Write-Output $runFrontend
    Write-Output $runBackend
}
