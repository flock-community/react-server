# This file reads the Makefile and tries to convert it to sensible Windows commands

param (
    [parameter(Position=0)]
    [ValidateSet('stack-build', 'graal-build', 'be-build', 'fe-build', 'stack-run', 'graal-run', 'be-run', 'fe-run', 'help')]
    [String]$argument=$(throw "Argument needed, for example: .\make.ps1 stack-build")
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

function transformDirFromUnixToWin ([string]$command) {
    return $command.replace($unixCurrentDir, '' + $winCurrentDir + '').replace($unixFeDir, $winFeDir).replace($unixMavenDir, $winMavenDir).Trim()
}

function makeForWindows([string]$command) {
    return transformDirFromUnixToWin($makeFile | Where-Object { $_ -match $command })
}

function invoke([string]$command) {
    $make = makeForWindows($command)
    Write-Output $make
    Invoke-Expression $make
}

switch ($argument) {
    'stack-build' { invoke('build.*?-stack') }
    'graal-build' { invoke('build.*?-graal') }
    'be-build'    { invoke('build.*?-be')    }
    'fe-build'    { invoke('build.*?-fe')    }
    'stack-run'   { invoke('run.*?-stack')   }
    'graal-run'   { invoke('run.*?-graal')   }
    'be-run'      { invoke('run.*?-be')      }
    'fe-run'      { invoke('run.*?-fe')      }
    default       {
        Write-Output "Use one of these commands:

        stack-build
        graal-build
        be-build
        fe-build
        stack-run
        graal-run
        be-run
        fe-run
        "
    }
}
